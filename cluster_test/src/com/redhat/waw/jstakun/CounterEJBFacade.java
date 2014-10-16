package com.redhat.waw.jstakun;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebService()
@EJB(name="CounterEJB", beanInterface = CounterEJBLocal.class)
public class CounterEJBFacade implements Serializable {
    
	private static final Logger logger = Logger.getLogger("com.redhat.waw.jstakun.CounterEJBFacade");
	private static final long serialVersionUID = 1L;
	CounterEJBLocal ejbLocal;
	
	public CounterEJBFacade() {
		try {
			ejbLocal = (CounterEJBLocal) new InitialContext().lookup("java:global/cluster/cluster_test_ejb/CounterEJB!com.redhat.waw.jstakun.CounterEJBLocal");
		} catch (NamingException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
    }
	
	@WebMethod()
	public int getCounter() {
		if (ejbLocal != null) {
			return ejbLocal.getCounter();
		} else {
			return -1;
		}
	}
	
	@WebMethod()
	public void increaseCounter() {
		if (ejbLocal != null) {
			ejbLocal.increaseCounter();
		} else {
			logger.log(Level.SEVERE, "ejbLocal is null");
		}
	}
}
