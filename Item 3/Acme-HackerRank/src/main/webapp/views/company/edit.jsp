<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!-- Only brotherhood can access to this view -->

<form:form action="parade/brotherhood/edit.do"
	modelAttribute="parade">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="mode" />
	<form:hidden path="company" />

	<acme:textbox code="problem.title" path="title" />
	<acme:textbox code="problem.statement" path="statement" />
	<acme:textbox code="problem.hint" path="hint" />
	<acme:textbox code="problem.attachments" path="attachments" />
	<h5 style="color: red;"><spring:message code="collection.attachments"/></h5>

	<input type="submit" name="save"
		value="<spring:message code="parade.save" />" />

	<acme:button url="problem/list.do" name="cancel"
		code="problem.cancel" />

</form:form>