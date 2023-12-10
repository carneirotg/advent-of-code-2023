package com.carneirotg.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        var lines = Files.readAllLines(Paths.get("src/test/resources/input-small.txt"));
        Assertions.assertEquals(925, testObject.solution(lines));
    }

    @Test
    void solutionFullInput() throws IOException {
        var lines = Files.readAllLines(Paths.get("src/test/resources/input.txt"));
        System.out.println(testObject.solution(lines));

    }

    @Test
    void solution2SmallInput() throws IOException {
        var lines = Files.readAllLines(Paths.get("src/test/resources/input-small.txt"));
        Assertions.assertEquals(6756, testObject.solution2(lines));
    }

    @Test
    void solution2FullInput() throws IOException {
        var lines = Files.readAllLines(Paths.get("src/test/resources/input.txt"));
        System.out.println(testObject.solution2(lines));

    }
}
