package calculator.commands;

import calculator.calculatorExceptions.WrongArgumentsException;

public class Push implements Command {
    @Override
    public void doOperation(Context data, String[] argsOfCommand) throws WrongArgumentsException{
        checkCommand(data, argsOfCommand);
        Double value;
        try {
            value = Double.valueOf(argsOfCommand[0]);
        }
        catch (NumberFormatException ignored){
            value = data.getVariableValue(argsOfCommand[0]);
            if (value == null){
                throw new WrongArgumentsException("Wrong argument for command " + this.getClass().getName());
            }
        }
        data.pushNumber(value);
    }

    public void checkCommand(Context data, String[] argsOfCommand) throws WrongArgumentsException{
        if (argsOfCommand.length != 1){
            throw new WrongArgumentsException("Need only 1 argument");
        }
    }
}
