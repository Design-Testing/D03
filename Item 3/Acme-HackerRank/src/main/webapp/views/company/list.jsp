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


	<br />
	<input type="button" name="create"
    value="<spring:message code="problem.create" />"
    onclick="relativeRedir('problem/create.do')" />
    <br />
<div id="problems">
        <ul style="list-style-type: disc">
            <li><b><spring:message code="problems"/></b>
        </ul>
    </div>
    
    <display:table pagesize="10" class="displaytag" keepStatus="true"
               name="problems" requestURI="${requestURI}" id="row">
    <!-- Attributes -->
	
    <spring:message var="title" code="problem.title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message var="statement" code="problem.statement"/>
    <display:column property="statement" title="${statement}" sortable="true"/>
    <spring:message var="hint" code="problem.hint"/>
    <display:column property="hint" title="${hint}" sortable="true"/>
    <spring:message var="attachments" code="problem.attachments"/>
    <display:column property="attachments" title="${attachments}" sortable="true"/>
    <spring:message var="mode" code="problem.mode"/>
    <display:column property="mode" title="${mode}" sortable="true"/>
    <spring:message var="company" code="problem.company"/>
    <display:column property="company" title="${company.title}" sortable="true"/>
    <jstl:if test="${problem.mode eq DRAFT}">
	<display:column>
            <input type="button" name="edit"
                value="<spring:message code="problem.edit" />"
                onclick="relativeRedir('problem/edit.do?problemId=${row.id}')" />
	</display:column>
	<display:column>
            <input type="button" name="toFinalMode"
                value="<spring:message code="problem.finalMode" />"
                onclick="relativeRedir('problem/finalMode.do?problemId=${row.id}')" />
	</display:column>
	</jstl:if>
	<display:column>
			<input type="button" name="delete"
                value="<spring:message code="problem.delete" />"
                onclick="relativeRedir('problem/delete.do?problemId=${row.id}')" />
	</display:column>
	
	<display:column>
			<input type="button" name="display"
                value="<spring:message code="problem.display" />"
                onclick="relativeRedir('problem/display.do?problemId=${row.id}')" />
	</display:column>
        
	</display:table>
	