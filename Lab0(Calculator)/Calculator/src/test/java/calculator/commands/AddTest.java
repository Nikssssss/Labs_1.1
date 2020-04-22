package calculator.commands;

import calculator.Calculator;
import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTest {
    @Test
    public void doOperation() {
        Context data = new Context();
        data.pushNumber(190d);
        Add add = new Add();
        try {
            add.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Too few elements on stack, need 2 at least", ex.getMessage());
        }
        try {
            add.doOperation(data, new String[]{"Arg"});
        }
        catch (CalculatorException ex){
            assertEquals("Too many arguments, need nothing", ex.getMessage());
        }
        assertEquals(190d, data.peekNumber(), 1e-10);
        data.pushNumber(210d);
        try {
            add.doOperation(data, new String[0]);
        }
        catch(CalculatorException ignored){ }
        assertEquals(400d, data.peekNumber(), 1e-10);
    }
}
