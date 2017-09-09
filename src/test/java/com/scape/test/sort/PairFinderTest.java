package com.scape.test.sort;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PairFinderTest {
	
	int sum = 10;

	PairFinder pairFinder = new PairFinder();

	@Test
	public void sort_pairPresentT1_present() {
		int [] values = {9, 7, 4, 2, 1};
		
		boolean found = pairFinder.find(sum, values);
		
		assertTrue(found);
	}

	@Test
	public void sort_pairNotPresentT1_notPresent() {
		int [] values = {9, 7, 4, 2, 11};
		
		boolean found = pairFinder.find(sum, values);
		
		assertFalse(found);
	}

	@Test
	public void sort_pairPresentT2_present() {
		int [] values = {9, 7, 9, 4, 2, 1};
		
		boolean found = pairFinder.find(sum, values);
		
		assertTrue(found);
	}

	@Test
	public void sort_pairNotPresentT2_notPresent() {
		int [] values = {9, 7, 4, 9, 2, 11};
		
		boolean found = pairFinder.find(sum, values);
		
		assertFalse(found);
	}
	
	@Test
	public void sort_hubbScenario_present(){
		int [] values = {3, 5, 3, 3, 5, 8};
		
		boolean found = pairFinder.find(sum, values);
		
		assertTrue(found);
	}
	
	@Test
	public void sort_lowerFirst_present(){
		int [] values = {5, 3, 7, 1, 8};
		
		boolean found = pairFinder.find(sum, values);
		
		assertTrue(found);
	}
	
	@Test
	public void sort_sumOdd_present(){
		int [] values = {5, 3, 7, 1, 6};
		
		boolean found = pairFinder.find(11, values);
		
		assertTrue(found);
	}
	
	@Test
	public void sort_only1EqualsToHalf_notPresent(){
		int [] values = {5, 4, 7, 1, 8};
		
		boolean found = pairFinder.find(sum, values);
		
		assertFalse(found);
	}
}
