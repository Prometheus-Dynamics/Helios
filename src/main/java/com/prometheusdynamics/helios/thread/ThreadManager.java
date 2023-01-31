package com.prometheusdynamics.helios.thread;

import java.util.HashMap;

import com.prometheusdynamics.helios.logging.Log;

public class ThreadManager {
    
    private static HashMap<String, Thread> threads;

    static{
        threads = new HashMap<>();
    }

    public static void run(Threadable run){
        String name = run.getClass().getSimpleName();
        Thread thread = new Thread(run, name);

        Log.System("Thread starting: "+ name);
        thread.start();
        threads.put(name, thread);
    }
}
