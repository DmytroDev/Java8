package com.dataart.streams;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamServiceImpl {

    public static Long wordsCounter(List<String> words, String word) {
        return words
                .stream()
                .filter(i -> i.contains(word))
                .count();
    }

    public static Integer getSum(int... i) {
        IntStream stream = IntStream.of(i);
        return stream
                .sum();
    }

    public static Long getCountWordsLongestThan(List<String> words, int count) {
        Predicate<String> longestThan = p -> p.length() > count;
        return words
                .stream()
                .filter(longestThan)
                .count();
    }

    public static Long getCountWordsStartWith(List<String> words, String startWith) {
        Predicate<String> startWithPredicate = p -> p.startsWith(startWith);
        return words
                .stream()
                .filter(startWithPredicate)
                .count();
    }

    public static String getFirstWordStartWith(List<String> words, String startWith) {
        Predicate<String> startWithPredicate = p -> p.startsWith(startWith);
        return words
                .stream()
                .filter(startWithPredicate)
                .findFirst()
                .orElse(null);
    }

    public static Double getAverageAge(List<Worker> workers) {
        return workers
                .stream()
                .mapToInt(Worker::getAge)
                .average()
                .getAsDouble();
    }

    public static List<String> capitalLetter(List<String> words) {
        Predicate<String> capitalLetterPredicate = p -> p.matches("\\b[A-Z]\\w*\\b");
        return words
                .stream()
                .filter(capitalLetterPredicate)
                .map(f -> f.substring(0))
                .collect(Collectors.toList());
    }

    public static List<String> concatenationAndToUpperCase(List<String> words1, List<String> words2) {
        return Stream
                .of(words1, words2)
                .flatMap(words -> words.stream())
                .map(w -> w.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<String> sortWordsByLength(List<String> words) {
        Comparator<String> stringComparator = Comparator.comparing(String::length);
        return  words
                .stream()
                .sorted(stringComparator)
                .collect(Collectors.toList());
    }

    public static List<String> excludeDuplicateAndAlphabeticalSort(List<String> words) {
        return words
                .parallelStream()
                .distinct()
                .sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.toList());
    }

    public static List<Worker> searchWorkers(List<Company> companies, Profession profession) {
        return companies
                .stream()
                .map(Company::getWorkers)
                .flatMap(workers -> workers.get().stream())
                .filter(worker -> worker.getProfession().equals(profession))
                .collect(Collectors.toList());
    }

}