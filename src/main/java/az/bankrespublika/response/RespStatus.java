package az.bankrespublika.response;

public class RespStatus {

    private Integer statusCode;
    private String statusMessage;

    public RespStatus() {
    }

    public RespStatus(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.statusMessage = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}