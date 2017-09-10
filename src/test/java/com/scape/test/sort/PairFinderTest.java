package com.scape.test.sort;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class PairFinderTest {
	
	int sum = 10;
	int [][] trueTestValues = {
			{9, 7, 4, 2, 1},
			{9, 7, 9, 4, 2, 1},
			{3, 5, 3, 3, 5, 8},
			{5, 3, 7, 1, 8},
			{5, 3, 7, 1, 6}
	};
	int [][] falseTestValues = {
			{9, 7, 4, 2, 11},
			{9, 7, 4, 9, 2, 11},
			{5, 4, 7, 1, 8}
	};

	PairFinder pairFinder = new PairFinder();
	
	@Test
	public void executeUnorderedTestBatch() {
		
		long failingTest = Arrays.stream(trueTestValues)
				.filter(
						(values) -> !pairFinder.findUnordered(sum, values))
				.peek(
						(values) -> System.out.println("Expected " + arrayToString(values) + " to have pair present"))
				.count();
		
		failingTest += Arrays.stream(falseTestValues)
				.filter(
						(values) -> pairFinder.findUnordered(sum, values))
				.peek(
						(values) -> System.out.println("Expected " + arrayToString(values) + " to NOT have pair present"))
				.count();

		assertTrue(failingTest + " tests failed", failingTest == 0);
	}
	
	@Test
	public void executeOrderedTestBatch() {
		
		long failingTest = Arrays.stream(trueTestValues)
				.peek(
						(values) -> Arrays.sort(values))
				.filter(
						(values) -> !pairFinder.findOrdered(sum, values))
				.peek(
						(values) -> System.out.println("Expected " + arrayToString(values) + " to have pair present"))
				.count();
		
		failingTest += Arrays.stream(falseTestValues)
				.peek(
						(values) -> Arrays.sort(values))
				.filter(
						(values) -> pairFinder.findOrdered(sum, values))
				.peek(
						(values) -> System.out.println("Expected " + arrayToString(values) + " to NOT have pair present"))
				.count();

		assertTrue(failingTest + " tests failed", failingTest == 0);
	}
	
	private String arrayToString(int [] values) {
		
		return Arrays.stream(values)
					.mapToObj(
							value -> String.valueOf(value))
					.reduce(
							"[", (acc, value) -> acc + value + ", ")
			+ "]";
	}

	
	@Test
	public void findUnordered_sumOdd_present(){
		int [] values = {5, 3, 7, 1, 6};
		
		boolean found = pairFinder.findUnordered(11, values);
		
		assertTrue(found);
	}
}
