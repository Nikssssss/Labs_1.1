package calculator.factoryExceptions;

public class UnknownCommandException extends FactoryException{
    public UnknownCommandException(){
        super();
    }

    public UnknownCommandException(String message){
        super(message);
    }

    public UnknownCommandException(Throwable cause){
        super(cause);
    }

    public UnknownCommandException(String message, Throwable cause){
        super(message, cause);
    }
}
