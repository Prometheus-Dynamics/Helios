package com.prometheusdynamics.helios.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prometheusdynamics.helios.thread.Threadable;

@SpringBootApplication
public class WebInterface implements Threadable {

	@Override
	public void run() {
		SpringApplication.run(WebInterface.class);
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
