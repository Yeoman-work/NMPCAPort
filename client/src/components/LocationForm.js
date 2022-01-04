import React, { useEffect, useReducer } from "react";
import axios from "axios";
import produce from "immer";
import {ImArrowRight, ImArrowLeft} from 'react-icons/im'
const {fieldLengthRequired,
       fieldLengthNotRequired,
       isValidCharacter
    } = require('../helper/generalFunctions')

const locationReducer = (locationState, action) =>{

    switch(action.type){

        case LOCATION_FIELDS.LOCATION_NAME:
            console.log(locationState);
            if(action.payload.length <=25){
                if(isValidCharacter(action.payload)){

                    return produce(locationState, draft=>{

                        draft.location.name = action.payload;
                        draft.locationDisplay.name = action.payload;

                    })

                }else if(action.payload < 1){

                    return produce(locationState, draft=>{

                        draft.location.name = action.payload;
                        draft.locationDisplay.name = action.payload;

                    })

                }else{

                    return locationState;

                }
            }else{

                return locationState;
            }


        case LOCATION_FIELDS.LOCATION_DESCRIPTION:

            if(action.payload.length <= 256) {

                if(isValidCharacter(action.payload)){

                    return produce(locationState, draft=>{

                        draft.location.description = action.payload;
                        draft.locationDisplay.description = action.payload;
                    })

                }else if(action.payload < 1){

                    return produce(locationState, draft=>{

                        draft.location.description = action.payload;
                        draft.locationDisplay.description = action.payload;
                    })

                }else{

                    return locationState;
                }

            }else{

                return locationState;
            }

        case LOCATION_FIELDS.LOCATION_STREET_ADDRESS:

           if(action.payload.length <= 100){

               if(isValidCharacter(action.payload)){

                   return produce(locationState, draft=>{

                       draft.location.streetAddress = action.payload;
                       draft.locationDisplay.streetAddress = action.payload;
                   })

               }else if(action.payload < 1){

                   return produce(locationState, draft=>{

                       draft.location.streetAddress = action.payload;
                       draft.locationDisplay.streetAddress = action.payload;
                   })

               }else{

                   return locationState;
               }

           }else{

               return locationState;
           }

        case LOCATION_FIELDS.LOCATION_CITY:
            const locationDataCity = action.payload.split('/')
            console.log(locationDataCity);
            return produce(locationState, draft=>{

                draft.location.city = locationDataCity[0];
                draft.locationDisplay.city = locationDataCity[1];

            })

        case LOCATION_FIELDS.LOCATION_ZIP_CODE:
            const locationDataZipCode = action.payload.split('/')

            return produce(locationState, draft=>{

                draft.location.zipCode = locationDataZipCode[0];
                draft.locationDisplay.zipCode = locationDataZipCode[1];

            })

        case LOCATION_FIELDS.LOCATION_COUNTY:
            const locationDataCounty = action.payload.split('/')

            return produce(locationState, draft=>{

                draft.location.county = locationDataCounty[0];
                draft.locationDisplay.county = locationDataCounty[1]
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
                console.log(locationState.location.rep);
                const rep = locationState.location.rep;
                draft.locationDisplayList = [...locationState.locationDisplayList, locationState.locationDisplay];
                draft.locationList = [...locationState.locationList, locationState.location];

                draft.locationDisplay = {...clearData};
                draft.location = {...clearData};
                draft.location.rep = rep;
            })

        case LOCATION_FIELDS.REMOVE_LOCATION:


            if(locationState.locationList.length > action.payload){

                console.log(action.payload);

                return produce(locationState, draft=>{

                    draft.locationList.splice(action.payload, 1);
                    draft.locationDisplayList.splice(action.payload, 1);
                });

            }else{
                console.log('other')
                return locationState;
            }

        case LOCATION_FIELDS.EDIT_LOCATION:
            console.log('here')
            if(locationState.locationList.length > action.payload){
                console.log(action.payload)
                return produce(locationState, draft=>{

                    const locationObj = locationState.locationList[action.payload];
                    const locationDisplayObj = locationState.locationDisplayList[action.payload];



                    draft.location = {...locationObj};
                    draft.locationDisplay = {...locationDisplayObj}



                    draft.locationList.splice(action.payload, 1);
                    draft.locationDisplayList.splice(action.payload, 1);

                    console.log(locationState)
                })
            }

        default:
            return locationState;
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
    LOCATION_LIST: 'locationList',
    REMOVE_LOCATION: 'remove',
    EDIT_LOCATION: 'edit location'

}

const clearData = {

    name: ''.toLowerCase().trim(),
    streetAddress : ''.toLowerCase().trim(),
    description: ''.toLowerCase().trim(),
    city: ''.trim(),
    county: ''.trim(),
    zipCode: ''.trim(),
    rep: ''.trim()
}



const LocationForm = props =>{

    const {id, handler, formLabel } = props;
    const [locationInfo, dispatchLocationInfo] = useReducer(locationReducer, {

        location:{
            name: ''.toLowerCase().trim(),
            streetAddress : ''.toLowerCase().trim(),
            description: ''.toLowerCase().trim(),
            city: ''.trim(),
            county: ''.trim(),
            zipCode: ''.trim(),
            rep: id,
        },

        locationDisplay:{

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

        locationDisplayList: [],

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


    const locationValidation = (location) =>{

        let isValid = false;

        if(fieldLengthRequired(3, 25, location.name)){
            if(fieldLengthRequired(5, 100, location.streetAddress)){
                if(fieldLengthNotRequired(5, 256, location.description)){
                    isValid = true
                }
            }
        }

        return isValid;
    }



    return(
        <div className={'m-auto'}>
            <h2>{formLabel}</h2>
            <div className={'w-50 m-auto height700 row d-inline-block border'}>
                <div className={'w-50 m-auto'}>
                    <label>Name</label>
                    <input type="text"
                           className={'form-control'}
                           name={LOCATION_FIELDS.LOCATION_NAME}
                           value={locationInfo? locationInfo.location.name : null}
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
                                value={locationInfo.location.city + '/' + locationInfo.locationDisplay.city}
                                onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                        >
                            <option value={''} >Choose a City</option>
                            {
                                locationInfo?
                                    locationInfo.formData.cityList.map((city, index)=>{

                                        return(
                                            <option key={index}
                                                    //selected={locationInfo.location.city + '/' + locationInfo.locationDisplay.city === city.cityId + '/' + city.name}
                                                    value={city.cityId + '/' + city.name}
                                            >
                                                {city.name}</option>
                                        )
                                    })
                                    : null
                            }
                        </select>
                    </div>
                    <div className={'col'}>
                        <label>County</label>
                        <select className={'form-control'}
                                value={locationInfo.location.county + '/' + locationInfo.locationDisplay.county}
                                name={LOCATION_FIELDS.LOCATION_COUNTY}
                                onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                        >
                            <option value={''}>Choose a county</option>
                            {
                                locationInfo?
                                    locationInfo.formData.countyList.map((county, index)=>{

                                        return(

                                            <option key={index}
                                                    //selected={locationInfo.location.county + '/' + locationInfo.locationDisplay.county === county.countyId + '/' + county.name }
                                                    value={county.countyId + '/' + county.name}
                                            >
                                                {county.name}</option>
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
                            value={locationInfo.location.zipCode + '/' + locationInfo.locationDisplay.zipCode}
                            name={LOCATION_FIELDS.LOCATION_ZIP_CODE}
                            onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                    >
                        <option value={''} >Choose a zip Code</option>
                        {

                            locationInfo?
                                locationInfo.formData.zipCodeList.map((zipCode, index)=>{

                                    return(
                                        <option key={ index }
                                                //selected={locationInfo.location.zipCode + '/' + locationInfo.locationDisplay.zipCode === zipCode.zipCodeId + '/' + zipCode.name}
                                                value={zipCode.zipCodeId + '/' + zipCode.name}
                                        >
                                            {zipCode.name}</option>
                                    )
                                })
                                : null
                        }

                    </select>
                </div>
                <div className={' mt-4 form-group'}>
                    <label>Description</label>
                    <textarea name={LOCATION_FIELDS.LOCATION_DESCRIPTION}
                              className={'form-control'}
                              value={locationInfo.location.description}
                              onChange={(e)=>dispatchLocationInfo({type: e.target.name, payload: e.target.value})}
                              cols="30"
                              rows="10"
                    ></textarea>
                </div>
            </div>
            <div className={'d-inline-block align-top pt-5 ms-5 me-5'}>
                <button disabled={!locationValidation(locationInfo.location)} onClick={(e)=>dispatchLocationInfo({type: LOCATION_FIELDS.LOCATION_LIST, payload: {...locationInfo.location}})}><ImArrowRight/></button><br/>
                <br/>
                <button><ImArrowLeft/></button>
            </div>
            <div className={'d-inline-block w-25 height700 align-top p-2 border'}>
                <div className={'d-inline-block w-100 height650 align-top m-auto border height500 overflow-auto'}>
                    <h6 className={'m-auto'}>New Office(s)</h6>
                    {
                        locationInfo.locationDisplayList.map((location, index)=>{


                            return(
                                <div key={index} className={''}>
                                    <button onClick={(e)=>dispatchLocationInfo({type: LOCATION_FIELDS.EDIT_LOCATION, payload: index})}>Edit</button><button onClick={(e)=>dispatchLocationInfo({type: LOCATION_FIELDS.REMOVE_LOCATION, payload: index})}>Delete</button>
                                    <h6>{location.name}</h6>
                                    <p>{location.streetAddress}</p>
                                    <p>{`${location.city} ${location.zipCode}`}</p>
                                    <p>{`${location.county} County`}</p>
                                </div>
                            )

                        })
                    }
                </div>
                <button disabled={locationInfo.locationList.length < 1} onClick={(e)=>handler(e, locationInfo.locationList)}>Save Locations</button>
            </div>


        </div>
    )
}

export default LocationForm