package com.carneirotg.aoc;

import java.util.List;

public class DayOne {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public Integer sumFIrstTwoDigits(List<String> input) {

        var result = 0;

        for (String s : input) {
            StringBuilder lineResult = new StringBuilder();
            char[] charArray = s.toCharArray();
            for (char c : charArray) {
                if (Character.isDigit(c)) {
                    lineResult.append(c);
                    break;
                }
            }

            for (int j = charArray.length - 1; j > 0; j--) {
                if (Character.isDigit(charArray[j])) {
                    lineResult.append(charArray[j]);
                    break;
                }
            }

            if (lineResult.length() > 0) {
                result += Integer.parseInt(String.valueOf(lineResult.charAt(0))) * 10 + Integer.parseInt(String.valueOf(lineResult.charAt(lineResult.length()-1)));
            }
        }

        return result;
    }
}