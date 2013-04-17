package it.uniroma1.dis.jaco.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public enum Queue {
	instance;

	private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();

	public synchronized BlockingQueue<String> getModel() {
		return messageQueue;
	}
}
