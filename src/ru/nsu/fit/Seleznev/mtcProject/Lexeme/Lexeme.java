package ru.nsu.fit.Seleznev.mtcProject.Lexeme;

import java.util.Objects;

public class Lexeme {
    private String value;
    private LexemeType type;

    public Lexeme(String value, LexemeType type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Lexeme)) {
            return false;
        }
        Lexeme objLexeme = (Lexeme) obj;

        return value.equals(objLexeme.value) && type.equals(objLexeme.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LexemeType getType() {
        return type;
    }

    public void setType(LexemeType type) {
        this.type = type;
    }
}
