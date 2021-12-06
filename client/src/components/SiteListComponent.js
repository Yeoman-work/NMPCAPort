import React from "react";



const SiteListComponent = props =>{

    const {
           healthCenterName,
           siteListing
    } = props;

    return(
        <div className={'d-inline-block'}>

                <h4>{healthCenterName}</h4>

                <h5>Sites</h5>
                <div className={' overflow-auto height500'}>
                    {
                        siteListing.map((site, index)=>{

                            return(
                                <div key={index}>
                                    <h6>{site.name}</h6>
                                    <div className={'text-start'}>
                                        <span className={'fw-bold me-1'}>Address:</span> {site.streetAddress}<br/>
                                        <span className={'fw-bold me-1'}>City:</span> {site.city.name}<br/>
                                        <span className={'fw-bold me-1'}>County:</span> {site.county.name}<br/>
                                        <span className={'fw-bold me-1'}>zip code:</span>{site.zipCode.name}<br/>
                                        <span className={'fw-bold me-1'}>Service(s):</span>
                                        <ul>
                                            {
                                                site.services.map(({name}, index)=>{

                                                    return(
                                                        <li key={index}>{name}</li>
                                                    )
                                                })
                                            }
                                        </ul>
                                        <span className={'fw-bold me-1'}>Funding:</span>
                                        <ul>
                                            {
                                                site.funding.map(({name}, index)=>{

                                                    return (
                                                        <li key={index}>{name}</li>
                                                    )
                                                })
                                            }
                                        </ul>
                                        <span className={'fw-bold me-1'}>NM House District:</span>{site.nmHouseDistrict.name}<br/>
                                        <span className={'fw-bold me-1'}>NM Senate District:</span>{site.senateDistrict.name}<br/>
                                        <span className={'fw-bold me-1'}>Congressional District:</span>{site.congressionalDistrict.name}
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>

        </div>
    )
}

export default SiteListComponent