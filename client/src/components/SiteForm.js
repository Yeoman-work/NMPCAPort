import React, { useState } from "react";
const { InList } = require('../helper/generalFunctions')
const { addSite } = require('../helper/siteValidation')




const SiteForm = props =>{

    const {
        stateWithSite,
        setStateWithSite,
        cityList,
        zipCodeList,
        countyList,
        serviceList,
        fundList,
        houseDistrictList,
        senateDistrictList,
        congressionalDistrictList,
        formFields,
        divClass
        
    } = props;

    const [site, setSite] = useState({
        
        name: ''.trim(),
        streetAddress: ''.trim(),
        city: ''.trim(),
        county: ''.trim(),
        zipCode: ''.trim(),
        description: ''.trim()

    })




    const saveHealthCenter = (healthCenter) =>{
        let disable = true;
        const siteList = healthCenter.sitesRequest.length;


        if(siteList > 0) {

            disable = false;

        }

        return disable;
    }


// Site form component adds Site to a state list until saved to DB

    return(
        <div className={divClass}>
            <div className={'align-top'}>
                <form >
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Site name</label>

                            <input type="text"
                                className={'form-control w-50 m-auto'}
                                value={stateWithSite.name}
                                name={formFields.SITE_NAME}
                                // onChange={(e)=>dispatchSite(
                                //     {type: e.target.name, payload: e.target.value})}
                            />

                        </div>
                    </div>
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Street Address</label>
                            <input type="text"
                                className={'form-control'}
                                value={stateWithSite.streetAddress}
                                name={formFields.STREET_ADDRESS}
                                // onChange={(e)=>dispatchSite(
                                //     {type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                        <div className={'col'}>
                            <label>City</label>
                            <select className={'form-control'}
                                    name={formFields.CITY}
                                    value={ stateWithSite.city }
                            >
                                <option>Choose a City</option>
                                {
                                    cityList?
                                        cityList.map(({name, cityId}, index)=>{

                                            return(
                                                <option key={index} value={ cityId }>{name}</option>
                                            )
                                        })

                                        : null
                                }
                            </select>
                        </div>
                        <div className={'row'}>
                            <div className={'col'}>
                                <label>County</label>
                                <select className={'form-control'}
                                        name={formFields.COUNTY}
                                        value={stateWithSite.county}
                                >
                                    <option>Choose a County</option>
                                    {

                                        countyList?
                                            countyList.map(({countyId, name}, index)=>{

                                                return(
                                                    <option key={index} value={countyId}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Zip Code</label>
                                <select name={formFields.ZIP_CODE}
                                        className={'form-control'}
                                        value={stateWithSite.zipCode}
                                >
                                    <option>Select a zip Code</option>
                                    {
                                        zipCodeList?
                                            zipCodeList.map(({zipCodeId, name}, index)=>{

                                                return(
                                                    <option key={index} value={ zipCodeId }>{name}</option>
                                                )
                                            })

                                            : null
                                    }
                                </select>

                            </div>
                        </div>
                        <div className={'row mb-2 p-3'}>
                            <div className={'col overflow-auto'}>
                                <label>Service</label><br/>
                                {
                                    serviceList?
                                        serviceList.map(({serviceId, name}, index)=>{

                                            return(
                                                    <div key={index} className={'d-flex justify-content-start'}>

                                                        <label htmlFor={serviceId}>
                                                            <input type="checkbox"
                                                                itemID={serviceId}
                                                                name={formFields.SITE_SERVICES}
                                                                className={'form-check-input me-2'}
                                                                checked={stateWithSite.service.includes(serviceId)}
                                                                value={ serviceId }
                                                                
                                                            />
                                                            {name}</label>
                                                    </div>
                                            )
                                        })
                                        : null
                                }

                            </div>
                            <div className={'col overflow-auto'}>
                                <label>Funding</label>
                                {
                                    fundList?
                                        fundList.map(({ name, fundId }, index)=>{

                                            return(
                                                <div key={index} className={'d-flex justify-content-start'}>
                                                    <label htmlFor={fundId}>
                                                        <input
                                                            type="checkbox"
                                                            name={formFields.SITE_FUNDING}
                                                            className={'form-check-input me-2'}
                                                            checked={stateWithSite.funding.includes(fundId)}
                                                            value={fundId}
                                                            itemID={fundId}
                                                            
                                                        />

                                                        {name}</label>
                                                </div>
                                            )
                                        })

                                        : null
                                }
                            </div>
                        </div>
                        <div className={'row'}>
                            <div className={'col'}>
                                <label> NM House district</label>
                                <select name={formFields.HOUSE_DISTRICT}
                                        value={ stateWithSite.houseDistrict }
                                        className={'form-control'}
                                        
                                >
                                    <option>Choose a District</option>
                                    {
                                        houseDistrictList?
                                            houseDistrictList.map(({houseDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={ houseDistrictId }>{name}</option>
                                                )
                                            })

                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Senate District</label>
                                <select name={formFields.SENATE_DISTRICT}
                                        className={'form-control'}
                                        value={ stateWithSite.senateDistrict }
                                >
                                    <option >Choose a Senate District</option>
                                    {
                                        senateDistrictList?
                                            senateDistrictList.map(({senateDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={ senateDistrictId }>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Congressional District</label>
                                <select name={formFields.CONGRESSIONAL_DISTRICT}
                                        className={'form-control'}
                                        value={stateWithSite.congressionalDistrict}
                                >
                                    <option>Choose a district</option>
                                    {
                                        congressionalDistrictList?
                                            congressionalDistrictList.map(({congressionalDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={ congressionalDistrictId }>{name}</option>
                                                )
                                            })

                                            : null
                                    }
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
                {/* <button onClick={(e)=>dispatchSite({type: siteDataFields.FORM_DECREMENT})}>Back</button>
                <button disabled={addSite(siteJson, formData)} onClick={(e)=>dispatchSite({ type: siteDataFields.NEW_SITE_REQUEST })}>Add Another Site</button>
                <button disabled={saveHealthCenter(healthCenter)} onClick={(e)=>healthCenterHandler(e)}>Finish</button> */}
            </div>
        </div>



    )
}

export default SiteForm