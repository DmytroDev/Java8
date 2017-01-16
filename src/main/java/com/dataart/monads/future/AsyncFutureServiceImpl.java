package com.dataart.monads.future;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AsyncFutureServiceImpl {


    public static CompletableFuture<Integer> handleString(CompletableFuture<String> future) {
        return future.thenApply(s -> 2 * Integer.parseInt(s));
    }

    public static Integer handleException(CompletableFuture<String> future) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = future.handle((r, ex) -> {
            if (r != null) {
                return Integer.parseInt(r);
            } else {
                ex.printStackTrace();
                return -1;
            }
        });
        return result.get();
    }
    // Try to use it thenApply(...) with exceptionally(...)

    public static CompletableFuture<List<Worker>> handleFutures(CompletableFuture<Company> companyCompletableFuture, CompletableFuture<Profession> professionCompletableFuture) throws ExecutionException, InterruptedException {

        CompletableFuture<List<Worker>> workers =
                CompletableFuture.supplyAsync(() -> getWorkers(companyCompletableFuture))
                        .thenApply(result -> result
                                .stream()
                                .filter(w -> w.getProfession().equals(getProfession(professionCompletableFuture)))
                                .collect(Collectors.toList()));
        return workers;
    }
    // Look at thenCombine(...) method. Try to use it here.
    // And two last methods are redundant;
    // if you want to handle the case where company doesn't have workers -
    // ...getWorkers().orElseGet(Collections::emptyList)...

    private static List<Worker> getWorkers(CompletableFuture<Company> companyCompletableFuture) {
        List<Worker> workers = null;
        try {
            workers = companyCompletableFuture.get().getWorkers().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return workers;
    }

    private static Profession getProfession(CompletableFuture<Profession> professionCompletableFuture) {
        Profession profession = null;
        try {
            profession = professionCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return profession;
    }
}
