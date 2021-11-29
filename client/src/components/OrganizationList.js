import React, {useState} from "react";
const {dateAndTime} = require('../helper/generalFunctions')



const OrganizationList = props =>{
    const [healthCenters, setHealthCenters] = useState([]);

    return(


        <div>
            {
                healthCenters?
                    healthCenters.map((healthCenter, index)=>{


                        return(

                            <div key={index} className={'row'}>
                                <div className={'col'}>
                                    <h3>{healthCenter.name} ({healthCenter.nameAbbr})</h3>
                                    <h6 className={'align-bottom'}>Updated: {dateAndTime(healthCenter.updatedAt)}</h6>
                                </div>
                                <div className={'col'}>
                                    <h4>Locations:</h4>
                                        <ul>
                                            {
                                                healthCenter.siteDetailsNestedResponse.map((site, index)=>{


                                                    return(
                                                        <li key={index}>{site.name}</li>
                                                    )
                                                })
                                            }

                                        </ul>
                                </div>
                                <div>
                                    <h4></h4>
                                </div>
                            </div>

                        )
                    })
                    : null
            }
        </div>

    )
}

export default OrganizationList