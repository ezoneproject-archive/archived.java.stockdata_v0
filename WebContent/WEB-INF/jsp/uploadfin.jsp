<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<body>
  <%@ include file="/WEB-INF/jsp/menu.jsp"%>

  <div class="container">
    <div class="page-header">
      <h1>
        <spring:message code="uploadfin.title" />
      </h1>
    </div>

    <div class="panel panel-success">
      <div class="panel-heading">
        <h3 class="panel-title"><spring:message code="uploadfin.notice" /></h3>
      </div>
      <div class="panel-body">
        <p>
          <spring:message code="upload.filetype" /> : ${upload_fileType }
        </p> 
        <p>
          <spring:message code="upload.timetype" /> : ${upload_timeType }
        </p> 
      </div>
    </div>

      <a class="btn btn-primary" href="${pageContext.request.contextPath}<spring:message code="menu.view.link"/>" role="button">
        <span class="glyphicon glyphicon-play"> <spring:message code="uploadfin.btnview" /></span>
      </a>
      <a class="btn btn-default" href="${pageContext.request.contextPath}<spring:message code="menu.upload.link"/>" role="button">
        <span class="glyphicon glyphicon-plus"> <spring:message code="uploadfin.btnupload" /></span>
      </a>
  </div>
  <%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>

