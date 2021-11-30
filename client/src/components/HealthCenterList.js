import React from "react";
import { NavLink } from 'react-router-dom'
import HealthCenterElement from "./HealthCenterElement";
const {dateAndTime} = require('../helper/generalFunctions')



const HealthCenterList = props =>{

    const { healthCenters } = props;

    return(


        <div>
            {
                healthCenters.length > 0?
                    healthCenters.map((healthCenter, index)=>{


                        return(

                            <HealthCenterElement key={index} healthCenter={healthCenter}/>

                        )
                    })
                    :
                    <div className={'text-primary'}>
                        <h4 className={'text-primary'}>No Organizations Listed <NavLink to={'/yeoman/healthCenters/addHealthCenter'}>Click here to add an Organizations</NavLink></h4>
                    </div>
            }
        </div>

    )
}

export default HealthCenterList