import React from "react";



const SiteListComponent = props =>{

    const {
           healthCenterName,
           siteListing,
           dispatchHealthCenterInfo,
           removeField,
           formData
    } = props;

    return(
        <div className={'d-inline-block'}>

                <h4>{healthCenterName}</h4>

                <div className={' overflow-auto height500'}>
                    {   siteListing?
                        siteListing.map((site, index)=>{

                            return(
                                <div key={index} className={'text-start mt-3 mb-3'}>
                                    <div className={'d-flex align-content-end'}>
                                        <button className={'btn btn-link'} onClick={(e)=>dispatchHealthCenterInfo({type: removeField, payload: index})}>
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                                 className="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                <path
                                                    d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
                                            </svg></button>
                                    </div>
                                    <h6><span className={'fw-bold me-1'}>Site Name:</span>{site.name}</h6>
                                    <span className={'fw-bold me-1'}>Address:</span> {site.streetAddress}<br/>
                                    <span className={'fw-bold me-1'}>City:</span> {site.city.name}<br/>
                                    <span className={'fw-bold me-1'}>County:</span> {site.county.name}<br/>
                                    <span className={'fw-bold me-1'}>zip code:</span>{site.zipCode.name}<br/>
                                    <span className={'fw-bold me-1'}>Service(s):</span>
                                    <ul>
                                        {
                                            formData?
                                            formData.service_list.map(({name, serviceId}, index)=>{

                                                return(

                                                        site.service.includes(serviceId)?

                                                    <li key={index}>{name}</li>

                                                            : null
                                                )
                                            })
                                                : null
                                        }
                                    </ul>
                                    <span className={'fw-bold me-1'}>Funding:</span>
                                    <ul>
                                        {
                                            formData?
                                            formData.funding_list.map(({name, fundId}, index)=>{

                                                return (
                                                    site.funding.includes(fundId)?

                                                    <li key={index}>{name}</li>
                                                        : null
                                                )
                                            })

                                                : null
                                        }
                                    </ul>
                                    <span className={'fw-bold me-1'}>NM House District:</span>{formData.nmHouse_districts.map((district, index)=>{

                                        return(
                                            site.nmHouseDistrict === district.houseDistrictId?
                                                district.name
                                                :null
                                        )
                                })}<br/>
                                    <span className={'fw-bold me-1'}>NM Senate District:</span>{formData.senate_districts.map((district, index)=>{

                                        return(
                                            site.senateDistrict === district.senateDistrictId?
                                                district.name

                                                : null
                                        )
                                })}<br/>
                                    <span className={'fw-bold me-1'}>Congressional District:</span>{ formData.congressional_districts.map((district, index)=>{

                                        return(
                                            site.congressionalDistrict === district.congressionalDistrictId?

                                                district.name
                                                : null
                                        )
                                })}
                                </div>

                            )
                        })
                        : null
                    }
                </div>

        </div>
    )
}

export default SiteListComponent