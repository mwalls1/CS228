package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author
 * 
 * 
 * 		An application class that uses EntryTree class to process a file of
 *         commands (one per line). Each command line consists of the name of a
 *         public method of the EntryTree class followed by its arguments in
 *         string form if the method has arguments. The name of the file is
 *         available to the main method from its String[] parameter at index 0.
 *         You can assume that the command file is always in correct format. The
 *         main method creates an object of the EntryTree class with K being
 *         Character and V being String, reads each line from the command file,
 *         decodes the line by splitting into String parts, forms the
 *         corresponding arguments, and calls the public method from the
 *         EntryTree object with the arguments, and prints out the result on the
 *         console. Note that the name of a public method in the EntryTree class
 *         on each command line specifies that the public method should be
 *         called from the EntryTree object. A sample input file of commands and
 *         a sample output file are provided.
 * 
 *         The sample output file was produced by redirecting the console output
 *         to a file, i.e.,
 * 
 *         java Dictionary infile.txt > outfile.txt
 * 
 * 
 *         NOTE that all methods of EntryTree class can be present as commands
 *         except for getAll method inside the input file.
 * 
 * 
 */
public class Dictionary {
	public static void main(String[] args) {
		// TODO
		EntryTree<String,String> myTree = new EntryTree<String,String>();
		File file = new File(args[0]);
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				Scanner scanLine = new Scanner(line);
				String next = scanLine.next();
				if(next.equals("showTree"))
				{
					System.out.println("Command: "+line+"\n\nResult from a showTree:");
					myTree.showTree();
					System.out.println();
				}
				else if(next.equals("add"))
				{
					String key = scanLine.next();
					String value = scanLine.next();
					String[] arrK = new String[key.length()];
					for(int i = 0; i < key.length(); i ++)
					{
						arrK[i] = ""+key.charAt(i);
					}
					System.out.println("Command: "+line);
					System.out.println("Result from an add: "+myTree.add(arrK, value)+"\n");
				}
				else if(next.equals("search"))
				{
					String key = scanLine.next();
					String[] arrK = new String[key.length()];
					for(int i = 0; i < key.length(); i ++)
					{
						arrK[i] = ""+key.charAt(i);
					}
					System.out.println("Command: "+line);
					System.out.println("Result from a search: "+myTree.search(arrK));
					System.out.println();
				}
				else if(next.equals("prefix"))
				{
					String key = scanLine.next();
					String[] arrK = new String[key.length()];
					for(int i = 0; i < key.length(); i ++)
					{
						arrK[i] = ""+key.charAt(i);
					}
					String values = "";
					Object[] temp = myTree.prefix(arrK);
					for(int j = 0; j < temp.length; j ++)
					{
						values+=""+temp[j];
					}
					System.out.println("Command: "+line);
					System.out.println("Result from a prefix: "+values);
					System.out.println();
				}
				else if(next.equals("remove"))
				{
					String key = scanLine.next();
					String[] arrK = new String[key.length()];
					for(int i = 0; i < key.length(); i ++)
					{
						arrK[i] = ""+key.charAt(i);
					}
					System.out.println("Command: "+line);
					System.out.println("Result from a remove: "+myTree.remove(arrK));
					System.out.println();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
