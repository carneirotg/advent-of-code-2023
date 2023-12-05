package com.carneirotg.aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private enum Numbers {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE
    }

    public Integer solution2(List<String> input) {

        List<Numbers> numbers = List.of(Numbers.ONE, Numbers.TWO, Numbers.THREE, Numbers.FOUR, Numbers.FIVE, Numbers.SIX, Numbers.SEVEN, Numbers.EIGHT, Numbers.NINE);
        var result = 0;

        for (String s : input) {
            StringBuilder lineResult = new StringBuilder();
            Map<Integer, String> values = new HashMap<>();

            for (Numbers number : numbers) {
                var firstIndex = s.indexOf(number.name().toLowerCase());
                if (firstIndex != -1) {
                    values.put(firstIndex, String.valueOf(number.ordinal() + 1));
                }

                var lastIndex = s.lastIndexOf(number.name().toLowerCase());
                if (lastIndex != -1) {
                    values.put(lastIndex, String.valueOf(number.ordinal() + 1));
                }
            }

            char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i ++) {
                if (Character.isDigit(charArray[i])) {
                    values.put(i, String.valueOf(charArray[i]));
                    break;
                }
            }

            for (int j = charArray.length - 1; j > 0; j--) {
                if (Character.isDigit(charArray[j])) {
                    values.put(j, String.valueOf(charArray[j]));
                    break;
                }
            }

            result += getFinalNumber(values, s);
        }

        return result;
    }

    private Integer getFinalNumber(Map<Integer, String> values, String s) {

        var sortedIndex = values.keySet().stream().sorted().toList();
        var sum = Integer.parseInt(values.get(sortedIndex.get(0))) * 10 + Integer.parseInt(values.get(sortedIndex.get(sortedIndex.size()-1)));
        return sum;
    }
}