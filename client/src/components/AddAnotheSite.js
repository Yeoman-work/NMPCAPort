import React from "react";



const AddAnotherSite = props =>{

    const {healthCenterInfo} = props

    return(
        <div>
            <div className={'d-inline-block'}>
                <h4>{healthCenterInfo.newHealthCenter.name}</h4>

                <h5>Sites</h5>

                {
                    healthCenterInfo.newSites.map((site, index)=>{

                        return(
                            <div>
                                <h6>{site.name}</h6>
                                <span>{site.streetAddress}</span>
                                <span>{site.}</span>
                            </div>
                        )
                    })
                }
            </div>
        </div>
    )
}

export default AddAnotherSite