package com.dataart.predicate;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class WorkerPredicates {

    public static Predicate<Worker> isAdult() {
        return p -> p.getAge() > 21;
    }

    public static Predicate<Worker> isAgeMoreThan(int age) {
        return p -> p.getAge() > age;
    }

    public static Predicate<Worker> isAgeMoreThanAndProfession(int age, Profession profession) {
        Predicate<Worker> isAgeMoreThan = p -> p.getAge() > age; // Just a note: you've already had isAgeMoreThan predicate
        Predicate<Worker> isProfession = p -> p.getProfession().equals(profession);
        return isAgeMoreThan.and(isProfession);
    }

    public static Predicate<Worker> isAgeMoreThanOrProfession(int age, Profession profession) {
        Predicate<Worker> isAgeMoreThan = p -> p.getAge() > age;
        Predicate<Worker> isProfession = p -> p.getProfession().equals(profession);
        return isAgeMoreThan.or(isProfession);
    }

    public static List<Worker> filterWorkers(List<Worker> workerList, Predicate<Worker> predicate) {
        return workerList.stream().filter(predicate).collect(Collectors.toList());
    }
}
