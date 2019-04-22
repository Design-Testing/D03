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
	doc.text('<spring:message code="hacker.name"/> : <jstl:out value="${hacker.name}"/>', 10, 30)
		doc.text('<spring:message code="hacker.surname"/> : <jstl:out value="${hacker.surname}"/>', 10, 40)
	doc.text('<spring:message code="hacker.photo"/> : <jstl:out value="${hacker.photo}"/>', 10, 50)
	doc.text('<spring:message code="hacker.phone"/> : <jstl:out value="${hacker.phone}"/>', 10, 60)
	doc.text('<spring:message code="hacker.email"/> : <jstl:out value="${hacker.email}"/>', 10, 70)
	doc.text('<spring:message code="hacker.address"/> : <jstl:out value="${hacker.address}"/>', 10, 80)
	doc.text('<spring:message code="hacker.vat"/> : <jstl:out value="${hacker.vat}"/>', 10, 90)
	doc.text('', 10, 100)
	doc.text('<spring:message code="hacker.creditCard"/>', 15, 110)
	doc.text('<spring:message code="hacker.creditCard.holderName"/> : <jstl:out value="${hacker.creditCard.holderName}"/>', 10, 120)
	doc.text('<spring:message code="hacker.creditCard.number"/> : <jstl:out value="${hacker.creditCard.number}"/>', 10, 130)
	doc.text('<spring:message code="hacker.creditCard.make"/> : <jstl:out value="${hacker.creditCard.make}"/>', 10, 140)
	doc.text('<spring:message code="hacker.creditCard.expirationMonth"/> : <jstl:out value="${hacker.creditCard.expirationMonth}"/>', 10, 150)
	doc.text('<spring:message code="hacker.creditCard.expirationYear"/> : <jstl:out value="${hacker.creditCard.expirationYear}"/>', 10, 160)
	doc.text('<spring:message code="hacker.creditCard.cvv"/> : <jstl:out value="${hacker.creditCard.cvv}"/>', 10, 170)
	doc.save('<spring:message code="display.document.fileName"/>.pdf')
}
function deletePersonalData(){
	var r = confirm('<spring:message code="display.deletePersonalData"/>');
	if (r == true) {
		location.href = "hacker/deletePersonalData.do";
	}
}
</script>

<acme:display code="hacker.name" value="${hacker.name}"/><br>
<jstl:if test="${not empty hacker.surname}">
<spring:message code="hacker.surname"/>:
<ul>
<jstl:forEach items="${hacker.surname}" var="df">
	<li><jstl:out value="${df}"/></li>
</jstl:forEach>
</ul>
</jstl:if>
<jstl:if test="${not empty hacker.photo}">
<spring:message code="hacker.photo"/>:<br>
<img src="${hacker.photo}" alt="<spring:message code="hacker.alt.image"/>" width="20%" height="20%"/>
<br><br></jstl:if>
<acme:display code="hacker.email" value="${hacker.email}"/><br>
<jstl:if test="${not empty hacker.phone}">
<acme:display code="hacker.phone" value="${hacker.phone}"/><br>
</jstl:if>
<jstl:if test="${not empty hacker.address}">
<acme:display code="hacker.address" value="${hacker.address}"/><br>
</jstl:if>
<acme:display code="hacker.vat" value="${hacker.vat}"/><br>
<h3><spring:message code="hacker.creditCard"/></h3>
<acme:display code="hacker.creditCard.holderName" value="${hacker.creditCard.holderName}"/>
<acme:display code="hacker.creditCard.number" value="${hacker.creditCard.number}"/>
<acme:display code="hacker.creditCard.make" value="${hacker.creditCard.make}"/>
<acme:display code="hacker.creditCard.expirationMonth" value="${hacker.creditCard.expirationMonth}"/>
<acme:display code="hacker.creditCard.expirationYear" value="${hacker.creditCard.expirationYear}"/>
<acme:display code="hacker.creditCard.cvv" value="${hacker.creditCard.cvv}"/>

<jstl:if test="${displayButtons}">
<br>
	<button onClick="generatePDF()"><spring:message code="display.getData"/></button>
	<button onClick="deletePersonalData()"><spring:message code="display.button.deletePersonalData"/></button>
	
<br>
</jstl:if>