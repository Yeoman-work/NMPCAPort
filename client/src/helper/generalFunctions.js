const { utcToZonedTime, format } = require('date-fns-tz');



const dateAndTime = (time) =>{

    const date = new Date(time);
    const nmTimeZone = 'America/Denver';
    const nmDate = utcToZonedTime(date, nmTimeZone);

    const updatedTime = format(nmDate, 'yyyy-MM-dd HH:mm:ss zzz', {timeZone: nmTimeZone});

    return updatedTime;
}


const inputChangeField = (e, state) =>{

    let stateObj = {...state};

    stateObj[e.target.name] = e.target.value;
        
    return stateObj;
}


const letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
'w', 'x', 'y', 'z']

const characters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
    'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',',
    '-', '(', ')', ' ' ]


const number = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0']

const legislativeList = ['in committee', 'passed', 'vote scheduled', 'voted down']
const governorList = ['awaiting bill', 'signed', 'veto']


const InList = (list, value) =>{
    let isPresent = false;

    if(list.includes(value)){

        isPresent = true;
    }

    return isPresent;
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


const fieldLengthRequired = (min, max, fieldInput)=>{

    let isValid = false;

    if(fieldInput.length >= min){
        if(fieldInput.length <= max){
            isValid = true;
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

const isValidCharacter = (characterInput) =>{

    const isValid = characters.includes(characterInput[characterInput.length - 1])

    return isValid;

}


const phoneNumberBuilder = (phoneNumber, state) =>{

    let returnValue;

    if(number.includes(phoneNumber[phoneNumber.length - 1])){

        if(phoneNumber.length <= 12){


                returnValue = phoneNumberPattern(phoneNumber);

        }else{

            return state;
        }

    }else if(phoneNumber.length < 1){

        console.log('here here')
        returnValue = phoneNumber;

    }else{

        return state;
    }


    return returnValue;


}


const staffMemberValidation = (staffInfo) =>{

    let isValid = false;

    if(fieldLengthRequired(3, 25, staffInfo.staffMember.firstName)){
        if(fieldLengthRequired(3, 25, staffInfo.staffMember.lastName)){
            if(fieldLengthNotRequired(3, 50, staffInfo.staffMember.title)){
                if(staffInfo.staffMember.email.length > 0){
                    if(emailValidation(staffInfo.staffMember.email)){

                        isValid = true;
                    }

                }else{
                    isValid = true;
                }
            }
        }
    }

    return isValid;
}

const characterCount = (characters) =>{

    const count = characters.length;

    return count;
}


const numberValidation = (numberString) =>{

    let isValid = false;

    
    if(number.includes(numberString[numberString.length - 1])){
        
        isValid = true;
    }


    return isValid;
}

const toogleSwitch = (state) =>{
    let returnValue;

    if(state){
        returnValue = false;
    }else{
        returnValue = true
    }

    return returnValue;
}

module.exports={
    phoneNumberBuilder,
    numberValidation,
    inputChangeField,
    characterCount,
    fieldLength,
    fieldLengthRequired,
    phoneNumberValidation,
    fieldLengthNotRequired,
    fieldLengthErrorMessage,
    phoneNumberCheck,
    emailValidation,
    isValidCharacter,
    number,
    legislativeList,
    governorList,
    dateAndTime,
    characters,
    InList,
    phoneNumberPattern,
    staffMemberValidation,
    toogleSwitch,
    letters
}