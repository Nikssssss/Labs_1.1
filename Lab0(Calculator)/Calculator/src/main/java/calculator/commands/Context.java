package calculator.commands;

import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Stack<Double> stackOfNumbers = new Stack<>();
    private HashMap<String, Double> variables = new HashMap<>();

    void pushNumber(Double number){
        stackOfNumbers.push(number);
    }

    void pushVariable(String variable, Double value){
        variables.put(variable, value);
    }

    Double getVariableValue(String variable){
        return variables.get(variable);
    }

    Double getNumber(){ return stackOfNumbers.pop(); }

    Double peekNumber(){
        return stackOfNumbers.peek();
    }

    Integer getStackSize(){
        return stackOfNumbers.size();
    }
}
