package net.yeoman.nmpcaport.services.Impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.yeoman.nmpcaport.entities.HouseDistrictEntity;
import net.yeoman.nmpcaport.entities.SiteEntity;
import net.yeoman.nmpcaport.entities.StateRepEntity;
import net.yeoman.nmpcaport.errormessages.ErrorMessages;
import net.yeoman.nmpcaport.exception.HouseDistrictServiceException;
import net.yeoman.nmpcaport.exception.SiteFundingServiceException;
import net.yeoman.nmpcaport.exception.SiteServiceException;
import net.yeoman.nmpcaport.exception.StateRepServiceException;
import net.yeoman.nmpcaport.io.repositories.HouseDistrictRepository;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequest;
import net.yeoman.nmpcaport.io.request.HouseDistrict.HouseDistrictDetailsRequestList;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictEssentialResponse;
import net.yeoman.nmpcaport.io.response.HouseDistrict.HouseDistrictPagination;
import net.yeoman.nmpcaport.services.HouseDistrictService;
import net.yeoman.nmpcaport.shared.utils.Utils;

@Service
public class HouseDistrictServiceImpl implements HouseDistrictService {



    private final HouseDistrictRepository houseDistrictRepository;
    private final StateRepServiceImpl stateRepService;
    private final Utils utils;

    public HouseDistrictServiceImpl(HouseDistrictRepository houseCommitteeRepository,
                                    @Lazy StateRepServiceImpl stateRepService,
                                    Utils utils
                                    ){

        this.houseDistrictRepository = houseCommitteeRepository;
        this.stateRepService = stateRepService;
        this.utils = utils;
    }


    @Override
    public void deleteHouseDistrict(String publicId) {

        this.houseDistrictRepository.delete(this.getHouseDistrict(publicId));
    }

    @Override
    public void updateHouseDistrict(String publicId, HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

    }

    


    @Override
	public List<HouseDistrictEntity> houseDistrictSearch(String name) {
		
		return this.houseDistrictRepository.findByNameContaining(name);
	}


    //pagination
    //general district pull
	@Override
	public HouseDistrictPagination getDistrictPageInfo(int pageNo, int limit) {
		
		PageRequest pageRequest = PageRequest.of(pageNo, limit);
		
		Page<HouseDistrictEntity> districtPage = this.houseDistrictRepository.findAll(pageRequest);
		
		HouseDistrictPagination pageEssentials = new HouseDistrictPagination();
		
		pageEssentials.setFirstPage(districtPage.isFirst());
		pageEssentials.setHasContent(districtPage.hasContent());
		pageEssentials.setIsEmpty(districtPage.isEmpty());
		pageEssentials.setLastPage(districtPage.isLast());
		pageEssentials.setNext(districtPage.hasNext());
		pageEssentials.setNumber(districtPage.getNumber());
		pageEssentials.setPrevious(districtPage.getPageable().hasPrevious());
		pageEssentials.setSize(districtPage.getSize());
		pageEssentials.setTotalElements(districtPage.getTotalElements());
		pageEssentials.setTotalPages(districtPage.getTotalPages());
		pageEssentials.setDistricts(this.entityToEssentials(districtPage.getContent()));
		
		return pageEssentials;
	}

	//pagination search
	@Override
	public HouseDistrictPagination getDistrictPageInfoSearch(String name, int startIndex, int endIndex  ) {
		
		if(name == null) throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());
		 
		List<HouseDistrictEntity> houseDistricts = this.houseDistrictSearch(name);
		List<HouseDistrictEssentialResponse> houseEssentials = new ArrayList<>(); 
		
		int limit = endIndex;
		
		if(endIndex > (houseDistricts.size() - 1)) {
			
			limit = houseDistricts.size();
			
		}
		
		 for(int i = startIndex; i < limit; i++) {
			 
			 if(houseDistricts.get(i) == null) break;
			 
			 houseEssentials.add(this.entityToEssentials(houseDistricts.get(i)));
		 }
		 
		 HouseDistrictPagination housePage = new HouseDistrictPagination();
		 
		 housePage.setFirstPage(startIndex == 0);
		 housePage.setHasContent(houseDistricts.size() > 0);
		 housePage.setIsEmpty(houseDistricts.size() == 0);
		 housePage.setLastPage(endIndex >= (houseDistricts.size() - 1));
		 housePage.setNext(endIndex < (houseDistricts.size() - 1));
		 
		 housePage.setNumber(startIndex - ((endIndex - startIndex) + 1));
		 housePage.setPrevious(startIndex > 0);
		 housePage.setSize((endIndex - startIndex));
		 housePage.setTotalElements(Long.valueOf(houseDistricts.size()));
		 
		 int totalPages = houseDistricts.size() / ((endIndex - startIndex) - 1);
		 
		 if((totalPages % 1) != 0) {
			 
			 totalPages++;
		 }
		 
		 housePage.setTotalPages(totalPages);
		 
		 return housePage;
	}



	@Override
    public HouseDistrictEntity saveHouseDistrict(HouseDistrictEntity houseDistrictEntity) {

        return this.houseDistrictRepository.save(houseDistrictEntity);
    }

    @Override
    public HouseDistrictEntity getHouseDistrict(String publicId) {

        return this.houseDistrictRepository.findByHouseDistrictId(publicId);
    }

    @Override
    public List<HouseDistrictEntity> getAllHouseDistrictEntities() {

        return this.houseDistrictRepository.findAll();
    }

    @Override
    public HouseDistrictEntity generateUniqueId(HouseDistrictEntity houseDistrictEntity) {

        if(entityIsNull(houseDistrictEntity))
           throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        houseDistrictEntity.setHouseDistrictId(this.utils.generateRandomID());

        while(this.houseDistrictRepository.existsByHouseDistrictId(houseDistrictEntity.getHouseDistrictId())){

            houseDistrictEntity.setHouseDistrictId(this.utils.generateRandomID());
        }

        return houseDistrictEntity;
    }

    @Override
    public HouseDistrictEntity createHouseDistrict(HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

        if(this.requestIsNull(houseDistrictDetailsRequest))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HouseDistrictEntity houseDistrict = this.generateUniqueId(new HouseDistrictEntity());

        houseDistrict.setName(houseDistrictDetailsRequest.getName());
        houseDistrict.setMap(houseDistrictDetailsRequest.getMap());

        if(houseDistrictDetailsRequest.getRepId() != null){

            StateRepEntity stateRep = this.stateRepService.getStateRepEntity(houseDistrictDetailsRequest.getRepId());

            if(this.stateRepService.entityIsNull(stateRep))
                throw new StateRepServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

            houseDistrict.setStateRep(stateRep);
        }

        if(houseDistrictDetailsRequest.getStateRepDetailsRequest() != null){

            houseDistrict.setStateRep(
                    this.stateRepService.createStateRep(
                            houseDistrictDetailsRequest.getStateRepDetailsRequest()
                    )
            );

        }

        return this.saveHouseDistrict(houseDistrict);
    }

    @Override
    public List<HouseDistrictEntity> createHouseDistrictBulk(HouseDistrictDetailsRequestList houseDistrictDetailsRequestList) {

        if(houseDistrictDetailsRequestList == null)
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEntity> returnValue = new ArrayList<>();

        for(HouseDistrictDetailsRequest request: houseDistrictDetailsRequestList.getHouseDistrictDetailsRequestList()){

            returnValue.add(this.createHouseDistrict(request));

        }

        return returnValue;
    }

    @Override
    public HouseDistrictEssentialResponse entityToEssentials(HouseDistrictEntity houseDistrictEntity) {

        if(this.entityIsNull(houseDistrictEntity))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        HouseDistrictEssentialResponse houseDistrictEssentialResponse = new HouseDistrictEssentialResponse();

        houseDistrictEssentialResponse.setHouseDistrictId(houseDistrictEntity.getHouseDistrictId());
        houseDistrictEssentialResponse.setName(houseDistrictEntity.getName());

        return houseDistrictEssentialResponse;
    }

    @Override
    public List<HouseDistrictEssentialResponse> entityToEssentials(List<HouseDistrictEntity> houseDistrictEntities) {

        if(this.entityIsNull(houseDistrictEntities))
            throw new HouseDistrictServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEssentialResponse> returnValue = new ArrayList<>();

        for(HouseDistrictEntity houseDistrictEntity: houseDistrictEntities){

            returnValue.add(this.entityToEssentials(houseDistrictEntity));
        }
        return returnValue.stream()
                .sorted(Comparator.comparing(houseDistrict ->Integer.parseInt(houseDistrict.getName())))
                .collect(Collectors.toList());
    }

    @Override
    public HouseDistrictEntity getHouseDistrictsFromSites(SiteEntity siteEntity) {

        if(siteEntity == null)
            throw new SiteFundingServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        return siteEntity.getHouseDistrictEntity();
    }

    @Override
    public List<HouseDistrictEntity> getHouseDistrictsFromSites(List<SiteEntity> siteEntities) {

        if(siteEntities == null)
            throw new SiteServiceException(ErrorMessages.RECORD_IS_NULL.getErrorMessage());

        List<HouseDistrictEntity> returnValue = new ArrayList<>();

        for(SiteEntity siteEntity: siteEntities){

            returnValue.add(this.getHouseDistrictsFromSites(siteEntity));
        }

        return returnValue;
    }


    @Override
    public Boolean entityIsNull(HouseDistrictEntity nmHouseDistrictEntity) {
        return nmHouseDistrictEntity == null;
    }

    @Override
    public Boolean entityIsNull(List<HouseDistrictEntity> nmHouseDistrictEntities) {
        return nmHouseDistrictEntities == null;
    }

    @Override
    public Boolean requestIsNull(HouseDistrictDetailsRequest houseDistrictDetailsRequest) {

        return houseDistrictDetailsRequest == null;
    }
    
    
    //end points
    
    //get request
    
    @Override
	public HouseDistrictPagination getHouseDistrictPageInfoEndPoint(int pageNo, int limit) {
		
		return this.getDistrictPageInfo(pageNo, limit);
	}


	@Override
	public HouseDistrictPagination getHouseDistrictPageInfoSearchEndPoint(String name, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return null;
	}


}
