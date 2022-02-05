const {numberValidation} = require('./generalFunctions')



const districtPerPageSearch = (e, districtParams) =>{
    

    let districtParamsObj = JSON.parse(JSON.stringify(districtParams));

    districtParamsObj.size = Number(e.target.value);
    districtParamsObj.startIndex = 0;
    districtParamsObj.endIndex = districtParamsObj.size;


    if(districtParams.search){

        districtParamsObj.search = false;
    
    }else{

        districtParamsObj.search = true;

    }

    
    
    return districtParamsObj;

}

const districtsPerPage = (e, districtPage) =>{


    let districtPageObj = JSON.parse(JSON.stringify(districtPage));

    districtPageObj.size = Number(e.target.value);

    return districtPageObj;


}

const districtPaging = (e, direction, districtPage) =>{

    let districtPageObj = JSON.parse(JSON.stringify(districtPage));

    if(direction === 'next'){

        districtPageObj.number = Number(districtPageObj.number + 1);
    }

    if(direction === 'previous'){

        districtPageObj.number = Number(districtPageObj.number - 1);
    }


    
    return districtPageObj

}


const districtPagingSearch = (e, direction, districtPaging) =>{

    let districtPagingSearchObj = JSON.parse(JSON.stringify(districtPaging));

    if(direction === 'previous'){

        districtPagingSearchObj.endIndex =  Number(districtPagingSearchObj.endIndex) - Number(districtPagingSearchObj.size);
        districtPagingSearchObj.startIndex = Number(districtPagingSearchObj.startIndex) - Number(districtPagingSearchObj.size);
    
    }else if(direction === 'next'){

        districtPagingSearchObj.startIndex = Number(districtPagingSearchObj.endIndex);
        districtPagingSearchObj.endIndex = Number(districtPagingSearchObj.endIndex) + Number(districtPagingSearchObj.size);

    }else if(direction === 'size'){

        districtPagingSearchObj.size = Number(e.target.value);
        districtPagingSearchObj.startIndex = 0;
        districtPagingSearchObj.endIndex = districtPagingSearchObj.size - 1;
    }


    return districtPagingSearchObj

}


const districtSearchText = (e, districtState) =>{
    
    let districtObj = JSON.parse(JSON.stringify(districtState));
    
    if(numberValidation(e.target.value)){

        if(e.target.value.length <= 2){
            
            districtObj.district = e.target.value;
        }

    }else if(e.target.value.length === 0){

        districtObj.district = e.target.value;
        districtObj.districts.districts = [];

        if(districtState.search){

            districtObj.search = false;

        }else{

            districtObj.search = true;

        }
    }

    return districtObj;
    
}


const districtTransferList = (districtPage) =>{

    let districtObj = JSON.parse(JSON.stringify(districtPage));

    districtObj.districts = [];

    return districtObj;

}

const districtTransferListSearch = (districtSearchParams)=>{

    let districtObj = JSON.parse(JSON.stringify(districtSearchParams));

    districtObj.districts.districts = []

    return districtObj;
}


module.exports={

    districtPerPageSearch,
    districtPagingSearch,
    districtsPerPage,
    districtPaging,
    districtSearchText,
    districtTransferList
    
}