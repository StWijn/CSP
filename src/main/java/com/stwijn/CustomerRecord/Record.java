package com.stwijn.CustomerRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="record")
public class Record {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	//@Column(name="transactionRef")
	@Column(name="transaction_ref")
	private int transactionRef;
	
	//@Column(name="accNumber")
	@Column(name="acc_number")
	private String accNumber;
	
	@Transient
	private double startBal;
	
	@Transient
	private String mutation;
	
	@Transient
	private String description;
	
	//@Column(name="endBal")
	@Column(name="end_bal")
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
	
	public Record() {}
	
	
	public Record(int transactionRef, String accNumber, double endBal) {
		this.transactionRef = transactionRef;
		this.accNumber = accNumber;
		this.endBal = endBal;
	}

	@Override
	public String toString() {
		return "{ \n\t \"reference\": " + transactionRef + ", \n\t \"accountnumber\": " + accNumber + "\n\t }";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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