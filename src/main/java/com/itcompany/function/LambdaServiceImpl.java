package com.itcompany.function;

import com.itcompany.core.data.Worker;
import com.itcompany.function.interfaces.Transform;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LambdaServiceImpl {

    public static String transformString(String data, Transform<String> t) {
        return t.transform(data);
    }

    public static void consumerInfo(Worker worker, Consumer<Worker> consumer) {
        consumer.accept(worker);
    }

    public static void sortString(List<String> data, Comparator<String> comparator) {
        data.sort(comparator);
    }
    // Or data.sort(comparator)

    public static void sortWorkers(List<Worker> data, Comparator<Worker> comparator) {
        //Collections.sort(data, comparator);
        data.sort(comparator);
    }

    public static Float calc(Float first, Float second, BiFunction<Float, Float, Float> bFunction) {
        return bFunction.apply(first, second);
    }
}
