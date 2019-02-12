package ru.nsu.fit.Seleznev.mtcProject.Parser;

import ru.nsu.fit.Seleznev.mtcProject.Exceptions.ParseException;

import java.io.IOException;

class ParserTest {

    @org.junit.jupiter.api.Test
    void calculate() throws IOException, ParseException {
        assert new Parser("1 + 2 + 3 * 6 - 8").calculate() == 13;
        assert new Parser("   10 ").calculate() == 10;
        assert new Parser("12 + -12 + 2 ^ (1+1) / 4 - 7 * 1 ^ 10 + 6").calculate() == 0;
        assert new Parser("2 * (7 + 1) - 10").calculate() == 6;
        assert new Parser(" 12   + (2 - 3) * 4^2   - 7 * (  5 + 1)").calculate() == -46;
        assert new Parser("7 + 2^2^2 - 2^(1+1)").calculate() == 19;

        try {
            new Parser("(1 + 1 + 1) )").calculate();
            assert false;
        }
        catch (ParseException e) {
            assert true;
        }

        try {
            new Parser("(1 + 1 + 1").calculate();
            assert false;
        }
        catch (ParseException e) {
            assert true;
        }

        try {
            new Parser("(1 + 1 + 1 +)").calculate();
            assert false;
        }
        catch (ParseException e) {
            assert true;
        }
    }

    @org.junit.jupiter.api.Test
    void parseExpression() throws IOException, ParseException {
        assert new Parser("8 +5 /5 + 0").parseExpression() == 9;
    }

    @org.junit.jupiter.api.Test
    void parseTerm() throws IOException, ParseException {
        assert new Parser("10 * 6 * 2 / 3").parseTerm() == 40;
        assert new Parser("1*1*5  / 5").parseTerm() == 1;
    }

    @org.junit.jupiter.api.Test
    void parseFactor() throws IOException, ParseException {
        assert new Parser("2 ^ 2 ^ 2").parseFactor() == 16;

    }

    @org.junit.jupiter.api.Test
    void parsePower() throws IOException, ParseException {
        assert new Parser("-9").parsePower() == -9;
        assert new Parser("1024").parseTerm() == 1024;
    }

    @org.junit.jupiter.api.Test
    void parseAtom() throws IOException, ParseException {
        assert new Parser("(7 + 8)").parseAtom() == 15;
        assert new Parser("(12345)").parseAtom() == 12345;
        assert new Parser("15").parseAtom() == 15;

        try {
            new Parser("(9").parseAtom();
            assert false;
        }
        catch (ParseException e) {
            assert true;
        }
    }
}