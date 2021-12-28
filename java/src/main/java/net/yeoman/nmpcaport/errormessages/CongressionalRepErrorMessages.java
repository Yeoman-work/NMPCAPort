package net.yeoman.nmpcaport.errormessages;

public enum CongressionalRepErrorMessages {

    FIRST_NAME_LENGTH("First name must be between 3 and 25 characters"),
    LAST_NAME_LENGTH("Last name be between 3 and 25 characters in length"),
    CHECK_EMAIL_LENGTH("Email must be 120 character or less");



    private String errorMessage;

    CongressionalRepErrorMessages(String errorMessage){

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
