package net.yeoman.nmpcaport.io.response.interimCommittee;

import java.util.HashMap;

public class InterimCommitteeEssentialsWithMembers {

    private String publicId;
    private String name;
    private HashMap<String, String> senatorIds = new HashMap<String, String>();
    private HashMap<String, String> repIds = new HashMap<String, String>();
    
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashMap<String, String> getSenatorIds() {
		return senatorIds;
	}
	public void setSenatorIds(HashMap<String, String> senatorIds) {
		this.senatorIds = senatorIds;
	}
	public HashMap<String, String> getRepIds() {
		return repIds;
	}
	public void setRepIds(HashMap<String, String> repIds) {
		this.repIds = repIds;
	}
	
	

	 
}
