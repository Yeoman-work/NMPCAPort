



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

    searchObj[e.target.name] = e.target.value;
    
    if(searchObj.name.length === 0){

        if(search.search){
            
            searchObj.search = false;

        }else{

            searchObj.search = true;

        }
    }

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