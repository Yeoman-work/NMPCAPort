import React from "react";
import {dateAndTime} from "../helper/generalFunctions";


const HealthCenterElement = props =>{

    const {healthCenter} = props


    return(

        <div className={'pt-4 p-2 row m-auto border rounded height400'}>
            <div className={'col'}>
                <h3 className={'mb-4'}>{healthCenter.name} ({healthCenter.nameAbbr})</h3>
                <div className={'row mt-4 pt-4'}>
                    <div className={'col height200 overflow-auto'}>
                        <h6>Service(s)</h6>
                        <ul>
                            {
                                healthCenter.serviceNestedResponses?
                                healthCenter.serviceNestedResponses.map(({name}, index)=>{

                                    return(
                                        <li key={index} className={'text-start'}>{name}</li>
                                    )
                                })
                                    : null
                            }
                        </ul>
                    </div>
                    <div className={'col'}>
                        <h6>Funding</h6>
                        <ul>
                            {
                                healthCenter.fundResponseModels?
                                healthCenter.fundResponseModels.map(({name}, index)=>{

                                    return(
                                        <li key={index}>{name}</li>
                                    )
                                })
                                    : <li>No services List</li>
                            }
                        </ul>
                    </div>
                </div>
                <h6 className={'text-start'}>Updated: {healthCenter.updatedAt? dateAndTime(healthCenter.updatedAt): dateAndTime(healthCenter.createdAt)}</h6>
            </div>
            <div className={'col'}>
                <h4 className={'m-auto'}>Location(s)</h4>
                <div className={' overflow-auto height200'}>
                    {
                        healthCenter?
                            healthCenter.siteDetailsNestedResponseList.map((site, index)=>{


                                return(
                                    <div key={index} className={'w-100 m-auto'}>
                                        <span><strong>{site.name}</strong></span><br/>
                                        <p>{site.streetAddress}<br/>
                                        {`${site.cityResponse.name}, ${site.zipCodeResponse.name}`}<br/>
                                        {`${site.countyResponse.name} county`}</p>
                                    </div>
                                )
                            })
                            : null
                    }
                </div>
            </div>
            <div className={'col overflow-auto height200'}>
                <div className={'row'}>
                    <div className={'col'}>
                        <h4>District(s)</h4>
                        <div className={'row'}>
                            <div className={'col'}>
                                <h6>House</h6>
                                <ul className={'w-25 m-auto'}>
                                    {
                                        healthCenter?
                                            healthCenter.nmHouseDistrictNestedResponses.map((district, index)=>{


                                                return(
                                                    <li key={index}>{district.name}</li>
                                                )
                                            })

                                            : null
                                    }
                                </ul>
                            </div>
                            <div className={'col'}>
                                <h6>Senate</h6>
                                <ul className={'w-25 m-auto'}>
                                    {
                                        healthCenter.senateDistrictNestedResponseList?
                                            healthCenter.senateDistrictNestedResponseList.map((district, index)=>{


                                                return(
                                                    <li key={index}>{district.name}</li>
                                                )
                                            })

                                            : null
                                    }
                                </ul>
                            </div>
                            <div className={'col'}>
                                <h6>Congressional</h6>
                                <ul className={'w-25 m-auto'}>
                                    {
                                        healthCenter.congressionalDistrictNestedResponseList?
                                            healthCenter.congressionalDistrictNestedResponseList.map((district, index)=>{


                                                return(
                                                    <li key={index}>{district.name}</li>
                                                )
                                            })

                                            : null
                                    }
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default HealthCenterElement