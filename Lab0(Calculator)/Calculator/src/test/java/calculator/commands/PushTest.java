package calculator.commands;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PushTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        data.pushVariable("a", 10d);
        Push push = new Push();
        try {
            push.doOperation(data, new String[]{"Arg", "Arg2"});
        }
        catch (CalculatorException ex){
            assertEquals("Need only 1 argument", ex.getMessage());
        }
        try{
            push.doOperation(data, new String[]{"c"});
        }
        catch(CalculatorException ex){
            assertEquals("Wrong argument for command " + Push.class.getName(), ex.getMessage());
        }
        try{
            push.doOperation(data, new String[]{"a"});
        }
        catch (CalculatorException ignored){}
        assertEquals(10d, data.peekNumber(), 1e-10);
    }
}