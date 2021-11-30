import React from "react";
import {dateAndTime} from "../helper/generalFunctions";


const HealthCenterElement = props =>{

    const {healthCenter} = props


    return(

        <div className={'row'}>
            <div className={'col'}>
                <h3>{healthCenter.name} ({healthCenter.nameAbbr})</h3>
                <h6 className={'align-bottom'}>Updated: {dateAndTime(healthCenter.updatedAt)}</h6>
            </div>
            <div className={'col overflow-auto'}>
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
            <div className={'overflow-auto col'}>
                <h4>NM House District</h4>
                <ul>
                    {
                        healthCenter.nmHouseDistrictNestedResponseList.map((district, index)=>{


                            return(
                                <li key={index}>{district.name}</li>
                            )
                        })
                    }
                </ul>
            </div>
            <div className={'col overflow-auto'}>
                <h4>Senate Districts</h4>
                <ul>
                    {
                        healthCenter.senateDistrictResponseList.map((district, index)=>{

                            return(
                                <li key={index}>{district.name}</li>
                            )
                        })
                    }
                </ul>
            </div>
            <div className={'col overflow-auto'}>
                <ul>
                    {
                        healthCenter.congressionalDistrictResponseList.map((district, index)=>{


                            return(
                                <li key={index}>{district.name}</li>
                            )
                        })
                    }
                </ul>
            </div>
        </div>
    )
}

export default HealthCenterElement