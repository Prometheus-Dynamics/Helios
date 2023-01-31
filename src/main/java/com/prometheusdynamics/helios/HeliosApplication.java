package com.prometheusdynamics.helios;

import java.io.InputStream;

import com.github.sarxos.webcam.log.WebcamLogConfigurator;
import com.prometheusdynamics.helios.camera.CameraStream;
import com.prometheusdynamics.helios.logging.Log;
import com.prometheusdynamics.helios.logging.TextEffect;
import com.prometheusdynamics.helios.logging.TextEffect.Colour;
import com.prometheusdynamics.helios.opencv.OpenCVProcessing;
import com.prometheusdynamics.helios.thread.ThreadManager;

public class HeliosApplication {

    public static void main(String[] args) {
        Log.setDebugMode(true);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("logback.xml");
        WebcamLogConfigurator.configure(is);
        Log.error("Hello World");
        ThreadManager.run(new CameraStream());
        ThreadManager.run(new OpenCVProcessing());

    }
}
