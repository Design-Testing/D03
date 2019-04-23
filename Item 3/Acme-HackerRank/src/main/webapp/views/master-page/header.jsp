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
	<a href="#"><img src="${bannerURL}" alt="Acme Hacker Rank Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<!-- ========================================================================================================= -->
		<!-- ========================================  ADMINNISTRATOR  ================================================ -->
		<!-- ========================================================================================================= -->
		
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.configurationParameters" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="configurationParameters/administrator/edit.do"><spring:message code="master.page.configurationParameters.edit" /></a></li>
					<li><a href="dashboard/administrator/dataBreach.do"><spring:message	code="master.page.administrator.data.breach" /></a></li>
				</ul>
			</li>
			
			<li><a href="finder/searching.do"><spring:message code="master.page.finder.hacker.edit" /></a></li>
			<li><a class="fNiv" href="company/list.do"><spring:message code="master.page.company.list" /></a></li>
			<li><a href="position/list.do"><spring:message code="master.page.position.list" /></a></li>
			<li><a href="dashboard/administrator/statistics.do"><spring:message	code="master.page.dashboard" /></a></li>
			<li><a href="administrator/create.do"><spring:message	code="master.page.create.administrator" /></a></li>
				
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
			
			<li><a href="finder/searching.do"><spring:message code="master.page.finder.hacker.edit" /></a></li>
			<li><a href="company/list.do"><spring:message code="master.page.company.list" /></a></li>
			<li><a href="position/list.do"><spring:message code="master.page.position.public.list" /></a></li>
			
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
					<li><a href="application/hacker/listPending.do"><spring:message code="master.page.application.listPending" /></a></li>
					<li><a href="application/hacker/listSubmitted.do"><spring:message code="master.page.application.listSubmitted" /></a></li>
					<li><a href="application/hacker/listAccepted.do"><spring:message code="master.page.application.listAccepted" /></a></li>
					<li><a href="application/hacker/listRejected.do"><spring:message code="master.page.application.listRejected" /></a></li>
				</ul>
			</li>
			<li><a href="finder/hacker/edit.do"><spring:message code="master.page.finder.hacker.edit" /></a></li>
			<li><a class="fNiv" href="company/list.do"><spring:message code="master.page.company.list" /></a></li>
			<li><a href="position/hacker/list.do"><spring:message code="master.page.position.list" /></a></li>
			<li><a href="curricula/displayAll.do"><spring:message code="master.page.curricula" /></a></li>
			
		</security:authorize>
		
		<!-- ========================================================================================================= -->
		<!-- ========================================  ANONYMOUS  ================================================ -->
		<!-- ========================================================================================================= -->
				
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="company/list.do"><spring:message code="master.page.company.list" /></a></li>
			<li><a href="finder/searching.do"><spring:message code="master.page.finder.hacker.edit" /></a></li>
			<li><a href="position/list.do"><spring:message code="master.page.position.list" /></a></li>
			<li><a class="fNiv" href="company/create.do"><spring:message code="master.page.company.register" /></a></li>
			<li><a class="fNiv" href="hacker/create.do"><spring:message code="master.page.hacker.register" /></a></li>
			
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
						<security:authorize access="hasRole('COMPANY')">
							<li><a href="company/edit.do"><spring:message code="master.page.company.edit" /></a></li>
							<li><a href="company/display2.do"><spring:message code="master.page.company.display" /></a></li>
						</security:authorize>
						<security:authorize access="hasRole('ADMIN')">
							<li><a href="administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
							<li><a href="administrator/display.do"><spring:message code="master.page.administrator.display" /></a></li>
						</security:authorize>
						<security:authorize access="hasRole('HACKER')">
							<li><a href="hacker/edit.do"><spring:message code="master.page.hacker.edit" /></a></li>
							<li><a href="hacker/display.do"><spring:message code="master.page.hacker.display" /></a></li>
						</security:authorize>
					<li><a href="folder/list.do"><spring:message code="master.page.folder.list" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

