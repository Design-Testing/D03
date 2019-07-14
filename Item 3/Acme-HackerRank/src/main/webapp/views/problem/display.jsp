<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:display code="problem.company" value="${problem.company}" />
<acme:display code="problem.title" value="${problem.title}" />
<acme:display code="problem.statement" value="${problem.statement}" />
<acme:display code="problem.hint" value="${problem.hint}" />
<spring:message code="problem.mode"/>:
 <acme:modeChoose mode="${problem.mode}"/>
<br>
<spring:message code="problem.attachments"/>: 
<jstl:forEach items="${problem.attachments}" var="l">
	<ul>
		<li><jstl:out value="${l}"/></li>
	</ul>
</jstl:forEach>
<br>

<security:authorize access="hasRole('COMPANY')">

<jstl:choose>
<jstl:when test="${empty applications and problem.position.mode ne 'FINAL'}">
	<acme:button url="problem/company/delete.do?problemId=${problem.id}" name="delete" code="problem.delete"/>	
</jstl:when>
<jstl:otherwise>
	<h4 style="color: red"><spring:message code="problem.not.delete"/></h4>
</jstl:otherwise>
</jstl:choose>

<br>
<jstl:if test="${not empty problem.position}">
	
<acme:button url="position/company/display.do?positionId=${problem.position.id}" name="back"
		code="problem.position.associated" />
</jstl:if>



<br>

<jstl:if test="${not empty errortrace}">
	<h3 style="color: red;"><spring:message code="${errortrace}"/></h3>
</jstl:if>

<br />

</security:authorize>



<security:authorize access="hasRole('HACKER')">
	<acme:button url="application/hacker/listPending.do" name="back"
		code="problem.back.pending" />
</security:authorize>