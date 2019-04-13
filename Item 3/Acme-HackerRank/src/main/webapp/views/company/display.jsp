<%--
 * action-2.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<script src="https://unpkg.com/jspdf@latest/dist/jspdf.min.js"></script>
<script src="https://unpkg.com/jspdf@latest/dist/jspdf.min.js"></script>

<script>
function generatePDF(){
	alert('<spring:message code="display.company.document.alert"/>')
	var doc = new jsPDF()
	doc.text('<spring:message code="display.document.title"/>', 20, 10)
	doc.text('', 10, 20)
	doc.text('<spring:message code="actor.name"/> : <jstl:out value="${company.name}"/>', 10, 30)
	doc.text('<spring:message code="actor.middleName"/> : <jstl:out value="${company.middleName}"/>', 10, 40)
	doc.text('<spring:message code="actor.surname"/> : <jstl:out value="${company.surname}"/>', 10, 50)
	doc.text('<spring:message code="actor.photo"/> : <jstl:out value="${company.photo}"/>', 10, 60)
	doc.text('<spring:message code="actor.phone"/> : <jstl:out value="${company.phone}"/>', 10, 70)
	doc.text('<spring:message code="actor.email"/> : <jstl:out value="${company.email}"/>', 10, 80)
	doc.text('<spring:message code="actor.address"/> : <jstl:out value="${company.address}"/>', 10, 90)
	doc.text('<spring:message code="actor.vat"/> : <jstl:out value="${company.vat}"/>', 10, 100)
	doc.save('<spring:message code="display.document.fileName"/>.pdf')
}
function deletePersonalData(){
	var r = confirm('<spring:message code="display.deletePersonalData"/>');
	if (r == true) {
		location.href = "company/deletePersonalData.do";
	}
}
</script>


<acme:display code="company.name" value="${company.name}"/>
<spring:message code="company.photo"/>:<br>
<img src="${company.photo}" alt="<spring:message code="hacker.alt.image"/>" width="20%" height="20%"/>
<br>
<jstl:if test="${not empty company.surname}">
<jstl:forEach items="${company.surname}" var="df">
	<acme:display code="company.surname" value="${df}"/>
</jstl:forEach>
</jstl:if>
<acme:display code="company.email" value="${company.email}"/>
<acme:display code="company.phone" value="${company.phone}"/>
<acme:display code="company.email" value="${company.email}"/>
<acme:display code="company.address" value="${company.address}"/>
<acme:display code="company.vat" value="${company.vat}"/>

<jstl:if test="${displayButtons}">
<br>
	<button onClick="generatePDF()"><spring:message code="display.getData"/></button>
	<button onClick="deletePersonalData()"><spring:message code="display.button.deletePersonalData"/></button>
	
<br>
</jstl:if>