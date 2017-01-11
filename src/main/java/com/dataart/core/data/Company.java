package com.dataart.core.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Company {

    private final String name;
    private Optional<List<Worker>> workers = Optional.of(new ArrayList<Worker>());

    public Company(String name, Optional<List<Worker>> workers) {
        this.name = name;
        this.workers = workers;
    }

    public String getName() {
        return name;
    }

    public Optional<List<Worker>> getWorkers() {
        return workers;
    }

    public void setWorkers(Optional<List<Worker>>  workers) {
        this.workers = workers;
    }
}
