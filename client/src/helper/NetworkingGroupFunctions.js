const {isValidCharacter, fieldLengthRequired, fieldLengthNotRequired} = require('../helper/generalFunctions')


const networkingGroupInputValidation = (e, networkingGroupState) =>{

    let networkingGroupObj =JSON.parse(JSON.stringify(networkingGroupState));

    if(e.target.value.length === 0){

        networkingGroupObj[e.target.name] = e.target.value;

    }else if(e.target.name === 'name'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 50){

                networkingGroupObj[e.target.name] = e.target.value;
            }
        }
    }else if(e.target.name === 'description'){
        
        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 250){

                networkingGroupObj[e.target.name] = e.target.value;
            }
        }
    }

    return networkingGroupObj;
}

const changeMembership = (e, networkingGroupState) =>{

    let networkingGroupObj = JSON.parse(JSON.stringify(networkingGroupState));

    if(networkingGroupObj.memberIds.hasOwnProperty(e.target.value)){

        delete networkingGroupObj.memberIds[e.target.value];

    }else if(!networkingGroupObj.memberIds.hasOwnProperty(e.target.value)){

        networkingGroupObj.memberIds[e.target.value] = e.target.value;
    }

    return networkingGroupObj;

    
}

const networkingGroupIsValid = (networkingGroup) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 50, networkingGroup.name)){
        if(fieldLengthNotRequired(5, 250, networkingGroup.description)){
            
            isValid = true;
        }
    }

    return isValid;
}


module.exports={
    networkingGroupInputValidation,
    changeMembership,
    networkingGroupIsValid

}