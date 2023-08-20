package com.deveficiente.rinhadevbackend;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncExecutor {
    
    @Async
    public <T> CompletableFuture<T> execute(Supplier<T> supplier){
        return CompletableFuture.supplyAsync(supplier);
    }
}
