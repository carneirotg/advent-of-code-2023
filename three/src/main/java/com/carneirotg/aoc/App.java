package com.carneirotg.aoc;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public Integer solution(List<String> input) {

        Map<String, Integer> numbers = new HashMap<>();

        char[][] matrix = new char[input.size()][input.get(0).length()];
        for( int j = 0; j < input.size(); j ++) {
            var line = input.get(j).toCharArray();
            System.arraycopy(line, 0, matrix[j], 0, line.length);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var element = matrix[i][j];
                if (!Character.isLetterOrDigit(element) && element != '.') {
                   findNeighbourDigits(matrix, i, j, matrix[i].length, numbers, input.size());
                }
            }
        }

        return numbers.values().stream().reduce(0, Integer::sum);
    }

    public Integer solution2(List<String> input) {

        Map<String, Integer> numbers = new HashMap<>();
        var totalSum = 0;

        char[][] matrix = new char[input.size()][input.get(0).length()];
        for( int j = 0; j < input.size(); j ++) {
            var line = input.get(j).toCharArray();
            System.arraycopy(line, 0, matrix[j], 0, line.length);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var element = matrix[i][j];
                if (element == '*') {
                    totalSum += findNeighbourDigits(matrix, i, j, matrix[i].length, numbers, input.size());
                }
            }
        }
        return totalSum;
    }

    private Integer findNeighbourDigits(char[][] matrix, int row, int col, int length, Map<String, Integer> numbers, int size) {
        var element  = matrix[row][col];
        Map<String, Integer> surroundingNumberElement = new HashMap<>();

        try {
            if (row > 0 && col > 0 && Character.isDigit(matrix[row-1][col-1])) {
                findFullNumber(matrix, row-1, col-1, length, numbers, element, surroundingNumberElement);
            }

            if (row > 0 && Character.isDigit(matrix[row-1][col])) {
                findFullNumber(matrix, row-1, col, length, numbers, element, surroundingNumberElement);
            }

            if (row > 0 && col + 1 < length && Character.isDigit(matrix[row-1][col+1])) {
                findFullNumber(matrix, row-1, col+1, length, numbers, element, surroundingNumberElement);
            }

            if (row > 0 && Character.isDigit(matrix[row-1][col])) {
                findFullNumber(matrix, row-1, col, length, numbers, element, surroundingNumberElement);
            }

            if (col > 0 && Character.isDigit(matrix[row][col-1])) {
                findFullNumber(matrix, row, col-1, length, numbers, element, surroundingNumberElement);
            }

            if (col + 1 < length && Character.isDigit(matrix[row][col+1])) {
                findFullNumber(matrix, row, col+1, length, numbers, element, surroundingNumberElement);
            }

            if (row + 1 < size && col > 0 && Character.isDigit(matrix[row+1][col-1])) {
                findFullNumber(matrix, row+1, col-1, length, numbers, element, surroundingNumberElement);
            }

            if (row + 1 < size && Character.isDigit(matrix[row+1][col])) {
                findFullNumber(matrix, row+1, col, length, numbers, element, surroundingNumberElement);
            }

            if (row + 1 < size && col + 1 < length && Character.isDigit(matrix[row+1][col+1])) {
                findFullNumber(matrix, row+1, col+1, length, numbers, element, surroundingNumberElement);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }

        return surroundingNumberElement.values().size() == 2 ?
                surroundingNumberElement.values().stream().reduce(1, (subtotal, value) -> subtotal * value) : 0;

    }

    private void findFullNumber(char[][] matrix, int row, int col, int length, Map<String, Integer> numbers, char element, Map<String, Integer> surroundingNumberElement) {
        var fullNumber = new StringBuilder(String.valueOf(matrix[row][col]));
        var colIndex = col;
        //looking to the left
        for (int i = col - 1; i >= 0; i--) {
            if (Character.isDigit(matrix[row][i])) {
                fullNumber.insert(0, matrix[row][i]);
                colIndex = i;
            } else {
                break;
            }
        }

        //looking to the right
        for (int i = col + 1; i < length; i++) {
            if (Character.isDigit(matrix[row][i])) {
                fullNumber.append(matrix[row][i]);
            } else {
                break;
            }

        }

        //Map<line+specialCharacter+number>
        var key = String.valueOf(row) + colIndex + element + fullNumber;
        numbers.put(key, Integer.parseInt(fullNumber.toString()));
        surroundingNumberElement.put(key, Integer.parseInt(fullNumber.toString()));
    }

}
