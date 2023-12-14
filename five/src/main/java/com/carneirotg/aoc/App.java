package com.carneirotg.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class App 
{
    public Long solution(String input) {

        Almanac almanac = getPopulateMaps(input);

        List<Long> locations = new ArrayList<>();

        for (String seedStr : almanac.seeds()) {
            //seed to soil find source
            populateLocations(almanac.seedToSoilMap(), almanac.soilToFertilizer(), almanac.fertilizerToWater(), almanac.waterToLight(), almanac.lightToTemperature(),
                almanac.temperatureToHumidity(), almanac.humidityToLocation(), locations, Long.parseLong(seedStr));
        }

        return locations.stream().sorted().findFirst().get();
    }

    private void populateLocations(List<List<Long>> seedToSoilMap, List<List<Long>> soilToFertilizer, List<List<Long>> fertilizerToWater, List<List<Long>> waterToLight,
                                   List<List<Long>> lightToTemperature, List<List<Long>> temperatureToHumidity, List<List<Long>> humidityToLocation, List<Long> locations, Long seed) {
        Long destSeed = 0L;
        destSeed = findDestination(seedToSoilMap, seed);
        destSeed = findDestination(soilToFertilizer, destSeed);
        destSeed = findDestination(fertilizerToWater, destSeed);
        destSeed = findDestination(waterToLight, destSeed);
        destSeed = findDestination(lightToTemperature, destSeed);
        destSeed = findDestination(temperatureToHumidity, destSeed);
        destSeed = findDestination(humidityToLocation, destSeed);

        if (!locations.isEmpty()) {
            var location = locations.get(0);
            if (destSeed < location) {
                locations.add(destSeed);
            }
        } else {
            locations.add(destSeed);
        }
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

    public Long solution2(String input) {
        Almanac almanac = getPopulateMaps(input);

        List<Long> locations = new ArrayList<>();

        var seeds = almanac.seeds();
        for (int i = 0; i < seeds.length; i +=2) {
            var start = Long.parseLong(seeds[i]);
            var end = Long.parseLong(seeds[i]) + Long.parseLong(seeds[i+1]);
            LongStream.rangeClosed(start, end).forEach(
                seed -> {
                    populateLocations(almanac.seedToSoilMap(), almanac.soilToFertilizer(), almanac.fertilizerToWater(), almanac.waterToLight(), almanac.lightToTemperature(),
                        almanac.temperatureToHumidity(), almanac.humidityToLocation(), locations, seed);
            });
        }

        return locations.stream().sorted().findFirst().get();
    }

    private Almanac getPopulateMaps(String input) {
        var lines = input.split("\\n\\n");

        var seeds = lines[0].split(":")[1].trim().split(" ");
        var seedToSoilMap = parseToMap(lines[1]);
        var soilToFertilizer = parseToMap(lines[2]);
        var fertilizerToWater = parseToMap(lines[3]);
        var waterToLight = parseToMap(lines[4]);
        var lightToTemperature = parseToMap(lines[5]);
        var temperatureToHumidity = parseToMap(lines[6]);
        var humidityToLocation = parseToMap(lines[7]);
        return new Almanac(seeds, seedToSoilMap, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);
    }

    private record Almanac(String[] seeds, List<List<Long>> seedToSoilMap, List<List<Long>> soilToFertilizer, List<List<Long>> fertilizerToWater, List<List<Long>> waterToLight, List<List<Long>> lightToTemperature, List<List<Long>> temperatureToHumidity, List<List<Long>> humidityToLocation) {
    }

}
