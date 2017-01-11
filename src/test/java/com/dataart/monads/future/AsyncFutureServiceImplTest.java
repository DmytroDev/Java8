package com.dataart.monads.future;

import com.dataart.core.constants.Data;
import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

public class AsyncFutureServiceImplTest {

    private static final CompletableFuture<String> FUTURE = CompletableFuture.supplyAsync(() -> {
        return "42";
    });

    private static final CompletableFuture<String> FUTURE_WAIT = CompletableFuture.supplyAsync(() -> {
        Data.sleep(1);
        return "42";
    });

    private static final CompletableFuture<String> FUTURE_EXCEPTION = CompletableFuture.supplyAsync(() -> {
        return String.valueOf(1 / 0);
    });


    private static final CompletableFuture<Profession> FUTURE_PROFESSION_DELAY = CompletableFuture.supplyAsync(() -> {
        Data.sleep(1);
        return Profession.PROGRAMMER;
    });


    private static final CompletableFuture<Company> FUTURE_COMPANY_DELAY_0 = CompletableFuture.supplyAsync(() -> {
        Data.sleep(2);
        return Data.COMPANY_LIST.get(0);
    });

    private static final CompletableFuture<Company> FUTURE_COMPANY_DELAY_1 = CompletableFuture.supplyAsync(() -> {
        Data.sleep(4);
        return Data.COMPANY_LIST.get(1);
    });

    @Test
    public void handleString() throws ExecutionException, InterruptedException {
        Assert.assertTrue(AsyncFutureServiceImpl.handleString(FUTURE).get() == 84);
        Assert.assertTrue(AsyncFutureServiceImpl.handleString(FUTURE_WAIT).get() == 84);
    }

    @Test
    public void testHandleException() throws ExecutionException, InterruptedException {
        Assert.assertTrue(AsyncFutureServiceImpl.handleException(FUTURE_EXCEPTION) == -1);
        Assert.assertTrue(AsyncFutureServiceImpl.handleException(FUTURE) == 42);
    }

    @Test
    public void testHandleFutures() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Worker>> first = AsyncFutureServiceImpl.handleFutures(FUTURE_COMPANY_DELAY_0, FUTURE_PROFESSION_DELAY);
        CompletableFuture.completedFuture("First has finished -> ").thenAcceptBoth(first, (company, worker) -> System.out.println(company + worker));

        CompletableFuture<List<Worker>> second = AsyncFutureServiceImpl.handleFutures(FUTURE_COMPANY_DELAY_1, FUTURE_PROFESSION_DELAY);
        CompletableFuture.completedFuture("Second has finished -> ").thenAcceptBoth(second, (company, worker) -> System.out.println(company + worker));


        Data.sleep(10);
        Assert.assertTrue(first.get().get(0).getFirstName().equals("Martin"));
        Assert.assertTrue(first.get().get(0).getProfession() == Profession.PROGRAMMER);

        Assert.assertTrue(second.get().get(0).getFirstName().equals("Linus"));
        Assert.assertTrue(second.get().get(0).getProfession() == Profession.PROGRAMMER);
    }

}