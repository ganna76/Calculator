import org.example.StringCalculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    static StringCalculator calculator;

    @BeforeAll
    static void setUp(){
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("Empty test")
    public void testAddEmptyString() {

        assertEquals(0, calculator.add(""));
    }

    @Test
    @DisplayName("Add 1 number")
    public void testAddOneNumber() {

        assertEquals(7, calculator.add("7"));
    }

    @Test
    public void testAddMultipleNumbersPart1() {

        assertEquals(55, calculator.add("13,42"));
    }

    @Test
    public void testAddMultipleNumbersPart2() {

        assertEquals(9, calculator.add("3,2,4"));
    }

    @Test
    public void testCarriageReturn() {

        assertEquals(3, calculator.add("1,1\n1"));
    }

    @Test
    public void testAddDifferentDelimiter() {

        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    public void testNumbersOver1000Ignored() {

        assertEquals(2, calculator.add("1001,2"));
    }

    @Test
    public void testDelimitersOfAnyLength() {

        assertEquals(6, calculator.add("//[###]\n1###2###3"));
        assertEquals(6, calculator.add("//[|||]\n1|||2|||3"));
    }

}