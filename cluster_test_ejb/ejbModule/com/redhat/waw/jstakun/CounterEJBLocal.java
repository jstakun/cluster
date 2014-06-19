package com.redhat.waw.jstakun;

import javax.ejb.Local;

@Local
public interface CounterEJBLocal {
	
	public int getCounter();
	
	public void increaseCounter(); 

}
