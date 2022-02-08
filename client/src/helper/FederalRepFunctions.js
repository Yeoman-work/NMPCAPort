const {
    fieldLengthRequired,
    fieldLengthNotRequired, 
    emailValidation, 
    inputChangeField,
    isValidCharacter
} = require('../helper/generalFunctions')



//validate congressional rep. enables/disables submit button based inputs
const submitRep = (repState) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 25, repState.firstName)){
        if(fieldLengthRequired(3, 25, repState.lastName)){
            if(fieldLengthNotRequired(0, 120, repState.email)){
                if(repState.email.length > 0){
                    if(emailValidation(repState.email)){
                        if(fieldLengthNotRequired(0, 250, repState.picture)){
                            if(fieldLengthNotRequired(0, 300, repState.website)){
                                if(repState.district.length > 0){
                                    if(repState.party.length > 0){
                                        
                                        isValid = true;
                                    }
                                }
                            }
                        }
                    }
                }else{

                    if(fieldLengthNotRequired(0, 250, repState.picture)){
                        if(fieldLengthNotRequired(0, 300, repState.website)){
                            if(repState.district.length > 0){
                                if(repState.party.length){

                                    isValid = true;    
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    return isValid;
}


//validate US senate. enables/disables submit button based inputs
const submitSen = (senator) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 25, senator.firstName)){
        if(fieldLengthRequired(3, 25, senator.lastName)){
            if(fieldLengthNotRequired(0, 120, senator.email)){
                if(senator.email.length > 0){
                    if(emailValidation(senator.email)){
                        if(fieldLengthNotRequired(0, 250, senator.picture)){
                            if(fieldLengthNotRequired(0, 300, senator.website)){
                                if(senator.party.length > 0){
                                    if(senator.elected.length > 0){

                                        isValid = true;
                                    }
                                }
                                
                            }
                        }
                    }
                }else{

                    if(fieldLengthNotRequired(0, 250, senator.picture)){
                        if(fieldLengthNotRequired(0, 300, senator.website)){
                            if(senator.party.length > 0){
                                if(senator.elected.length > 0){
                                    
                                    isValid = true;
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    return isValid;
}

//validates inputs for US senator and congressional rep
const federalValidation =(e, repState)=>{

    let repStateObj = JSON.parse(JSON.stringify(repState));

    if(e.target.value.length === 0){

        repStateObj[e.target.name] = e.target.value;

    }else if (e.target.name === 'firstName'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 25){

                repStateObj[e.target.name] = e.target.value;
            }
        }

        
    }else if(e.target.name === 'lastName'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 25){

                repStateObj[e.target.name] = e.target.value;
            }
        }
    }else if(e.target.name === 'email'){

        if(e.target.value.length <= 150){

            repStateObj[e.target.name] = e.target.value;
        }

    }else if(e.target.name === 'picture'){

        if(e.target.value.length <= 250){

            repStateObj[e.target.name] = e.target.value;
        }

    }else if(e.target.name === 'website'){

        if(e.target.value.length <=250){

            repStateObj[e.target.name] = e.target.value;
        }

    }else{

        
        repStateObj[e.target.name] = e.target.value;
    }

    return repStateObj;
}

//clear US Senator data on forms
const clearUsSenator={
        firstName: ''.toLowerCase().trim(),
        lastName: ''.toLowerCase().trim(),
        email: ''.trim(),
        picture: ''.trim(),
        website: ''.trim(),
        party: ''.trim(),
        elected: ''.trim(),
        locations: [],
}

const clearCongresionalRep={
        firstName: ''.toLowerCase().trim(),
        lastName: ''.toLowerCase().trim(),
        email: ''.trim(),
        picture: ''.trim(),
        website: ''.trim(),
        party: ''.trim(),
        district: ''.trim(),
        locations: [],
}

module.exports={
    clearCongresionalRep,
    clearUsSenator,
    federalValidation,
    submitRep,
    submitSen
}
