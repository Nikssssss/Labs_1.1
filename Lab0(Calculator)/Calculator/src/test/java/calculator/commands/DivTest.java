package calculator.commands;

import calculator.Calculator;
import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DivTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        data.pushNumber(10d);
        Div div = new Div();
        try {
            div.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Too few elements on stack, need 2 at least", ex.getMessage());
        }
        try {
            div.doOperation(data, new String[]{"Arg"});
        }
        catch (CalculatorException ex){
            assertEquals("Too many arguments, need nothing", ex.getMessage());
        }
        assertEquals(10d, data.peekNumber(), 1e-10);
        data.pushNumber(20d);
        try{
            div.doOperation(data, new String[0]);
        }
        catch(CalculatorException ignored){}
        assertEquals(0.5d, data.peekNumber(), 1e-10);
        data.pushNumber(0d);
        try{
            div.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Division by zero", ex.getMessage());
        }
        assertEquals(0d, data.peekNumber(), 1e-10);
    }
}