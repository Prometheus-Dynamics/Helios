package com.prometheusdynamics.helios.camera;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamViewer;
import com.github.sarxos.webcam.log.WebcamLogConfigurator;
import com.prometheusdynamics.helios.logging.Log;
import com.prometheusdynamics.helios.thread.ThreadManager;
import com.prometheusdynamics.helios.thread.Threadable;

import antlr.collections.List;

public class CameraStream implements Threadable {

    private Webcam webcam;
    private static final int CAMERA_FIND_ATTEMPTS = 15;
    private static BufferedImage image;

	@Override
	public void run() {
        Log.system("Starting...");
        Log.system("Retreiving Cameras");
        ArrayList<Webcam> webcams = new ArrayList<>(Webcam.getWebcams());
        Log.system("Found "+ webcams.size()+ " Cameras");
        if(webcams.size() <= 0){
            int attempts = 0;
            Log.System("No camera detected, please insure the camera is installed correctly waiting for camera...");
            do{
                try {
                    if(attempts >= CAMERA_FIND_ATTEMPTS){
                        Log.System("Faild to find camera after "+attempts + " attempts, restarting service!!!");
                        ThreadManager.restart(Thread.currentThread().getName());
                    }
                    Thread.sleep(1000);
                    Log.debug("Looking for camera");
                    attempts++;
                } catch (InterruptedException e) {
                }
            }while(Webcam.getWebcams().size() <= 0);
        }
        Log.System("Webcam found!");
        webcam = Webcam.getDefault();
        Log.system("Opening Camera");
        webcam.open();
        while (true) {
            image = webcam.getImage();
        }
	}

	@Override
	public void interupted() {
		
	}

	@Override
	public void stop() {
	}

	@Override
	public void restart() {
		Log.Debug("Restart requested");
	}

    public static BufferedImage getFrame(){
        return image;
    }

    public static Mat getMat() {
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}
