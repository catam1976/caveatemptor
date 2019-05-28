package ro.clovertech.backend.resources;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    private static final Logger logger = Logger.getLogger(RuntimeExceptionMapper.class.getName());

    @Override
    public Response toResponse(RuntimeException exception) {
        logger.log(Level.SEVERE, exception.getMessage(), exception);

        return Response
                .serverError()
                .entity("internal error")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

}
