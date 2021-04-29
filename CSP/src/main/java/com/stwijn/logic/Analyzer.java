package com.stwijn.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stwijn.CustomerRecord.Record;

@Service
public class Analyzer {

	private List<Record> listOfAccounts;
	
	public Analyzer() {listOfAccounts = new ArrayList<>();}
	
	public Analyzer(List<Record> listOfAccounts) {this.listOfAccounts = listOfAccounts;}

	public void addAccount(Record newRecord) {listOfAccounts.add(newRecord);}

	public List<Record> returnList() {return listOfAccounts;}

	public boolean checkUniqueReference(Record newRecord) {
		for (Record acc : listOfAccounts) {
			if (acc.getTransactionRef() == newRecord.getTransactionRef()) {
				return false;
			}
		}
		return true;
	}

	public boolean validateEndBalance(Record newRecord) {
        double val = transformMutation(newRecord);

        if (newRecord.getStartBal() + val == newRecord.getEndBal()) {
            return true;
        }
        
        return false;
    }
	
	public boolean checkMutationPrefix(Record newRecord) {
		String mutation = newRecord.getMutation();
		
		if (mutation.startsWith("+") || mutation.startsWith("-")) {
			return true;
		}	
		return false;
	}
	
	public boolean checkIBAN(Record newRecord) {
		String IBAN = newRecord.getAccNumber();
		
		if (IBAN.length() >= 15 && IBAN.length() <= 34) {
			return true;
		}
		return false;	
	}
	
	
    private double transformMutation(Record newRecord) {
        String mutation = newRecord.getMutation();

        if (mutation.startsWith("+")) {
            Double valPos = Double.valueOf(mutation.substring(1, mutation.length()));
            return valPos;

        } else if (mutation.startsWith("-")) {
            Double valNeg = Double.valueOf(mutation.substring(1, mutation.length()));
            return (-valNeg);
            
        } else {
            throw new IllegalArgumentException("this should not be reached, method checkMutationPrefix() should prevent this");
        }
    }
}
