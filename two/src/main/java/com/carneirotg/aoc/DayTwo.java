package com.carneirotg.aoc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayTwo
{
    public static void main( String[] args ) throws IOException {

    }

    public Integer findAmountOfPossibleGames(List<String> input, Integer red, Integer green, Integer blue) {

        Map<Integer, List<Map<Integer, String>>> games = new HashMap<>();

        String RED = "red";
        String BLUE = "blue";
        String GREEN = "green";

        var result = 0;
        //parse
        for (String s : input) {
            var possible = false;
            String[] game = s.split(":");
            var gameIndex = game[0].trim().split(" ")[1];
            var gameList = game[1].split(";");

            for (int i = 0; i < gameList.length; i++) {
                Map<String, Boolean> colorMatch = new HashMap<>();
                var innerGame = gameList[i].split(",");
                for (int j = 0; j < innerGame.length; j ++) {
                    var colorNumber = innerGame[j].trim().split(" ")[0];
                    var colorName = innerGame[j].trim().split(" ")[1];

                    if (RED.equals(colorName.toLowerCase())) {
                        var status = red >= Integer.parseInt(colorNumber);
                        colorMatch.put(colorName, status);
                        if (!status) {
                            break;
                        }
                        continue;
                    }

                    if (BLUE.equals(colorName.toLowerCase())) {
                        var status = blue >= Integer.parseInt(colorNumber);
                        colorMatch.put(colorName, status);
                        if (!status) {
                            break;
                        }
                        continue;
                    }

                    if (GREEN.equals(colorName.toLowerCase())) {
                        var status = green >= Integer.parseInt(colorNumber);
                        colorMatch.put(colorName, status);
                        if (!status) {
                            break;
                        }
                    }
                }

                if (colorMatch.containsValue(false)) {
                    possible = false;
                    break;
                }
                possible = true;
            }

            if (possible) {
                result += Integer.parseInt(gameIndex);
            }
        }

        return result;
    }

    public Integer findMinimumProductOfNumbers(List<String> input) {
        var result = 0;

        for (String s : input) {
            String[] game = s.split(":");
            var gameIndex = game[0].trim().split(" ")[1];
            var gameList = game[1].split(";");

            Map<String, Integer> colorMatch = new HashMap<>();
            for (int i = 0; i < gameList.length; i++) {
                var innerGame = gameList[i].split(",");
                for (int j = 0; j < innerGame.length; j++) {
                    var colorNumber = innerGame[j].trim().split(" ")[0];
                    var colorName = innerGame[j].trim().split(" ")[1];

                    if (!colorMatch.containsKey(colorName)|| colorMatch.get(colorName) < Integer.parseInt(colorNumber)) {
                        colorMatch.put(colorName, Integer.parseInt(colorNumber));
                    }
                }
            }
            result += calculateProduct(colorMatch);

        }
        return result;
    }

    private Integer calculateProduct(Map<String, Integer> colorMatch) {
        var result = 1;
        for (Integer value : colorMatch.values()) {
            result *= value;
        }
        return result;
    }
}
