package stidx.controllers.form;

import org.springframework.web.multipart.MultipartFile;

public class UploadCommand {

    String fileType = "";
    String gsdate = "";
    String timeType = "";
    MultipartFile uploadFile = null;
    String validCheck = "";

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getGsdate() {
        return gsdate;
    }

    public void setGsdate(String gsdate) {
        this.gsdate = gsdate;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getValidCheck() {
        return validCheck;
    }

    public void setValidCheck(String validCheck) {
        this.validCheck = validCheck;
    }

}
