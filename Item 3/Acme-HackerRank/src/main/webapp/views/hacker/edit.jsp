
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2><spring:message code="member.edit.msg"/></h2>
<jstl:if test="${not empty alert}">
	<script>
	 $(document).ready(function() {
		 alert('<spring:message code="${alert}"/>');
	    });
		
	</script>
</jstl:if>

	<jstl:if test="${not empty errors}">
		<div class="errorDiv">
			<ul>
				<jstl:forEach items="${errors}" var="error">
					<li><spring:message code="member.edit.${error.field}"/> - <jstl:out value="${error.defaultMessage}" /></li>
				</jstl:forEach>
			</ul>
		</div>
	</jstl:if>



<form:form modelAttribute="actorForm" action="hacker/edit.do" method="POST">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<acme:textbox code="hacker.edit.userAccountuser" path="userAccountuser" />
	<acme:password code="hacker.edit.userAccountpassword" path="userAccountpassword" />

	<acme:textbox code="hacker.edit.name" path="name" />
	<acme:textbox code="hacker.edit.middleName" path="middleName" />
	<acme:textbox code="hacker.edit.surname" path="surname" />
	<acme:textbox code="hacker.edit.photo" path="photo" />
	<acme:textbox code="hacker.edit.email" path="email" />
	<acme:textbox code="hacker.edit.phone" path="phone" />
	<acme:textbox code="hacker.edit.address" path="address" />

	<jstl:choose>
	    <jstl:when test="${actorForm.termsAndCondicions == true}">
	        <form:hidden path="termsAndCondicions"/>
	    </jstl:when>    
	    <jstl:otherwise>
			<form:checkbox path="termsAndCondicions"/><a href="profile/terms.do"><spring:message code="edit.accepted"/> <spring:message code="edit.termsAndConditions"/></a>
			<br>
	    </jstl:otherwise>
	</jstl:choose>

	<acme:submit code="hacker.edit.submit" name="save"/>
</form:form>