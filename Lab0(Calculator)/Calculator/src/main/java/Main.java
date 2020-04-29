import calculator.Calculator;
import calculator.factoryExceptions.PropertyLoadException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/loggerConfig.properties"));
        }
        catch(IOException ex){
            LOGGER.log(Level.WARNING, "Can't open the logger configuration file in class {0}", Main.class.getName());
        }
        if (args.length > 1){
            LOGGER.log(Level.SEVERE, "Too many arguments in main");
            return;
        }
        InputStream inputFile;
        try {
            inputFile = createReader(args);
        }
        catch (IOException ex){
            LOGGER.log(Level.SEVERE, "Can't open input file");
            return;
        }
        Calculator calculator = new Calculator();
        LOGGER.log(Level.INFO, "Created Calculator in class {0}", Main.class.getName());
        try {
            calculator.calculate(inputFile);
        }
        catch (PropertyLoadException ex){
            LOGGER.log(Level.SEVERE, "Can't find the property file of commands, " + ex.getMessage());
            return;
        }
        LOGGER.log(Level.INFO, "Calculator has ended the calculation");
    }

    private static InputStream createReader(String[] args) throws IOException{
        InputStream inputFile;
        if (args.length == 0){
                inputFile = System.in;
        }
        else {
            inputFile = new FileInputStream("src/main/resources/" + args[0]);
        }
        return inputFile;
    }
}
