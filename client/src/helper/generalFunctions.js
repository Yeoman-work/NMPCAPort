const { utcToZonedTime, format } = require('date-fns-tz')


const dateAndTime = (time) =>{

    const date = new Date(time);
    const nmTimeZone = 'America/Denver';
    const nmDate = utcToZonedTime(date, nmTimeZone);

    const updatedTime = format(nmDate, 'yyyy-MM-dd HH:mm:ss zzz', {timeZone: nmTimeZone});

    return updatedTime;
}


module.exports={
    dateAndTime
}