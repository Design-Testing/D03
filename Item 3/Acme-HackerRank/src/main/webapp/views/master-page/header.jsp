<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Hacker Rank Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>
					<li><a href="problem/list.do"><spring:message code="master.page.problem" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
				<security:authorize access="hasRole('COMPANY')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="problem/list.do"><spring:message code="master.page.problem" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<!-- ========================================================================================================= -->
		<!-- ========================================  COMPANY  ================================================ -->
		<!-- ========================================================================================================= -->
		
		<security:authorize access="hasRole('COMPANY')">
			<!-- APPLICATIONS -->
			<li><a class="fNiv"><spring:message	code="master.page.applications" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/company/list.do"><spring:message code="master.page.application.list" /></a></li>
					<li><a href="application/company/listSubmitted.do"><spring:message code="master.page.application.listSubmitted" /></a></li>
					<li><a href="application/company/listAccepted.do"><spring:message code="master.page.application.listAccepted" /></a></li>
					<li><a href="application/company/listRejected.do"><spring:message code="master.page.application.listRejected" /></a></li>
					
				</ul>
			</li>
			
			<!-- POSITION -->
			<li><a class="fNiv"><spring:message	code="master.page.position" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="position/company/create.do"><spring:message code="master.page.position.create" /></a></li>
					<li><a href="position/company/myPositions.do"><spring:message code="master.page.position.myPositions" /></a></li>										
				</ul>
			</li>
			
		</security:authorize>
		
		<!-- ========================================================================================================= -->
		<!-- ========================================  HACKER  ================================================ -->
		<!-- ========================================================================================================= -->
		
		<security:authorize access="hasRole('HACKER')">
			<li><a class="fNiv"><spring:message	code="master.page.applications" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/hacker/create.do"><spring:message code="master.page.application.create" /></a></li>
					<li><a href="application/hacker/listPending.do"><spring:message code="master.page.application.listPendig" /></a></li>
					<li><a href="application/hacker/listSubmitted.do"><spring:message code="master.page.application.listSubmitted" /></a></li>
					<li><a href="application/hacker/listAccepted.do"><spring:message code="master.page.application.listAccepted" /></a></li>
					<li><a href="application/hacker/listRejected.do"><spring:message code="master.page.application.listRejected" /></a></li>
					
				</ul>
			</li>
			
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

