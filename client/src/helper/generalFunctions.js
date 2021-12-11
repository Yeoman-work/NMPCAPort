const { utcToZonedTime, format } = require('date-fns-tz')


const dateAndTime = (time) =>{

    const date = new Date(time);
    const nmTimeZone = 'America/Denver';
    const nmDate = utcToZonedTime(date, nmTimeZone);

    const updatedTime = format(nmDate, 'yyyy-MM-dd HH:mm:ss zzz', {timeZone: nmTimeZone});

    return updatedTime;
}



const characters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
                    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',',
                    '-', '(', ')', ' ']




module.exports={
    dateAndTime,
    characters
}