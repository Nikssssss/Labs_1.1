package calculator.commands;

import calculator.Calculator;
import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrintTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        Print print = new Print();
        try {
            print.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Too few elements on stack, need 1 at least", ex.getMessage());
        }
        try {
            print.doOperation(data, new String[]{"Arg"});
        }
        catch (CalculatorException ex){
            assertEquals("Too many arguments, need nothing", ex.getMessage());
        }
        data.pushNumber(10d);
        try{
            print.doOperation(data, new String[0]);
        }
        catch (CalculatorException ignored){}
        assertEquals(10d, data.peekNumber(), 1e-10);
    }
}