package tests;

import main.Lexer;
import main.Parser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParserTest {
    private static final double DELTA = 1e-15;

    private final int expected;
    private final String testData;

    public ParserTest(int expected, String testData) {
        this.expected = expected;
        this.testData = testData;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 12, "12" },
                { 12, "((12))" },
                { 2, "(1) + (1)"},
                { 15, "(12 + 3)" },
                { 16, "(12 + 3)+1" },
                { 17, "(1 +(12 + 3)+1)" },
                { 13, "12 + 2 - 1" },
                { 13, "(12 + 2 - 1)" },
                { 14, "12 + 2 - ((1)) + 1"},
                { 13, "12 + (2 - 1)"},

                { 7, "1+3*4/2" },
                { 8, "(4+3*4)/2" },

                { 8, "2^(1+2)" },
                { 10, "2^3+2" },
                { 256, "2^2^3" },

                { -5, "-5" },
                { 1, "3+-2" },
                { 0 , "1 - - 2 - 3"},

                {6, "12 / 2"},
                {36, "12 * 3"},
                {9, "12 * 3 / 4"},
                {15, "12 + (6 / 2)"},
                {6, "(12 + 6) / 3"},
                {25, "(2 + 3) ^ (4 - 4 / 2)"},
                {8, "(2 + 3) ^ ((4 - 4 / 2) / 2) + 3"}
        });
    }

    @Test
    public void testParseExprPusAndMinusRight() throws Exception {
        StringReader stringReader = new StringReader(testData);
        Parser parser = new Parser(new Lexer(stringReader));
        System.out.println("exp:" + expected);
        System.out.println("test:" + testData);
        double res = parser.parseExpr();
        System.out.println("actual:" + res);
        System.out.println();
        Assert.assertEquals(expected, res, DELTA);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseExprPusAndMinusError() throws IOException {
//        StringReader stringReader = new StringReader(")");
//        StringReader stringReader = new StringReader("()");
//        StringReader stringReader = new StringReader("((1)");
//        StringReader stringReader = new StringReader("(1+)");
        StringReader stringReader = new StringReader("-1+");
        Parser parser = new Parser(new Lexer(stringReader));
        parser.parseExpr();
    }
}
