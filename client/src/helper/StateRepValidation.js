const {
    fieldLength, 
    emailValidation,
    isValidCharacter,
    fieldLengthNotRequired
} = require('../helper/generalFunctions')


const canSubmit = (stateRep) =>{

    let isDisabled = true;

    if(!fieldLength(3, 50, stateRep.firstName)){
        if(!fieldLength(3, 50, stateRep.lastName)){
            if(emailValidation(stateRep.email)){
                if(fieldLengthNotRequired(0, 150, stateRep.email)){
                    if(fieldLengthNotRequired(5, 250, stateRep.picture)){
                        if(fieldLengthNotRequired(5, 150, stateRep.streetAddress)){
                            if(fieldLengthNotRequired(0, 8, stateRep.capitolRoom)){
                                if(stateRep.houseDistrict.length > 0 || stateRep.senateDistrict.length > 0){
                                    if(stateRep.party.length > 0){
                                        isDisabled = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    
    return isDisabled;
}

const stateRepInput = (e, stateRep) =>{
    
    let stateRepObj = JSON.parse(JSON.stringify(stateRep));
    
    if(e.target.value.length === 0){

        stateRepObj[e.target.name] = e.target.value;

    }else if(e.target.name === 'firstName'){

        if(isValidCharacter(e.target.value)){
            if(e.target.value.length <= 50){

                stateRepObj[e.target.name] = e.target.value;
            }
        }
        

    }else if(e.target.name === 'lastName'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 50){

                stateRepObj[e.target.name] = e.target.value;
            }
        }
        

    }else if(e.target.name === 'streetAddress'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 150){

                stateRepObj[e.target.name] = e.target.value;
            }
        }
        


    }else if(e.target.name === 'capitolRoom'){

        if(isValidCharacter(e.target.value)){
            if(e.target.value.length <= 8){

                stateRepObj[e.target.name] = e.target.value;
            }
        }
        

    }else if(e.target.name === 'picture'){

        if(e.target.value.length <= 250){

            stateRepObj[e.target.name] = e.target.value
        }

    }else if(e.target.name === 'email'){

        if(e.target.value.length <= 150){

            stateRepObj[e.target.name] = e.target.value;
        }

    }else{

        stateRepObj[e.target.name] = e.target.value;

    }

    return stateRepObj;
}

module.exports={
    canSubmit,
    stateRepInput
}