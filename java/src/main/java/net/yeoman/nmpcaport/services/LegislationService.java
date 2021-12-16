package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.LegislationEntity;
import net.yeoman.nmpcaport.shared.dto.LegislationDto;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public interface LegislationService {

    public LegislationDto getLegislation(String legislationId);
    public LegislationDto createLegislation(LegislationDto legislationDto);
    public LegislationDto updateLegislation(String legislationId, LegislationDto legislationDto);
    public LegislationDto deleteLegislation(String legislationId);
    public LegislationEntity getLegislativeEntity(String legislationId);
    public List<LegislationDto> getLegislationList();


}
