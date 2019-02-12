package ru.nsu.fit.Seleznev.mtcProject.Lexeme;

import ru.nsu.fit.Seleznev.mtcProject.Exceptions.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Lexer {
    private int currentValue;
    private Reader reader;

    public Lexer(String data) throws IOException {
        reader = new StringReader(data);
        currentValue = reader.read();
    }

    public Lexeme getNextLexeme() throws IOException, ParseException {
        Lexeme lexeme;

        while (Character.isSpaceChar(currentValue)){
            currentValue = reader.read();
        }

        switch (currentValue) {
            case '+':
                lexeme = new Lexeme("+", LexemeType.PLUS);
                currentValue = reader.read();
                break;
            case '-':
                lexeme = new Lexeme("-", LexemeType.MINUS);
                currentValue = reader.read();
                break;
            case '*':
                lexeme = new Lexeme("*", LexemeType.MULT);
                currentValue = reader.read();
                break;
            case '/':
                lexeme = new Lexeme("/", LexemeType.DIV);
                currentValue = reader.read();
                break;
            case '^':
                lexeme = new Lexeme("^", LexemeType.POW);
                currentValue = reader.read();
                break;
            case '(':
                lexeme = new Lexeme("(", LexemeType.OPEN_BRACKET);
                currentValue = reader.read();
                break;
            case ')':
                lexeme = new Lexeme(")", LexemeType.CLOSE_BRACKET);
                currentValue = reader.read();
                break;
            case -1:
                lexeme = new Lexeme("EOF", LexemeType.EOF);
                currentValue = reader.read();
                break;
            default:
                if (!Character.isDigit(currentValue)) {
                    throw new ParseException("Incorrect char input");
                }

                StringBuilder builder = new StringBuilder();
                while (Character.isDigit(currentValue)) {
                    builder.append((char)currentValue);
                    currentValue = reader.read();
                }

                lexeme = new Lexeme(builder.toString(), LexemeType.NUMBER);
        }

        return lexeme;
    }
}
