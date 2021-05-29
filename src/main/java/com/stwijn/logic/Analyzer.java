package com.stwijn.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stwijn.CustomerRecord.Record;

@Repository
public class Analyzer {

	private List<Record> listOfAccounts;
	private EntityManager entityManager;

	@Autowired
	public Analyzer(List<Record> listOfAccounts, EntityManager entityManager) {
		this.listOfAccounts = listOfAccounts;
		this.entityManager = entityManager;
	}

	@Transactional
	public void addAccount(Record newRecord) {

		Record record = entityManager.merge(newRecord);

		// force correctly setting id, in case it is explicitly set by user
		record.setId(record.getId());
	}

	@Transactional
	public List<Record> DBreturnList() {
		Query query = entityManager.createQuery("from Record");
		List<Record> records = query.getResultList();

		return records;
	}

	@Transactional
	public Record findByTransactionRef(int transactionRef) {
		for (Record record : DBreturnList()) {
			if (record.getTransactionRef() == transactionRef) {
				return record;
			}
		}
		
		return null;
	}


	public boolean checkUniqueReference(Record newRecord) {
		for (Record acc : DBreturnList()) {
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
			throw new IllegalArgumentException(
					"this should not be reached, method checkMutationPrefix() should prevent this");
		}
	}
}
