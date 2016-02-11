package main;

import java.io.IOException;
import java.io.Reader;

import static java.lang.Character.isDigit;

public class Lexer {
    private final Reader reader;
    private int data;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        data = reader.read();
    }

    public Lexeme getLexeme() throws IOException {
        char dataChar = (char) data;
        if (dataChar == ' ') {
            while (dataChar == ' ' && data != -1) {
                data = reader.read();
                dataChar = (char) data;
            }
        }
        if (data == -1) {
            data = reader.read();
            return new Lexeme(String.valueOf(dataChar), LexemeType.EOF);
        }
        switch (dataChar) {
            case '+':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.PLUS);
            case '-':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.MINUS);
            case '*':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.MULTIPLY);
            case '/':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.DIVIDE);
            case '(':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.OPENING_PARENTHESIS);
            case ')':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.CLOSING_PARENTHESIS);
            case '^':
                data = reader.read();
                return new Lexeme(String.valueOf(dataChar), LexemeType.POWER);
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                String number = "";
                while (isDigit(dataChar)) {
                    number += dataChar;
                    data = reader.read();
                    dataChar = (char) data;
                }
                return new Lexeme(number, LexemeType.NUMBER);
            default:
                throw new IllegalArgumentException("unexpected symbol : <" + dataChar + ">");
        }
    }
}
