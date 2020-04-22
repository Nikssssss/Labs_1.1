package calculator.commands;

import calculator.calculatorExceptions.StackSizeException;
import calculator.calculatorExceptions.WrongArgumentsException;
import com.sun.jdi.request.WatchpointRequest;

public class Pop implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException{
        checkCommand(data, argsOfCommand);
        data.getNumber();
    }

    @Override
    public void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException, StackSizeException{
        if (argsOfCommand.length != 0){
            throw new WrongArgumentsException("Too many arguments, need nothing");
        }
        if (data.getStackSize() < 1){
            throw new StackSizeException("Too few elements on stack, need 1 at least");
        }
    }
}
