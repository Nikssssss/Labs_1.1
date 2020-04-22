package calculator.commands;

import calculator.calculatorExceptions.StackSizeException;
import calculator.calculatorExceptions.WrongArgumentsException;

public class Sub implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException{
        checkCommand(data, argsOfCommand);
        Double value1 = data.getNumber();
        Double value2 = data.getNumber();
        data.pushNumber(value2 - value1);
    }

    @Override
    public void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException {
        if (argsOfCommand.length != 0){
            throw new WrongArgumentsException("Too many arguments, need nothing");
        }
        if (data.getStackSize() < 2){
            throw new StackSizeException("Too few elements on stack, need 2 at least");
        }
    }
}
