const { 
    fieldLengthRequired, 
    fieldLengthNotRequired, 
    emailValidation,
    isValidCharacter
} = require('../helper/generalFunctions')

//check contact fields before submits
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

const contactInputValidation = (e, contactState)=>{

    let contactStateObj = JSON.parse(JSON.stringify(contactState));
    
    if(e.target.value.length === 0){

        contactStateObj[e.target.name] = e.target.value;

    }else if(e.target.name === 'firstName'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length  <= 50){

                contactStateObj[e.target.name] = e.target.value;
            }
            
        }
    }else if(e.target.name === 'lastName'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 50){

                contactStateObj[e.target.name] = e.target.value;
            }
        }

    }else if(e.target.name === 'title'){

        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 25 ){

                contactStateObj[e.target.name] = e.target.value;
            }
        }
    }else if(e.target.name === 'email'){

        if(e.target.value.length <= 150){

            contactStateObj[e.target.name] = e.target.value;
        }
    }else if(e.target.name === 'healthCenter'){

        contactStateObj[e.target.name] = e.target.value;

    }else if(e.target.name === 'networkingGroups'){

        console.log(contactStateObj['networkingGroups'].hasOwnProperty(e.target.value));
        if(contactStateObj['networkingGroups'].hasOwnProperty(e.target.value)){

            delete contactStateObj['networkingGroups'][e.target.value];

        }else if (!contactStateObj['networkingGroups'].hasOwnProperty(e.target.value)){

            contactStateObj['networkingGroups'][e.target.value] = e.target.value;
        }
    }

    return contactStateObj;
}






module.exports={
    contactInputValidation,
    isContact,
}