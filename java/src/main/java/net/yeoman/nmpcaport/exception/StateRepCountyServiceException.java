package net.yeoman.nmpcaport.exception;

import net.yeoman.nmpcaport.entities.StateRepCountyEntity;
import org.springframework.data.repository.CrudRepository;



public class StateRepCountyServiceException extends RuntimeException {

    private static final long serialVersionUID = 1423852156571449780L;

    public StateRepCountyServiceException(String message) {
        super(message);
    }
}
