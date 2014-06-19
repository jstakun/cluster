package com.redhat.waw.jstakun;

import javax.ejb.Remote;

@Remote
public interface CounterEJBRemote {
	public int getCounter();
	
	public void increaseCounter(); 
}
