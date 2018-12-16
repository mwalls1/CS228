package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Mason Walls
 *
 */
public class SelectionSort extends SorterWithStatistics {

	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// TODO: implement SelectionSort
		int n = words.length;

		for (int i = 0; i < n - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (comp.compare(words[j], words[min]) < 0)
					min = j;
			String temp = words[min];
			words[min] = words[i];
			words[i] = temp;
		}
	}
}
