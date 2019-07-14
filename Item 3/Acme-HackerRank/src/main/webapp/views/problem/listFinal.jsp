    
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h4><spring:message code="problem.clarify.message" /></h4>

<security:authorize access="hasRole('COMPANY')">
    
    <display:table pagesize="10" class="displaytag" keepStatus="true"
               name="problems" requestURI="${requestURI}" id="row">
    <!-- Attributes -->
	
    <display:column property="title" titleKey="problem.title"/>
    <display:column property="statement" titleKey="problem.statement"/>
    <display:column property="mode" titleKey="problem.mode"/>
    <display:column property="company" titleKey="problem.company"/>
    
	<display:column>
            <input type="button" name="asign"
                value="<spring:message code="problem.asign" />"
                onclick="relativeRedir('problem/company/asign.do?positionId=${positionId}&problemId=${row.id}')" />
	</display:column>
	
	
	<display:column>
			<input type="button" name="display"
                value="<spring:message code="problem.display" />"
                onclick="relativeRedir('problem/company/display.do?problemId=${row.id}')" />
	</display:column>
        
	</display:table>
</security:authorize>