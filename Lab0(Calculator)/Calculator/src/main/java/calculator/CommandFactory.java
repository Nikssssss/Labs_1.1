package calculator;

import calculator.commands.Command;
import calculator.factoryExceptions.CommandCreateException;
import calculator.factoryExceptions.PropertyLoadException;
import calculator.factoryExceptions.UnknownCommandException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommandFactory {
    Properties property = new Properties();
    private CommandFactory() throws PropertyLoadException{
        try {
            property.load(new FileInputStream("src/main/resources/commandsConfig.properties"));
        }
        catch(IOException ex){
            throw new PropertyLoadException("Can't load the property file of commands");
        }
    }

    private static CommandFactory instance = null;
    public static CommandFactory getInstance() throws PropertyLoadException{
        if (instance == null){
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command createCommand(String nameOfCommand) throws UnknownCommandException, CommandCreateException{
        String nameOfClass = property.getProperty(nameOfCommand);
        if (nameOfClass == null){
            throw new UnknownCommandException("Can't find the class for" + nameOfCommand);
        }
        try {
            return (Command)Class.forName(nameOfClass).getDeclaredConstructor().newInstance();
        }
        catch(Exception ex){
            throw new CommandCreateException("Can't create a command " + nameOfClass);
        }
    }
}
