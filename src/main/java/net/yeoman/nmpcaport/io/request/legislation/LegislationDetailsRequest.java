package net.yeoman.nmpcaport.io.request.legislation;

import javax.persistence.Lob;
import javax.validation.constraints.Size;

public class LegislationDetailsRequest {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
