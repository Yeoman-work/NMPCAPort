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
                                healthCenter.serviceEssentialsResponses?
                                healthCenter.serviceEssentialsResponses.map(({name}, index)=>{

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
                                healthCenter.fundEssentialsResponses.length > 0?
                                healthCenter.fundEssentialsResponses.map(({name}, index)=>{

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
                        healthCenter.siteEssentialsResponses?
                            healthCenter.siteEssentialsResponses.map((site, index)=>{


                                return(
                                    <div key={index} className={'w-100 m-auto'}>
                                        <span><strong>{site.name}</strong></span><br/>
                                        <p>{site.streetAddress}<br/>
                                        {`${site.cityEssentials.name}, ${site.zipCodeEssentials.name}`}<br/>
                                        {`${site.countyEssentials.name} county`}</p>
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
                                        healthCenter.nmHouseDistrictEssentialResponses?
                                            healthCenter.nmHouseDistrictEssentialResponses.map((district, index)=>{


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
                                        healthCenter.senateDistrictEssentialResponses?
                                            healthCenter.senateDistrictEssentialResponses.map((district, index)=>{


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
                                        healthCenter.congressionalEssentialsResponses?
                                            healthCenter.congressionalEssentialsResponses.map((district, index)=>{


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