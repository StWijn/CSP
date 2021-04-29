package com.stwijn.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.stwijn.CustomerRecord.Record;
import com.stwijn.logic.Analyzer;


//helper class
public class ResponseHelper {
	
	public static ResponseEntity<String> create_RESPONSE(String result, Record record, HttpStatus statusCode) {
		String body = "{ \n\t \"result\": " + result + ", \n\t \"errorRecords\": [\n\t " + record + "\n\t] \n\t}";
		return new ResponseEntity<String>(body, statusCode);
	}
	
	public static ResponseEntity<String> create_RESPONSE_emptyRecord(String result, HttpStatus statusCode) {
		 String body = "{ \n\t \"result\": " + result + ", \n\t \"errorRecords\": [] \n}";
			return new ResponseEntity<String>(body, statusCode);
		}
	
	public static ResponseEntity<String> create_RESPONSE_twiceRecord(String result, Record record, HttpStatus statusCode) {		
		String body = "{ \n\t \"result\": " + result + ", \n\t \"errorRecords\": [\n\t " + record + ",\n" + record + "\n\t] \n\t}";
		return new ResponseEntity<String>(body, statusCode);
	}
		
}


