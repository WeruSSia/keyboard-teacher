import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class KeyboardTeacherTest {

    private KeyboardTeacher keyboardTeacher;
    private String firstLineToBeTaught;
    private String secondLineToBeTaught;
    private String inputFileContent;
    private File inputFile;

    @Before
    public void setUp() throws IOException {
        keyboardTeacher = new KeyboardTeacher();
        inputFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("inputFile.txt")).getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        firstLineToBeTaught = bufferedReader.readLine();
        secondLineToBeTaught = bufferedReader.readLine();
        inputFileContent = firstLineToBeTaught + "\n" + secondLineToBeTaught + "\n";
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void teach_success() throws IOException {

        systemInMock.provideLines(firstLineToBeTaught, secondLineToBeTaught);

        keyboardTeacher.teach(inputFile.toString());

        assertEquals("\r\nCongratulations!\r\n", systemOutRule.getLog().substring(inputFileContent.length()));
    }

    @Test
    public void teach_lineTooShort() throws IOException {
        systemInMock.provideLines("This is th", firstLineToBeTaught, secondLineToBeTaught);
        keyboardTeacher.teach(inputFile.toString());
        assertEquals("\r\nMistake on position 11. Given line is too short. You should type in \'e\' char now. Try again!", systemOutRule.getLog().substring(firstLineToBeTaught.length(), systemOutRule.getLog().length() - inputFileContent.length() - firstLineToBeTaught.length() + 1));
    }

    @Test
    public void teach_lineTooLong() throws IOException {
        systemInMock.provideLines("This is the first line..", firstLineToBeTaught, secondLineToBeTaught);
        keyboardTeacher.teach(inputFile.toString());
        assertEquals("\r\nMistake on position 24. Given line is too long. Try again!", systemOutRule.getLog().substring(firstLineToBeTaught.length(), systemOutRule.getLog().length() - inputFileContent.length() - firstLineToBeTaught.length() + 1));
    }

    @Test
    public void teach_mistake() throws IOException {
        systemInMock.provideLines("Thus is the first line.", firstLineToBeTaught, secondLineToBeTaught);
        keyboardTeacher.teach(inputFile.toString());
        assertEquals("\r\nMistake on position 3. You typed in \'u\' char instead of \'i\' char. Try again!", systemOutRule.getLog().substring(firstLineToBeTaught.length(), systemOutRule.getLog().length() - inputFileContent.length() - firstLineToBeTaught.length() + 1));
    }
}