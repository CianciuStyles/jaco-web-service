package it.uniroma1.dis.jaco.dao;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public enum StorageDao {
	instance;

	private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();

	public void pushToQueue(String message) throws InterruptedException {
		messageQueue.put(message);
	}

	public String popMessageFromQueue() {
		return messageQueue.poll();
	}
}
