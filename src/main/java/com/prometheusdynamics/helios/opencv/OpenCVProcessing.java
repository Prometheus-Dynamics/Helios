package com.prometheusdynamics.helios.opencv;

import java.awt.image.BufferedImage;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.videoio.VideoCapture;

import com.prometheusdynamics.helios.camera.CameraStream;
import com.prometheusdynamics.helios.logging.Log;
import com.prometheusdynamics.helios.thread.ThreadManager;
import com.prometheusdynamics.helios.thread.Threadable;

import nu.pattern.OpenCV;

public class OpenCVProcessing implements Threadable {
	
	@Override
	public void run() {
        Log.system("Starting...");
		Log.system("Loading OpenCV...");
		OpenCV.loadLocally();
		Log.System("OpenCV loaded!");
		Log.system("Waiting for Camera Stream");
		do{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(!ThreadManager.hasThread(CameraStream.class));
		Log.System("Stream found!");
		Mat mat = CameraStream.getMat();

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
