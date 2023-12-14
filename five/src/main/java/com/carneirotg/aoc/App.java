package com.carneirotg.aoc;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public Long solution(String input) {

        var lines = input.split("\\n\\n");

        var seeds = lines[0].split(":")[1].trim().split(" ");
        var seedToSoilMap = parseToMap(lines[1]);
        var soilToFertilizer = parseToMap(lines[2]);
        var fertilizerToWater = parseToMap(lines[3]);
        var waterToLight = parseToMap(lines[4]);
        var lightToTemperature = parseToMap(lines[5]);
        var temperatureToHumidity = parseToMap(lines[6]);
        var humidityToLocation = parseToMap(lines[7]);

        List<Long> locations = new ArrayList<>();
        //79, 14, 55, 13
        for (String seedStr : seeds) {
            //seed to soil find source
            var seed = Long.parseLong(seedStr);
            Long destSeed = 0L;
            destSeed = findDestination(seedToSoilMap, seed);
            destSeed = findDestination(soilToFertilizer, destSeed);
            destSeed = findDestination(fertilizerToWater, destSeed);
            destSeed = findDestination(waterToLight, destSeed);
            destSeed = findDestination(lightToTemperature, destSeed);
            destSeed = findDestination(temperatureToHumidity, destSeed);
            destSeed = findDestination(humidityToLocation, destSeed);

            locations.add(destSeed);
        }


        return locations.stream().sorted().findFirst().get();
    }

    private Long findDestination(List<List<Long>> searchMap, Long seed) {
        var destSeed = seed;
        for (int i = 0; i < searchMap.size(); i += 2) {
            var sourceStart = searchMap.get(i).get(0);
            var sourceFinish = searchMap.get(i).get(1);

            if (seed <= sourceFinish && seed >= sourceStart) {
                //is within the range let's search the destination
                var destStart = searchMap.get(i+1).get(0);
                var diffDestSourceStart = Math.abs(sourceStart - destStart);
                if (sourceStart > destStart) {
                    destSeed -= diffDestSourceStart;
                } else {
                    destSeed += diffDestSourceStart;
                }
                break;
            }
        }
        return destSeed;
    }

    private List<List<Long>> parseToMap(String line) {
        List<List<Long>> map = new ArrayList<>();
        var lines = line.split("\n");
        for (int i = 1; i < lines.length; i++) {
            var mapLine = lines[i].split(" ");
            map.add(List.of(Long.parseLong(mapLine[1]), Long.parseLong(mapLine[1]) + (Long.parseLong(mapLine[2]) - 1)));
            map.add(List.of(Long.parseLong(mapLine[0]), Long.parseLong(mapLine[0]) + (Long.parseLong(mapLine[2]) - 1)));
        }

        return map;
    }

    public Integer solution2(List<String> input) {
        return 0;
    }

}
