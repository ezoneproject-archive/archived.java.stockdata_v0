<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<body>
  <%@ include file="/WEB-INF/jsp/menu.jsp"%>
  <div class="container">
    <div class="page-header">
      <h1>
        <spring:message code="upload.title" />
        <small><spring:message code="upload.sub" /></small>
      </h1>
    </div>

    <form:form commandName="upload" action="${pageContext.request.contextPath}/uploadproc.do" enctype="multipart/form-data" cssClass="form-horizontal">
      <div class="form-group">
        <label for="fileType" class="col-lg-2 control-label"><spring:message code="upload.filetype" /></label>
        <div class="col-lg-10">
          <div class="radio">
            <label> <input type="radio" name="fileType" id="fileType1" value="inv" checked> <spring:message code="upload.filetype.inv" />
            </label>
          </div>
          <div class="radio">
            <label> <input type="radio" name="fileType" id="fileType2" value="fgn"> <spring:message code="upload.filetype.fgn" />
            </label>
          </div>
          <div class="radio">
            <label> <input type="radio" name="fileType" id="fileType3" value="fgninv"> <spring:message code="upload.filetype.fgninv" />
            </label>
          </div>

          <div class="radio">
            <label> <input type="radio" name="fileType" id="fileType4" value="strmov"> <spring:message code="upload.filetype.strmov" />
            </label>
          </div>
          <div class="radio">
            <label> <input type="radio" name="fileType" id="fileType5" value="tr20"> <spring:message code="upload.filetype.tr20" />
            </label>
          </div>

          <form:errors path="fileType" element="div" cssClass="alert alert-danger" />
        </div>
      </div>

      <div class="form-group">
        <label for="gsdate" class="col-lg-2 control-label"><spring:message code="upload.gsdate" /></label>
        <div class="col-lg-10">
          <input type="text" name="gsdate" class="form-control" id="datepicker" value="${gsdate}" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd">
          <form:errors path="gsdate" element="div" cssClass="alert alert-danger" />
     <script type="text/javascript">
     $(function () {
         $('#datepicker').datepicker()
     });
     </script>
        </div>
      </div>
      <div class="form-group">
        <label for="timeType" class="col-lg-2 control-label"><spring:message code="upload.timetype" /></label>
        <div class="col-lg-10">
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType1" value="0920" checked>09:20</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType2" value="0950">09:50</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType3" value="1100">11:00</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType4" value="1320">13:20</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType5" value="1505">15:05</label>
          <br/>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType1a" value="0930">09:30</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType2a" value="1000">10</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType3a" value="1100">11</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType4a" value="1200">12</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType5a" value="1300">13</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType6a" value="1400">14</label>
          <label class="radio-inline"> <input type="radio" name="timeType" id="timeType7a" value="1530">종가</label>
          <form:errors path="timeType" element="div" cssClass="alert alert-danger" />
        </div>
      </div>

      <div class="form-group">
        <label for="uploadFile" class="col-lg-2 control-label"><spring:message code="upload.selectFile" /></label>
        <div class="col-lg-10">
          <input type="file" class="form-control" id="uploadFile" placeholder="Upload" name="uploadFile">
          <form:errors path="uploadFile" element="div" cssClass="alert alert-danger" />
        </div>
      </div>

      <c:if test="${upload_error eq 'y'}">
        <div class="form-group">
          <div class="col-lg-offset-2 col-lg-10">
            <div class="alert alert-warning">
              <label> <input type="checkbox" name="validCheck" value="skip"> <spring:message code="upload.validskip" />
              </label>
            </div>
          </div>
        </div>
      </c:if>

      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <button type="submit" class="btn btn-primary">
            <span class="glyphicon glyphicon-upload"> <spring:message code="upload.uploadBtn" /></span>
          </button>
        </div>
      </div>
    </form:form>

  </div>
  <%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>

