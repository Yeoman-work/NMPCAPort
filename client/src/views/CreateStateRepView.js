import React, { useState, useEffect, useReducer } from "react";
import axios from "axios";
import SiteForm from "../components/SiteForm";
import Header from "../components/Header";
import produce from "immer";
import StateRepForm from "../components/StateRepForm";


const stateRepReducer = (stateRepState, action) =>{

    switch(action.type){
        case STATE_REP_FIELDS.STATE_REP_FIRST_NAME:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.firstName = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_LAST_NAME:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.lastName = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_EMAIL:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.email = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_ADDRESS:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.streetAddress = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_CITY:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.city = action.payload;
            })

        case STATE_REP_FIELDS.ZIP_CODE:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.zipCode = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_CAPITAL_RM:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.capitolRoom = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_DISTRICT:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.district = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_PICTURE:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.picture = action.payload
                })

        case STATE_REP_FIELDS.STATE_REP_COUNTIES:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.counties = [...stateRepState, action.payload]
            })

        case STATE_REP_FIELDS.COUNTIES:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.formData.countiesList = [...action.payload]
            })

        case STATE_REP_FIELDS.CITIES:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.formData.citiesList = [...action.payload];
            })

        case STATE_REP_FIELDS.ZIP_CODE:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.formData.zipCodeList = [...action.payload];
            })

        case STATE_REP_FIELDS.DISTRICTS:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.formData.districtList = [...action.payload];
            })

        case STATE_REP_FIELDS.STATE_REP_LIST:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRepList = [...action.payload];
            })

        default:
            return stateRepState;

    }
}

const STATE_REP_FIELDS ={

    STATE_REP_FIRST_NAME: 'firstName',
    STATE_REP_LAST_NAME: 'lastName',
    STATE_REP_EMAIL: 'email',
    STATE_REP_PICTURE: 'picture',
    STATE_REP_ADDRESS: 'streetAddress',
    STATE_REP_CITY: 'city',
    STATE_REP_ZIP_CODE: 'zipCode',
    STATE_REP_DISTRICT: 'district',
    STATE_REP_CAPITAL_RM: 'capitalRoom',
    STATE_REP_COUNTIES: 'counties',
    COUNTIES: 'countiesList',
    CITIES: 'citiesList',
    ZIP_CODE: 'zipCodeList',
    DISTRICTS: 'districtList',
    STATE_REP_LIST: []
}


const CreateStateRepView = props =>{

    const [stateRepInfo, dispatchStateRepInfo] = useReducer(stateRepReducer, {

        stateRep:{
            firstName: ''.trim(),
            lastName: ''.trim(),
            email: ''.trim(),
            picture: ''.trim(),
            streetAddress: ''.trim(),
            city: ''.trim(),
            capitolRoom: ''.trim(),
            zipCode: ''.trim(),
            nmHouseDistrict: ''.trim(),
            counties: []
        },

        stateRepList: [],

        formData:{
            countiesList: [],
            citiesList: [],
            zipCodeList: [],
            districtList: [],
        }

    })


    useEffect(()=>{

        (async ()=>{

            try{

                const countyListResponse = await axios.get('http://localhost:8080/legislation',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchStateRepInfo({type: STATE_REP_FIELDS.COUNTIES, payload: [...countyListResponse]})

            }catch(error){

                console.log(error.response);

            }
        })()

    },[])

    useEffect(()=>{

        (async ()=>{

            try{

                const cityListResponse = await axios.get('http://localhost:8080/cities', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchStateRepInfo({type: STATE_REP_FIELDS.CITIES, payload: [...cityListResponse]})
            }catch(error){

                console.log(error.response)

            }

        })()

    },[])


    useEffect(()=>{

        (async ()=>{

            try{

                const zipCodeListResponse = await axios.get('http://localhost:8080/zipCodes', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchStateRepInfo({type: STATE_REP_FIELDS.ZIP_CODE, action: [...zipCodeListResponse]})

            }catch(error){

                console.log(error.response)

            }
        })()

    },[])

    return(
        <div>
            <Header/>
            <StateRepForm
                stateRepInfo={stateRepInfo}
                formFields={STATE_REP_FIELDS}
                dispatchStateRepInfo={dispatchStateRepInfo}
                formLabel={'Create State Rep'}
                formButton={'Create Rep'}
            />
        </div>
    )
}

export default CreateStateRepView;