package com.prometheusdynamics.helios.thread;

import java.util.HashMap;
import java.util.Map;

import com.prometheusdynamics.helios.logging.Log;

public class ThreadManager {
    
    private static HashMap<String, Threadable> threads;

    static{
        threads = new HashMap<>();
    }

    public static void run(Threadable run){
        String name = run.getClass().getSimpleName();
        Thread thread = new Thread(run, name);

        Log.System("Thread starting: "+ name);
        thread.start();
        threads.put(name, run);
    }

    public static void restart(String name){
        Threadable thread = threads.get(name);
        thread.stop();
        thread.run();
    }

    public static boolean hasThread(Class<? extends Threadable> threadable){
        return threads.containsKey(threadable.getClass().getSimpleName());
    }

    public static <T> T getThread(Class<T> clazz){
        return (T)threads.get(clazz.getSimpleName());
    }
}
