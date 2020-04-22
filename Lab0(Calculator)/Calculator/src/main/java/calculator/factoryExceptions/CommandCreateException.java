package calculator.factoryExceptions;

public class CommandCreateException extends FactoryException{
    public CommandCreateException(){
        super();
    }

    public CommandCreateException(String message){
        super(message);
    }

    public CommandCreateException(Throwable cause){
        super(cause);
    }

    public CommandCreateException(String message, Throwable cause){
        super(message, cause);
    }
}
