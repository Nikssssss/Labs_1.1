package calculator.commands;

import calculator.calculatorExceptions.NegativeOperandException;
import calculator.calculatorExceptions.StackSizeException;
import calculator.calculatorExceptions.WrongArgumentsException;

public class Sqrt implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws NegativeOperandException, WrongArgumentsException, StackSizeException{
        checkCommand(data, argsOfCommand);
        Double number = data.getNumber();
        if (number < 0){
            data.pushNumber(number);
            throw new NegativeOperandException("Negative number in command " + this.getClass().getName());
        }
        else {
            data.pushNumber(Math.sqrt(number));
        }
    }

    @Override
    public void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException {
        if (argsOfCommand.length != 0){
            throw new WrongArgumentsException("Too many arguments, need nothing");
        }
        if (data.getStackSize() < 1){
            throw new StackSizeException("Too few elements on stack, need 1 at least");
        }
    }
}
