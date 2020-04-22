package calculator.commands;

import calculator.calculatorExceptions.DivisionByZeroException;
import calculator.calculatorExceptions.NegativeOperandException;
import calculator.calculatorExceptions.StackSizeException;
import calculator.calculatorExceptions.WrongArgumentsException;

public interface Command {
    void doOperation(Context data, String[] argsOfCommand)
            throws DivisionByZeroException, NegativeOperandException, WrongArgumentsException, StackSizeException;
    void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException;
}
