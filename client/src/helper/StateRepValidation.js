const {fieldLength, emailValidation, fieldLengthNotRequired} = require('../helper/generalFunctions')


const canSubmit = (stateRep) =>{

    let isDisabled = true;

    if(!fieldLength(3, 50, stateRep.firstName)){
        if(!fieldLength(3, 50, stateRep.lastName)){
            if(emailValidation(stateRep.email)){
                if(fieldLengthNotRequired(0, 150, stateRep.email)){
                    if(fieldLengthNotRequired(5, 250, stateRep.picture)){
                        if(fieldLengthNotRequired(5, 150, stateRep.streetAddress)){
                            if(fieldLengthNotRequired(0, 8, stateRep.capitolRoom)){
                                isDisabled = false;
                            }
                        }
                    }
                }
            }
        }

    }

    
    return isDisabled;
}

module.exports={
    canSubmit
}