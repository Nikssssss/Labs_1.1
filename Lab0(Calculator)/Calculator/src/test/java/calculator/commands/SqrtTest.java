package calculator.commands;

import calculator.Calculator;
import calculator.calculatorExceptions.CalculatorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void doOperation() {
        Context data = new Context();
        Sqrt sqrt = new Sqrt();
        try {
            sqrt.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Too few elements on stack, need 1 at least", ex.getMessage());
        }
        try {
            sqrt.doOperation(data, new String[]{"Arg"});
        }
        catch (CalculatorException ex){
            assertEquals("Too many arguments, need nothing", ex.getMessage());
        }
        data.pushNumber(-9d);
        try{
            sqrt.doOperation(data, new String[0]);
        }
        catch(CalculatorException ex){
            assertEquals("Negative number in command " + Sqrt.class.getName(), ex.getMessage());
        }
        assertEquals(-9d, data.getNumber(), 1e-10);
        data.pushNumber(9d);
        try{
            sqrt.doOperation(data, new String[0]);
        }
        catch(CalculatorException ignored){}
        assertEquals(3d, data.peekNumber(), 1e-10);

    }
}