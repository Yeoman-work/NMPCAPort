const {isValidCharacter, fieldLengthRequired, fieldLengthNotRequired} = require('../helper/generalFunctions')


///valddate input for committee properties
const committeeInputValidation =(e, committeeState)=>{

    let committeeObj  = JSON.parse(JSON.stringify(committeeState));

    if(e.target.name === 'name'){
        
        if(isValidCharacter(e.target.value)){

            if(e.target.value.length <= 75 ){
                
                committeeObj[e.target.name] = e.target.value;
            }
        }

    }else if(e.target.name === 'description'){

        if(isValidCharacter(e.target.name)){

            if(e.target.value.length <= 150 ){

                committeeObj[e.target.name] = e.target.value;
            }
        }
    }

    return committeeObj;

}

//validate input validation
const committeeValidation = (committee) =>{

    let isValid = true;

    if(fieldLengthRequired(3, 75, committee.name)){
        if(fieldLengthNotRequired(0, 150, committee.description)){

            isValid  = false
        }
    }

    return isValid;
}


//adds/removes member to committee
const changeMemberShip = (e, committeeState) =>{
    
    let committeeStateObj = JSON.parse(JSON.stringify(committeeState));

    if(e.target.name === 'repIds'){

        if(committeeStateObj.repIds.hasOwnProperty(e.target.value)){

            delete committeeStateObj['repIds'][e.target.value];
        }

        if(!committeeStateObj.repIds.hasOwnProperty(e.target.value)){
            
            committeeStateObj['repIds'][e.target.value] = e.target.value;
        }
        

    }else if(e.target.name === 'senatorIds'){

        if(committeeStateObj.senatorIds.hasOwnProperty(e.target.value)){

            delete committeeStateObj['senatorIds'][e.target.value];
        }

        if(!committeeStateObj.senatorIds.hasOwnProperty(e.target.value)){
            
            committeeStateObj['senatorIds'][e.target.value] = e.target.value;
        }
    }


    return committeeStateObj;
}



module.exports={
    changeMemberShip,
    committeeInputValidation,
    committeeValidation,
}