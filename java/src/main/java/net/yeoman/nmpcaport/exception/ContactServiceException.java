package net.yeoman.nmpcaport.exception;


public class ContactServiceException extends RuntimeException{


    private static final long serialVersionUID = 5941803847126060875L;

    public ContactServiceException(String message) {
        super(message);
    }
}
