
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

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
					<li><spring:message code="company.edit.${error.field}"/> - <jstl:out value="${error.defaultMessage}" /></li>
				</jstl:forEach>
			</ul>
		</div>
	</jstl:if>



<form:form modelAttribute="companyForm" action="company/edit.do" method="POST">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<acme:textbox code="company.edit.userAccountuser" path="userAccountuser" />
	<acme:password code="company.edit.userAccountpassword" path="userAccountpassword" />

	<acme:textbox code="company.edit.name" path="name" />
	<acme:textbox code="company.edit.surname" path="surname" />
	<acme:textbox code="company.edit.photo" path="photo" />
	<acme:textbox code="company.edit.email" path="email" />
	<acme:textbox code="company.edit.phone" path="phone" />
	<acme:textbox code="company.edit.address" path="address" />
	<acme:textbox code="company.edit.commercialName" path="commercialName" />
	<br>
	<spring:message code="company.edit.creditCard"/>
	<br>
	<acme:textbox code="company.edit.holder" path="creditCard.holderName" />
	<acme:select items="${makes}" itemLabel="makes" code="company.edit.make" path="creditCard.make"/>
	<acme:textbox code="company.edit.number" path="creditCard.number" />
	<acme:textbox code="company.edit.expirationMonth" path="creditCard.expirationMonth" />
	<acme:textbox code="company.edit.expirationYear" path="creditCard.expirationYear" />
	<acme:textbox code="company.edit.cvv" path="creditCard.cvv" />
	

	<jstl:choose>
	    <jstl:when test="${companyForm.termsAndCondicions == true}">
	        <form:hidden path="termsAndCondicions"/>
	    </jstl:when>    
	    <jstl:otherwise>
			<form:checkbox path="termsAndCondicions"/><a href="profile/terms.do"><spring:message code="edit.accepted"/> <spring:message code="edit.termsAndConditions"/></a>
			<br>
	    </jstl:otherwise>
	</jstl:choose>
	
	<input type="submit" name="save"
		value="<spring:message code="company.edit.submit" />" />
	
</form:form>