<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="java.util.ArrayList,java.util.Properties" %>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<body>
  <%@ include file="/WEB-INF/jsp/menu.jsp"%>
  <div class="container">
    <div class="page-header">
      <h1>
        <spring:message code="list.title" />
      </h1>
    </div>

    <style>
.clickme {
    cursor: pointer; 
}
    </style>
    <script>
function order() {
    $('#ord').val(this.id);
    $('#list').submit();
}

$(document).ready(function() {
    $('#SubtotForeign_0920').click(order);

    $('#SubtotForeign_0950').click(order);
    $('#SubtotInvest_0950').click(order);
    $('#Sum_0950').click(order);

    $('#SubtotForeign_1100').click(order);
    $('#SubtotInvest_1100').click(order);
    $('#Sum_1100').click(order);

    $('#SubtotForeign_1320').click(order);
    $('#SubtotInvest_1320').click(order);
    $('#Sum_1320').click(order);

    $('#SubtotForeign_1505').click(order);
    $('#SubtotInvest_1505').click(order);
    $('#Sum_1505').click(order);
});
    </script>

    <form:form commandName="list" action="${pageContext.request.contextPath}/view.do" cssClass="form-horizontal">
      <div class="form-group">
        <label for="fileType" class="col-lg-2 control-label"><spring:message code="upload.filetype" /></label>
        <div class="col-lg-10">
          <label class="radio-inline"> <input type="radio" name="fileType" id="fileType1" value="inv" ${inv}> <spring:message code="upload.filetype.inv" />
          </label> <label class="radio-inline"> <input type="radio" name="fileType" id="fileType2" value="fgn" ${fgn}> <spring:message code="upload.filetype.fgn" />
          </label> <label class="radio-inline"> <input type="radio" name="fileType" id="fileType3" value="fgninv" ${fgninv}> <spring:message code="upload.filetype.fgninv" />
          </label>
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

  <div class="">
    <table class="table table-bordered table-striped table-condensed table-responsive">
      <thead>
        <tr>
          <th rowspan="3">종목(코드)</th>
          <th >09:20</th>
          <th colspan="5">09:50</th>
          <th colspan="5">11:00</th>
          <th colspan="5">13:20</th>
          <th colspan="5">15:05</th>
        </tr>
        <tr>
          <!-- 09:20 -->
          <th >외</th>
          <!-- 09:50 -->
          <th colspan="2">외</th>
          <th colspan="2">기</th>
          <th>&nbsp;</th>
          <!-- 11:00 -->
          <th colspan="2">외</th>
          <th colspan="2">기</th>
          <th>&nbsp;</th>
          <!-- 13:20 -->
          <th colspan="2">외</th>
          <th colspan="2">기</th>
          <th>&nbsp;</th>
          <!-- 15:05 -->
          <th colspan="2">외</th>
          <th colspan="2">기</th>
          <th>&nbsp;</th>
        </tr>
        <tr>
          <!-- 09:20/외 -->
          <th id="SubtotForeign_0920" class="clickme">계 ${SubtotForeign_0920 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 09:50/외 -->
          <th>(+/-)</th>
          <th id="SubtotForeign_0950" class="clickme">계 ${SubtotForeign_0950 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 09:50/기 -->
          <th>(+/-)</th>
          <th id="SubtotInvest_0950" class="clickme">계 ${SubtotInvest_0950 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <th id="Sum_0950" class="success clickme">소계 ${Sum_0950 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 11:00/외 -->
          <th>(+/-)</th>
          <th id="SubtotForeign_1100" class="clickme">계 ${SubtotForeign_1100 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 11:00/기 -->
          <th>(+/-)</th>
          <th id="SubtotInvest_1100" class="clickme">계 ${SubtotInvest_1100 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <th id="Sum_1100" class="success clickme">소계 ${Sum_1100 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 13:20/외 -->
          <th>(+/-)</th>
          <th id="SubtotForeign_1320" class="clickme">계 ${SubtotForeign_1320 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 13:20/기 -->
          <th>(+/-)</th>
          <th id="SubtotInvest_1320" class="clickme">계 ${SubtotInvest_1320 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <th id="Sum_1320" class="success clickme">소계 ${Sum_1320 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 15:05/외 -->
          <th>(+/-)</th>
          <th id="SubtotForeign_1505" class="clickme">계 ${SubtotForeign_1505 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <!-- 15:05/기 -->
          <th>(+/-)</th>
          <th id="SubtotInvest_1505" class="clickme">계 ${SubtotInvest_1505 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
          <th id="Sum_1505" class="success clickme">소계 ${Sum_1505 eq "D" ? "<span class=\"glyphicon glyphicon-sort-by-attributes-alt\"></span>" : ""}</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${lview}" varStatus="status">
        <tr>
          <td>${item['StockName']}<br/>(${item['StockCode']})</td>
          <td align="right">${item['SubtotForeign_0920'] }</td>

          <td align="right">${item['ChangeForeign_0950'] }</td>
          <td align="right">${item['SubtotForeign_0950'] }</td>
          <td align="right">${item['ChangeInvest_0950'] }</td>
          <td align="right">${item['SubtotInvest_0950'] }</td>
          <td align="right" class="success">${item['Sum_0950'] }</td>

          <td align="right">${item['ChangeForeign_1100'] }</td>
          <td align="right">${item['SubtotForeign_1100'] }</td>
          <td align="right">${item['ChangeInvest_1100'] }</td>
          <td align="right">${item['SubtotInvest_1100'] }</td>
          <td align="right" class="success">${item['Sum_1100'] }</td>

          <td align="right">${item['ChangeForeign_1320'] }</td>
          <td align="right">${item['SubtotForeign_1320'] }</td>
          <td align="right">${item['ChangeInvest_1320'] }</td>
          <td align="right">${item['SubtotInvest_1320'] }</td>
          <td align="right" class="success">${item['Sum_1320'] }</td>

          <td align="right">${item['ChangeForeign_1505'] }</td>
          <td align="right">${item['SubtotForeign_1505'] }</td>
          <td align="right">${item['ChangeInvest_1505'] }</td>
          <td align="right">${item['SubtotInvest_1505'] }</td>
          <td align="right" class="success">${item['Sum_1505'] }</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>

