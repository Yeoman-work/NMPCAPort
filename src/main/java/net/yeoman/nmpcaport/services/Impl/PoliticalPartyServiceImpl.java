package net.yeoman.nmpcaport.services.Impl;

import net.yeoman.nmpcaport.entities.PoliticalPartyEntity;
import net.yeoman.nmpcaport.repositories.PoliticalPartyRepository;
import net.yeoman.nmpcaport.services.PoliticalPartyService;
import net.yeoman.nmpcaport.shared.dto.PoliticalPartyDto;
import net.yeoman.nmpcaport.shared.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliticalPartyServiceImpl implements PoliticalPartyService {

    @Autowired
    private PoliticalPartyRepository partyRepository;

    @Autowired
    private Utils utils;

    @Override
    public PoliticalPartyDto getPoliticalParty(String partyId) {
        return new ModelMapper().map(this.partyRepository.findByPartyId(partyId), PoliticalPartyDto.class);
    }

    @Override
    public PoliticalPartyDto createPoliticalParty(PoliticalPartyDto politicalPartyDto) {

        PoliticalPartyEntity politicalParty = new ModelMapper().map(politicalPartyDto, PoliticalPartyEntity.class);

        politicalParty.setPartyId(utils.generateRandomID());

        while(this.partyRepository.existsByPartyId(politicalParty.getPartyId())){

            politicalParty.setPartyId(utils.generateRandomID());
        }

        PoliticalPartyEntity storedPoliticalParty = this.partyRepository.save(politicalParty);

        return new ModelMapper().map(storedPoliticalParty, PoliticalPartyDto.class);
    }

    @Override
    public PoliticalPartyDto updatePoliticalParty(String partyId, PoliticalPartyDto partyDto) {
        return null;
    }

    @Override
    public PoliticalPartyDto deletePoliticalParty(String partyId) {
        return null;
    }

    @Override
    public PoliticalPartyEntity politicalPartyEntity(String partyId) {
        return null;
    }
}
