package edu.iastate.cs228.hw05;

/**
 * 
 * @author Mason Walls
 * 
 *         NOTE: 0. Put your Firstname and Lastname after above author tag. Make
 *         sure that in both cases the first letter is uppercase and all others
 *         are lowercase. 1. In all of these methods implementations you are
 *         allowed to use the StringBuilder class. 2. You are allowed to create
 *         and use your own private helper methods. 3. No data fields can be
 *         introduced. 4. No custom classes of your own can be introduced or
 *         used. 5. Import statements are not allowed. 6. Fully qualified class
 *         names usage is not allowed. 7. You are allowed to reuse any part of
 *         the source codes provided or shown under lecture notes.
 * 
 */

public class SortingExercises {
	/**
	 * Recursive implementation of selection sort.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void selectionSort_Rec(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		selectionSortHelper(arr, arr.length, 0);
		// TODO
	}

	/**
	 * Helps the main method by giving a start index. Sorts the array using
	 * recursion
	 * 
	 * @param arr
	 *            The array to be sorted
	 * @param start
	 *            the index to start the sort
	 */
	public static void selectionSortHelper(int[] arr, int len, int index) {
		if (index == len)
			return;
		int k = minIndex(arr, index, len - 1);

		if (k != index) {
			// swap
			int temp = arr[k];
			arr[k] = arr[index];
			arr[index] = temp;
		}
		// Recursively calling selection sort function
		selectionSortHelper(arr, len, index + 1);
	}

	static int minIndex(int a[], int i, int j) {
		if (i == j)
			return i;

		int k = minIndex(a, i + 1, j);

		return (a[i] < a[k]) ? i : k;
	}

	/**
	 * Recursive implementation of insertion sort.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void insertionSort_Rec(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		insertionSortHelper(arr, arr.length);
		// TODO
	}

	/**
	 * The main method uses the helper method to sort by giving an extra parameter
	 * (end index)
	 * 
	 * @param arr
	 *            the array to be sorted
	 * @param end
	 *            the index to end the iteration
	 */
	public static void insertionSortHelper(int[] arr, int end) {
		// Base case
		if (end <= 1)
			return;

		// Sort first n-1 elements
		insertionSortHelper(arr, end - 1);

		// Insert last element at its correct position
		// in sorted array.
		int last = arr[end - 1];
		int j = end - 2;

		/*
		 * Move elements of arr[0..i-1], that are greater than key, to one position
		 * ahead of their current position
		 */
		while (j >= 0 && arr[j] > last) {
			arr[j + 1] = arr[j];
			j--;
		}
		arr[j + 1] = last;
	}

	/**
	 * Iterative implementation of selection sort with modifications as follows. On
	 * each pass in this case the method finds both the largest and smallest values
	 * in the unsorted portion of the array, and places them in the correct
	 * locations.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void selectionSort_Itr(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int minidex = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[minidex])
					minidex = j;

			// Swap the found minimum element with the first
			// element
			int temp = arr[minidex];
			arr[minidex] = arr[i];
			arr[i] = temp;
		}
	}

	/**
	 * A bubble sort can sort an array of n entries into ascending order by makeing
	 * n-1 passes through the array. On each pass, it compares adjacent entries and
	 * swaps them if they are out or order. For example, on the first pass, it
	 * compares the first and second entries, then the second and third entries, and
	 * so on. At the end of the first pass, the largest entry is in its proper
	 * position at the end of the array. We say that it has bubbled to its correct
	 * spot. Each subsequent pass ignores the entries at the end of the array, since
	 * they are sorted and are larger than any of the remaining entries. Thus, each
	 * pass makes one fewer comparison than the previous pass. Check the figure
	 * under HW05 assignment on Canvas.
	 * 
	 * This method implements bubble sort iteratively.
	 * 
	 * @param arr
	 *            Array of objects (with specific bounds) to be sorted in
	 *            nondecreasing order.
	 */
	public static <T extends Comparable<? super T>> void bubbleSort_Itr(T[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		int end = arr.length;
		for (int i = end - 1; i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					T temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		// TODO
	}

	/**
	 * A bubble sort can sort an array of n entries into ascending order by makeing
	 * n-1 passes through the array. On each pass, it compares adjacent entries and
	 * swaps them if they are out or order. For example, on the first pass, it
	 * compares the first and second entries, then the second and third entries, and
	 * so on. At the end of the first pass, the largest entry is in its proper
	 * position at the end of the array. We say that it has bubbled to its correct
	 * spot. Each subsequent pass ignores the entries at the end of the array, since
	 * they are sorted and are larger than any of the remaining entries. Thus, each
	 * pass makes one fewer comparison than the previous pass. Check the figure
	 * under HW05 assignment on Canvas.
	 * 
	 * This method implements bubble sort recursively.
	 * 
	 * @param arr
	 *            Array of ints to be sorted in nondecreasing order.
	 */
	public static void bubbleSort_Rec(int[] arr) {
		if (arr == null)
			throw new NullPointerException();
		if (arr.length == 0)
			throw new IllegalArgumentException();
		if (arr.length == 1)
			return;
		bubbleSortHelper(arr, arr.length);
	}

	/**
	 * The main method uses this method to sort the array with a given length
	 * 
	 * @param arr
	 *            the array to be sorted
	 * @param len
	 *            the length of the array
	 */
	public static void bubbleSortHelper(int[] arr, int len) {
		if (len == 1)
			return;
		for (int i = 0; i < len - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}
		bubbleSortHelper(arr, len - 1);
	}
}
