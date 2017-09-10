package com.scape.test.sort;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PairFinder {
	
	/**
	 * Sort the elements prior to finding the pair, so the ordered algorithm for
	 * finding pairs can be used.
	 * 
	 * Sorting algorithm used - quick sort, time complexity O(n log n)
	 * 
	 * Time complexity: sort + find = O (n log n) + O (n) = O(n log n)
	 * 
	 */
	public boolean findUnordered(int sum, int[] values){
		
		Arrays.sort(values);
		
		return findOrdered(sum, values);
	}
	
	/**
	 * Find pair in a sorted array. Pick the lowest element and the highest and applies:
	 * 
	 * IF lower + highest > sum THEN pick the second highest and then repeat
	 * 
	 * IF lower + highest < sum THEN pick the second lowest and then repeat
	 * 
	 * Time complexity: O (n)
	 */
	public boolean findOrdered(int sum, int[] values) {
		int lowerEnd = 0;
		int upperEnd = values.length -1;
		
		while (lowerEnd < upperEnd) {
			int sumRemaining = sum - values[lowerEnd] - values[upperEnd];
			
			if (sumRemaining == 0) {
				return true;
			} else if (sumRemaining > 0){
				lowerEnd ++;
			} else {
				upperEnd --;
			}
		}
		
		return false;
	}

	/**
	 * 
	 * Algorithm: Divide numbers bigger than half from number smaller than half. 
	 * 
	 * Short circuiting decision:
	 * If total of divided elements < total numbers - 1 means there was at least 2 numbers equal
	 * to half of the sum
	 * 
	 * 
	 * Time complexity: n^2 / 4 -> O(n^2), worst than applying Quick Sort and then apply sorted find
	 * 
	 * Space complexity: n
	 * 
	 */
	public boolean findDivideBiggerFromSmallerThanHalf(int sum, int[] values) {

		double half = sum / 2.0;

		int[] aboveHalf = stream(values).filter(
												(value) -> value > half)
										.toArray();
		
		int[] lowerHalf = stream(values).filter(
												(value) -> value < half)
										.toArray();

		if (aboveHalf.length + lowerHalf.length  + 1 < values.length) {
			//	if more than 1 element was half of sum, a pair is present
			return true;
		}

		return stream(aboveHalf)
				.map(
						(value) -> sum - value)
				.anyMatch(
						(remaining) -> stream(lowerHalf)
										.anyMatch(
												(lowerValue) -> lowerValue == remaining));
	}
	
	

	/**
	 * 
	 * Plain algorithm: creates all possible combinations between two number
	 *
	 * Time complexity:
	 * 
	 * worst case scenario (pair not found) -> o(n^2) -> exponential
	 *
	 * average scenario (pair found half way) -> O(n^2) -> exponential
	 * 
	 */
	public boolean findV1SubExponential(int sum, int[] values) {

		return IntStream.range(0, values.length)
				.anyMatch(
						(i) -> IntStream.range(i + 1, values.length)
										.anyMatch(
												(j) -> values[i] + values[j] == sum));
	}

	/**
	 * Algorithm: cut condition introduced in plain algorithm - iteration for current
	 * element stops when a repetition is found
	 * 
	 * Complexity strongly depends on probability of element repetition
	 * 
	 * For no repetitions behaves same as plain algorithm
	 * 
	 * Time complexity: O(n^2) exponential
	 * 
	 */
	public boolean findV2SubExponential(int sum, int[] values) {

		return IntStream.range(0, values.length)
						.anyMatch(
								(i) -> matchDecreasing(i, values, sum));
	}

	private boolean matchDecreasing(int i, int[] values, int sum) {
		int current = values[i];
		int toFind = sum - current;

		return IntStream.range(i + 1, values.length)
				.map(
						(j) -> values[j])
				.filter(
						(value) -> value == toFind || value == current)
				.anyMatch(
						(value) -> value == toFind);
	}
}
