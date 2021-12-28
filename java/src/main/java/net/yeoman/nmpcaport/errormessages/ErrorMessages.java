package net.yeoman.nmpcaport.errormessages;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required Fields"),
    RECORD_ALREADY_EXISTS("record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    FAILED_TO_SAVE_RECORD("Failed to save records"),
    Email_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
    DISTRICT_NOT_VALID("Not a valid district"),
    EMAIL_VALIDATION("Please enter a valid email address"),
    NO_PARTY_AFFILIATION("Party affiliation required"),
    EMAIL_CHARACTER_LENGTH("Email must be 150 characters and below"),
    PICTURE_LENGTH("Picture URL must be 250 characters or less"),
    WEBSITE_URL("Website URL must be 300 characters or less");

    private String errorMessage;

    ErrorMessages(String errorMessage){

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){

        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage){

        this.errorMessage = errorMessage;
    }
}
