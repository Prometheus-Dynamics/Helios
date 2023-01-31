package com.prometheusdynamics.helios.web.views;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.prometheusdynamics.helios.camera.CameraStream;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamReceiver;
import com.vaadin.flow.server.StreamResource;

@Route("")
public class MainView extends VerticalLayout{
    
    public MainView(){
        
        
        Image image = new Image(createVideoStream(),"");

        add(image);
        
      //  while (true) {
            image.setSrc(createVideoStream());
      //  }
    }

    private StreamResource createVideoStream(){
        return new StreamResource("Camera.png", new InputStreamFactory() {

            @Override
            public InputStream createInputStream() {
                BufferedImage frame = CameraStream.getFrame();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    ImageIO.write(frame, "png", out);
                    return new ByteArrayInputStream(out.toByteArray());
                } catch (IOException e) {
                    
                    e.printStackTrace();
                    return null;
                }
                
            }
            
        });
    }
}
