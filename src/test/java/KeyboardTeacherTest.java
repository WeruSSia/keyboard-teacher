import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class KeyboardTeacherTest {

    private KeyboardTeacher keyboardTeacher;

    @Before
    public void setUp() {
        keyboardTeacher = new KeyboardTeacher();
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void teach_success() throws IOException {
        final String firstLineToBeTaught = "This is the first line.";
        final String secondLineToBeTaught = "This is the second line.";
        String inputFileContent = firstLineToBeTaught + "\n" + secondLineToBeTaught + "\n";
        final String fileName = "inputFile.txt";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(inputFileContent);
        fileWriter.close();

        systemInMock.provideLines(firstLineToBeTaught, secondLineToBeTaught);

        keyboardTeacher.teach(fileName);

        assertEquals("\r\nCongratulations!\r\n", systemOutRule.getLog().substring(inputFileContent.length()));
    }
}