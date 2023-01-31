package com.prometheusdynamics.helios;

import com.prometheusdynamics.helios.camera.CameraStream;
import com.prometheusdynamics.helios.logging.Log;
import com.prometheusdynamics.helios.logging.TextEffect;
import com.prometheusdynamics.helios.logging.TextEffect.Colour;
import com.prometheusdynamics.helios.thread.ThreadManager;

public class HeliosApplication {

    public static void main(String[] args) {
        Log.setDebugMode(true);
        Log.error("Hello World");
        ThreadManager.run(new CameraStream());
    }
}
