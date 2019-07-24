import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Simple program teaching typing on a keyboard. Reads text from input file line by line, after each line user must type in the exact line.
 * If user input is incorrect, program returns information about the mistake position and what should be a correct character.
 */
@SuppressWarnings("WeakerAccess")
public class KeyboardTeacher {
    private final Scanner userInputScanner = new Scanner(System.in);

    /**
     * Teaches user typing after reading each line from the input file. Compares user input with the given line from file and checks for mistakes.
     *
     * @param fileName Name of the input file containing text to be taught.
     * @throws IOException When something goes wrong with reading the input file.
     */
    public void teach(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        compareFileLineWithUserInput(bufferedReader);
        System.out.println("Congratulations!");
    }

    private String getUserInput() {
        return userInputScanner.nextLine();
    }

    private void compareFileLineWithUserInput(BufferedReader bufferedReader) throws IOException {
        String lineOfFile;
        lineOfFile = bufferedReader.readLine();
        while (lineOfFile != null) {
            System.out.println(lineOfFile);
            String userInput = getUserInput();
            if (lineOfFile.equals(userInput)) {
                lineOfFile = bufferedReader.readLine();
            } else {
                findMistakenPosition(lineOfFile, userInput);
            }
        }
    }

    private void findMistakenPosition(String lineOfFile, String userInput) {
        for (int i = 0; i < lineOfFile.length(); i++) {
            char comparedCharacterFromLineOfFile = lineOfFile.charAt(i);
            char comparedCharacterFromUserInput;
            try {
                comparedCharacterFromUserInput = userInput.charAt(i);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Mistake on position " + (userInput.length() + 1) + ". Given line is too short. You should type in \'" + lineOfFile.charAt(userInput.length()) + "\' char now. Try again!");
                return;
            }
            if (comparedCharacterFromLineOfFile != comparedCharacterFromUserInput) {
                System.out.println("Mistake on position " + (i + 1) + ". You typed in \'" + comparedCharacterFromUserInput + "\' char instead of \'" + comparedCharacterFromLineOfFile + "\' char. Try again!");
                return;
            }
        }
        System.out.println("Mistake on position " + (lineOfFile.length() + 1) + ". Given line is too long. Try again!");
    }
}
