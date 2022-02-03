



const districtPerPageSearch = (e, districtParams) =>{
    

    let districtParamsObj = JSON.parse(JSON.stringify(districtParams));

    districtParamsObj.size = Number(e.target.value);

    
    return districtParamsObj;

}

const districtsPerPage = (e, districtPage) =>{


    let districtPageObj = JSON.parse(JSON.stringify(districtPage));

    districtPageObj.size = Number(e.target.value);


    console.log('check this');
    console.log(districtPageObj);
    

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


    console.log(districtPageObj.size)
    return districtPageObj

}




module.exports={

    districtPerPageSearch,
    districtsPerPage,
    districtPaging
}