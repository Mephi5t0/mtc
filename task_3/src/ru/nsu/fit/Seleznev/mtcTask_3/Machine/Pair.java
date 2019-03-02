package ru.nsu.fit.Seleznev.mtcTask_3.Machine;

import java.util.Objects;

public class Pair {
    private String state;
    private String transition;

    Pair(String state, String transition) {
        this.state = state;
        this.transition = transition;
    }

    String state() {
        return state;
    }

    String transition() {
        return transition;
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

        return state.equals(key.state) && transition.equals(key.transition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, transition);
    }

}
