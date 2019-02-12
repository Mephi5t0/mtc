package ru.nsu.fit.Seleznev.mtcProject.Lexeme;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Seleznev.mtcProject.Exceptions.ParseException;

import java.io.IOException;

class LexerTest {

    @Test
    void getNextLexeme() throws IOException, ParseException {
        Lexer lexer = new Lexer("10 +12*7 - 90 ^  (   )");

        assert lexer.getNextLexeme().equals(new Lexeme("10", LexemeType.NUMBER));
        assert lexer.getNextLexeme().equals(new Lexeme("+", LexemeType.PLUS));
        assert lexer.getNextLexeme().equals(new Lexeme("12", LexemeType.NUMBER));
        assert lexer.getNextLexeme().equals(new Lexeme("*", LexemeType.MULT));
        assert lexer.getNextLexeme().equals(new Lexeme("7", LexemeType.NUMBER));
        assert lexer.getNextLexeme().equals(new Lexeme("-", LexemeType.MINUS));
        assert lexer.getNextLexeme().equals(new Lexeme("90", LexemeType.NUMBER));
        assert lexer.getNextLexeme().equals(new Lexeme("^", LexemeType.POW));
        assert lexer.getNextLexeme().equals(new Lexeme("(", LexemeType.OPEN_BRACKET));
        assert lexer.getNextLexeme().equals(new Lexeme(")", LexemeType.CLOSE_BRACKET));
        assert lexer.getNextLexeme().equals(new Lexeme("EOF", LexemeType.EOF));
    }
}