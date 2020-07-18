import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {    /**
 * Finds the word count in the file and the average word length. Ignores punctuation before/after words, but
 * keeps them in if they are part of the word or number. This is done for simplicity; for example words such as
 * 'didn't' will be of length 6 but '"Hello!"' will be of length 5. Punctuation outside the string does not contribute
 * towards the word's length, as they are not intrinsic to the word, whereas mathematical operators, apostrophes etc. form
 * a part of the word.
 * @param textFile
 */
public static void analyzeFile(File textFile) {

    double numWords = 0;
    double totalChars = 0;
    //Map of length of word being the key with how many occurrences of that word length being the value.
    TreeMap<Integer, Integer> numWordsOfLengths = new TreeMap<Integer, Integer>();
    try {
        Scanner scanner = new Scanner(textFile);
        while (scanner.hasNextLine()) {

            //Number of words is how many strings appear in a line separated by a space or tab.
            String[] wordsInLine = scanner.nextLine().split("\\s+");
            numWords += wordsInLine.length;

            //Checking the length of the word.
            for (String word : wordsInLine) {

                totalChars += wordCondenser(word);

                    /*Finding the number of words of different lengths, by adding to the key in the TreeMap representing the length of the word, or if a word of this length does not
                    exist yet, then add a new key.
                    */
                int key = word.length();
                if (!(numWordsOfLengths.containsKey(key))) {
                    numWordsOfLengths.put(key, 1);
                } else {
                    numWordsOfLengths.put(key, numWordsOfLengths.get(key) + 1);
                }
            }
        }

    } catch (FileNotFoundException noFileException) {
        System.out.println("File cannot be found, please try another one.");
        noFileException.printStackTrace();
    }

    //Find the max value of occurrences in the word map.
    int max = Collections.max(numWordsOfLengths.values());
    ArrayList<Integer> keys = new ArrayList<>();
    for (int key : numWordsOfLengths.keySet()) {
        if (numWordsOfLengths.get(key) == max) {
            keys.add(key);
        }
    }

    //Build the end string of maximum word length occurrences for the print statements.
    StringBuilder endString = endStringBuilder(keys);

    //Final prints.
    System.out.println("Word Count = " + numWords);
    System.out.println("Average word length = " + (totalChars / numWords));
    for (int key : numWordsOfLengths.keySet()) {
        System.out.println("Number of words of length " + key + " is " + numWordsOfLengths.get(key));
    }
    System.out.println("The most frequently occuring word length is " + max + ", for word lengths of " + endString);
}


    /**
     * Function for removing unnecessary punctuation from the start and end of the word.
     * @param word Word in the sentence to check for attached punctuation.
     * @return The word without punctuation that could affect word length.
     */
    public static int wordCondenser(String word){
        /*If length of word is one, adds it to the total. This means that any standalone punctuation such as & count towards the word count. This
        relies on all words being properly formatted however.
        */
        if (word.length() == 1) {
            return 1;

        } else {
            //Taking the current word and removing any unnecessary punctuation from the front and back while still retaining punctuation inside the word.
            while (!(Character.isLetterOrDigit(word.charAt(0)) && Character.isLetterOrDigit(word.charAt(word.length() - 1)))) {
                if (!(Character.isLetterOrDigit(word.charAt(0)))) {
                    word = word.substring(1);
                }
                if (!(Character.isLetterOrDigit(word.charAt(word.length() - 1)))) {
                    word = word.substring(0, word.length() - 1);
                }
            }

            return word.length();
        }
    }

    /**
     * Function for building the end string of words of maximum lengths - for example if words of length 2 and 4 occur the most frequently,
     * it will build "2 & 4".
     * @param keys The word lengths that have the maximum occurrences.
     * @return A string containing all of the maximum word lengths.
     */
    public static StringBuilder endStringBuilder(ArrayList<Integer> keys){
        StringBuilder endString = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            endString.append(keys.get(i).toString());
            if(i != keys.size() - 1){
                endString.append(" & ");
            }
        }
        return endString;
    }

    public static void main(String[] args) {
        File testFile = new File("src/main/resources/TestText.txt");
        analyzeFile(testFile);
    }
}