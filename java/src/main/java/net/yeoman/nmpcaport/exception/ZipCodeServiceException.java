package net.yeoman.nmpcaport.exception;


public class ZipCodeServiceException extends RuntimeException{

    private static final long serialVersionUID = 6399517883296145690L;

    public ZipCodeServiceException(String message) {
        super(message);
    }
}
