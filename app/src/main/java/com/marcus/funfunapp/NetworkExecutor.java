package com.marcus.funfunapp;

import java.util.concurrent.Executor;

class NetworkExecutor implements Executor
{
    public void execute(Runnable r)
    {
        new Thread(r).start();
    }
}