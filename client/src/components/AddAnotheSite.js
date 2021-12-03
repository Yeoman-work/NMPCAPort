import React from "react";



const AddAnotherSite = props =>{

    const {healthCenterInfo} = props

    return(
        <div className={'d-inline-block'}>

                <h4>{healthCenterInfo.newHealthCenter.name}</h4>

                <h5>Sites</h5>
                <div className={' overflow-auto height500'}>
                    {
                        healthCenterInfo.newSites.map((site, index)=>{

                            return(
                                <div>
                                    <h6>{site.name}</h6>
                                    <p className={'text-start'}>
                                        <span className={'fw-bold me-1'}>Address:</span> {site.streetAddress}<br/>
                                        <span className={'fw-bold me-1'}>City:</span> {site.city.name}<br/>
                                        <span className={'fw-bold me-1'}>County:</span> {site.county.name}<br/>
                                        <span className={'fw-bold me-1'}>zip code:</span>{site.zipCode.name}<br/>
                                        <span className={'fw-bold me-1'}>NM House District:</span>{site.nmHouseDistrict.name}<br/>
                                        <span className={'fw-bold me-1'}>NM Senate District:</span>{site.senateDistrict.name}<br/>
                                        <span className={'fw-bold me-1'}>Congressional District:</span>{site.congressionalDistrict.name}
                                    </p>
                                </div>
                            )
                        })
                    }
                </div>

        </div>
    )
}

export default AddAnotherSite