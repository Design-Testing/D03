
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="application/hacker/edit.do" modelAttribute="application"  method="POST">
	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<acme:textarea code="application.explanation" path="explanation" />
	<acme:textbox code="application.link" path="link" />


	<acme:submit code="application.submit" name="save"/>
</form:form>