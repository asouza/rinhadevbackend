package com.deveficiente.rinhadevbackend;

import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class TransactionRunner {
    
    @Transactional
    public void execute(Runnable runnable){
        runnable.run();
    }
}
