import React from "react";





const SiteForm = props =>{

   const {siteState,
          siteDataFields,
          dispatchSite,
          dispatchFormData,
          formData,
          formDataFields
   } = props;



// Site form component adds Site to a state list until saved to DB

    return(
        <div className={'w-50 m-auto d-inline-block'}>
            <div className={'align-top'}>
                <form >
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Site name</label>
                            <input type="text"
                                   className={'form-control w-50 m-auto'}
                                   value={siteState.name}
                                   name={siteDataFields.NAME}
                                   onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                    </div>
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Street Address</label>
                            <input type="text"
                                   className={'form-control'}
                                   value={siteState.streetAddress}
                                   name={siteDataFields.STREET_ADDRESS}
                                   onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                        <div className={'col'}>
                            <label>City</label>
                            <select className={'form-control'}
                                    name={siteDataFields.CITY}
                                    value={siteState.city.id}
                                    onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})} >
                                <option>Choose a City</option>
                                {
                                    formData?
                                        formData.cities.map(({name, cityId}, index)=>{

                                            return(
                                                <option key={index} value={cityId}>{name}</option>
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
                                        name={siteDataFields.COUNTY}
                                        value={siteState.county.id}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}>
                                    <option>Choose a County</option>
                                    {
                                        formData?
                                            formData.counties.map(({countyId, name}, index)=>{

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
                                <select name={siteDataFields.ZIP_CODE}
                                        className={'form-control'}
                                        value={siteState.zipCode.id}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}>
                                    <option>Select a zip Code</option>
                                    {
                                        formData?
                                            formData.zipCodes.map(({zipCodeId, name}, index)=>{

                                                return(
                                                    <option key={index} value={zipCodeId}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                        </div>
                        <div className={'row'}>
                            <div className={'col'}>
                                <label>Service</label>
                                {
                                    formData?
                                        formData.services.map(({serviceId, name}, index)=>{

                                            return(
                                                <label for={serviceId}> <input type="checkbox"
                                                                      itemID={serviceId}
                                                                      name={siteDataFields.SERVICES}
                                                                      className={'form-control'}
                                                                      value={serviceId}
                                                                      onChange={(e)=>dispatchSite({type: e.target.name,
                                                                                                             payload: {id : e.target.value, name: name}})}
                                                />{name}</label>
                                            )
                                        })
                                        : null
                                }

                            </div>
                            <div className={'col'}>
                                <label>Funding</label>
                                {
                                    formData?
                                        formData.funding.map(({ name, fundId }, index)=>{
                                           
                                            return(

                                                <label htmlFor={fundId}>
                                                    <input
                                                        type="checkbox"
                                                        className={'btn-check'}
                                                        name={siteDataFields.FUNDING}
                                                        value={fundId}
                                                        itemID={fundId}
                                                        autoComplete={'off'}
                                                        onChange={(e)=>dispatchSite({type: e.target.name,
                                                                                                               payload: {id: e.target.id, name: name}})}
                                                    />
                                                    {name}
                                                </label>
                                            )
                                        })
                                        : null
                                }
                            </div>
                        </div>
                        <div className={'row'}>
                            <div className={'col'}>
                                <label> NM House district</label>
                                <select name={'nmHouseDistrictId'}
                                        value={siteState.nmHouseDistrict.id}
                                        className={'form-control'}
                                        onChange={(e)=>dispatchSite({type: e.target.name,
                                                                                        payload: e.target.value})}
                                >
                                    <option>Choose a District</option>
                                    {
                                        formData?
                                            formData.nmHouseDistricts.map(({houseDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={{houseDistrictId} + '/' + {name}}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Senate District</label>
                                <select name={'senateDistrictId'}
                                        className={'form-control'}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                                >
                                    <option >Choose a Senate District</option>
                                    {
                                        formData?
                                            formData.senateDistricts.map(({senateDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={{senateDistrictId} + '/' + {name}}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Congressional District</label>
                                <select name={'congressionalDistrictId'}
                                        className={'form-control'}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                                >
                                    <option>Choose a district</option>
                                    {
                                        formData?
                                            formData.congressionalDistricts.map(({congressionalDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={{congressionalDistrictId} + '/' + {name}}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                        </div>
                    </div>
                    <button onClick={(e)=>dispatchFormData({type: formDataFields.FORM_DECREMENT})}>Back</button>
                    <button onClick={(e)=>dispatchFormData({type: formDataFields.FORM_INCREMENT})}>Add Another Site</button>
                    <button>Finish</button>
                </form>
            </div>
        </div>



    )
}

export default SiteForm