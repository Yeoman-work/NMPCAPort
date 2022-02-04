




const citiesPerPage = (e, cityPerPage)=>{

    let cityPageObj = JSON.parse(JSON.stringify(cityPerPage));
        
        
        cityPageObj.size = Number(e.target.value);
        
        return cityPageObj;
        
}

const citiesPerPageSearch = (e, citySearchParams) =>{
    
    let citySearchParamsObj = JSON.parse(JSON.stringify(citySearchParams));
    
    citySearchParamsObj.size = Number(e.target.value);

    citySearchParamsObj.endIndex = (Number(citySearchParamsObj.startIndex + citySearchParamsObj.size));

    if(citySearchParams.search){
            
        citySearchParams.search = false;

    }else{
        
        citySearchParams.search = true;
    }

    return citySearchParamsObj;
}

const citySearchField = (e, citySearchParams) =>{

    let citySearchObj = JSON.parse(JSON.stringify(citySearchParams));

        citySearchObj[e.target.name] = e.target.value;

        if(citySearchObj.city.length <= 0){

            citySearchObj.cities.cities = [];
            
            if(citySearchParams.search){

                citySearchObj.search = false;

            }else{

                citySearchObj.search = true;
            }
        }


        return citySearchObj;

}


const cityPageableRequest = (cityPage, direction) =>{

    let cityPageObj = JSON.parse(JSON.stringify(cityPage));

    if(direction === 'next'){

        cityPageObj.number = Number(cityPageObj.number + 1);
        
    }
    
    if(direction === 'previous'){

        cityPageObj.number = Number(cityPageObj.number - 1)
    }

    return cityPageObj;
}

const cityPageableCityRequestSearch = (e, citySearchParams, direction)=>{

    console.log('yes')
        let citySearchParamsObj = JSON.parse(JSON.stringify(citySearchParams));

        if(direction === 'previous'){

            citySearchParamsObj.endIndex -= citySearchParamsObj.size;
            citySearchParamsObj.startIndex -= citySearchParamsObj.size;

            

        }else if(direction === 'next'){

            citySearchParamsObj.startIndex = citySearchParamsObj.endIndex + 1;
            citySearchParamsObj.endIndex = citySearchParamsObj.startIndex + (citySearchParamsObj.size - 1);
            console.log('check this out ')
            console.log(citySearchParams);
        

        }else if(e.target.name === 'size'){

            
            citySearchParamsObj.size = Number(e.target.value);
            citySearchParamsObj.startIndex = 0;
            citySearchParamsObj.endIndex = (citySearchParamsObj.size - 1);
            
        }

        return citySearchParamsObj;
}

const citySearchListTransfer = (cityPage)=>{

    let cityPageObj = JSON.parse(JSON.stringify(cityPage));
    cityPageObj.cities = [];
    
    return cityPageObj;

}


module.exports={

    citiesPerPage,
    cityPageableRequest,
    citiesPerPageSearch,
    citySearchField,
    cityPageableCityRequestSearch,
    citySearchListTransfer
}