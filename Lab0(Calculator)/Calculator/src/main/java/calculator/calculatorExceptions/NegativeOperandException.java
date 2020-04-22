package calculator.calculatorExceptions;

public class NegativeOperandException extends CalculatorException {
    public NegativeOperandException(){
        super();
    }

    public NegativeOperandException(String message){
        super(message);
    }

    public NegativeOperandException(Throwable cause){
        super(cause);
    }

    public NegativeOperandException(String message, Throwable cause){
        super(message, cause);
    }
}
