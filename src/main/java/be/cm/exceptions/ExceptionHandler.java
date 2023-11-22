package be.cm.exceptions;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionHandler {
    @ServerExceptionMapper(value = {IllegalArgumentException.class})
    public RestResponse<ErrorMessage> illegalArgumentException(RuntimeException ex) {
        Response.Status errorCode = Response.Status.BAD_REQUEST;
        return RestResponse.status(errorCode, new ErrorMessage(errorCode, ex.getMessage(), "Provide a valid input (not null, empty, invalid)."));
    }

    @ServerExceptionMapper(value = {NotFoundException.class})
    public RestResponse<ErrorMessage> notFoundException(RuntimeException ex) {
        Response.Status errorCode = Response.Status.NOT_FOUND;
        return RestResponse.status(errorCode, new ErrorMessage(errorCode, ex.getMessage(), "Provide an existing identifier."));
    }

    @ServerExceptionMapper(value = {UnauthorizatedException.class, UnknownUserException.class, WrongPasswordException.class})
    public RestResponse<ErrorMessage> securityServiceException(RuntimeException ex) {
        Response.Status errorCode = Response.Status.UNAUTHORIZED;
        return RestResponse.status(errorCode, new ErrorMessage(errorCode, ex.getMessage(), "provide valid user login."));
    }
}
