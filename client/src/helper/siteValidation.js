



const cityIdValidation = (cityList, value) =>{

    let isValid = false;
    console.log(isValid)
    for(let i = 0; i < cityList.length; i++){

        if(cityList[i].cityId === value){
            console.log('city test')
            isValid = true;
            break;
        }
    }

    return isValid
}

const countyIdValidation = (countyList, value)=>{

    let isValid = false;

    for(let i = 0; i < countyList.length; i++){

        if(countyList[i].countyId === value){

            isValid = true;
            break;
        }
    }

    return isValid;
}


const zipCodeIdValidation = (zipCodeList, value)=>{

    let isValid = false;

    for(let i = 0; i < zipCodeList.length; i++){

        if(zipCodeList[i].zipCodeId === value){

            isValid = true;
            break;
        }
    }

    return isValid;
}


const senateDistrictIdValidationNotRequired = (districtList, value)=>{

    let isValid = true;
    let idList = [];

    for(let i = 0; i < districtList.length; i++){

        idList.push(districtList[i].senateDistrictId);

    }

    if(value.length > 0){

        if(!idList.includes(value)){

            isValid = false;
        }
    }

    return isValid;
}


const houseDistrictIdValidationNotRequired = (districtList, value)=>{

    let isValid = true;
    let idList = [];

    for(let i = 0; i < districtList.length; i++){

        idList.push(districtList[i].houseDistrictId)
    }

    if(value.length > 0){

        if(!idList.includes(value)){

            isValid = false;
        }
    }


    return isValid;
}


const congressionalDistrictNotRequired = (districtList, value)=>{

    let isValid = true;
    let idList = [];

    for(let i = 0; i < districtList.length; i++){

        idList.push(districtList[i].congressionalDistrictId)
    }

    if(value.length > 0){

        if(!idList.includes(value)){

            isValid = false
        }
    }

    return isValid;
}

const addSite = (siteJson, formData) =>{

    let disable = true;

    if(siteJson.name.length >=3 && siteJson.name.length <=25){

        if(siteJson.streetAddress.length >= 5 && siteJson.streetAddress.length <= 50){

            if(formData.city_list){
                if(cityIdValidation(formData.city_list, siteJson.city)){

                    if(formData.county_list){
                        if(countyIdValidation(formData.county_list, siteJson.county)){

                            if(formData.zipCode_list){
                                if(zipCodeIdValidation(formData.zipCode_list, siteJson.zipCode)){
                                    console.log('here')
                                    if(houseDistrictIdValidationNotRequired(formData.nmHouse_districts, siteJson.nmHouseDistrict)){

                                        if(senateDistrictIdValidationNotRequired(formData.senate_districts, siteJson.senateDistrict)){

                                            if(congressionalDistrictNotRequired(formData.congressional_districts, siteJson.congressionalDistrict)){

                                                disable = false;

                                            }

                                        }


                                    }


                                }
                            }

                        }
                    }
                }
            }

        }
    }

    return disable;

}


module.exports={
    addSite,
    zipCodeIdValidation,
    countyIdValidation,
    cityIdValidation,
    congressionalDistrictNotRequired,
    senateDistrictIdValidationNotRequired,
    houseDistrictIdValidationNotRequired
}