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

}
