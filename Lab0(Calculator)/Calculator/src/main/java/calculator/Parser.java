package calculator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
    private Queue<String[]> commands = new LinkedList<>();
    Scanner scanner;
    String inputFileName;

    public Parser(String inputFile){
        inputFileName = inputFile;
    }

    public void createReader() throws IOException{
        if (inputFileName.isEmpty()){
            scanner = new Scanner(System.in);
        }
        else{
            try {
                scanner = new Scanner(Paths.get("src/main/resources/" + inputFileName));
            }
            catch (IOException e){
                throw new IOException("Can't open inputFile");
            }
        }
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
