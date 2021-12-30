import React, { useEffect, useReducer } from "react";
import axios from "axios";
import produce from "immer";
import {ImArrowRight, ImArrowLeft} from 'react-icons/im'

const locationReducer = (locationState, action) =>{

    switch(action.type){

        case LOCATION_FIELDS.LOCATION_NAME:

            return produce(locationState, draft=>{

                draft.location.name = action.payload;

            })

        case LOCATION_FIELDS.LOCATION_DESCRIPTION:

            return produce(locationState, draft=>{

                draft.location.description = action.payload;
            })

        case LOCATION_FIELDS.LOCATION_STREET_ADDRESS:

            return produce(locationState, draft=>{

                draft.location.streetAddress = action.payload;
            })

        case LOCATION_FIELDS.CITY_LIST:

            return produce(locationState, draft=>{

                draft.formData.cityList = [...action.payload];
            })

        case LOCATION_FIELDS.COUNTY_LIST:

            return produce(locationState, draft=>{

                draft.formData.countyList = [...action.payload];
            })

        case LOCATION_FIELDS.ZIP_CODE_LIST:

            return produce(locationState, draft=>{

                draft.formData.zipCodeList = [...action.payload];
            })

        case LOCATION_FIELDS.LOCATION_LIST:

            return produce(locationState, draft=>{

                draft.locationList = [...locationState.locationList, action.payload]
            })
    }
}

const LOCATION_FIELDS ={

    LOCATION_NAME: 'name',
    LOCATION_DESCRIPTION: 'description',
    LOCATION_STREET_ADDRESS: 'streetAddress',
    LOCATION_CITY: 'city',
    LOCATION_COUNTY: 'county',
    LOCATION_ZIP_CODE: 'zip code',
    CITY_LIST: 'cityList',
    COUNTY_LIST: 'countyList',
    ZIP_CODE_LIST: 'zipCodeList',
    LOCATION_LIST: 'locationList'

}



const LocationForm = props =>{

    const { senator, handler } = props;
    const [locationInfo, dispatchLocationInfo] = useReducer(locationReducer, {

        location:{
            name: ''.toLowerCase().trim(),
            streetAddress : ''.toLowerCase().trim(),
            description: ''.toLowerCase().trim(),
            city: ''.trim(),
            county: ''.trim(),
            zipCode: ''.trim()
        },

        formData:{
            cityList: [],
            countyList: [],
            zipCodeList: [],
        },

        locationList: []
    })

    useEffect(()=>{

        (async ()=>{

            try{

                const cityResponse = await axios.get('http://localhost:8080/cities',{

                    headers:{

                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchLocationInfo({type: LOCATION_FIELDS.CITY_LIST, payload: [...cityResponse.data]})

            }catch(error){


            }

        })()


    }, [])

    useEffect(()=>{

        (async ()=>{

            try{

                const countyResponse = await axios.get('http://localhost:8080/counties', {

                    headers:{

                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchLocationInfo({type: LOCATION_FIELDS.COUNTY_LIST, payload: [...countyResponse.data]})

            }catch(error){


            }

        })()

    },[])


    useEffect(()=>{

        (async ()=>{

            try{

                const zipCodeResponse = await axios.get('http://localhost:8080/zipCodes',{

                    headers:{

                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchLocationInfo({type: LOCATION_FIELDS.ZIP_CODE_LIST, payload: [...zipCodeResponse.data]})

            }catch(error){


            }

        })()


    },[])



    return(
        <div className={'m-auto'}>
            <div className={'w-50 m-auto row d-inline-block'}>
                <div className={'w-50 m-auto'}>
                    <label>Name</label>
                    <input type="text"
                           className={'form-control'}
                           name={LOCATION_FIELDS.LOCATION_NAME}
                           value={locationInfo.location.name}
                           onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={''}>
                    <label>Address</label>
                    <input type="text"
                           className={'form-control'}
                           name={LOCATION_FIELDS.LOCATION_STREET_ADDRESS}
                           value={locationInfo.location.streetAddress}
                           onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>City</label>
                        <select className={'form-control'}
                                name={LOCATION_FIELDS.LOCATION_CITY}
                                onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                        >
                            <option>Choose a City</option>
                            {
                                locationInfo?
                                    locationInfo.formData.cityList.map((city, index)=>{

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
                                name={LOCATION_FIELDS.LOCATION_COUNTY}
                                onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                        >
                            <option>Choose a county</option>
                            {
                                locationInfo?
                                    locationInfo.formData.countyList.map((county, index)=>{

                                        return(

                                            <option key={index} value={county.countyId}>{county.name}</option>
                                        )
                                    })
                                    : null
                            }
                        </select>
                    </div>
                </div>
                <div className={'m-auto w-50'}>
                    <label>Zip Code</label>
                    <select className={'form-control'}
                            name={LOCATION_FIELDS.LOCATION_ZIP_CODE}
                            onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                    >
                        <option>Choose a zip Code</option>
                        {

                            locationInfo?
                                locationInfo.formData.zipCodeList.map((zipCode, index)=>{

                                    return(
                                        <option key={ index } value={zipCode.zipCodeId}>{zipCode.name}</option>
                                    )
                                })
                                : null
                        }

                    </select>
                </div>
            </div>
            <div className={'d-inline-block align-top pt-5 ms-5'}>
                <button onClick={(e)=>dispatchLocationInfo({type: LOCATION_FIELDS.LOCATION_LIST, payload: {...locationInfo.location}})}><ImArrowRight/></button><br/>
                <br/>
                <button><ImArrowLeft/></button>
            </div>
            <div className={'d-inline-block w-25 align-top m-auto'}>
                <h6 className={'m-auto'}>New Office(s)</h6>
                {
                    locationInfo.locationList.map((location, index)=>{

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