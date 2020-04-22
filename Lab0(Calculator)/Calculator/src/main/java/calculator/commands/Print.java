package calculator.commands;

import calculator.calculatorExceptions.StackSizeException;
import calculator.calculatorExceptions.WrongArgumentsException;

public class Print implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException{
        checkCommand(data, argsOfCommand);
        System.out.println(data.peekNumber());
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
