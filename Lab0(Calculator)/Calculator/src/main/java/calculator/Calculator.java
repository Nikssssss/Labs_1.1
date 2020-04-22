package calculator;

import calculator.calculatorExceptions.CalculatorException;
import calculator.commands.Command;
import calculator.commands.Context;
import calculator.factoryExceptions.FactoryException;
import calculator.factoryExceptions.PropertyLoadException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Calculator {
    private Logger LOGGER = Logger.getLogger(this.getClass().getName());
    public void calculate(String inputFile) throws PropertyLoadException, IOException{
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/loggerConfig.properties"));
        }
        catch(IOException ex){
            LOGGER.log(Level.WARNING, "Can't open the logger configuration file in class {0}", this.getClass().getName());
        }
        Parser parserOfCommands = new Parser(inputFile);
        parserOfCommands.createReader();
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
                throw new PropertyLoadException("Can't load the property file of commands");
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
            catch(NumberFormatException ex){
                LOGGER.log(Level.SEVERE, "Incorrect number in command {0}", nameOfCommand);
            }
            LOGGER.log(Level.INFO, "Command {0} did calculation successfully", nameOfCommand);
        }
    }
}
