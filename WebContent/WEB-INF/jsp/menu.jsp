
<!-- Fixed navbar -->
<div class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="${menu_home}"><a href="<%=request.getContextPath()%>">HOME</a></li>
        <li class="${menu_view}"><a href="${pageContext.request.contextPath}<spring:message code="menu.view.link"/>"><spring:message code="menu.view" /></a></li>
        <li class="${menu_diff}"><a href="${pageContext.request.contextPath}<spring:message code="menu.diff.link"/>"><spring:message code="menu.diff" /></a></li>
<!-- 
        <li class="${menu_detail}"><a href="${pageContext.request.contextPath}<spring:message code="menu.detail.link"/>"><spring:message code="menu.detail" /></a></li>
 -->
        <li class="${menu_upload}"><a href="${pageContext.request.contextPath}<spring:message code="menu.upload.link"/>"><spring:message code="menu.upload" /></a></li>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>
