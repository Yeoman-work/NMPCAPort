package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.GovernorEntity;
import net.yeoman.nmpcaport.shared.dto.GovernorDto;

public interface GovernorService {

    public GovernorDto getGovernor(String governorId);
    public GovernorDto createGovernor(GovernorDto governorDto);
    public GovernorDto updateGovernor(String governorId, GovernorDto governorDto);
    public GovernorDto deleteGovernor(String governorId);
    public GovernorEntity getGovernorEntity(String governorId);
}
