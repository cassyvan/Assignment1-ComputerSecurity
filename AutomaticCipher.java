import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class AutomaticCipher {

    private ArrayList<Integer> keyList = new ArrayList<Integer>();
    private int chosenKey;
    private String msg;

    private Scanner scan = new Scanner(System.in);

    private void generateKeys() {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            keyList.add(Integer.valueOf(rand.nextInt(25)));
        }
    }

    private void printKeys() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("option %d: %d\n", i, keyList.get(i));
        }
    }

    private String takeUserInput(String prompt) {
        System.out.print(prompt);
        String input = scan.nextLine();
        return input;
    }

    private boolean checkChosenKey(int key) {
        return key <= 2;
    }

    private String encryptMsg(int key, String msg) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char character = msg.charAt(i);
            int ascii = (int) character;
            int encryptedAscii = ascii + key;
            String encryptedChar = Character.toString(encryptedAscii);
            str.append(encryptedChar);
        }
        return str.toString();
    }

    private String decryptMsg(int key, String msg) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char character = msg.charAt(i);
            int ascii = (int) character;
            int encryptedAscii = ascii - key;
            String encryptedChar = Character.toString(encryptedAscii);
            str.append(encryptedChar);
        }
        return str.toString();
    }

    public void encryption() {
        generateKeys();
        printKeys();
        int chosenKeyIndex = Integer.valueOf(takeUserInput("Here are your secret keys, please pick one: "));
        if (!checkChosenKey(chosenKeyIndex)) {
            System.out.println("Invalid key");
            System.exit(1);
        }
        chosenKey = keyList.get(chosenKeyIndex);
        msg = takeUserInput("What is the message you would like to encrypt? ");
        String encryptedMsg = encryptMsg(chosenKey, msg);
        System.out.println(encryptedMsg);

    }

    public void decryption() {
        int secretKey = Integer.valueOf(takeUserInput("To decrypt your message, please enter in your secret key: "));
        if (!keyList.contains(secretKey)) {
            System.out.println("Key is incorrect");
            System.exit(1);
        }
        String decryptMsg = takeUserInput("Enter your message to decrypt: ");
        String decryptedMsg = decryptMsg(secretKey, decryptMsg);
        System.out.println(decryptedMsg);
    }

    public void run() {
        encryption();
        decryption();
    }
}
