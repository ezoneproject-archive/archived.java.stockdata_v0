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
        <spring:message code="detail.title" />
      </h1>
    </div>

    <style>
.clickme {
    cursor: pointer; 
}
    </style>

    <form:form commandName="list" action="${pageContext.request.contextPath}/detail.do" cssClass="form-horizontal">
      <div class="form-group">
        <label for="stcode" class="col-lg-2 control-label"><spring:message code="detail.stcodetit" /></label>
        
        <div class="col-md-4">
          <input type="text" name="stcode" class="form-control" id="stcode" value="${stcode}">
          <form:errors path="stcode" element="div" cssClass="alert alert-danger" />
        </div>
      </div>

      <div class="form-group">
        <label for="frtodate" class="col-lg-2 control-label"><spring:message code="detail.datefrto" /></label>
        <div class="col-md-4">
          <input type="text" name="frdate" class="form-control" id="datepicker1" value="${frdate}" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd">
        </div>
        <div class="col-md-1">
        ~
        </div>
        <div class="col-md-4">
          <input type="text" name="todate" class="form-control" id="datepicker2" value="${todate}" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd">
        </div>
        <form:errors path="frtodate" element="div" cssClass="alert alert-danger" />
        <script type="text/javascript">
          $(function() {
            $('#datepicker1').datepicker(),
            $('#datepicker2').datepicker()
          });
        </script>
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
기관잠정치매매  외인잠정치매매  외인기관쌍끌이잠정치매매
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

