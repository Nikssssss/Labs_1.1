package calculator.calculatorExceptions;

public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException(){
        super();
    }

    public DivisionByZeroException(String message){
        super(message);
    }

    public DivisionByZeroException(Throwable cause){
        super(cause);
    }

    public DivisionByZeroException(String message, Throwable cause){
        super(message, cause);
    }
}
