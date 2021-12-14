import React from "react";
import {dateAndTime} from "../helper/generalFunctions";


const HealthCenterElement = props =>{

    const {healthCenter} = props


    return(

        <div className={'row m-auto align-items-center border rounded height400'}>
            <div className={'col height200'}>
                <h3>{healthCenter.name} ({healthCenter.nameAbbr})</h3>
                <h6 className={'align-bottom'}>Updated: {healthCenter.updatedAt? dateAndTime(healthCenter.updatedAt): dateAndTime(healthCenter.createdAt)}</h6>
            </div>
            <div className={'col height200'}>
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
                    {/*<div className={'col overflow-auto'}>*/}
                    {/*    <h4 className={'m-auto'}>Location(s):</h4>*/}
                    {/*    <ul className={'w-25 m-auto'}>*/}
                    {/*        {*/}
                    {/*            healthCenter?*/}
                    {/*                healthCenter.siteDetailsNestedResponseList.map((site, index)=>{*/}


                    {/*                    return(*/}
                    {/*                        <li key={index}>{site.name}</li>*/}
                    {/*                    )*/}
                    {/*                })*/}
                    {/*                : null*/}
                    {/*        }*/}

                    {/*    </ul>*/}
                    {/*</div>*/}
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
                                        healthCenter?
                                            healthCenter.senateDistrictResponseModelList.map((district, index)=>{


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
                                        healthCenter?
                                            healthCenter.senateDistrictResponseModelList.map((district, index)=>{


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