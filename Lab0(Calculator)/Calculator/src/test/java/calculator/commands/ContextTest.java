package calculator.commands;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class ContextTest {
    Context data = new Context();

    @Test
    public void getVariableValue() {
        data.pushVariable("a", 5d);
        data.pushVariable("b", 7d);
        assertEquals(7d, data.getVariableValue("b"), 1e-10);
        assertNull(data.getVariableValue("c"));
    }

    @Test
    public void getNumber() {
        data.pushNumber(1d);
        assertEquals(1d, data.getNumber(), 1e-10);
        try{
            data.getNumber();
        }
        catch(EmptyStackException ignored){ }
    }

    @Test
    public void peekNumber() {
        data.pushNumber(6d);
        assertEquals(6d, data.peekNumber(), 1e-10);
        assertEquals(6d, data.getNumber(), 1e-10);
        try{
            data.peekNumber();
        }
        catch(EmptyStackException ignored){}
    }

    @Test
    public void getStackSize() {
        assertEquals(0, data.getStackSize().intValue());
        data.pushNumber(6d);
        assertEquals(1, data.getStackSize().intValue());
    }
}