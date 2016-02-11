package main;

import java.io.IOException;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws IOException {
        StringReader stringReader = new StringReader("*");

        Lexer lexer = new Lexer(stringReader);
        Parser parser = new Parser(lexer);
        double result = parser.parseExpr();
        System.out.printf("Result: " + result);

    }
}
