const { utcToZonedTime, format } = require('date-fns-tz')


const dateAndTime = (time) =>{

    const date = new Date(time);
    const nmTimeZone = 'America/Denver';
    const nmDate = utcToZonedTime(date, nmTimeZone);

    const updatedTime = format(nmDate, 'yyyy-MM-dd HH:mm:ss zzz', {timeZone: nmTimeZone});

    return updatedTime;
}


const InList = (List, id, itemLabel) =>{
    let isPresent = false;
    const itemId = itemLabel + 'Id';
    for(let item of List){
        console.log(item[itemId.toString()]);
        if(item[itemId] === id){
            isPresent = true;
        }
    }

    return isPresent;
}


const phoneNumberPattern = (number) =>{
    let returnValue;
    //console.log('number ' + number)
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


const phoneNumberValidation = (phoneNumber) =>{

    let isValid;

    const pattern = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

    isValid = pattern.test(phoneNumber);

    return isValid;

}

const fieldLength = (min, max, fieldInput) =>{

    let isValid = true;

    if(fieldInput){
        if(fieldInput.length >= min){

            if(fieldInput.length <= max){

                isValid = false;
            }

        }
    }


    return isValid;
}

const fieldLengthNotRequired = (min, max, fieldInput) =>{

    let isValid = true;


    if(fieldInput.length !== 0){
        if(fieldInput.length < min){

            isValid = false;
        }

        if(fieldInput.length > max){

            isValid = false;
        }

    }

    return isValid;
}


const fieldLengthErrorMessage =(min, max, fieldName)=>{
    let errorMessage;

    if(min === 0){

        errorMessage = fieldName + ' must contain ' + max + ' characters or less';
    }else{

        errorMessage = fieldName + ' must be between ' + min + ' and ' + max + ' characters';
    }

    return errorMessage
}

const emailValidation =(email)=>{

    let isValid;

    //email regex
    const regexp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    isValid = regexp.test(email);

    return isValid;
}




const characters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',',
                    '-', '(', ')', ' ' ]


const number = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0']

const legislativeList = ['in committee', 'passed', 'vote scheduled', 'voted down']
const governorList = ['awaiting bill', 'signed', 'veto']



module.exports={
    fieldLength,
    phoneNumberValidation,
    fieldLengthNotRequired,
    fieldLengthErrorMessage,
    phoneNumberCheck,
    emailValidation,
    number,
    legislativeList,
    governorList,
    dateAndTime,
    characters,
    InList,
    phoneNumberPattern
}