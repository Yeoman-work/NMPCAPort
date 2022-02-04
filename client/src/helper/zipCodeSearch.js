const {numberValidation} = require('../helper/generalFunctions')



const adjustZipCodesPerSearchPage = (e, zipCodeSearch) =>{

        let searchObj = JSON.parse(JSON.stringify(zipCodeSearch));
        
        searchObj.size = Number(e.target.value);
        
        searchObj.endIndex = (searchObj.startIndex + searchObj.size) - 1; 

        return searchObj;
}

const adjustZipCodesPerPage = (e, zipCodePage) =>{

    let zipCodePageObj = JSON.parse(JSON.stringify(zipCodePage));
    
    zipCodePageObj.size = Number(e.target.value);

    return zipCodePageObj;

}

const zipCodeSearchField =(e, search)=>{

    let searchObj = JSON.parse(JSON.stringify(search));

    let returnValue;

    if(numberValidation(e.target.value)){
        
        if(e.target.value.length <= 5){

            
            searchObj[e.target.name] = e.target.value;
            returnValue = searchObj;
        
        }else{

            returnValue = searchObj;
        }
        

    }else if (e.target.value.length === 0){

        searchObj.name = ''.trim();
        
        if(search.search){
                
            searchObj.search = false;

        }else{

            searchObj.search = true;

        }

        returnValue = searchObj;

    }else{
        
        
        returnValue = searchObj;
    }


    return returnValue;

}

const zipCodePaging = (zipCodePage, direction)=>{


    let zipCodeObj = JSON.parse(JSON.stringify(zipCodePage));
    zipCodeObj.size = Number(zipCodeObj.size);
    
    if(direction === 'next'){

        if(zipCodePage.next){
            
            zipCodeObj.number = Number(zipCodeObj.number) + 1;
            
            
        }
    }

    if(direction === 'previous'){

        if(zipCodePage.previous){
            
            zipCodeObj.number = Number(zipCodeObj.number) - 1;
            
        }
    }

    return zipCodeObj;
}


const zipCodeSearchParams = (e, direction, searchParams)=>{
    
    
        let searchObj= JSON.parse(JSON.stringify(searchParams));

        
        if(direction === 'previous'){
            
            searchObj.endIndex -= searchObj.size;
            searchObj.startIndex -= searchObj.size;
            

        }else if(direction === 'next'){

            searchObj.startIndex = searchObj.endIndex + 1;
            searchObj.endIndex = searchObj.startIndex + (searchObj.size - 1);
            

        }else if(e.target.name === 'size'){

            
            searchObj.size = Number(e.target.value);
            searchObj.startIndex = 0;
            searchObj.endIndex = (searchObj.size - 1);
        
        }

        return searchObj

        
}

const zipCodeSearchFieldTransfer = (zipCodePage) =>{

    let zipCodePageObj = JSON.parse(JSON.stringify(zipCodePage));

    zipCodePageObj.zipCodes = [];

    return zipCodePageObj;

}





module.exports={

    adjustZipCodesPerSearchPage,
    adjustZipCodesPerPage,
    zipCodeSearchField,
    zipCodePaging,
    zipCodeSearchParams,
    zipCodeSearchFieldTransfer,
}