package calculator;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
    private Queue<String[]> commands = new LinkedList<>();
    Scanner scanner;

    public Parser(InputStream inputFile){
        scanner = new Scanner(inputFile);
    }

    public void parseText(){
        String currentLine;
        while(scanner.hasNext()){
            currentLine = scanner.nextLine();
            if (!currentLine.isEmpty() && !currentLine.startsWith("#")){
                commands.offer(currentLine.split("\\s"));
            }
        }
    }

    public String[] getCommand(){
        return commands.poll();
    }

    public boolean isEmpty(){
        return commands.isEmpty();
    }
}
