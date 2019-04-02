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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<acme:display code="application.status" value="${application.status}"/>

<jstl:choose>
	<jstl:when test="${lang eq 'en' }">
		<spring:message code="application.moment" />: <fmt:formatDate
			value="${application.moment}" type="both" pattern="yyyy/MM/dd HH:mm" />
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="application.moment" />: <fmt:formatDate
			value="${application.moment}" type="both" pattern="dd/MM/yyyy HH:mm" />
	</jstl:otherwise>
</jstl:choose>

<jstl:choose>
	<jstl:when test="${lang eq 'en' }">
		<spring:message code="application.submitMoment" />: <fmt:formatDate
			value="${application.submitMoment}" type="both" pattern="yyyy/MM/dd HH:mm" />
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="application.submitMoment" />: <fmt:formatDate
			value="${application.submitMoment}" type="both" pattern="dd/MM/yyyy HH:mm" />
	</jstl:otherwise>
</jstl:choose>

<acme:button url="answer/hacker/display.do?answerId=${answer.id}" name="seeAnswer" code="application.seeAnswer"/>

<acme:display code="application.position" value="${application.position.title}"/>

<acme:display code="application.hacker" value="${application.hacker.name}"/>

<acme:display code="application.problem" value="${application.problem.title}"/>

