



const districtPerPageSearch = (e, districtParams) =>{
    

    let districtParamsObj = JSON.parse(JSON.stringify(districtParams));

    districtParamsObj.size = Number(e.target.value);

    console.log('another mod')
    console.log(districtParamsObj);
    return districtParamsObj;

}

const districtsPerPage = (e, districtPage) =>{


    let districtPageObj = JSON.parse(JSON.stringify(districtPage));

    districtPageObj.size = Number(e.target.value);
    

    return districtPageObj;


}




module.exports={

    districtPerPageSearch,
    districtsPerPage
}