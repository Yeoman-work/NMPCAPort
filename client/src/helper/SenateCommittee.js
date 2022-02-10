

const changeMembership = (e, commiteeState) =>{

    let committeeObj = JSON.parse(JSON.stringify(commiteeState));

    if(committeeObj.senatorIds.hasOwnProperty(e.target.value)){

        delete committeeObj.senatorIds[e.target.value]

    }else if(!commiteeState.senatorIds.hasOwnProperty(e.target.value)){

        committeeObj.senatorIds[e.target.value] = e.target.value;
    }
    

    return committeeObj;
}



module.exports={
    changeMembership
}