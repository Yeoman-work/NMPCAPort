

const setIndex = (searchObj) =>{

    

    switch(searchObj.range){

        case '10':
            searchObj.endIndex = (searchObj.startIndex + 10) - 1;
            break;

        case '25':
            searchObj.endIndex = (searchObj.startIndex + 25) - 1;
            break;
        
        case '50':
            searchObj.endIndex = (searchObj.startIndex + 50) - 1;
            break;
        
        default:
            searchObj.endIndex = (searchObj.startIndex + 10) - 1;
    }

    return searchObj;

}


const indexBackward = (searchObj) =>{
    
    let returnValue;

    switch(searchObj.listSize){

        case '10':
            let endIndex = (searchObj.startIndex - 1);
            returnValue = endIndex - 9;
            break;

        case '25':
            returnValue = (searchObj.startIndex + 25) - 1;
            break;
        
        case '50':
            returnValue = (searchObj.startIndex + 50) - 1;
            break;
        
        default:
            returnValue = (searchObj.startIndex + 10) - 1;
    }

    return returnValue;
}

const next = (searchInputObj) =>{
    
    let returnValue = {...searchInputObj}

    if(searchInputObj.next){

        searchInputObj.startIndex = searchInputObj.endIndex + 1;
        searchInputObj.endIndex = setIndex(searchInputObj);
        
    }
    
} 


const previous = (searchObj) =>{

    let returnValue = {...searchObj}

    if(searchObj.previous){

        searchObj.startIndex = indexBackward(searchObj)
        searchObj.endIndex = setIndex(searchObj);
    }

    return searchObj
}


module.exports={
    setIndex,
    previous
}

