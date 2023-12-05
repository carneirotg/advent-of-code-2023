package com.carneirotg.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DayOneTest {

    DayTwo testObject = new DayTwo();

    @Test
    void test() throws IOException {
        String file ="src/test/resources/input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> input = new ArrayList<>();
        while(reader.ready()) {
            input.add(reader.readLine());
        }

//        Assertions.assertEquals(8, testObject.findAmountOfPossibleGames(input, 12, 13, 14));
        System.out.println(testObject.findAmountOfPossibleGames(input, 12, 13, 14));

        reader.close();


    }
}