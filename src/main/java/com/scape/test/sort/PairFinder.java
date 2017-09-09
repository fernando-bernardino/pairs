package com.scape.test.sort;

import static java.util.Arrays.stream;
import java.util.stream.IntStream;

public class PairFinder {
	
	/*
	 * Algorithm: Divide numbers bigger than half from number smaller than half. 
	 * 
	 * Short circuiting decision:
	 * 
	 * If total of divided elements < total numbers - 1 means there was at least 2 numbers equal
	 * to half of the sum
	 * 
	 * 
	 * Time complexity: O(n log n)
	 * 
	 * Space complexity: n
	 * 
	 */
	public boolean find(int sum, int[] values) {

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
	
	
	public boolean findV1SubExponential(int sum, int[] values) {

		/*
		 * Plain algorithm: creates all possible combinations between two number
		 *
		 * Time complexity:
		 * 
		 * worst case scenario (pair not found) -> o(n^2) -> exponential
		 *
		 * average scenario (pair found half way) -> O(n^2) -> exponential
		 * 
		 * Space complexity: 1
		 * 
		 */

		return IntStream.range(0, values.length)
				.anyMatch(
						(i) -> IntStream.range(i + 1, values.length)
										.anyMatch(
												(j) -> values[i] + values[j] == sum));
	}

	/*
	 * Algorithm: cut condition introduced - iteration stops when a repetition
	 * is found
	 * 
	 * Performance strongly depends on probability of element repetition
	 * Time complexity:
	 * 
	 * For no repetitions behaves same as plain algorithm -> O(n^2) exponential
	 * 
	 * Space complexity: 1
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
	
	/*
	 * Algorithm: Divide numbers bigger than half from number smaller than half.
	 * 
	 * Time complexity: O(n log n)
	 * 
	 * Space complexity: n
	 * 
	 */	
	public boolean findUltraOptimizedUnreadableSolution(int sum, int[] values) {
		
		double half = sum / 2.0;
		int length = values.length;
		int [] divided = new int [length];
		int upperEnd = 0;
		int lowerEnd = length;
		int numbersEqualHalf = 0;
		
		for(int i = 0; i < length; i ++){
			int value = values[i];
			
			if (value == half) {
				numbersEqualHalf ++;
			} else if (value < half) {
				divided[--lowerEnd] = value;
			} else if (value < sum) {
				divided[upperEnd ++] = value;
			}
		}
		
		return numbersEqualHalf > 1 ?
			true :
			match(sum, length, divided, upperEnd, lowerEnd);
	}

	private boolean match(int sum, int length, int[] divided, int upperEnd, int lowerEnd) {
		
		return IntStream.range(0, upperEnd)
						.map(
								(i) -> sum - divided[i])
						.anyMatch(
								(remaining) -> IntStream.range(lowerEnd, length)
														.anyMatch(
																(j) -> divided[j] == remaining));
	}

}
