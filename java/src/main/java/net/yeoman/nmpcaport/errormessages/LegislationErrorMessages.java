package net.yeoman.nmpcaport.errormessages;

public enum LegislationErrorMessages {


    LEGISLATION_NAME_LENGTH("must be between 3 and 25 characters"),
    LEGISLATION_DESCRIPTION_LENGTH("must be 700 characters or less");

    private String errorMessage;

    LegislationErrorMessages(String errorMessage){

        this.errorMessage = errorMessage;
    }

    public String getLegislationErrorMessage(){

        return this.errorMessage;
    }

    public void setLegislationErrorMessage(String errorMessage){

        this.errorMessage = errorMessage;
    }
}
