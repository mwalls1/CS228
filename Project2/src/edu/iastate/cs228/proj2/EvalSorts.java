package edu.iastate.cs228.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EvalSorts {
	public static final int kNumberOfWordsToSort = 10000;
	/**
	 * @author Mason Walls
	 * The sorts used in this project have different degrees of complexity in terms of Big O.
	 * When using the 10 word list, all of the sorts have very similar speeds. This is the case because the small
	 * number of items to sort. As the word lists increase, the time of the sorts increases as well. Merge sort 
	 * and selection sort are very similar in terms of speed. Merge sort has a time complexity of O(nlogn) meaning
	 * that it is very fast even with larger data sets. Quick sort has a similar average speed of O(nlogn) and 
	 * rarely it has speed O(n^2). In these tests the quick sort is at its average time complexity. Selection sorts
	 * time complexity is O(n^2) meaning that as data sets get large, the time to sort increases at an increasing rate.
	 */
	/**
	 main is responsible only for extracting fileNames from args,
     reading the files, and constructing an instance of the this 
     class configured with the input data.
	 FileNotFoundException and FileConfigurationException exceptions 
	 should be handled in main, i.e., print out appropriate message
	 to the user.
	*/
	public static void main(String args[]) {
		try
		{
		char[] alphabet   = null;  //ref to the Lexicon it creates. 
		String[] wordList = null;  //ref to the list of words to be sorted. 
		EvalSorts theApp  = null;  //ref to the app. 
		LexiconImpl comp  = null;  //the concrete lexicon your app uses. 
		String[] sortedList = null;
		File file1 = new File(args[0]);
		File file2 = new File(args[1]);
		try {
		comp = new LexiconImpl(readCharacterOrdering(file1.getName()));
		wordList = readWordsFile(file2.getName(),comp);
		}
		catch(FileConfigurationException t)
		{
			System.err.println(t);
			return;
		}
		//TODO
		/*
		 * 
		 *      Here you should add code that extracts the file names from the args array,
		 *      opens and reads the data from the files,constructs an instance of Lexicon from the character order file, 
		 *      and then create an instance of this class (EvalSorts) to act as a configured
		 *      instance of the application. After you have constructed the configured
		 *      instance, you should start it running (see below). 
		 *      
		 *      
		 *   
		 *  
		*/		
		

		//configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		//now execute that instance
		theApp.runSorts();
		}
		catch(FileNotFoundException t){

            System.err.print(t);
            return;

        }
	}
	private String[] words; // ref to the word lit
	private Lexicon lex; // ref to the relevant lexicon
	private int numWordsToSort = kNumberOfWordsToSort;

	/**
	 * This constructor configures an instance of EvalSorts to sort input read my
	 * main, using the character order read by main and now embedded in an instance
	 * of Lexicon
	 * 
	 * @param lex
	 *            the instance of lexicon to be used
	 * @param wordList
	 *            the wordlist (as array of string) to be sorted
	 * @param numWordsToSort
	 *            each sort will be repeated until it has sorted this many words.
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		// TODO
		this.lex = lex;
		words = wordList;
		this.numWordsToSort = numWordsToSort;
	}

	/**
	 * runSorts() performs the sort evaluation.
	 * 
	 * Note: The three sorters extend a common base so they share the same interface
	 * for starting the sort and collecting statistics. Thus, you should create
	 * instances of the sorter and save references to each in an array of base type.
	 * This allows you to use a simple loop to run all the reports and collect the
	 * statistics.
	 */
	public void runSorts() {

		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		// TODO
		sorters[0] = new MergeSort();
		sorters[1] = new QuickSort();
		sorters[2] = new SelectionSort();
		for (int i = 0; i < 3; i++) {
			sorters[i].sort(words, lex);
			System.out.print(sorters[i].getReport());
		}

	}

	/**
	 * Reads the characters contained in filename and returns them as a character
	 * array.
	 * 
	 * @param filename
	 *            the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used or
	 *          null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename)
			throws FileNotFoundException, FileConfigurationException {
		File f1 = new File(filename);
		Scanner scan = new Scanner(f1);
		ArrayList<Character> temp = new ArrayList<Character>();
		while (scan.hasNext()) {
			String line = scan.nextLine();
			char[] tempArr = line.toCharArray();
			for (int i = 0; i < tempArr.length; i++) {
				temp.add(new Character(tempArr[i]));
			}

		}
		return temp.toString().toCharArray();
	}

	/**
	 * Reads the words from the file and returns them as a String array.
	 * 
	 * @param filename
	 *            file containing words
	 * @return the words contained in the file or null if there was a
	 *         FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		// TODO
		File f1 = new File(filename);
		Scanner scan = new Scanner(f1);
		ArrayList<String> temp = new ArrayList<String>();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			temp.add(line);
		}
		return temp.toArray(new String[temp.size()]);
	}

}
