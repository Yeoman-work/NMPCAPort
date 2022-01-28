package net.yeoman.nmpcaport.errormessages;

public enum CityErrorFormatMessages {
	
	
	CITY_ALREADY_EXIST("%s already exist");
	
	
	
	private String formatCityErrorMessage;
	
	CityErrorFormatMessages(String formatCityErrorMessage){
		
		this.formatCityErrorMessage = formatCityErrorMessage;
	}

	
	public String getFormatCityErrorMessage(String messages) {
		return String.format(formatCityErrorMessage, messages);
	}

	public void setFormatCityErrorMessage(String formatCityErrorMessage) {
	 	this.formatCityErrorMessage = formatCityErrorMessage;
	}
	
	

}
