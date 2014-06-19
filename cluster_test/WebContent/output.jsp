<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.redhat.waw.jstakun.CounterEJBFacade" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% 
//interval in seconds
 String interval = request.getParameter("interval"); 
 if (interval != null) {
	try {
		int intValue = Integer.parseInt(interval);
		if (intValue < 1) {
			interval = "5";
		}
	} catch (Exception e) {
		interval = "5";
	}
 } else {
	 interval = "5";
 }
%>	
	<meta http-equiv="refresh" content="<%= interval %>">
<title>Status</title>
</head>
<body>
Session id:  <%=  session.getId() %><br/>

Counter value: <%= session.getAttribute("counter") %><br/> 

EJB counter value: <%= ((CounterEJBFacade)session.getAttribute("ejbcounter")).getCounter() %> <br/>

You are logged in as <%= request.getUserPrincipal() != null ?  request.getUserPrincipal().getName() : "anonymous" %><br/>

Session initialized on <fmt:formatDate value="${sessionScope.initialized_at}" pattern="yyyy-MM-dd HH:mm:ss"/> 
at server <%= session.getAttribute("initial_server") %> 
</body>
</html>