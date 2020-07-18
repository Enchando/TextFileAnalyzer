import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeAll
    static void setUp() {
        System.out.println("TextFileAnalyzer tests beginning...");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("TextFileAnalyzer tests completed");
    }

    @Test
    void wordCondenser() {

        //Test that if the input is of length 1, then return 1.
        int testSize = Main.wordCondenser("I");
        assertEquals(1, testSize);

        //Test it works for special chars.
        testSize = Main.wordCondenser("&");
        assertEquals(1, testSize);

        //Test if word with chars at start and end are removed.
        testSize = Main.wordCondenser("!!££))''``¬¬||Hello@@~~#][{}=+-_");
        assertEquals(5, testSize);

        //Test that formatted numbers work.
        testSize = Main.wordCondenser("43+32-45/100=2.1234");
        assertEquals(19, testSize);

        System.out.println("wordCondenser test completed, moving on.");
    }

    @Test
    void notEmptyWord(){
        //Test to see if word that has letters and digits returns true.
        boolean testValidity = Main.notEmptyWord("////e$$");
        assertTrue(testValidity);

        //Test to see if word made of no digits or letters passes.
        testValidity = Main.notEmptyWord("><><>?>?>{}{@~@_+_+-=--");
        assertFalse(testValidity);

        System.out.println("notEmptyWord test completed, moving on.");
    }

    @Test
    void endStringBuilder() {

        //Test to see if numbers are properly formatted when entered in the string builder.
        ArrayList<Integer> testKeys = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        StringBuilder correctString = new StringBuilder();
        correctString.append("1 & 2 & 3 & 4 & 5 & 6 & 7 & 8 & 9 & 10");
        StringBuilder testString = Main.endStringBuilder(testKeys);
        /*As StringBuilder creates objects, although the two strings tested are the same they are not the same object and so assert does not work
        unless convert back to a string. */
        assertEquals(correctString.toString(), testString.toString());

        //Test that a singular number does not include any '&'.
        testKeys = new ArrayList<>(Arrays.asList(1));
        StringBuilder correctSingleString = new StringBuilder();
        correctSingleString.append("1");
        StringBuilder testSingleString = Main.endStringBuilder(testKeys);
        assertEquals(correctSingleString.toString(), testSingleString.toString());

        System.out.println("endStringBuilder test completed, moving on.");
    }

    @Test
    void checkFile() {

        //Test to see if a correct file (a .txt file, has content and exists) works
        boolean testValidity = Main.checkFile("src/test/java/TestText.txt");
        assertTrue(testValidity);

        //Test to see if wrongly formatted file (a .docx document) works
        testValidity = Main.checkFile("src/test/java/IncorrectFile.docx");
        assertFalse(testValidity);

        //Test to see if empty file works
        testValidity = Main.checkFile("src/test/java/TestBlank.txt");
        assertFalse(testValidity);

        System.out.println("checkFile test completed, moving on.");
    }

    @Test
    void userInput() {

        //Test to see if user input works for a correct file.
        ByteArrayInputStream in = new ByteArrayInputStream("src/test/java/TestText.txt".getBytes());
        System.setIn(in);
        String testUserInput = Main.userInput();
        assertEquals("src/test/java/TestText.txt", testUserInput);

        //Test to see if user input works for an incorrect file, then proceeds with a correct file.
        String wrongThenRightString = "src/test/java/TestBlank.txt" + "\nsrc/test/java/TestText.txt";
        in = new ByteArrayInputStream(wrongThenRightString.getBytes());
        System.setIn(in);
        testUserInput = Main.userInput();
        assertEquals("src/test/java/TestText.txt", testUserInput);

        System.out.println("userInput test completed, moving on.");

    }
}