/**
 * Project 1 - CSC 201 Spring 2023
 *
 * Honor Pledge:
 * The code submitted for this project was developed by
 * YOUR NAME HERE without outside assistance or consultation
 * other than that allowed by the instructions for this project.
 *
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Collections;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    // Use a List to store and count shingles encountered in a text file

    // Students with last name starting with A-L use an ArrayList
    //private static ArrayList<Node<String>> shingles = new ArrayList<>();

    // Students with last name starting with M-Z use a LinkedList
    private static LinkedList<Node<String>> shingles = new LinkedList<>();

    // shingle length (in words)
    private final static int k = 3;

    public static void main(String[] args) {
        // Name of file to read, assumes files have a .txt extension and that they are stored in a folder named files
        // Assumes file name is provided as an argument (use Run --> Edit Configurations in IntelliJ to set name or
        // run program from the Terminal window).
        String fileName = ".\\files\\" + args[0];

        // Text file contents read into a single variable named fileText
        String fileText = "";
        try {
            fileText = Files.readString(Path.of(fileName));
        } catch (IOException e){
            System.err.format("Exception while reading %s", fileName);
        }

        // Provide some info on the file that was read.
        System.out.printf("%s contains %d characters\n", fileName, fileText.length());

        // Convert to lower case and throw away all characters except for letters.
        fileText = clean( fileText );

        // more info about the data
        System.out.printf("after cleaning, %d characters remain\n", fileText.length());

        // create array of words and provide some info to user
        String[] wordArray = fileText.split(" ");
        System.out.printf("%d words identified in file\n", wordArray.length);

        /*
            TODO: Use the wordArray to generate shingles and store them in a List of Nodes<String>
                  named "shingles"
                  Each Node should store a shingle (String) consisting of k words
                  and a count of the number of times that shingle has appeared in the text.
         */



        /*
             End TODO - the rest of main() generates a report and dumps the List of shingles to
                        a file
         */

        // use the List of Nodes to generate a report
        int totalShingles = 0;
        for( Node<String> aNode : shingles ){
            totalShingles += aNode.getCount();
        }
        System.out.format("\n%s contains %d %d-shingles, including %d distinct shingles.\n",
                fileName, totalShingles, k, shingles.size());

        // create comparator for comparing Count values and sort shingles on the Count field
        Comparator<Node<String>> countComparator = (Node<String> s1, Node<String> s2) -> (int) -(s1.getCount() - s2.getCount());
        Collections.sort(shingles, countComparator);

        // open an output file and dump contents of the List of shingles to the file
        String outfileName = fileName.replace(".txt", "-" + k + "-shingles.txt");
        saveResults( outfileName );
        System.out.println("Shingles written to file: " + outfileName);

        // assuming there are at least 10 shingles, print the 10 most frequent, for debugging, etc.
        assert shingles.size() >= 10 : "Fewer than 10 shingles found.";

        System.out.format("\nThe 10 most frequent shingles in %s are: \n", fileName);
        for(int i=0; i<10; i++){
            System.out.println(shingles.get(i));
        }
    }

    /*
         Open output text file and write each Node to the file
     */
    public static void saveResults(String outfileName){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfileName));

            for( Node<String> aNode : shingles ){
                writer.write(aNode.toString() + '\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Remove non-letters and multiple adjacent spaces from a String.
     */
    public static String clean(String str){
        str = str.toLowerCase();
        str = str.replaceAll("[^a-z]"," "); // replace all non-letters by spaces
        str = str.replaceAll("\\s{2,}", " "); // replace multiple spaces by one space
        str = str.strip();  // remove leading and trailing spaces
        return str;
    }
}


