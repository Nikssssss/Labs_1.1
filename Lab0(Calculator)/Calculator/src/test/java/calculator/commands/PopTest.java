package calculator.commands;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        Pop pop = new Pop();
        try {
            pop.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Too few elements on stack, need 1 at least", ex.getMessage());
        }
        try {
            pop.doOperation(data, new String[]{"Arg"});
        }
        catch (CalculatorException ex){
            assertEquals("Too many arguments, need nothing", ex.getMessage());
        }
        data.pushNumber(10d);
        data.pushNumber(20d);
        try {
            pop.doOperation(data, new String[0]);
        }
        catch(CalculatorException ignored){}
        assertEquals(10d, data.peekNumber(), 1e-10);
    }
}