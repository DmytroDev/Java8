package com.dataart.predicate;

import com.dataart.core.constants.Data;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WorkerPredicatesTest {

    @Test
    public void testIsAdult() {
        Assert.assertTrue(WorkerPredicates.filterWorkers(Data.WORKER_LIST_0, WorkerPredicates.isAdult()).size() == 4);
    }

    @Test
    public void testIsAgeMoreThan() {
        Assert.assertTrue(WorkerPredicates.filterWorkers(Data.WORKER_LIST_0, WorkerPredicates.isAgeMoreThan(33)).size() == 1);
        Assert.assertTrue(WorkerPredicates.filterWorkers(Data.WORKER_LIST_0, WorkerPredicates.isAgeMoreThan(20)).size() == 4);
        Assert.assertTrue(WorkerPredicates.filterWorkers(Data.WORKER_LIST_0, WorkerPredicates.isAgeMoreThan(25)).size() == 2);
    }

    @Test
    public void testIsAgeMoreThanAndProfession() {
        List<Worker> workerListRes = WorkerPredicates.filterWorkers(Data.WORKER_LIST_0, WorkerPredicates.isAgeMoreThanAndProfession(30, Profession.PM));
        Assert.assertTrue(workerListRes.size() == 1);
        Assert.assertTrue(workerListRes.get(0).getFirstName().equals("Ivan"));
        Assert.assertTrue(workerListRes.get(0).getAge() == 35);
    }

    @Test
    public void testIsAgeMoreThanOrProfession() {
        List<Worker> workerListRes = WorkerPredicates.filterWorkers(Data.WORKER_LIST_0, WorkerPredicates.isAgeMoreThanOrProfession(30, Profession.QA));
        Assert.assertTrue(workerListRes.size() == 3);
    }

}