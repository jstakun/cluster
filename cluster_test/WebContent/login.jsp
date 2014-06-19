<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Form</title>
</head>
<body>
<form method="POST" action="j_security_check">
   <div class="center">
    <div style="background-color:#F6F6F6;">
     <div class="title" align="right">TrustBuilder<br/>Administration<br/>GUI<div align="left">LOGIN</div></div>
    </div>
    <div style="height:5px;"></div>
<div class="formBlock" align="center" style="border:1px solid black;">
      <div style="padding-top:15px;margin-left:0.5em;" >
   <div class="label" align="left">Username:</div>
   <div align="left"><input type="text" style="width:20em" name="j_username" id="user"></div>
      </div>
      <div style="clear:both;margin-left:0.5em;">
   <div align="left" class="label">Password:</div>
   <div align="left"><input type="password" style="width:20em" name="j_password"></div>
      </div>
  <div align="right" style="padding-top:15px;margin-right:0.5em;"><input type="submit" style="width:10em;" value="Login"></div>
    </div>
   </div>
 </form>
</body>
</html>