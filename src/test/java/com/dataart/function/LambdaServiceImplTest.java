package com.dataart.function;

import com.dataart.core.constants.Data;
import com.dataart.core.data.Worker;
import com.dataart.function.interfaces.Transform;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static java.lang.System.out;

public class LambdaServiceImplTest {

    public static Consumer<Worker> CONSUMER_CONSOLE = x -> out.println("Firstname is: " + x.getFirstName());
    public static Consumer<Worker> CONSUMER_CONSOLE_ALL_INFO = x -> out.println(x);

    public static Transform<String> TO_UPPER_CASE = from -> from.toUpperCase();
    public static Transform<String> STR_DOUBLE = from -> from.concat(from);
    public static Transform<String> STR_DOUBLE_UPPER = from -> from.concat(from).toUpperCase();

    public static Comparator<String> COMPARE_STRINGS = (string1, string2) -> (string1.compareTo(string2));
    public static Comparator<Worker> COMPARE_WORKERS_BY_ID = Comparator.comparing(Worker::getId).thenComparing(Worker::getFirstName);
    public static Comparator<Worker> COMPARE_WORKERS_BY_FIRST_NAME = Comparator.comparing(Worker::getFirstName);
    public static Comparator<Worker> COMPARE_WORKERS_BY_AGE = (worker1, worker2) -> worker1.getAge() - worker2.getAge();

    public static BiFunction<Float, Float, Float> ADDITION = (a, b) -> (a + b);
    public static BiFunction<Float, Float, Float> DEDUCTION = (a, b) -> (a - b);
    public static BiFunction<Float, Float, Float> MULTIPLICATION = (a, b) -> (a * b);


    @Test
    public void testConsumerInfo() {
        Data.WORKER_LIST_0.forEach(x -> LambdaServiceImpl.consumerInfo(x, CONSUMER_CONSOLE));
        Data.WORKER_LIST_0.forEach(x -> LambdaServiceImpl.consumerInfo(x, CONSUMER_CONSOLE_ALL_INFO));
    }

    @Test
    public void testTransformString() {
        Assert.assertTrue(LambdaServiceImpl.transformString("test", TO_UPPER_CASE).equals("TEST"));
        Assert.assertTrue(LambdaServiceImpl.transformString("test", STR_DOUBLE).equals("testtest"));
        Assert.assertTrue(LambdaServiceImpl.transformString("test", STR_DOUBLE_UPPER).equals("TESTTEST"));
    }

    @Test
    public void testCompare() {
        Assert.assertTrue(LambdaServiceImpl.transformString("test", TO_UPPER_CASE).equals("TEST"));
        Assert.assertTrue(LambdaServiceImpl.transformString("test", STR_DOUBLE).equals("testtest"));
        Assert.assertTrue(LambdaServiceImpl.transformString("test", STR_DOUBLE_UPPER).equals("TESTTEST"));
    }

    @Test
    public void testSortString() {
        Data.STRING_LIST.forEach(out::println);
        LambdaServiceImpl.sortString(Data.STRING_LIST, COMPARE_STRINGS);
        Assert.assertTrue(Data.STRING_LIST.get(0).equals("Alphabet"));
        Assert.assertTrue(Data.STRING_LIST.get(1).equals("Apple"));
        Assert.assertTrue(Data.STRING_LIST.get(2).equals("Buss"));
        Assert.assertTrue(Data.STRING_LIST.get(3).equals("Data"));
        Assert.assertTrue(Data.STRING_LIST.get(4).equals("Sport"));
        Assert.assertTrue(Data.STRING_LIST.get(5).equals("Symphony"));
    }

    @Test
    public void testSortWorkers() {
        LambdaServiceImpl.sortWorkers(Data.WORKER_LIST_0, COMPARE_WORKERS_BY_ID);
        Assert.assertTrue(Data.WORKER_LIST_0.get(0).getId() == 1);
        Assert.assertTrue(Data.WORKER_LIST_0.get(1).getId() == 2);
        Assert.assertTrue(Data.WORKER_LIST_0.get(2).getId() == 3);
        Assert.assertTrue(Data.WORKER_LIST_0.get(3).getId() == 4);
        Assert.assertTrue(Data.WORKER_LIST_0.get(4).getId() == 5);
        Assert.assertTrue(Data.WORKER_LIST_0.get(5).getId() == 6);

        LambdaServiceImpl.sortWorkers(Data.WORKER_LIST_0, COMPARE_WORKERS_BY_FIRST_NAME);
        Assert.assertTrue(Data.WORKER_LIST_0.get(0).getFirstName().equals("Alex"));
        Assert.assertTrue(Data.WORKER_LIST_0.get(1).getFirstName().equals("Alexander"));
        Assert.assertTrue(Data.WORKER_LIST_0.get(2).getFirstName().equals("Ivan"));
        Assert.assertTrue(Data.WORKER_LIST_0.get(3).getFirstName().equals("Martin"));
        Assert.assertTrue(Data.WORKER_LIST_0.get(4).getFirstName().equals("Nikola"));
        Assert.assertTrue(Data.WORKER_LIST_0.get(5).getFirstName().equals("Petr"));

        LambdaServiceImpl.sortWorkers(Data.WORKER_LIST_0, COMPARE_WORKERS_BY_AGE);
        Assert.assertTrue(Data.WORKER_LIST_0.get(0).getAge() == 20);
        Assert.assertTrue(Data.WORKER_LIST_0.get(1).getAge() == 20);
        Assert.assertTrue(Data.WORKER_LIST_0.get(2).getAge() == 22);
        Assert.assertTrue(Data.WORKER_LIST_0.get(3).getAge() == 25);
        Assert.assertTrue(Data.WORKER_LIST_0.get(4).getAge() == 29);
        Assert.assertTrue(Data.WORKER_LIST_0.get(5).getAge() == 35);
    }

    @Test
    public void calc() {
        Assert.assertTrue(LambdaServiceImpl.calc(2F, 2F, ADDITION) == 4F);
        Assert.assertTrue(LambdaServiceImpl.calc(2F, 0.25F, DEDUCTION) == 1.75F);
        Assert.assertTrue(LambdaServiceImpl.calc(2F, 5F, MULTIPLICATION) == 10F);
    }

}