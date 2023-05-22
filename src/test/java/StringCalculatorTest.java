import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class StringCalculatorTest {


    private StringCalculator calculator;


    @BeforeEach
    public void beforeEach() {
        Logger mockLogger = mock(Logger.class);
        calculator = new StringCalculatorImpl(mockLogger);
    }

    @Test
    public void testEmptyStringReturnsZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testNotEmptyStringReturns5() {
        assertEquals(5, calculator.add("5"));
    }

    @Test
    public void testNotEmptyStringReturns0() {
        assertEquals(0, calculator.add("0"));
    }

    @Test
    public void testNotEmptyStringReturnsSum5() {
        assertEquals(5, calculator.add("2,3"));
    }

    @Test
    public void testNotEmptyStringReturnsSum5inputSpace() {
        assertEquals(5, calculator.add("2, 3"));
    }

    @Test
    public void testNotEmptyStringReturnsSum3Nums() {
        assertEquals(9, calculator.add("2, 3, 4"));
    }

    @Test
    public void testNotEmptyStringReturnsSum3NumNewLine() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testNotEmptyStringReturnsSumNewLineEnd() {
        assertEquals(5, calculator.add("3, 2, \n"));
    }

    @Test
    public void testNotEmptyStringReturnsSum3NumNewLineEnd() {
        assertEquals(3, calculator.add("//[;]\n1;2"));
    }

    @Test
    public void testNotEmptyStringReturnsSum5NumNewLineEnd() {
        assertEquals(5, calculator.add("//[p]\n3p2"));
    }


//    @Test
//    public void testNotEmptyStringReturnExc() {
//
//        String expectedMessage = "Negatives not allowed";
//        Throwable exception = Assertions.assertThrows(NumberFormatException.class, () -> {
//            calculator.add("2, -3");
//        });
//
//
//        Assertions.assertEquals(expectedMessage, exception.getMessage());
//    }


    @Test
    public void testAddWithLargeNumbersLogsToLogger() {
        // Arrange
        Logger mockLogger = mock(Logger.class);
        StringCalculatorImpl calculator = new StringCalculatorImpl(mockLogger);

        // Act
        int result = calculator.add("1,2,1001,200");

        // Assert
        verify(mockLogger).log(1001);
    }

    @Test
    public void testMainOutputsWelcomeAndHelpText() {

        // Arrange
        String expectedWelcomeText = "Welcome to Calculator!";
        String expectedHelpText = "Usage: ...";

        // Skapa en inmatningssträng för att simulera användarinmatning
        String inputString = "\n";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);

        // Skapa en ByteArrayOutputStream för att fånga upp utskrift från System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        MainApp.main();

        // Assert
        String consoleOutput = outputStream.toString().trim();
        String[] outputLines = consoleOutput.split(System.lineSeparator());

        assertEquals(expectedWelcomeText, outputLines[0]);
        assertEquals(expectedHelpText, outputLines[1]);
    }

    @Test
    public void testMainOutputResultOneRow() {

        // Arrange
        String expectedResult = "Result: 6";

        // Skapa en inmatningssträng för att simulera användarinmatning
        String inputString = "1,2,3\n\n";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);

        // Skapa en ByteArrayOutputStream för att fånga upp utskrift från System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        MainApp.main();

        // Assert
        String consoleOutput = outputStream.toString().trim();
        String[] outputLines = consoleOutput.split(System.lineSeparator());

        assertEquals(expectedResult, outputLines[3]);
    }

    @Test
    public void testMainOutputResultsTwoRows() {

        // Arrange
        String expectedResultFirst = "Result: 6";
        String expectedResultSecond = "Result: 5";

        // Skapa en inmatningssträng för att simulera användarinmatning
        String inputString = "1,2,3\n 2,3 \n\n";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);

        // Skapa en ByteArrayOutputStream för att fånga upp utskrift från System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        MainApp.main();

        // Assert
        String consoleOutput = outputStream.toString().trim();
        String[] outputLines = consoleOutput.split(System.lineSeparator());

        assertEquals(expectedResultFirst, outputLines[3]);
        assertEquals(expectedResultSecond, outputLines[5]);
    }

    @Test
    public void testNotEmptyStringReturnsSum7NumNewLineEnd() {
        assertEquals(7, calculator.add("//[***][%%%]\n1***2%%%4"));
    }

    @Test
    public void testMainOutputResultOneRowDelimiters() {

        // Arrange
        String expectedResult = "Result: 7";

        // Create an input string to simulate user input
        String inputString = "//[***][%%%]\n1***2%%%4\n\n";


        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        MainApp.main();

        // Assert
        String consoleOutput = outputStream.toString().trim();
        String[] outputLines = consoleOutput.split(System.lineSeparator());

        assertEquals(expectedResult, outputLines[3]);
    }
}