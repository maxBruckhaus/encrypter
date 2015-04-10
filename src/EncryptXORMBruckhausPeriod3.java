/*  Maxwell Bruckhaus
 *  Period 3
 *  April 5th, 2015
 *  Time taken: 3 hours
 *  I didn't get a chance to work more than 3 hours on this lab
 *  due to working and spending time with my family for Easter. I got
 *  done with most of the lab; I only need to find a way to encrypt the
 *  characters. I got the reading and output file working.
 *
*/


import java.io.*;
import java.util.Scanner;

public class EncryptXORMBruckhausPeriod3 {

    private String inputFileName = "";
    private File inputFile;
    private FileReader reader;
    private String outputFileName = "";
    private File outputFile;
    private FileWriter writer;
    private String encryptionKey = "";
    private int keyPosition = 0;
    private char[] inputBuffer = new char[64];

    public static void main(String[] args) throws IOException {
        EncryptXORMBruckhausPeriod3 crypter = new EncryptXORMBruckhausPeriod3();
        crypter.getInputs();
        crypter.prepareFiles();
        crypter.encrypt();
        crypter.cleanUp();
    }

    private void encrypt() throws IOException {
        while(reader.ready()) { // there are bytes to be read
            this.readCharacters();
            this.encryptCharacters();
        }
    }

    private void prepareFiles() throws IOException {
        inputFile = new File(this.inputFileName);
        reader = new FileReader(inputFile);
        outputFile = new File(outputFileName);
        outputFile.createNewFile();
        writer = new FileWriter(outputFile);
    }

    private void readCharacters() throws IOException {
        // 2.  Read characters into the buffer using the
        //     read(char[] cbuf) method and save the return value.
        //     The return value tells you how many characters were read,
        //     which may be less than the array/buffer size.
        reader.read(inputBuffer); // reads the content to the array
        encryptCharacters();
    }

    private void encryptCharacters() throws IOException {
        char encryptedCharacter;
        // 3.  Loop through each character in the buffer
        for(char c : inputBuffer) {
            System.out.print(c); //prints the characters one by one
            encryptedCharacter = encryptCharacter(c);
            // Write the encrypted message to the output file:
            writer.write(encryptedCharacter);
        }
    }

    private char encryptCharacter(char c) {
        //    As you read the input file, XOR each character with the next available
        //    character in the key and append the result to the encrypted message String.
        //    Cycle through the key characters as needed.
        //    use Java's built-in ^ operator:
        char result;
        int intResult;
        int keyValue = (int)encryptionKey.charAt(keyPosition);
        int intCharValue = (int)c;
        // XOR it now:
        intResult = keyValue ^ intCharValue;
        result = (char)intResult;
        keyPosition = (keyPosition + 1) % encryptionKey.length();
        return result;
    }

    private void getInputs() {
        // Prompt the user for the following information:
        // An input file to encrypt
        // An output file to store the result
        // A private key (String)
        System.out.println("Welcome to the XOR Text File Encryptor!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file to encrypt: ");
        inputFileName = scanner.next();
        System.out.print("Enter a file to output: ");
        outputFileName = scanner.next();
        System.out.print("Enter a private key: ");
        encryptionKey = scanner.next();
    }

    private void cleanUp() throws IOException {
        writer.flush();
        writer.close();
        reader.close();
        System.out.println();
        System.out.println("Success!  File " + outputFileName + " created!");
        System.out.print("Press any key to continue... ");
        // close the FileReader when you're done with it
    }
}