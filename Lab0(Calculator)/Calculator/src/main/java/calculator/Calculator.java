package calculator;

import calculator.calculatorExceptions.CalculatorException;
import calculator.commands.Command;
import calculator.commands.Context;
import calculator.factoryExceptions.FactoryException;
import calculator.factoryExceptions.PropertyLoadException;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {
    private static Logger LOGGER = Logger.getLogger(Calculator.class.getName());
    public void calculate(InputStream inputFile) throws PropertyLoadException, NumberFormatException {
        Parser parserOfCommands = new Parser(inputFile);
        parserOfCommands.parseText();
        Context data = new Context();
        while(!parserOfCommands.isEmpty()){
            String[] currentCommand = parserOfCommands.getCommand();
            String nameOfCommand = currentCommand[0];
            String[] argsOfCommand = new String[currentCommand.length - 1];
            System.arraycopy(currentCommand, 1, argsOfCommand, 0, currentCommand.length - 1);
            Command command;
            try {
                command = CommandFactory.getInstance().createCommand(nameOfCommand);
            }
            catch (PropertyLoadException ex){
                throw ex;
            }
            catch (FactoryException ex){
                LOGGER.log(Level.SEVERE, "Error during creation of command {0}, " + ex.getMessage(), nameOfCommand);
                continue;
            }
            try {
                command.doOperation(data, argsOfCommand);
            }
            catch(CalculatorException ex){
                LOGGER.log(Level.SEVERE, "Error during calculation of command {0}, " + ex.getMessage(), nameOfCommand);
                continue;
            }
            LOGGER.log(Level.INFO, "Command {0} did calculation successfully", nameOfCommand);
        }
    }
}
