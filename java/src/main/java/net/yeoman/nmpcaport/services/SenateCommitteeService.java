package net.yeoman.nmpcaport.services;

import java.util.List;

import org.springframework.data.domain.Page;

import net.yeoman.nmpcaport.entities.SenateCommitteeEntity;
import net.yeoman.nmpcaport.io.response.senateCommittee.SenateCommittee;
import net.yeoman.nmpcaport.io.response.senateCommittee.SenateCommitteeEssentials;

public interface SenateCommitteeService {

	//get Senate committee Entity
	SenateCommittee getSenateCommitteeEntity(String publicId);
	
	//delete entity
	void deleteSeanteCommittee(String publicId);
	
	//get page from database
	Page<SenateCommitteeEntity> getSeanteCommitteePageInfo(int PageNo,  int limit);
	
	
	//get page from search
	List<SenateCommitteeEssentials> getSenateCommitteeFromSearch(int startIndex, int endIndex);
	
	
}
