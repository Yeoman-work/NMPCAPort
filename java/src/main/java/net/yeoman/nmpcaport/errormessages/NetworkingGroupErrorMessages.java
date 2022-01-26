package net.yeoman.nmpcaport.errormessages;

public enum NetworkingGroupErrorMessages {

    NAME_LENGTH("Name must be between 3 and 50 characters"),
    DESCRIPTION("Description must be between 5 and 250 characters");

    private String errorMessage;

    NetworkingGroupErrorMessages(String errorMessage){

        this.errorMessage =errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
