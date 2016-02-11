package main;

import java.io.IOException;

import static java.lang.Math.pow;

public class Parser {
    private final Lexer lexer;
    private Lexeme currentLexeme;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        currentLexeme = lexer.getLexeme();
    }

    public double parseExpr() throws IOException {
        double res = parseTerm();
        while (currentLexeme.getType() == LexemeType.PLUS ||
                currentLexeme.getType() == LexemeType.MINUS) {
            switch (currentLexeme.getType()) {
                case PLUS:
                    currentLexeme = lexer.getLexeme();
                    res += parseTerm();
                    break;
                case MINUS:
                    currentLexeme = lexer.getLexeme();
                    res -= parseTerm();
                    break;
            }
        }
        return res;
    }

    private double parseTerm() throws IOException {
        double res = parseFactor();
        while (currentLexeme.getType() == LexemeType.MULTIPLY ||
                currentLexeme.getType() == LexemeType.DIVIDE) {
            switch (currentLexeme.getType()) {
                case MULTIPLY:
                    currentLexeme = lexer.getLexeme();
                    res *= parseFactor();
                    break;
                case DIVIDE:
                    currentLexeme = lexer.getLexeme();
                    res /= parseFactor();
                    break;
            }
        }
        return res;
    }

    private double parseFactor() throws IOException {
        double res = parsePower();
        if(currentLexeme.getType() == LexemeType.POWER) {
            currentLexeme = lexer.getLexeme();
            res = pow(res, parseFactor());
        }
        return res;
    }

    private double parsePower() throws IOException {
        double res;
        if(currentLexeme.getType() == LexemeType.MINUS) {
            currentLexeme = lexer.getLexeme();
            res = -parseAtom();
        } else {
            res = parseAtom();
        }
        return res;
    }

    private double parseAtom() throws IOException {
        double res;
        switch (currentLexeme.getType()) {
            case OPENING_PARENTHESIS:
                currentLexeme = lexer.getLexeme();
                res = parseExpr();
                if (currentLexeme.getType() != LexemeType.CLOSING_PARENTHESIS) {
                    throw new IllegalArgumentException("expected ) , actual " + currentLexeme.getLexemeStr());
                }
                break;
            case NUMBER:
                res = Integer.parseInt(currentLexeme.getLexemeStr());
                break;
            default:
                throw new IllegalArgumentException("expected ( or NUMBER, actual " + currentLexeme.getLexemeStr());
        }
        currentLexeme = lexer.getLexeme();
        return res;
    }


}
