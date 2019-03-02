package ru.nsu.fit.Seleznev.mtcTask_3;

import ru.nsu.fit.Seleznev.mtcTask_3.Exceptions.ParseException;
import ru.nsu.fit.Seleznev.mtcTask_3.Machine.FileParser;
import ru.nsu.fit.Seleznev.mtcTask_3.Machine.Machine;

public class Main {

    public static void main(String[] args) {
        try {

            Machine machine = new Machine(args[0]);
            FileParser parser = new FileParser(args[1]);

            String str = parser.parse();

            if (machine.isStringRecognizable(str)) {
                System.out.println("String was recognized");
            }
            else {
                System.out.println("Machine cannot recognize string");
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
