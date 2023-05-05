import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new StringCalculatorImpl();
    }

    @Test
    public void testEmptyStringReturnsZero() {
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    public void testNotEmptyStringReturns5() {
        Assertions.assertEquals(5, calculator.add("5"));
    }

    @Test
    public void testNotEmptyStringReturns0() {
        Assertions.assertEquals(0, calculator.add("0"));
    }

    @Test
    public void testNotEmptyStringReturnsSum5() {
        Assertions.assertEquals(5, calculator.add("2,3"));
    }

    @Test
    public void testNotEmptyStringReturnsSum5inputSpace() {
        Assertions.assertEquals(5, calculator.add("2, 3"));
    }

}
