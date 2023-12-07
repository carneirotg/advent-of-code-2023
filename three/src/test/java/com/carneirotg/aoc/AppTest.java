package com.carneirotg.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    App testObject = new App();

    @Test
    void solutionSmallInput() throws IOException {
        String file ="src/test/resources/input-small.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> input = new ArrayList<>();
        var numLines = 0;
        var numColumns = 0;
        while(reader.ready()) {
            var line = reader.readLine();
            numLines += 1;
            numColumns = line.length();
            input.add(line);
        }
        Assertions.assertEquals(4361, testObject.solution(input, numLines, numColumns));

        reader.close();
    }
}
