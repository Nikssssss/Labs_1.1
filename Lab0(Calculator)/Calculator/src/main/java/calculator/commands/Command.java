package calculator.commands;

import calculator.calculatorExceptions.*;

public interface Command {
    void doOperation(Context data, String[] argsOfCommand) throws CalculatorException;
}
