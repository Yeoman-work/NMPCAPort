package net.yeoman.nmpcaport.io.response.contact;

import java.util.List;

public class ContactFormListResponse {

    List<ContactNestedResponseModel> contactNestedResponses;


    public void setContactNestedResponses(List<ContactNestedResponseModel> contactNestedResponses) {
        this.contactNestedResponses = contactNestedResponses;
    }

    public List<ContactNestedResponseModel> getContactNestedResponses() {
        return contactNestedResponses;
    }


}
