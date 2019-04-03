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
<acme:modeChoose code="problem.mode"  mode="${problem.mode}"/>
<br>
<acme:display code="problem.attachments" value="${problem.attachments}" />

<acme:button url="problem/list.do" name="cancel"
		code="problem.cancel" />

<br />
