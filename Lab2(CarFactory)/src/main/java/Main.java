import factory.Factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/loggerConfig.properties"));
        }
        catch (IOException ex){
            LOGGER.log(Level.WARNING, "Can't open logger configuration");
        }
        Factory factory;
        try {
            factory = new Factory();
            LOGGER.log(Level.INFO, "Factory has been created");
        }
        catch (IOException ex){
            LOGGER.log(Level.SEVERE, "Can't open factory configuration");
            return;
        }
        Scanner checkEnd = new Scanner(System.in);
        LOGGER.log(Level.INFO, "Factory has begun the working process");
        factory.beginWork();
        if (checkEnd.nextLine().equals("end")) {
            LOGGER.log(Level.INFO, "Factory is beginning to end");
            factory.endWork();
            LOGGER.log(Level.INFO, "Factory has ended the working process");
        }
    }
}
