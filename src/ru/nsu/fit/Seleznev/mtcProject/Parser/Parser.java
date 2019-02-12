package ru.nsu.fit.Seleznev.mtcProject.Parser;

import ru.nsu.fit.Seleznev.mtcProject.Exceptions.ParseException;
import ru.nsu.fit.Seleznev.mtcProject.Lexeme.Lexeme;
import ru.nsu.fit.Seleznev.mtcProject.Lexeme.Lexer;
import ru.nsu.fit.Seleznev.mtcProject.Lexeme.LexemeType;

import java.io.IOException;

public class Parser {
    private Lexeme currentLexeme;
    private Lexer lexer;

    public Parser(String data) throws IOException, ParseException {
        lexer = new Lexer(data);
        currentLexeme = lexer.getNextLexeme();
    }

    public long calculate() throws IOException, ParseException {
        long value = parseExpression();

        if (currentLexeme.getType() != LexemeType.EOF) {
            throw new ParseException("Unclosed resource");
        }

        return value;
    }

    public long parseExpression() throws IOException, ParseException {
        long value = parseTerm();

        while (currentLexeme.getType() == LexemeType.PLUS || currentLexeme.getType() == LexemeType.MINUS) {
            LexemeType type = currentLexeme.getType();
            currentLexeme = lexer.getNextLexeme();

            if (type == LexemeType.PLUS) {
                value += parseTerm();
            }
            else {
                value -= parseTerm();
            }
        }

        return value;
    }

    public long parseTerm() throws IOException, ParseException {
        long value = parseFactor();

        while (currentLexeme.getType() == LexemeType.MULT || currentLexeme.getType() == LexemeType.DIV) {
            LexemeType type = currentLexeme.getType();
            currentLexeme = lexer.getNextLexeme();

            if (type == LexemeType.MULT) {
                value *= parseFactor();
            }
            else {
                value /= parseFactor();
            }
        }

        return value;
    }

    public long parseFactor() throws IOException, ParseException {
        long value = parsePower();

        if (currentLexeme.getType() == LexemeType.POW) {
            currentLexeme = lexer.getNextLexeme();
            value = (long) Math.pow(value, parseFactor());
        }

        return value;
    }

    public long parsePower() throws IOException, ParseException {
        if (currentLexeme.getType() == LexemeType.MINUS) {
            currentLexeme = lexer.getNextLexeme();
            return -parseAtom();
        }
        else {
            return parseAtom();
        }
    }

    public long parseAtom() throws IOException, ParseException {
        long value;

        switch (currentLexeme.getType()) {
            case NUMBER:
                value = Long.parseLong(currentLexeme.getValue());
                currentLexeme = lexer.getNextLexeme();
                break;
            case OPEN_BRACKET:
                currentLexeme = lexer.getNextLexeme();
                value = parseExpression();

                if (currentLexeme.getType() != LexemeType.CLOSE_BRACKET) {
                    throw new ParseException("Incorrect input: unclosed brackets");
                }
                currentLexeme = lexer.getNextLexeme();
                break;
            default:
                throw new ParseException("Incorrect input");
        }

        return value;
    }
}
