package com.redhat.waw.jstakun;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */
public class SessionCounterListener implements HttpSessionListener {

	private static int totalActiveSessions = 0;
	private static final Logger logger = Logger.getLogger("com.redhat.waw.jstakun.HttpSessionListener");
    /**
     * Default constructor. 
     */
    public SessionCounterListener() {
    	logger.log(Level.INFO, "SessionCounterListener created...");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
    	totalActiveSessions++;
    	HttpSession session = arg0.getSession();
    	session.setAttribute( "initialized_at", new Date(session.getCreationTime()) );
        session.setAttribute( "initial_server", System.getProperty( "jboss.server.name" ));
    	logger.log(Level.INFO, "New session " + session.getId() + " created!\nThere are " + totalActiveSessions + " active sessions..." );
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	if (totalActiveSessions > 0) {
    		totalActiveSessions--;
    	}
    	logger.log(Level.INFO, "Expired session " + arg0.getSession().getId() + " destroyed!\nThere are " + totalActiveSessions + " active sessions..." );
    }
	
}
