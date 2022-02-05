package net.yeoman.nmpcaport.services.Impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.yeoman.nmpcaport.entities.SenateDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.SenateDistrictServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.io.repositories.SenateDistrictRepository;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictEssentialsPagination;
import net.yeoman.nmpcaport.io.response.senateDistrict.SenateDistrictNestedResponse;
import net.yeoman.nmpcaport.io.response.stateSenator.StateSenatorNestedResponse;
import net.yeoman.nmpcaport.services.SenateDistrictService;
import net.yeoman.nmpcaport.shared.dto.SenateDistrictDto;
import net.yeoman.nmpcaport.shared.utils.Utils;

@Service
public class SenateDistrictServiceImpl implements SenateDistrictService {


    private final SenateDistrictRepository senateDistrictRepository;
    private final Utils utils;

    public SenateDistrictServiceImpl(SenateDistrictRepository senateDistrictRepository,
                                     Utils utils
    ){
        this.senateDistrictRepository = senateDistrictRepository;
        this.utils = utils;
    }

    @Override
    public SenateDistrictDto getDistrict(String districtId) {

        SenateDistrictEntity senateDistrictEntity = this.senateDistrictRepository.findBySenateDistrictId(districtId);
        SenateDistrictDto senateDistrictDto = new ModelMapper().map(senateDistrictEntity, SenateDistrictDto.class);
        if(senateDistrictDto.getStateSenator() != null){

            senateDistrictDto.setStateSenatorNestedResponse(new ModelMapper().map(senateDistrictDto.getStateSenator(), StateSenatorNestedResponse.class));
        }

        return senateDistrictDto;
    }


    @Override
    public SenateDistrictDto createDistrict(SenateDistrictDto senateDistrictDto) {

        SenateDistrictEntity senateDistrict = new ModelMapper().map(senateDistrictDto, SenateDistrictEntity.class);

        senateDistrict.setSenateDistrictId(utils.generateRandomID());
        senateDistrict.setElectionDate(utils.initialStateSenateTerm());

        if(utils.isSenateDistrict(senateDistrict.getName())){
            if(this.senateDistrictRepository.existsByName(senateDistrict.getName())){
                throw new RuntimeException("Senate District already exist");
            }

            this.senateDistrictRepository.save(senateDistrict);

        }else{

            throw new RuntimeException(senateDistrict.getName() + " is not a valid district");
        }

        return new ModelMapper().map(senateDistrict, SenateDistrictDto.class);
    }




    @Override
    public SenateDistrictDto updateDistrict(String senateDistrictId, SenateDistrictDto senateDistrictDto) {
        return null;
    }


    @Override
    public SenateDistrictDto deleteDistrict(String senateDistrictId) {
        return null;
    }

    @Override
    public SenateDistrictEntity findSenateDistrictEntity(String senateDistrictId){

        return this.senateDistrictRepository.findBySenateDistrictId(senateDistrictId);
    }


    @Override
    public List<SenateDistrictDto> createBulkSenateDistrict(List<SenateDistrictDto> senateDistrictDtoList) {
        List<SenateDistrictDto> returnValue = new ArrayList<>();

        for(SenateDistrictDto senateDistrictDto: senateDistrictDtoList){

            SenateDistrictEntity preSaveEntity = new ModelMapper().map(senateDistrictDto, SenateDistrictEntity.class);

            preSaveEntity.setSenateDistrictId(utils.generateRandomID());
            SenateDistrictEntity savedEntity = this.senateDistrictRepository.save(preSaveEntity);

            returnValue.add(new ModelMapper().map(savedEntity, SenateDistrictDto.class));
        }

        return returnValue;
    }

    @Override
    public List<SenateDistrictEntity> getAllDistrictEntities() {

        return this.senateDistrictRepository.findAll();
    }

    @Override
    public List<SenateDistrictEssentialResponse> getAllDistrictEssentials() {

        List<SenateDistrictEntity> senateDistrictEntities = this.getAllDistrictEntities();

        return this.entitiesToEssentials(senateDistrictEntities);
    }

    @Override
    public SenateDistrictEssentialResponse entityToEssentials(SenateDistrictEntity senateDistrictEntity) {

        if(this.entityIsNull(senateDistrictEntity))
            throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        SenateDistrictEssentialResponse senateDistrict = new SenateDistrictEssentialResponse();

        senateDistrict.setName(senateDistrictEntity.getName());
        senateDistrict.setSenateDistrictId(senateDistrictEntity.getSenateDistrictId());

        return senateDistrict;
    }

    @Override
    public List<SenateDistrictEssentialResponse> entitiesToEssentials(List<SenateDistrictEntity> senateDistrictEntities) {

        if(this.entityIsNull(senateDistrictEntities))
            throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(SenateDistrictEntity district: senateDistrictEntities){

            returnValue.add(this.entityToEssentials(district));
        }

        return returnValue.stream()
        		.sorted(Comparator.comparing(district ->Integer.parseInt(district.getName())))
        		.collect(Collectors.toList());
    }
    
    
    

    @Override
	public SenateDistrictEssentialsPagination getSenateDistrictPageInfo(int pageNo, int limit) {
		
    	PageRequest pageRequest = PageRequest.of(pageNo, limit);
    	
    	Page<SenateDistrictEntity> pageEntity = this.senateDistrictRepository.findAll(pageRequest);
    	
    	SenateDistrictEssentialsPagination pageEssentials = new SenateDistrictEssentialsPagination();
    	
    	pageEssentials.setFirstPage(pageEntity.isFirst());
    	pageEssentials.setHasContent(pageEntity.isLast());
    	pageEssentials.setIsEmpty(pageEntity.isEmpty());
    	pageEssentials.setLastPage(pageEntity.isLast());
    	pageEssentials.setNext(pageEntity.hasNext());
    	pageEssentials.setNumber(pageEntity.getNumber());
    	pageEssentials.setPrevious(pageEntity.hasPrevious());
    	pageEssentials.setSize(pageEntity.getSize());
    	pageEssentials.setTotalElements(pageEntity.getTotalElements());
    	pageEssentials.setTotalPages(pageEntity.getTotalPages());
    	pageEssentials.setDistricts(this.entitiesToEssentials(pageEntity.getContent()));
    	
		return pageEssentials;
	}

	@Override
	public SenateDistrictEssentialsPagination getSenateDistrictpageInfoSearch(String name, int startIndex, int endIndex){
		
		if(name == null) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		
		List<SenateDistrictEntity> senateDistrictEntity = this.senateDistrictRepository.findByNameContaining(name);
		
		int limit = endIndex;
		
		if(endIndex > (senateDistrictEntity.size() - 1)){
			
			limit = senateDistrictEntity.size();
		}
		
		SenateDistrictEssentialsPagination senateDistrictEssentialsPagination = new SenateDistrictEssentialsPagination();
		
		List<SenateDistrictEssentialResponse> senateEssentials = new ArrayList<>();
		
		for(int i = startIndex; i < limit; i++ ) {
			
			if(senateDistrictEntity.get(i) == null) break;
			
			senateEssentials.add(this.entityToEssentials(senateDistrictEntity.get(i)));
		
		}
		
		senateDistrictEssentialsPagination.setDistricts(senateEssentials);
		senateDistrictEssentialsPagination.setFirstPage(startIndex == 0);
		senateDistrictEssentialsPagination.setHasContent(senateEssentials.size() > 0);
		senateDistrictEssentialsPagination.setIsEmpty(senateEssentials.size() == 0);
		senateDistrictEssentialsPagination.setLastPage(endIndex >= senateDistrictEntity.size() - 1);
		senateDistrictEssentialsPagination.setNext(endIndex < senateDistrictEntity.size() - 1);
		senateDistrictEssentialsPagination.setNumber(startIndex / ((endIndex - startIndex) - 1));
		senateDistrictEssentialsPagination.setPrevious(startIndex > 0);
		senateDistrictEssentialsPagination.setSize(endIndex - startIndex);
		senateDistrictEssentialsPagination.setTotalElements(Long.valueOf(senateDistrictEntity.size()));
		
		double totalPages = (double) senateDistrictEntity.size() / (double)(endIndex - startIndex);
		if(totalPages > 0) {
			
			if((totalPages % 1) != 0) {
				
				totalPages++;
			}
			
		}else {
			
			totalPages = 1;
		}
		
		senateDistrictEssentialsPagination.setTotalPages((int)totalPages);
		
		
		return senateDistrictEssentialsPagination;
		
	}

	@Override
    public SenateDistrictEntity getSenateDistrictEntitiesFromSites(SiteEntity siteEntity) {

        if(siteEntity == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteEntity.getSenateDistrictEntity();
    }

    @Override
    public List<SenateDistrictEntity> getSenateDistrictEntitiesFromSites(List<SiteEntity> siteEntities) {

        if(siteEntities == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictEntity> returnValue = new ArrayList<>();

        for(SiteEntity site: siteEntities){

            if(site.getSenateDistrictEntity() != null){

                if(!returnValue.contains(site.getSenateDistrictEntity())){

                    returnValue.add(site.getSenateDistrictEntity());
                }
            }

        }

        return returnValue;
    }

    @Override
    public SenateDistrictDto entityToDto(SenateDistrictEntity senateDistrictEntity) {

        if(this.entityIsNull(senateDistrictEntity)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(senateDistrictEntity, SenateDistrictDto.class);
    }

    @Override
    public List<SenateDistrictDto> entityToDto(List<SenateDistrictEntity> senateDistrictEntities) {

        if(this.entityIsNull(senateDistrictEntities)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictDto> returnValue = new ArrayList<>();

        for(SenateDistrictEntity senateDistrictEntity: senateDistrictEntities){

            returnValue.add(this.entityToDto(senateDistrictEntity));
        }

        return returnValue;
    }

    @Override
    public SenateDistrictNestedResponse dtoToNestedResponse(SenateDistrictDto senateDistrictDto) {

        if(this.dtoIsNull(senateDistrictDto)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return this.utils.objectMapper().map(senateDistrictDto, SenateDistrictNestedResponse.class);
    }

    @Override
    public List<SenateDistrictNestedResponse> dtoToNestedResponse(List<SenateDistrictDto> senateDistrictDtoList) {

        if(this.dtoIsNull(senateDistrictDtoList)) throw new SenateDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<SenateDistrictNestedResponse> returnValue = new ArrayList<>();

        for(SenateDistrictDto senateDistrictDto: senateDistrictDtoList){

            returnValue.add(this.dtoToNestedResponse(senateDistrictDto));
        }

        return returnValue;
    }

    @Override
    public Boolean entityIsNull(SenateDistrictEntity senateDistrictEntity) {
        return senateDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<SenateDistrictEntity> senateDistrictEntities) {
        return senateDistrictEntities == null;
    }

    @Override
    public Boolean dtoIsNull(SenateDistrictDto senateDistrictDto) {
        return senateDistrictDto == null;
    }

    @Override
    public Boolean dtoIsNull(List<SenateDistrictDto> senateDistrictDtoList) {
        return senateDistrictDtoList == null;
    }
    
    //end points
    // getMapping
	
    @Override
	public SenateDistrictEssentialsPagination getSenateDistrictPageInfoEndPoint(int pageNo, int limit) {
		
		return this.getSenateDistrictPageInfo(pageNo, limit);
	}

	@Override
	public SenateDistrictEssentialsPagination getSenateDistrictPageInfoSearchEndPoint(String name, int pageNo, int limit) {
		
		return this.getSenateDistrictpageInfoSearch(name, pageNo, limit);
	}
    
    
    
}
