package net.yeoman.nmpcaport.errormessages;

public enum StateRepErrorMessage {

    FIRST_NAME_LENGTH("Length must be between 3 and 50 characters"),
    LAST_NAME_LENGTH("Length must be between 3 and 50 characters");

    private String errorMessage;

    StateRepErrorMessage(String errorMessage){

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){

        this.errorMessage = errorMessage;
    }
}
