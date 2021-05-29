package com.stwijn.logic;

import com.stwijn.CustomerRecord.Record;
import com.stwijn.logic.Analyzer;
import com.stwijn.rest.RestAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;


public class AnalyzerTest {

	Analyzer recordAnalyzer;
	
	@BeforeEach
	void init() {
	recordAnalyzer = new Analyzer(new ArrayList<Record>(), null);

	} 
	
	@Test
	void test_MutationPrefix() {
		Record r1 = new Record(333, "NLABNA873928394039", 100, "+30", "", 130);
		Record r2 = new Record(333, "NLABNA873928394039", 100, "-30", "", 130);
		Record r3 = new Record(333, "NLABNA873928394039", 100, "30", "", 130);
		assertTrue(recordAnalyzer.checkMutationPrefix(r1));
		assertTrue(recordAnalyzer.checkMutationPrefix(r2));
		assertFalse(recordAnalyzer.checkMutationPrefix(r3), "should be prefixed with + or -");
	}
	
	@Test
	void test_validateEndBalance() {
		Record r1 = new Record(888, "NLABNA873928394039", 100, "+30", "", 130);
		Record r2 = new Record(111, "NLABNA873928394039", 100, "-30", "", 70);
		Record r3 = new Record(111, "NLABNA873928394039", 100, "-30", "", 10);
		Record r4 = new Record(111, "NLABNA873928394039", 100, "50", "", 150);
		
		assertTrue(recordAnalyzer.validateEndBalance(r1));
		assertTrue(recordAnalyzer.validateEndBalance(r2));
		assertFalse(recordAnalyzer.validateEndBalance(r3));
		
		assertThrows(IllegalArgumentException.class, 
				() -> recordAnalyzer.validateEndBalance(r4), "een mutatie zonder + of - hoort niet doorgekomen te mogen zijn");
	}
	
	@Test
	void test_checkIBANLength() {
		Record r1 = new Record(888, "NLABNA873928394039", 100, "+30", "", 130);
		Record r2 = new Record(111, "NLABNA87392839403900000000000000000", 100, "+30", "too long", 130);
		Record r3 = new Record(111, "NLABNA8739", 100, "+30", "too short", 130);
		
		assertTrue(recordAnalyzer.checkIBAN(r1));
		assertFalse(recordAnalyzer.checkIBAN(r2));
		assertFalse(recordAnalyzer.checkIBAN(r3));
	}
	
	/* EntityManager bean creation complications, needs fixing
	 * @Test
	void test_checkUniqueReference() {
		Record r1 = new Record(333, "NLABNA873928394039", 100, "+30", "description", 130);
		Record r2 = new Record(666, "NLABN234920101922222438", 100, "50", "....", 130);
		
		recordAnalyzer.addAccount(r1);
		
		assertTrue(recordAnalyzer.checkUniqueReference(r2));
		assertFalse(recordAnalyzer.checkUniqueReference(r1), "r1 has already been added to internal List, should return false for uniqueness of transaction reference");
	}*/
	
	//@PersistenceContext
	//EntityManager entityManager;
	
}
