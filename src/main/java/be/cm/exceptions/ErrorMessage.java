package be.cm.exceptions;

import jakarta.ws.rs.core.Response;

public class ErrorMessage {
    private Response.Status errorCode;
    private String errorMessage;
    private String solution;

    private ErrorMessage(){
        // for Jackson
    }

    public ErrorMessage(Response.Status errorCode, String errorMessage, String solution) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.solution = solution;
    }

    public Response.Status getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSolution() {
        return solution;
    }
}
