package com.redhat.waw.jstakun;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HttpSessionServlet
 */
@WebServlet("/cluster_test")
public class HttpSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String KEY = "counter";
    private static final String EJB_KEY = "ejbcounter";
    private static final Logger logger = Logger.getLogger("com.redhat.waw.jstakun.HttpSessionServlet");
	/**
     * Default constructor. 
     */
    public HttpSessionServlet() {
    	
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     processRequest(request, response);
	}	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

        if (session.isNew()) {
            logger.log(Level.INFO, "New session created: " + session.getId());
            session.setAttribute(KEY, 0);
            session.setAttribute(EJB_KEY, new CounterEJBFacade());
        }
        
        Integer counter = (Integer) session.getAttribute(KEY);
        if (counter == null) {
        	counter = 0;
        }
        counter++;
        session.setAttribute(KEY, counter);

        CounterEJBFacade ejbFacade = (CounterEJBFacade) session.getAttribute(EJB_KEY);            
        if (ejbFacade == null) {
        	ejbFacade = new CounterEJBFacade();
        }
        ejbFacade.increaseCounter();
        session.setAttribute(EJB_KEY, ejbFacade);
        
        Principal user = request.getUserPrincipal();
        if (user != null) {
        	logger.log(Level.INFO, "User principal is " + request.getUserPrincipal().getName());	
        }
        
        logger.log(Level.INFO, "Handing request received from session " + session.getId());

        try {
        	request.getRequestDispatcher("/output.jsp").forward(request, response);
        } catch (Exception e) {
        	response.sendError(HttpServletResponse.SC_GONE);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet using Session to store object.";
    }

}
