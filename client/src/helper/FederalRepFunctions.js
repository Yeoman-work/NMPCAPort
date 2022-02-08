const {
    fieldLengthRequired,
    fieldLengthNotRequired, 
    emailValidation, 
    inputChangeField,
    isValidCharacter
} = require('../helper/generalFunctions')

const submitRep = (repState) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 25, repState.firstName)){
        if(fieldLengthRequired(3, 25, repState.lastName)){
            if(fieldLengthNotRequired(0, 120, repState.email)){
                if(repState.email.length > 0){
                    if(emailValidation(repState.email)){
                        if(fieldLengthNotRequired(0, 250, repState.picture)){
                            if(fieldLengthNotRequired(0, 300, repState.website)){

                                isValid = true;
                            }
                        }
                    }
                }else{

                    if(fieldLengthNotRequired(0, 250, repState.picture)){
                        if(fieldLengthNotRequired(0, 300, repState.website)){

                            isValid = true;
                        }
                    }

                }
            }
        }
    }

    return isValid;
}


const submitSen = (senator) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 25, senator.firstName)){
        if(fieldLengthRequired(3, 25, senator.lastName)){
            if(fieldLengthNotRequired(0, 120, senator.email)){
                if(senator.email.length > 0){
                    if(emailValidation(senator.email)){
                        if(fieldLengthNotRequired(0, 250, senator.picture)){
                            if(fieldLengthNotRequired(0, 300, senator.website)){

                                isValid = true;
                            }
                        }
                    }
                }else{

                    if(fieldLengthNotRequired(0, 250, senator.picture)){
                        if(fieldLengthNotRequired(0, 300, senator.website)){

                            isValid = true;
                        }
                    }
                }

            }
        }
    }

    return isValid;
}


const federalValidation =(e, repState)=>{

    let repStateObj = JSON.parse(JSON.stringify(repState));

    if(e.target.value.length === 0){

        repStateObj[e.target.name] = e.target.value;

    }else if (e.target.name === 'firstName'){

        if(isValidCharacter(e.target.value)){
            if(e.target.value.length <= 50){

                repStateObj[e.target.name] = e.target.value;
            }
        }

        
    }else if(e.target.name === 'lastName'){

    }else if(e.target.name === 'email'){

    }else if(e.target.name === 'picture'){

    }else if(e.target.name === 'website'){

    }else if(e.target.name === 'politicalParty'){

    }else if(e.target.name === 'elected'){

    }
}

module.exports={

    submitRep,
    submitSen
}
