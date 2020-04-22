package calculator.commands;

import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefineTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        Define define = new Define();
        try {
            define.doOperation(data, new String[]{"a", "b"});
        }
        catch(CalculatorException | NumberFormatException ignored){}
        try{
            define.doOperation(data, new String[]{"a", null});
        }
        catch (CalculatorException | NullPointerException ignored){}
        try{
            define.doOperation(data, new String[]{"a", "6"});
        }
        catch(CalculatorException ignored){}
        assertEquals(6d, data.getVariableValue("a"), 1e-10);
        try{
            define.doOperation(data, new String[]{"Odin"});
        }
        catch(CalculatorException ex){
            assertEquals("Need only 2 arguments", ex.getMessage());
        }
    }
}