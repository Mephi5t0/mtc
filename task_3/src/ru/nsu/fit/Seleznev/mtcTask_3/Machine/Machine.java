package ru.nsu.fit.Seleznev.mtcTask_3.Machine;

import ru.nsu.fit.Seleznev.mtcTask_3.Exceptions.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.String.valueOf;

public class Machine {
    private File file;
    private List<String> endStates;
    private Hashtable<Pair, List<String>> machine;

    public Machine(String fileName) throws ParseException {
        file = new File(fileName);
        endStates = new LinkedList<>();
        machine = new Hashtable<>();
        setMachine();
    }

    private void setMachine() throws ParseException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (!line.contains(" ")) {
                    endStates.add(line);
                } else {
                    String elements[] = line.split("\\s+");
                    if (elements.length != 3) {
                        throw new ParseException("File is corrupt");
                    }
                    Pair pair = new Pair(elements[0], elements[1]);
                    if (machine.containsKey(pair)) {
                        machine.get(pair).add(elements[2]);
                    }
                    else {
                        machine.put(pair, new LinkedList<>(Arrays.asList(elements[2])));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isStringRecognizable(String str) {
        List<String> currentStates = new LinkedList<>();
        List<String> nextStates = new LinkedList<>();

        char[] tape = str.toCharArray();

        currentStates.add("0");
        for (int i = 0; i < tape.length; ++i) {
            currentStates.addAll(nextStates);
            nextStates.clear();
            for (String currentState : currentStates) {
                Pair pair = new Pair(currentState, valueOf(tape[i]));
                if (machine.containsKey(pair)) {
                    nextStates.addAll(machine.get(pair));
                }
            }
            currentStates.clear();
            if (i == tape.length - 1) {
                for (String nextState : nextStates) {
                    if (endStates.contains(nextState)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
