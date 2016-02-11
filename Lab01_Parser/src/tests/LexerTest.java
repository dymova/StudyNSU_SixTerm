package tests;

import java.io.IOException;
import java.io.StringReader;

import main.Lexeme;
import main.LexemeType;
import main.Lexer;
import org.junit.Assert;

public class LexerTest {

    @org.junit.Test
    public void testGetLexeme() throws Exception {
        StringReader stringReader = new StringReader("  +-*/ ^ ( ) 123");
        Lexer lexer = new Lexer(stringReader);

        Lexeme lexeme = lexer.getLexeme();
        Assert.assertEquals("+", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.PLUS, lexeme.getType());

        lexeme =  lexer.getLexeme();
        Assert.assertEquals("-", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.MINUS, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals("*", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.MULTIPLY, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals("/", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.DIVIDE, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals("^", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.POWER, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals("(", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.OPENING_PARENTHESIS, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals(")", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.CLOSING_PARENTHESIS, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals("123", lexeme.getLexemeStr());
        Assert.assertEquals( LexemeType.NUMBER, lexeme.getType());

        lexeme = lexer.getLexeme();
        Assert.assertEquals( LexemeType.EOF, lexeme.getType());

    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testUnexpectedSymbol() throws IOException {
        StringReader stringReader = new StringReader("%");
        Lexer lexer = new Lexer(stringReader);

        Lexeme lexeme = lexer.getLexeme();
    }
}