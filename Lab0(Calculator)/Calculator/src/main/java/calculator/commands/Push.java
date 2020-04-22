package calculator.commands;

import calculator.calculatorExceptions.WrongArgumentsException;

public class Push implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws WrongArgumentsException{
        checkCommand(data, argsOfCommand);
        Double variableValue = data.getVariableValue(argsOfCommand[0]);
        if (variableValue == null){
            throw new WrongArgumentsException("Wrong argument for command " + this.getClass().getName());
        }
        data.pushNumber(variableValue);
    }

    @Override
    public void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException{
        if (argsOfCommand.length != 1){
            throw new WrongArgumentsException("Need only 1 argument");
        }
    }
}
