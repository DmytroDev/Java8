package com.dataart.monads.optional;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonServiceImpl {

    public static List<Worker> getWorkersByCompanyAndProf(MapNpeProtection<String, Company> companyMap, String companyName, Profession profession) {
        Predicate<Worker> workerPredicate = w -> w.getProfession().equals(profession);
        return companyMap
                .find(companyName)
                .flatMap(Company::getWorkers)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(workerPredicate)
                .collect(Collectors.toList());
        }

    // Make it safer, please. Try to use advantage of MapNpeProtection - find() method
    // and combine it with Optional's map/flatMap to avoid NPE.

    // And optional improving:
}   // In addition you can handle the case when company doesn't have workers, adding orElseGet(Collections::emptyList)
    // or as an alternative - use .orElseThrow(() -> new RuntimeException("The %company% doesn't have workers"))
