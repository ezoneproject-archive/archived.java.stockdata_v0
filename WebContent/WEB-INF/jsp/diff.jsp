<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.ArrayList,java.util.Properties" %>
<%!
%>

<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<body>
  <%@ include file="/WEB-INF/jsp/menu.jsp"%>
  <div class="container">
    <div class="page-header">
      <h1>
        <spring:message code="diff.title" />
      </h1>
    </div>

    <style>
.clickme {
    cursor: pointer; 
}
    </style>

    <form:form commandName="list" action="${pageContext.request.contextPath}/diffview.do" cssClass="form-horizontal">
      <div class="form-group">
        <label for="fileType" class="col-lg-2 control-label"><spring:message code="upload.filetype" /></label>
        <div class="col-lg-10">
          <label class="radio-inline"> <input type="radio" name="fileType" id="fileType1" value="strmov" ${strmov}> <spring:message code="upload.filetype.strmov" /></label>
          <label class="radio-inline"> <input type="radio" name="fileType" id="fileType2" value="tr20" ${tr20}> <spring:message code="upload.filetype.tr20" /></label>
          <form:errors path="fileType" element="div" cssClass="alert alert-danger" />
        </div>
      </div>

      <div class="form-group">
        <label for="gsdate" class="col-lg-2 control-label"><spring:message code="upload.gsdate" /></label>
        <div class="col-lg-10">
          <input type="text" name="gsdate" class="form-control" id="datepicker" value="${gsdate}" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd">
          <form:errors path="gsdate" element="div" cssClass="alert alert-danger" />
          <script type="text/javascript">
            $(function() {
              $('#datepicker').datepicker()
            });
          </script>
        </div>
      </div>

      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-search"> <spring:message code="list.searchBtn" /></span>
          </button>
        </div>
      </div>
      <input type="hidden" name="ord" id="ord">
    </form:form>
  </div>

<%
ArrayList<Properties> timeList = (ArrayList<Properties>) request.getAttribute("timelist");
ArrayList<Properties> stockList = (ArrayList<Properties>) request.getAttribute("stocklist");
Properties stockCodes = stockList.remove(0);
%>
  <div class="">
    <table class="table table-bordered table-striped table-condensed table-responsive">
      <thead>
        <tr>
<%
for (Properties timeItem : timeList) {
    String timestr = timeItem.getProperty("Time");
    if (timestr.length() > 2) {
        timestr = timestr.substring(0, 2) + ":" + timestr.substring(2);
    }

    out.print("          <th>");
    out.print(timestr);
    out.println("</th>");
}
%>
        </tr>
      </thead>
      <tbody>
<%
for (int i=1; i > 0; i++) {
    String gsStCode = stockCodes.getProperty("" + i);
    if (gsStCode == null)
        break;

    out.println("        <tr>");
    for (Properties timeItem : timeList) {
        String curTime = timeItem.getProperty("Time");

        boolean found = false;
        for (Properties stockItem : stockList) {
            if (gsStCode.equals(stockItem.getProperty("StockCode")) &&
                    curTime.equals(stockItem.getProperty("Time"))) {
                found = true;
                out.println("          <td>" + stockItem.getProperty("StockName") + "</td>");
                break;
            }
        }
        if (!found) {
            out.println("          <td>-</td>");
        }
    }
    out.println("        </tr>");
}
%>
      </tbody>
    </table>
  </div>

  <%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>

