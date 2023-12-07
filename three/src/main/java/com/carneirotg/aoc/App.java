package com.carneirotg.aoc;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public Integer solution(List<String> input, int numLines, int numColumns) {

        char[][] matrix = new char[numLines][numColumns];
        for( int j = 0; j < input.size(); j ++) {
            var line = input.get(j).toCharArray();
            for (int i = 0; i < line.length; i++) {
                matrix[j][i] = line[i];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var element = matrix[i][j];
                if (!Character.isDigit(element) && element != '.') {
                    findNeighborDigits(matrix, i, j);
                }
            }
        }

        return 0;
    }

    private void findNeighborDigits(char[][] matrix, int row, int col) {

    }
}
