import React from "react";
import { FaRegHospital } from "react-icons/fa"
import { Link } from 'react-router-dom'
import HealthCenterElement from "./HealthCenterElement";




const HealthCenterList = props =>{

    const { healthCenters } = props;

    return(


        <div className={'m-auto container'}>
            <div className={''}>
                <h1 className={'d-inline-block mt-5 mb-5 pb-4 pt-4'}>Health Centers</h1>
                <h4 className={'ms-2 d-inline-block'}><Link to={'/yeoman/healthCenters/addHealthCenter'}><FaRegHospital/></Link></h4>
            </div>

            {
                healthCenters.length > 0?
                    healthCenters.map((healthCenter, index)=>{


                        return(

                            <HealthCenterElement key={index} healthCenter={healthCenter}/>

                        )
                    })
                    :
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
            }
        </div>

    )
}

export default HealthCenterList