package edu.iastate.cs228.hw03;

/**
 * 
 * @author
 *
 */
public class HW03_Part2 {
	/*
	 * Answers to short questions:
	 * 
	 * 1. O(N)
	 * 
	 * 2. O(N)
	 * 
	 * 3. O(N^3)
	 * 
	 * 4.O(N^N)
	 * 
	 * 5.O(N^N)
	 * 
	 */

	/*
	 * In all of the following methods you can assume that array will always have
	 * elements (ints) in it. And will have proper integers as defined in the
	 * description of HW03, i.e., in first two it will be in the range, and in last
	 * two it will be composed of negative and positive values only.
	 */
	/**
	 * @author Mason Walls
	 * @param The
	 *            array to be searched
	 * @return The integer that is missing from the array
	 */
	public static int findMissingInt_a_On2(int[] array) {
		int mainValue = 0;
		int actualValue = 0;
		for (int i = 0; i < array.length; i++) {
			actualValue += array[i];
			mainValue = 0;
			for (int j = 1; j <= array.length + 1; j++) {
				mainValue += j;

			}
		}
		return mainValue - actualValue;
	}

	/**
	 * @author Mason Walls
	 * @param The
	 *            array to be searched
	 * @return The integer that is missing from the array
	 */
	public static int findMissingInt_b_On1(int[] array) {
		int num = 0;
		// TODO
		// Part (a) of subsection 2.2.1 of HW03.
		for (int i = 1; i <= array.length + 1; i++) {
			num += i;
		}
		for (int i = 0; i < array.length; i++) {
			num -= array[i];
		}
		return num;
	}

	/**
	 * @author Mason Walls
	 * @param array
	 *            Re-arranges the array from negative to positive (Not in numerical
	 *            order)
	 */
	public static void rearrange_a_On2(int[] array) {
		// TODO
		// Part (a) of subsection 2.2.2 of HW03.
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				for (int j = array.length - 1; j > i; j--) {
					if (array[j] < 0) {
						int temp = array[j];
						array[j] = array[i];
						array[i] = temp;
						break;
					}
				}
			}
		}
	}

	/**
	 * @author Mason Walls
	 * @param array
	 *            Re-arranges the array from negative to positive (Not in numerical
	 *            order)
	 */
	public static void rearrange_b_On1(int[] array) {
		// TODO
		// Part (b) of subsection 2.2.2 of HW03.
		boolean switched = false;
		int len = array.length;
		for (int i = 1; i <= len; i++) {
			if (i == len) {
				if (!switched) {
					break;
				}
				switched = false;
				i = 1;
			}
			if (array[i - 1] > array[i]) {
				int temp = array[i - 1];
				array[i - 1] = array[i];
				array[i] = temp;
				switched = true;
			}
		}
	}

}
