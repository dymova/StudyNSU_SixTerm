package main;

public class Lexeme {
    private final String lexemeStr;
    private final LexemeType type;


    public Lexeme(String lexeme, LexemeType type) {
        this.lexemeStr = lexeme;
        this.type = type;
    }

    public String getLexemeStr() {
        return lexemeStr;
    }

    public LexemeType getType() {
        return type;
    }
}
