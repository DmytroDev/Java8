package com.dataart.monads.future;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AsyncFutureServiceImpl {


    public static CompletableFuture<Integer> handleString(CompletableFuture<String> future) {
        return future.thenApply(s -> 2 * Integer.parseInt(s));
    }

    public static Integer handleException(CompletableFuture<String> future) throws ExecutionException, InterruptedException {
        return future
                .thenApply(r -> Integer.parseInt(r))
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return -1;
                }).get();
    }
    // Try to use it thenApply(...) with exceptionally(...)

    public static CompletableFuture<List<Worker>> handleFutures(CompletableFuture<Company> companyCompletableFuture, CompletableFuture<Profession> professionCompletableFuture) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Worker>> workers =
                CompletableFuture.supplyAsync(() -> {
                    List<Worker> workerList = null;
                    try {
                        workerList = companyCompletableFuture.get().getWorkers().orElseGet(Collections::emptyList);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return workerList;
                });
        return workers.thenCombine(professionCompletableFuture,
                (worker, profession) -> worker.stream()
                        .filter(w -> w.getProfession().equals(profession))
                        .collect(Collectors.toList()));
    }
    // Look at thenCombine(...) method. Try to use it here.
    // And two last methods are redundant;
    // if you want to handle the case where company doesn't have workers -
    // ...getWorkers().orElseGet(Collections::emptyList)...
}
