package valuemakers.app.rentye.util;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ServiceErrorException extends RuntimeException {
    private final List<ObjectError> errors;

    public ServiceErrorException(ObjectError error) {
        super(error.getDefaultMessage());
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public ServiceErrorException(List<ObjectError> errors, String message) {
        super(message);
        this.errors = errors;
    }

    public ServiceErrorException(String message) {
        super(message);
        this.errors = new ArrayList<>();
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    @Override
    public ServiceErrorException initCause(Throwable ex) {
        super.initCause(ex);
        return this;
    }
}
