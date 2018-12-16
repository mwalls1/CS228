package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * @author Mason Walls
 */
public class QuickSort extends SorterWithStatistics {

	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// TODO: implement QuickSort;

		if (words == null || words.length == 0) {
			return;
		}
		int length = words.length;
		quickSort(0, length - 1, words, comp);

	}
	/**
	 * Sorts the array with more paramaters
	 * @param low the lower index
	 * @param high the higher index
	 * @param words the array to be sorted
	 * @param comp the compare tool
	 */
	private void quickSort(int low, int high, String[] words, Comparator<String> comp) {

		int i = low;
		int j = high;
		String pivot = words[low + (high - low) / 2];
		while (i <= j) {

			while (comp.compare(words[i], pivot) < 0) {
				i++;
			}
			while (comp.compare(words[j], pivot) > 0) {
				j--;
			}
			if (i <= j) {
				changeNumbers(i, j, words);
				i++;
				j--;
			}
		}
		if (low < j)
			quickSort(low, j, words, comp);
		if (i < high)
			quickSort(i, high, words, comp);
	}
	/**
	 * Swaps numbers in an array
	 * @param i index one
	 * @param j index two
	 * @param words array to be sorted
	 */
	private void changeNumbers(int i, int j, String[] words) {
		String temp = words[i];
		words[i] = words[j];
		words[j] = temp;
	}
}
