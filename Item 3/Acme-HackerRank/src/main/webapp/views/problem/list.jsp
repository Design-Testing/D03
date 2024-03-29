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



<div id="problems">
        <ul style="list-style-type: disc">
            <li><b><spring:message code="problems"/></b>
        </ul>
    </div>
    
    <display:table pagesize="10" class="displaytag" keepStatus="true"
               name="problems" requestURI="${requestURI}" id="row">
    <!-- Attributes -->
	
    <spring:message var="title" code="problem.title"/>
    <display:column property="title" sortable="true"/>
    <spring:message var="statement" code="problem.statement"/>
    <display:column property="statement" sortable="true"/>
    <spring:message var="hint" code="problem.hint"/>
    <display:column property="hint" sortable="true"/>
    <spring:message var="attachments" code="problem.attachments"/>
    <display:column property="attachments" sortable="true"/>
    <spring:message var="mode" code="problem.mode"/>
    <display:column property="mode" sortable="true"/>
    <spring:message var="company" code="problem.company"/>
    <display:column property="company" sortable="true"/>
    
	<display:column>
	<jstl:if test="${row.mode eq 'DRAFT'}">
            <input type="button" name="edit"
                value="<spring:message code="problem.edit" />"
                onclick="relativeRedir('problem/company/edit.do?problemId=${row.id}&positionId=${row.position.id}')" />
	</jstl:if>
	</display:column>
	<display:column>
	<jstl:if test="${row.mode eq 'DRAFT'}">
            <input type="button" name="toFinalMode"
                value="<spring:message code="problem.finalMode" />"
                onclick="relativeRedir('problem/company/finalMode.do?problemId=${row.id}')" />
	</jstl:if>
	</display:column>
	
	<display:column>
			<input type="button" name="delete"
                value="<spring:message code="problem.delete" />"
                onclick="relativeRedir('problem/company/delete.do?problemId=${row.id}&positionId=${row.position.id}')" />
	</display:column>
	
	<display:column>
			<input type="button" name="display"
                value="<spring:message code="problem.display" />"
                onclick="relativeRedir('problem/company/display.do?problemId=${row.id}')" />
	</display:column>
        
	</display:table>
	