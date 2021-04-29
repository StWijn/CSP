package com.stwijn.CustomerRecord;

public class Record {

	private int transactionRef;
	private String accNumber;
	private double startBal;
	private String mutation;
	private String description;
	private double endBal;
	
	public Record(int transactionRef, String accNumber, double startBal, String mutation, String description,
			double endBal) {
		super();
		this.transactionRef = transactionRef;
		this.accNumber = accNumber;
		this.startBal = startBal;
		this.mutation = mutation;
		this.description = description;
		this.endBal = endBal;
	}
	
	@Override
	public String toString() {
		return "{ \n\t \"reference\": " + transactionRef + ", \n\t \"accountnumber\": " + accNumber + "\n\t }";
	}

	public int getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(int transactionRef) {
		this.transactionRef = transactionRef;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public double getStartBal() {
		return startBal;
	}
	public void setStartBal(double startBal) {
		this.startBal = startBal;
	}
	public String getMutation() {
		return mutation;
	}
	public void setMutation(String mutation) {
		this.mutation = mutation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getEndBal() {
		return endBal;
	}
	public void setEndBal(double endBal) {
		this.endBal = endBal;
	}

}