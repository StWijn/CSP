package com.stwijn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stwijn.CustomerRecord.Record;
import com.stwijn.logic.Analyzer;

@RestController
@RequestMapping("/api")
public class RestAPI {

	private Analyzer recordAnalyzer;

	@Autowired
	public RestAPI(Analyzer recordAnalyzer) {
		this.recordAnalyzer = recordAnalyzer;
	}

	@PostMapping(path = "/in", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addRecord(@RequestBody Record newRecord) {

		// checkt eerst op correct gebruik van + en - mbt mutaties, evenals dat een
		// (internationale) IBAN tussen de 15 en 34 karakters ligt
		if (recordAnalyzer.checkMutationPrefix(newRecord) == false || recordAnalyzer.checkIBAN(newRecord) == false) {
			return ResponseHelper.create_RESPONSE_emptyRecord("BAD_REQUEST", HttpStatus.BAD_REQUEST);
		}

		// check unieke transactie reference
		boolean checkUniqueReference = recordAnalyzer.checkUniqueReference(newRecord);
		// check correcte eindbalans
		boolean validateEndBalance = recordAnalyzer.validateEndBalance(newRecord);

		if (checkUniqueReference && validateEndBalance) {
			recordAnalyzer.addAccount(newRecord);
			return ResponseHelper.create_RESPONSE_emptyRecord("SUCCESSFUL", HttpStatus.OK);

		} else if (checkUniqueReference == false && validateEndBalance == true) {
			return ResponseHelper.create_RESPONSE("DUPLICATE_REFERENCE", newRecord, HttpStatus.OK);

		} else if (checkUniqueReference == true && validateEndBalance == false) {
			return ResponseHelper.create_RESPONSE("INCORRECT_END_BALANCE", newRecord, HttpStatus.OK);

		} else if (checkUniqueReference == false && validateEndBalance == false) {
			return ResponseHelper.create_RESPONSE_twiceRecord("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE", newRecord,
					HttpStatus.OK);

		} else {
			return ResponseHelper.create_RESPONSE_emptyRecord("INTERNAL_SERVER_ERROR", HttpStatus.OK);
		}

	}

	// update balance of existing record in db
	@PutMapping("/in/{transactionRef}/{mutation}")
	public Record updateBalance(@PathVariable int transactionRef, @PathVariable double mutation) {
		Record theRecord = recordAnalyzer.findByTransactionRef(transactionRef);

		if (theRecord == null) {
			throw new RuntimeException("No record found for transaction reference: " + transactionRef);
		}

		theRecord.setEndBal(theRecord.getEndBal() + mutation);
		recordAnalyzer.addAccount(theRecord);
		return theRecord;
	}

	// delete record from db by transactionRef
	@DeleteMapping("/in/{transactionRef}")
	public String deleteRecord(@PathVariable int transactionRef) {
		Record theRecord = recordAnalyzer.findByTransactionRef(transactionRef);

		if (theRecord == null) {
			throw new RuntimeException("No record found for transaction reference: " + transactionRef);
		}

		recordAnalyzer.deleteByTransactionRef(transactionRef);
		return "Successfully deleted Record with transaction reference: " + transactionRef;
	}

	@GetMapping("/get")
	public List<Record> getListSuccessfulEntries() {
		return recordAnalyzer.getList();
	}

	@GetMapping("/get/{transactionRef}")
	public Record getRecordByTransactionRef(@PathVariable int transactionRef) {
		Record theRecord = recordAnalyzer.findByTransactionRef(transactionRef);

		if (theRecord == null) {
			throw new RuntimeException("No record found for transaction reference: " + transactionRef);
		}
		return theRecord;
	}

}
