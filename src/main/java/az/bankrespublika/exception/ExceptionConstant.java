package az.bankrespublika.exception;

public class ExceptionConstant {
    public final Integer SUCCESS_CODE = 200;
    public final Integer ERROR_CODE = 201;
    public final Integer NOT_ADDED = 202;

    public final Integer ACCOUNT_EXISTS = 203;
    public final Integer NOT_FOUND = 204;

    public final Integer NOT_CORRECT = 205;

    public final Integer INVALID_USERNAME_OR_PASSWORD = 206;
    public final Integer INVALID_DATA = 207;

    public Integer getSUCCESS_CODE() {
        return SUCCESS_CODE.intValue();
    }

    public Integer getERROR_CODE() {
        return ERROR_CODE.intValue();
    }

    public Integer getACCOUNT_EXISTS() {
        return ACCOUNT_EXISTS.intValue();
    }

    public Integer getNOT_ADDED() {
        return NOT_ADDED.intValue();
    }

    public Integer getNOT_FOUND() {
        return NOT_FOUND.intValue();
    }

    public Integer getNOT_CORRECT() {
        return NOT_CORRECT.intValue();
    }

    public Integer getINVALID_USERNAME_OR_PASSWORD() {
        return INVALID_USERNAME_OR_PASSWORD.intValue();
    }

    public Integer getINVALID_DATA() {
        return INVALID_DATA.intValue();
    }
}