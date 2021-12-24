import React from "react";





const LocationForm = props =>{
        const {formFields,
               dispatchFunction,
               locationData,
               formData,
               locationList
        } = props;


    return(
        <div className={' w-75 m-auto row'}>
            <div className={'col'}>
                <div>
                    <label>Name</label>
                    <input type="text"
                           className={'form-control'}
                           name={formFields.LOCATION_NAME}
                           value={locationData.name}
                           onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={''}>
                    <label>Address</label>
                    <input type="text"
                           className={'form-control'}
                           name={formFields.LOCATION_STREET_ADDRESS}
                           value={locationData.streetAddress}
                           onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                    />
                </div>

                    <div className={'col'}>
                        <label>City</label>
                        <select className={'form-control'}
                                name={formFields.LOCATION_CITY}
                                onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                        >
                            <option>Choose a City</option>
                            {
                                formData?
                                formData.cityList.map((city, index)=>{

                                    return(
                                        <option key={index} value={city.cityId}>{city.name}</option>
                                    )
                                })
                                    : null
                            }
                        </select>

                    </div>
                    <div className={'col'}>
                        <label>County</label>
                        <select className={'form-control'}
                                name={formFields.LOCATION_COUNTY}
                                onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                        >
                            <option>Choose a county</option>
                            {
                                formData?
                                formData.countyList.map((county, index)=>{

                                    return(

                                        <option key={index} value={county.countyId}>{county.name}</option>
                                    )
                                })
                                : null
                            }
                        </select>
                    </div>
                <div className={'col'}>
                    <label>Zip Code</label>
                    <select className={'form-control'}
                            name={formFields.LOCATION_ZIP_CODE}
                            onChange={(e)=>dispatchFunction({type: e.target.name, payload:e.target.value})}
                    >
                        <option>Choose a Zip Code</option>
                        {
                            formData?
                                formData.zipCodeList.map((zipCode, index)=>{

                                    return(
                                        <option value={zipCode.zipCodeId + '/' + zipCode.name}>{zipCode.name}</option>
                                    )
                                })
                                : null
                        }
                    </select>
                </div>
            </div>
            <div className={'col'}>
                <label>New Locations</label>
                {
                    locationList.map((location, index)=>{

                        return(
                            <div>
                                <h6>{location.name}</h6>
                                <p>{location.streetAddress}</p>
                                <p>{`${location.city} ${location.county} ${location.zipCode}`}</p>
                            </div>
                        )

                    })
                }
            </div>
        </div>
    )
}

export default LocationForm