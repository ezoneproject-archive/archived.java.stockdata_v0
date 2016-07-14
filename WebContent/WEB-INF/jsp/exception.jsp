<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<body>
  <%@ include file="/WEB-INF/jsp/menu.jsp"%>

  <div class="container">
    <div class="page-header">
      <h1><spring:message code="exception.title" /> </h1>
    </div>

    <div class="panel panel-danger">
      <div class="panel-heading">
        <h3 class="panel-title">${exception.class.name}</h3>
      </div>
      <div class="panel-body">
        <p>${exception.message }</p>
        <p>
        <c:forEach var="result" items="${exception.stackTrace}" varStatus="status">
          <c:if test="${fn:startsWith(result,'stidx.')}"> 
            ${result }<br/>
          </c:if>
        </c:forEach>
        </p>
      </div>
    </div>
  </div>

  <%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>

