*** Text File Analyzer Installation/Running***

To run the text analyzer, Java 11 or higher is required to be installed.

- Opening the Program -
Once this has been installed, the program does not need to be installed; inside the zip file/github repository is TextFileAnalyzer.jar, which can be
run from command line/terminal. Navigate to the folder where it is located and type "java -jar TextFileAnalyzer.jar". The files to be tested 
need to be in the same location as the .jar file; alternatively if it is stored elsewhere, the full file location address can be given.

- Using the Program - 
Once the program is running, type the name of the txt file including '.txt'. The example given in the email has been written in "TestText.txt",
and so to run it you will need to type "TestText.txt" without quotation marks.

- Other Information - 
The unit tests can be found with the rest of the code in src/test/java/MainTest.java.

- Assumptions of Defining Words - 
I have tried to make the definition of words as basic as possible. Assuming that the .txt file is properly formatted, a word can be any string of 
characters, letters and punctuation. The only time that a word is changed is if it starts or ends with punctuation. For example, the word "didn't" 
will be counted as 6 characters, as the " ' " makes up a part of the word and therefore is counted. If a word has punctuation attached to it either end,
e.g. ""Hello!"." (one set of speech marks, one exclamation mark, one full stop), then they will be ignored - the example will be 5 characters long.

This also means that numbers will be formatted in the same way. If there is a date, such as "25/6/23.", then this will be counted as one word and the 
" / " will also be counted. This applies to calculations too, "45/23.12341525+3=10" will be one word. However, this does have limitations - notably if
doing numbers with punctuation such as "£23" or "23%", they will both count as 2. 

Any words consisting of pure punctuations are not counted. Going to the bible example, there is use of "************" to clearly show separations in 
chapters etc. This is not considered to be a word and none of the characters are counted.

Separated punctuation is slightly difficult to judge, and so a rule has been set that if the length of a word is equal to one, then it will be counted
as a word. This means that punctuation such as "&" and "-" will be included in the word count. Again this heavily depends on the format of the text.
Some may use "[ Hello ]" and others "[Hello]", altering both word and character count.