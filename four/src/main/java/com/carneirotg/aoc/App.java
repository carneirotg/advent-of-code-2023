package com.carneirotg.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App 
{
    private static final String GAME = "game";
    public Integer solution(List<String> input) {
        var totalCount = 0;
//        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Map<Integer, Integer> gameCount = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            //get winners and my list
            String s = input.get(i);
            var games = s.split("\\|");
            //first part winners + game number
            Map<String, List<Integer>> left = getNumbers(Pattern.compile("\\d+").matcher(games[0]));
            Map<String, List<Integer>> right = getNumbers(Pattern.compile("\\d+").matcher(games[1]));

            totalCount += findMatchesRightToLeft(left, right);


        }
        return totalCount;
    }

    public Integer solution2(List<String> input) {
        var totalCount = 0;
//        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Map<Integer, Integer> gameCount = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            //get winners and my list
            String s = input.get(i);
            var games = s.split("\\|");
            //first part winners + game number
            Map<String, List<Integer>> left = getNumbers(Pattern.compile("\\d+").matcher(games[0]));
            Map<String, List<Integer>> right = getNumbers(Pattern.compile("\\d+").matcher(games[1]));

            var index = left.get(GAME).remove(0);
            gameCount.merge(index, 1, Integer::sum);

            findMatchesAndCopies(left, right, gameCount, index);

        }
        return gameCount.values().stream().reduce(0, Integer::sum);
    }

    private int findMatchesAndCopies(Map<String, List<Integer>> left, Map<String, List<Integer>> right, Map<Integer, Integer> gameCount, Integer cardNumber) {

        var winningNumbers = left.get(GAME);
        var count = (int) right.get(GAME).stream()
                .filter(winningNumbers::contains)
                .count();

        var current = gameCount.get(cardNumber);
        for (int i = 1; i <= count; i++) {
            gameCount.merge(cardNumber + i, current, Integer::sum);
        }

        return count;

    }

    private int findMatchesRightToLeft(Map<String, List<Integer>> left, Map<String, List<Integer>> right) {
        //removing game number
        left.get(GAME).remove(0);

        var winningNumbers = left.get(GAME);
        int count = (int) right.get(GAME).stream()
                .filter(winningNumbers::contains)
                .count();
        return (int) Math.pow(2, count - 1);
    }

    private Map<String,List<Integer>> getNumbers(Matcher matcher) {
        var cards = new ArrayList<Integer>();
        while (matcher.find()) {
            cards.add(Integer.parseInt(matcher.group()));
        }
        var result = new HashMap<String, List<Integer>>();
        result.put(GAME, cards);
        return result;
    }

}
