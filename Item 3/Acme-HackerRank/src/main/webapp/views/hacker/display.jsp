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
	alert('<spring:message code="display.hacker.document.alert"/>')
	var doc = new jsPDF()
	doc.text('<spring:message code="display.document.title"/>', 20, 10)
	doc.text('', 10, 20)
	doc.text('<acme:display code="hacker.name" value="${hacker.name}"/>', 10, 30)
	doc.text('<acme:display code="hacker.surname" value="${hacker.surname}"/>', 10, 40)
	doc.text('<img src="${hacker.photo}" alt="<spring:message code="hacker.alt.image"/>" width="20%" height="20%"/>', 10, 50)
	doc.text('<acme:display code="hacker.email" value="${hacker.email}"/>', 10, 60)
	doc.text('<acme:display code="hacker.phone" value="${hacker.phone}"/>', 10, 70)
	doc.text('<acme:display code="hacker.address" value="${hacker.address}"/>', 10, 80)
	doc.text('<acme:display code="hacker.vat" value="${hacker.vat}"/>', 10, 90)
	doc.text('<acme:display code="hacker.creditCard" value="${hacker.creditCard}"/>', 10, 100)
	doc.save('<spring:message code="display.document.fileName"/>.pdf')
}
function deletePersonalData(){
	var r = confirm('<spring:message code="display.deletePersonalData"/>');
	if (r == true) {
		location.href = "member/deletePersonalData.do";
	}
}
</script>


<acme:display code="hacker.name" value="${hacker.name}"/>
<jstl:if test="${not empty hacker.surname}">
<jstl:forEach items="${hacker.surname}" var="df">
	<acme:display code="hacker.surname" value="${df}"/>
</jstl:forEach>
</jstl:if>
<spring:message code="hacker.photo"/>:<br>
<img src="${hacker.photo}" alt="<spring:message code="hacker.alt.image"/>" width="20%" height="20%"/>
<br>
<acme:display code="hacker.email" value="${hacker.email}"/>
<acme:display code="hacker.phone" value="${hacker.phone}"/>
<acme:display code="hacker.address" value="${hacker.address}"/>
<acme:display code="hacker.vat" value="${hacker.vat}"/>
<acme:display code="hacker.creditCard" value="${hacker.creditCard}"/>

<jstl:choose>
	<jstl:when test="${hacker.spammer}">
		<spring:message code="hacker.spammer"/>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="hacker.spammer.no"/>
	</jstl:otherwise>
</jstl:choose>


<jstl:if test="${displayButtons}">
<br>
	<button onClick="generatePDF()"><spring:message code="display.getData"/></button>
	<button onClick="deletePersonalData()"><spring:message code="display.button.deletePersonalData"/></button>
	
<br>
</jstl:if>