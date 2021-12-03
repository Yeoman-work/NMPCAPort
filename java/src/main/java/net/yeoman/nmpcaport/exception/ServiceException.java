package net.yeoman.nmpcaport.exception;


public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -1790166116696132620L;

    public ServiceException(String message) {
        super(message);
    }
}
