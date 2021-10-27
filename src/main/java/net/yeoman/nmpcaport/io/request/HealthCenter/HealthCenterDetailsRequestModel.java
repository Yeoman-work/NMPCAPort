package net.yeoman.nmpcaport.io.request.HealthCenter;

import java.util.List;

public class HealthCenterDetailsRequestModel {

    private String name;
    private String nameAbbr;
    private List<String> siteIds;
    private List<String> contactIds;
    private List<String> userIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public List<String> getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(List<String> siteIds) {
        this.siteIds = siteIds;
    }

    public List<String> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<String> contactIds) {
        this.contactIds = contactIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
