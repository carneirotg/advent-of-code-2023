package com.carneirotg.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DayOneTest {

    DayOne testObject = new DayOne();

    @Test
    void sumFIrstTwoDigits() throws IOException {
        String file ="src/test/resources/input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> input = new ArrayList<>();
        while(reader.ready()) {
            input.add(reader.readLine());
        }

//        Assertions.assertEquals(287, testObject.sumFIrstTwoDigits(input));
        System.out.println(testObject.sumFIrstTwoDigits(input));

        reader.close();
    }

    @Test
    void solution2() throws IOException {
        String file ="src/test/resources/input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> input = new ArrayList<>();
        while(reader.ready()) {
            input.add(reader.readLine().trim());
        }

//        Assertions.assertEquals(281, testObject.solution2(input));
        System.out.println(testObject.solution2(input));

        reader.close();
    }
}