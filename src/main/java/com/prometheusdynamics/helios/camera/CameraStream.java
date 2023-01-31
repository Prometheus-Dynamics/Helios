package com.prometheusdynamics.helios.camera;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.github.sarxos.webcam.Webcam;
import com.prometheusdynamics.helios.logging.Log;
import com.prometheusdynamics.helios.thread.Threadable;

import antlr.collections.List;

public class CameraStream implements Threadable {

    private Webcam webcam;

	@Override
	public void run() {
        Log.system("Starting...");
        Log.system("Retreiving Cameras");
        
        ArrayList<Webcam> webcams = new ArrayList<>(Webcam.getWebcams());
       
        Log.system("Found "+ webcams.size()+ " Cameras");
        if(webcams.size() <= 0){
            Log.System("No camera detected, please insure the camera is installed correctly waiting for camera...");
            do{
                try {
                    Thread.sleep(100);
                    Log.debug("Looking for camera");
                } catch (InterruptedException e) {
                }
            }while(Webcam.getWebcams().size() <= 0);
        }
        Log.System("Webcam found!");
        webcam = Webcam.getDefault();
        
	}

	@Override
	public void interupted() {
		
	}

	@Override
	public void stop() {
	}

	@Override
	public void restart() {
		
	}
}
