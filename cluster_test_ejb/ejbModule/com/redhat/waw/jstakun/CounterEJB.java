package com.redhat.waw.jstakun;

import javax.ejb.Stateful;

import org.jboss.ejb3.annotation.Clustered;

/**
 * Session Bean implementation class CounterEJB
 */
@Stateful
@Clustered
public class CounterEJB implements CounterEJBRemote, CounterEJBLocal {

	int counter = 0;
    /**
     * Default constructor. 
     */
    public CounterEJB() {
    }

	@Override
	public int getCounter() {
		return counter;
	}

	@Override
	public void increaseCounter() {
		counter++;
	}

}
