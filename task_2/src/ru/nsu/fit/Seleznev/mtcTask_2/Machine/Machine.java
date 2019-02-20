package ru.nsu.fit.Seleznev.mtcTask_2.Machine;

import ru.nsu.fit.Seleznev.mtcTask_2.Exceptions.MachineException;
import ru.nsu.fit.Seleznev.mtcTask_2.Exceptions.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Machine {
    private File file;
    private List<String> endStates;
    private String currentState;
    private Hashtable<Pair, String> machine;

    public Machine(String fileName) throws ParseException, MachineException {
        file = new File(fileName);
        endStates = new LinkedList<>();
        currentState = "0";
        setMachine();
        checkMachineValidity();
    }

    private void setMachine() throws ParseException {
        Hashtable<Pair, String> dsm = new Hashtable<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (!line.contains(" ")) {
                    endStates.add(line);
                } else {
                    String elements[] = line.split("\\s+");
                    if (elements.length != 3) {
                        throw new ParseException("File is corrupt");
                    }
                    dsm.put(new Pair(elements[0], elements[1]), elements[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        machine = dsm;
    }

    private void checkMachineValidity() throws MachineException {
        Set<String> alphabet = new HashSet<>();
        Set<String> states = new HashSet<>();
        Set<Pair> keys = machine.keySet();

        for (Pair pair : keys) {
            states.add(pair.first());
            alphabet.add(pair.second());
        }

        if (keys.size() != states.size() * alphabet.size()) {
            throw new MachineException("Invalid machine");
        }
    }

    public boolean isStringRecognizable(String str) throws MachineException {
        for (char sym : str.toCharArray()) {
            Pair key = new Pair(currentState, String.valueOf(sym));
            if (!machine.containsKey(key)) {
                throw new MachineException("Machine was determined incorrectly");
            }
            currentState = machine.get(key);
        }

        return endStates.contains(currentState);
    }
}
