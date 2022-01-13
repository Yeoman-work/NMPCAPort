package net.yeoman.nmpcaport.io.response.contact;

import java.util.List;

public class ContactFormListResponse {

    List<ContactNestedResponseModel> contactNestedResponses;
    List<String> memberIds;
    List<String> nonMemberIds;

    public void setContactNestedResponses(List<ContactNestedResponseModel> contactNestedResponses) {
        this.contactNestedResponses = contactNestedResponses;
    }

    public List<ContactNestedResponseModel> getContactNestedResponses() {
        return contactNestedResponses;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    public List<String> getNonMemberIds() {
        return nonMemberIds;
    }

    public void setNonMemberIds(List<String> nonMemberIds) {
        this.nonMemberIds = nonMemberIds;
    }
}
