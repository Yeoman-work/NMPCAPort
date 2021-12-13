import React from "react";
import {dateAndTime} from "../helper/generalFunctions";


const HealthCenterElement = props =>{

    const {healthCenter} = props


    return(

        <div className={'row m-auto align-items-center'}>
            <div className={'col'}>
                <h3>{healthCenter.name} ({healthCenter.nameAbbr})</h3>
                <h6 className={'align-bottom'}>Updated: {healthCenter.updatedAt? dateAndTime(healthCenter.updatedAt): dateAndTime(healthCenter.createdAt)}</h6>
            </div>
            <div className={'col overflow-auto'}>
                <div className={'row'}>

                </div>
                <div className={'row'}>
                    <div className={'col overflow-auto'}>
                        <h4 className={'m-auto'}>Location(s):</h4>
                        <ul className={'w-25 m-auto'}>
                            {
                                healthCenter?
                                    healthCenter.siteDetailsNestedResponseList.map((site, index)=>{


                                        return(
                                            <li key={index}>{site.name}</li>
                                        )
                                    })
                                    : null
                            }

                        </ul>
                    </div>
                    <div className={'col'}>
                        <h4>District(s)</h4>
                        <div className={'row'}>
                            <div className={'col'}>
                                <h6>House</h6>
                                <ul className={'w-25 m-auto'}>
                                    {
                                        // healthCenter?
                                        //     healthCenter.nmHouseDistrictNestedResponses.map((district, index)=>{
                                        //
                                        //
                                        //         return(
                                        //             <li key={index}>{district.name}</li>
                                        //         )
                                        //     })
                                        //
                                        //     : null
                                    }
                                </ul>
                            </div>
                            <div className={'col'}>
                                <h6>Senate</h6>
                                <ul className={'w-25 m-auto'}>
                                    {/*{*/}
                                    {/*    healthCenter?*/}
                                    {/*        healthCenter.senateDistrictResponseModelList.map((district, index)=>{*/}


                                    {/*            return(*/}
                                    {/*                <li key={index}>{district.name}</li>*/}
                                    {/*            )*/}
                                    {/*        })*/}

                                    {/*        : null*/}
                                    {/*}*/}
                                </ul>
                            </div>
                            <div className={'col'}>
                                <h6>Congressional</h6>
                                <ul className={'w-25 m-auto'}>
                                    {
                                        // healthCenter?
                                        //     healthCenter.senateDistrictResponseModelList.map((district, index)=>{
                                        //
                                        //
                                        //         return(
                                        //             <li key={index}>{district.name}</li>
                                        //         )
                                        //     })
                                        //
                                        //     : null
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