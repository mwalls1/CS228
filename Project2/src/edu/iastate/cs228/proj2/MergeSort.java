package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * @author walls
 *
 */
public class MergeSort extends SorterWithStatistics {
	// This method will be called by the base class sort() method to
	// actually perform the sort.
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		// TODO: implement mergeSort.
		sortHelp(words,0,words.length-1, comp);
	}
	/**
	 * Uses recursion to split the array in halves
	 * @param arr the array to be sorted
	 * @param ind
	 * @param len
	 * @param comp
	 */
	public void sortHelp(String arr[], int ind, int len, Comparator<String> comp) 
    { 
        if (ind < len) 
        { 
            int m = (ind+len)/2; 
            sortHelp(arr, ind, m, comp); 
            sortHelp(arr , m+1, len, comp); 
            mergeHelp(arr, ind, m, len, comp); 
        } 
    } 
	/**
	 * Compares the pieces of the array and sorts them accordingly
	 * @param arr array to be sorted
	 * @param ind start index
	 * @param m midpoint of the array
	 * @param len length of the array
	 */
	public void mergeHelp(String arr[], int ind, int m, int len, Comparator<String> comp) {
		int n1 = m - ind + 1;
		int n2 = len - m;
		String L[] = new String[n1];
		String R[] = new String[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[ind + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		int i = 0, j = 0;

		int k = ind;
		while (i < n1 && j < n2) {
			if (comp.compare(L[i], R[j])<=0) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

}
