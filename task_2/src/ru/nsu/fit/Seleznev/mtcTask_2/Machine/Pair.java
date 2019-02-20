package ru.nsu.fit.Seleznev.mtcTask_2.Machine;

import java.util.Objects;

public class Pair {

    private String x;
    private String y;

    Pair(String x, String y) {
        this.x = x;
        this.y = y;
    }

    String first() {
        return x;
    }

    String second() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair key = (Pair) o;

        return x.equals(key.x) && y.equals(key.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
