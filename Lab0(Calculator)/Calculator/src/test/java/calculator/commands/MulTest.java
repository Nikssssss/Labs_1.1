package calculator.commands;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MulTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        data.pushNumber(10d);
        Mul mul = new Mul();
        try {
            mul.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Too few elements on stack, need 2 at least", ex.getMessage());
        }
        try {
            mul.doOperation(data, new String[]{"Arg"});
        }
        catch (CalculatorException ex){
            assertEquals("Too many arguments, need nothing", ex.getMessage());
        }
        assertEquals(10d, data.peekNumber(), 1e-10);
        data.pushNumber(20d);
        try{
            mul.doOperation(data, new String[0]);
        }
        catch(CalculatorException ignored){}
        assertEquals(200d, data.peekNumber(), 1e-10);
    }
}