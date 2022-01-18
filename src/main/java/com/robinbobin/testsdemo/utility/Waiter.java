package com.robinbobin.testsdemo.utility;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicate;
import com.robinbobin.testsdemo.http.request.GetPersonQueueStatus;
import com.robinbobin.testsdemo.model.QueueStatus;
import com.robinbobin.testsdemo.http.response.GetQueueStatusResponse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class Waiter {

    public static GetQueueStatusResponse waitForQueueToProcess(String queueId) {
        Callable<GetQueueStatusResponse> getQueueStatus = () -> GetPersonQueueStatus.send(queueId)
                .deserializeBody(GetQueueStatusResponse.class);
        Predicate<GetQueueStatusResponse> checkStatus = (response) ->
                !response.getStatus().equals(QueueStatus.SUCCESS.getName());

        return wait(getQueueStatus, checkStatus);
    }

    public static <T> T wait(Callable<T> call, Predicate<T> retryIf) {
        try {
            return RetryerBuilder.<T>newBuilder()
                    .retryIfException()
                    .retryIfResult(retryIf)
                    .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                    .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                    .build()
                    .call(call);
        } catch (ExecutionException | RetryException e) {
            throw new RuntimeException("Waiting for result failed", e);
        }
    }

}
