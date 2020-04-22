package calculator.calculatorExceptions;

public class WrongArgumentsException extends CalculatorException{
    public WrongArgumentsException(){
        super();
    }

    public WrongArgumentsException(String message){
        super(message);
    }

    public WrongArgumentsException(Throwable cause){
        super(cause);
    }

    public WrongArgumentsException(String message, Throwable cause){
        super(message, cause);
    }
}
