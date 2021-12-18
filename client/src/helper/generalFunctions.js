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



const characters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',',
                    '-', '(', ')', ' ']


const number = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0']

const legislativeList = ['in committee', 'passed', 'vote scheduled', 'voted down']
const governorList = ['awaiting bill', 'signed', 'veto']



module.exports={
    number,
    legislativeList,
    governorList,
    dateAndTime,
    characters,
    InList,
    phoneNumberPattern
}