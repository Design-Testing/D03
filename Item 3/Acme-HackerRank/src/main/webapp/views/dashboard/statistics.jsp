<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
 
 <!-- TABLE --> 
 <table >
 <spring:message code="dashboard.positions.company" />
    <tr >
    <td><spring:message code="average.positions.company"/></td>
    <td>${averagePositions}</td>
    </tr>
    <tr >
    <td><spring:message code="min.positions.company"/></td>
    <td>${minPositions}</td>
    </tr>
    <tr >
    <td><spring:message code="max.positions.company" /></td>
    <td>${maxPositions}</td>
    </tr>
    <tr > 
    <td><spring:message code="desviation.positions.company" /></td>
    <td>${desviationPositions}</td>
    </tr>
    <tr >
    <td><spring:message code="largest.company" /></td>
    <td>${companyMorePositions}</td>
    </tr>
  </table>
  
   <table >
 <spring:message code="dashboard.applications.hacker" />
 <caption><jstl:out value="${brotherhoodsH}" /></caption>

    <tr >
    <td><spring:message code="average.applications.hacker"/></td>
    <td>${averageHacker}</td>
    </tr>
    <tr >
    <td><spring:message code="min.applications.hacker"/></td>
    <td>${minHacker}</td>
    </tr>
    <tr >
    <td><spring:message code="max.applications.hacker" /></td>
    <td>${maxHacker}</td>
    </tr>
    <tr > 
    <td><spring:message code="desviation.applications.hacker" /></td>
    <td>${desviationHacker}</td>
    </tr>
    <tr >
    <td><spring:message code="largest.hacker" /></td>
    <td>${hackerMoreApplications}</td>
    </tr>
  </table>
  
  
  
   <table >
 <spring:message code="dashboard.positions" />
 	<tr>
    <td><spring:message code="largest.position" /></td>
    <td>${best}</td>
    </tr>
    <tr >
    <td><spring:message code="smallest.position" /></td>
    <td>${worst}</td>
    </tr>
  </table>
  

    <table>
    <spring:message code="dashboard.curricula" />
    <tr >
    <td><spring:message code="average.curricula" /></td>
    <td>${averageCurricula}</td>
    </tr>
    <tr >
    <td><spring:message code="min.curricula" /></td>
    <td>${minCurricula}</td>
    </tr>
    <tr >
    <td><spring:message code="max.curricula" /></td>
    <td>${maxCurricula}</td>
    </tr>
    <tr >
    <td><spring:message code="desviation.curricula" /></td>
    <td>${desviationCurricula}</td>
    </tr>
    </table>
    
    <table>
    <spring:message code="dashboard.finders" />
    <tr >
    <td><spring:message code="average.results" /></td>
    <td>${averageResults}</td>
    </tr>
    <tr >
    <td><spring:message code="min.results" /></td>
    <td>${minResults}</td>
    </tr>
    <tr >
    <td><spring:message code="max.results" /></td>
    <td>${maxResults}</td>
    </tr>
    <tr >
    <td><spring:message code="desviation.results" /></td>
    <td>${desviationResults}</td>
    </tr>
    <tr >
    <td><spring:message code="ratio.finders" /></td>
    <td>${ratioFinders}</td>
    </tr>


</table>

</security:authorize>
