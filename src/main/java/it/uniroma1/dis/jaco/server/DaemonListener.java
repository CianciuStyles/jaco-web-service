package it.uniroma1.dis.jaco.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DaemonListener implements ServletContextListener {

	private DaemonThread myDaemonThread = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			// myDaemonThread.doShutdown();
			myDaemonThread.interrupt();
		} catch (Exception ex) {
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if ((myDaemonThread == null) || (myDaemonThread.isAlive())) {
			myDaemonThread = new DaemonThread();
			myDaemonThread.start();
		}
	}

}
