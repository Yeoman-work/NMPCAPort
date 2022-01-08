const { fieldLengthRequired, fieldLengthNotRequired, emailValidation } = require('../helper/generalFunctions')


const isContact = (contact) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 25, contact.firstName)){
        if(fieldLengthRequired(3, 25, contact.lastName)){
            if(fieldLengthNotRequired(2, 25, contact.title)){
                if(emailValidation(contact.email)){

                    isValid = true;
                }
            }
        }
    }

    return isValid;
}






module.exports={

    isContact
}