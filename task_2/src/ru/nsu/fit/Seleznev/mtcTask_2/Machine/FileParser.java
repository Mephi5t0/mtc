package ru.nsu.fit.Seleznev.mtcTask_2.Machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
    private File file;

    public FileParser(String fileName) {
        file = new File(fileName);
    }

    public String parse() {
        StringBuilder builder = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
