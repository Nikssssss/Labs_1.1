package calculator.factoryExceptions;

public class PropertyLoadException extends FactoryException{
    public PropertyLoadException(){
        super();
    }

    public PropertyLoadException(String message){
        super(message);
    }

    public PropertyLoadException(Throwable cause){
        super(cause);
    }

    public PropertyLoadException(String message, Throwable cause){
        super(message, cause);
    }
}
