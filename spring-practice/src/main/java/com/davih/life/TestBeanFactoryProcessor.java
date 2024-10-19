package com.davih.life;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryProcessor implements SmartLifecycle {

	private volatile boolean running;

	@Override
	public void start() {
		System.out.println(" test start ... ");
		running = true;
	}

	@Override
	public void stop() {
		System.out.println(" test stop ... ");
		running = false;
	}

	@Override
	public boolean isRunning() {
		return running;
	}
}
