package calculator.commands;

import calculator.calculatorExceptions.WrongArgumentsException;

public class Define implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws WrongArgumentsException {
        checkCommand(data, argsOfCommand);
        data.pushVariable(argsOfCommand[0], Double.parseDouble(argsOfCommand[1]));
    }

    public void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException{
        if (argsOfCommand.length != 2){
            throw new WrongArgumentsException("Need only 2 arguments");
        }
    }
}
