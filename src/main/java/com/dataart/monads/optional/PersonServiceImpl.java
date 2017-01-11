package com.dataart.monads.optional;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonServiceImpl {

    public static List<Worker> getWorkersByCompanyAndProf(MapNpeProtection<String, Company> companyMap, String companyName, Profession profession) {
        Predicate<Worker> workerPredicate = w -> w.getProfession().equals(profession);
        return companyMap
                .get(companyName)
                .getWorkers()
                .get()
                .stream()
                .filter(workerPredicate)
                .collect(Collectors.toList());
        }
}
