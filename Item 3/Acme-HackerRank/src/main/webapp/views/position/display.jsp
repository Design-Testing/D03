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


<acme:display code="position.title" value="${position.title}"/>

<acme:display code="position.description" value="${position.description}"/>

<jstl:choose>
	<jstl:when test="${lang eq 'en' }">
		<spring:message code="position.deadline" />: <fmt:formatDate
			value="${position.deadline}" type="both" pattern="yyyy/MM/dd HH:mm" />
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="position.deadline" />: <fmt:formatDate
			value="${position.deadline}" type="both" pattern="dd/MM/yyyy HH:mm" />
	</jstl:otherwise>
</jstl:choose>

<acme:display code="position.ticker" value="${position.ticker}"/>

<acme:display code="position.profile" value="${position.profile}"/>

<acme:display code="position.skills" value="${position.skills}"/>

<acme:display code="position.technologies" value="${position.technologies}"/>

<acme:display code="position.salary" value="${position.salary}"/>

<acme:display code="position.mode" value="${position.mode}"/>

<acme:display code="position.company" value="${position.company.name}"/>

<jstl:if test="${position.status eq 'DEFAULT'}">
<acme:button url="problem/company/create.do?positionId=${position.id}" name="create" code="position.create" />
</jstl:if>

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="problems" requestURI="problem/company/list.do" id="row">
	
    <display:column property="problem.title" title="${problem.title}" sortable="true"/>

    <display:column property="problem.statement" title="${problem.statement}" sortable="true"/>

    <display:column property="problem.hint" title="${problem.hint}" sortable="true"/>

	<display:column>
			<acme:button url="problem/company/display.do?problemId=${row.id}" name="display" code="problem.display"/>
	</display:column>
        
</display:table>



