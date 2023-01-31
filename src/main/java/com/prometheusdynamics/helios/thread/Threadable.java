package com.prometheusdynamics.helios.thread;

public interface Threadable extends Runnable{

    void interupted();

    void stop();

    void restart();
}
