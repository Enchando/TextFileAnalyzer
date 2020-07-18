import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * Basic file reader using scanner. Used for checking file is being read correctly.
     * @param fileName Name of the file to be used.
     */
    public static void readFile(String fileName){
        try{
            File textFile = new File(fileName);
            Scanner scanner = new Scanner(textFile);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not Found, please try another.");
            e.printStackTrace();
        }
    }

    /**
     * Finds the word count in the file and the average word length. Ignores punctuation before/after words, but
     * keeps them in if they are part of the word or number. This is done for simplicity; for example words such as
     * 'didn't' will be of length 6 but '"Hello!"' will be of length 5. Punctuation outside the string does not contribute
     * towards the word's length, as they are not intrinsic to the word, whereas mathematical operators, apostrophes etc. form
     * a part of the word.
     * @param textFile
     */
    public static void wordCountAndAverage(File textFile){

        double numWords = 0;
        double totalChars = 0;

        try{
            Scanner scanner = new Scanner(textFile);
            while(scanner.hasNextLine()) {

                //Number of words is how many strings appear in a line separated by a space or tab.
                String[] wordsInLine = scanner.nextLine().split("\\s+");
                numWords += wordsInLine.length;

                //Checking the length of the word.
                for(String word : wordsInLine){

                    /*If length of word is one, adds it to the total. This means that any standalone punctuation such as & count towards the word count. This
                    relies on all words being properly formatted however.
                     */
                    if(word.length() == 1){
                        totalChars += 1;

                    }else {
                        //Taking the current word and removing any unnecessary punctuation from the front and back while still retaining punctuation inside the word.
                        while(!(Character.isLetterOrDigit(word.charAt(0)) && Character.isLetterOrDigit(word.charAt(word.length() - 1)))){
                            if(!(Character.isLetterOrDigit(word.charAt(0)))){
                                word = word.substring(1);
                            }
                            if(!(Character.isLetterOrDigit(word.charAt(word.length() - 1)))) {
                                word = word.substring(0, word.length() - 1);
                            }
                        }

                        totalChars += word.length();
                    }

                }
            }

        }catch(FileNotFoundException noFileException){
            System.out.println("File cannot be found, please try another one.");
            noFileException.printStackTrace();
        }

        System.out.println("Word Count = " + numWords);
        System.out.println("Average word length = " + (totalChars / numWords));
    }

    public static void main(String[] args) {
        File testFile = new File("src/main/resources/TestText.txt");
        wordCountAndAverage(testFile);
    }
}