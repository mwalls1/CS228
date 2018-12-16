package edu.iastate.cs228.hw06;

import java.util.Comparator;

/**
 * 
 * @author Mason Walls
 * 
 *         NOTE:
 * 
 *         0. Put your Firstname and Lastname after above author tag. Make sure
 *         that in both cases the first letter is uppercase and all others are
 *         lowercase. 1. You are allowed to create and use your own private
 *         helper methods. 2. No data fields can be introduced. 3. No custom
 *         classes of your own can be introduced or used. 4. Import statements
 *         are not allowed. 5. Fully qualified class names usage is not allowed.
 *         6. You are allowed to reuse any part of the source codes provided or
 *         shown under lecture notes, which do not violate any of above.
 * 
 * 
 * 
 */

public class SortingExercise {
	/**
	 * Modified implementation of in class provided quick sort code.
	 * 
	 * 
	 * The implementation of our original quick sort needs to be revised as follows
	 * in this implementation. If the array has 23 entries, choose the middle entry
	 * as the pivot. For arrays between 24 - 50 use the last element as the pivot
	 * value. For arrays larger than 50 entries, use the median-of-three
	 * pivot-selection scheme described below. For arrays fewer than 23 entries, use
	 * insertion sort instead of quick sort.
	 * 
	 * Median-of-three pivot selection chooses as pivot the median of three entries
	 * in the array, i.e., the first entry, the middle entry, and the last entry. We
	 * will use specific version of it as follows.
	 * 
	 * For example, let's say original array is as follows
	 * 
	 * 5, 8, 6, 4, 9, 3, 7, 1, 2
	 * 
	 * first entry = 5 middle entry = 9 // index is (0+8)/2=4 last entry = 2
	 * 
	 * Median of 5, 9, 2, would be 5. Check: https://en.wikipedia.org/wiki/Median
	 * 
	 * Now our array would look as follows after positioning the pivot:
	 * 
	 * 2, 8, 6, 4, 5, 3, 7, 1, 9
	 * 
	 * Now our array would look as follows just before partitioning:
	 * 
	 * 2, 5, 6, 4, 8, 3, 7, 1, 9
	 * 
	 * Our pivot is at position 1 of array, i.e., value 5. Both low and high start
	 * as shown in source code of quick sort under lecture notes, i.e.,
	 * 
	 * int low = first + 1; int high = last;
	 * 
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void modifiedQuickSort(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		if (arr.length < 23)
			selectionSort(arr);
		else
			quickSort(0, arr.length - 1, arr);

		// TODO
	}
	/**
	 * Uses selection sort method to sort the array (only arrays with less than 23 entries will be sorted with this method)
	 * @param arr the array to be sorted
	 */
	private static void selectionSort(int[] arr) {
		// TODO: implement SelectionSort
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[min])
					min = j;
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}

	/**
	 * Sorts the array with more paramaters (only arrays with 24 or more elements will be sorted with this method)
	 * 
	 * @param low
	 *            the lower index
	 * @param high
	 *            the higher index
	 * @param words
	 *            the array to be sorted
	 * @param comp
	 *            the compare tool
	 */
	private static void quickSort(int low, int high, int[] arr) {

		int i = low;
		int j = high;
		int pivot;
		if (high > 50)
			pivot = arr[low + high / 2];
		else
			pivot = arr[high];

		while (i <= j) {

			while (arr[i] < pivot) {
				i++;
			}
			while (arr[j] > pivot) {
				j--;
			}
			if (i <= j) {
				changeNumbers(i, j, arr);
				i++;
				j--;
			}
		}
		if (low < j)
			quickSort(low, j, arr);
		if (i < high)
			quickSort(i, high, arr);
	}

	/**
	 * Swaps numbers in an array
	 * 
	 * @param i
	 *            index one
	 * @param j
	 *            index two
	 * @param words
	 *            array to be sorted
	 */
	private static void changeNumbers(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
