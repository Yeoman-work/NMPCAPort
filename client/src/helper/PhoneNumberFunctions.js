const {number, isValidCharacter, fieldLengthRequired} = require('../helper/generalFunctions');

const clearPhoneNumber = {
        number: ''.trim(),
        description: ''.trim()
}

//build phone number
const phoneNumberBuilder = (phoneNumber) =>{

    let returnValue;

    if(number.includes(phoneNumber[phoneNumber.length - 1])){

        if(phoneNumber.length <= 12){


            returnValue = phoneNumberPattern(phoneNumber);

        }

    }else if(phoneNumber.length < 1){

        
        returnValue = phoneNumber;

    }

    return returnValue;

}

//enable/disable phone number submit button
const phoneNumberCheck = (phoneNumber) =>{

    let disabled = true;

    const phoneNumberObj = {
        phoneNumber: phoneNumber.number,
        description: phoneNumber.description
    }
    const pattern = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im

    if(pattern.test(phoneNumberObj.phoneNumber)){

        if(phoneNumberObj.description.length >= 5 && phoneNumberObj.description.length <= 25){

            disabled = false;
        }
    }

    return disabled;
}

//phone number validation
const phoneNumberValidation = (phoneNumber) =>{

    let isValid;

    const pattern = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

    isValid = pattern.test(phoneNumber);

    return isValid;

}

const addPhoneNumberToList = (phoneNumber, phoneNumberList) =>{
    console.log('phone number ' + phoneNumber)
    let phoneNumbers = [...phoneNumberList, phoneNumber];

    return phoneNumbers;

}


const phoneNumberPattern = (number) =>{
    let returnValue;

    if(number.length === 7 && number.charAt(3) !== '-'){
        returnValue = number.substring(0,3) + '-' + number.substring(3);

    }else if (number.length === 11 && number.charAt(7) !== '-'){

        returnValue = number.substring(0, 7) + '-' + number.substring(7);

    }else if(number.length === 11 && number.charAt(7) === '-'){
        returnValue = number.substring(0, 7) + number.substring(8);

    } else if(number.length === 7 && number.charAt(3) === '-'){

        returnValue = number.substring(0, 3) + number.substring(4);

    }else{

        returnValue = number;
    }

    return returnValue;
}

const inputPhoneNumber = (e, phoneNumberState) =>{

    
    let phoneNumberStateObj = JSON.parse(JSON.stringify(phoneNumberState));

    if(e.target.value.length === 0){

        phoneNumberStateObj[e.target.name] = e.target.value;

    }else if(e.target.name === 'number'){

        if(phoneNumberBuilder(e.target.value)){

            if(e.target.value.length <= 12){

            phoneNumberStateObj.number = phoneNumberBuilder(e.target.value, phoneNumberState)

            }
        }
        
        
    }else if (e.target.name === 'description'){

        if(isValidCharacter(e.target.value)){
            
            if(e.target.value.length <= 25){

                    phoneNumberStateObj[e.target.name] = e.target.value;
            }

        }
    }

    return phoneNumberStateObj;
}


const submitPhoneNumber = (phoneNumberState) =>{

    let canSubmit = false;

    if(phoneNumberValidation(phoneNumberState.number)){
        if(fieldLengthRequired(5, 25, phoneNumberState.description)){

            canSubmit = true;
        }
    }

    return canSubmit;
}







module.exports={
    phoneNumberBuilder,
    phoneNumberPattern,
    phoneNumberValidation,
    phoneNumberCheck,
    clearPhoneNumber,
    addPhoneNumberToList,
    inputPhoneNumber,
    submitPhoneNumber,
    clearPhoneNumber
}