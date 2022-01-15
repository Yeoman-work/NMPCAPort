package net.yeoman.nmpcaport.services;

import net.yeoman.nmpcaport.entities.ZipCodeEntity;
import net.yeoman.nmpcaport.io.response.zipCode.ZipCodeResponse;
import net.yeoman.nmpcaport.shared.dto.ZipCodeDto;

import java.util.List;

public interface ZipCodeService {

    public List<ZipCodeDto> findALl();

    public ZipCodeEntity getZipCodeEntity(String zipCodeId);

    public ZipCodeDto findZipCodeById(String zipCode);

    public List<ZipCodeResponse> createZipCodesFromList(List<String> zipCodes);


    public ZipCodeDto entityToDto(ZipCodeEntity zipCodeEntity);
    public List<ZipCodeDto> entityToDto(List<ZipCodeEntity> zipCodeEntityList);

    public ZipCodeResponse dtoToResponse(ZipCodeDto zipCodeDto);
    public List<ZipCodeResponse> dtoToResponse(List<ZipCodeDto> zipCodeDtoList);

    public Boolean entityIsNull(ZipCodeEntity zipCodeEntity);
    public Boolean entityIsNull(List<ZipCodeEntity> zipCodeEntities);

    public Boolean dtoIsNull(ZipCodeDto zipCodeDto);
    public Boolean dtoIsNull(List<ZipCodeDto> zipCodeDtoList);
}
