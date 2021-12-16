import React, { useState, useEffect, useReducer } from "react";
import SiteForm from "../components/SiteForm";
import Header from "../components/Header";
import produce from "immer";


const stateRepReducer = (stateRepState, action) =>{

    switch(action.type){
        case STATE_REP_FIELDS.STATE_REP_FIRST_NAME:
            return produce(stateRepState, draft=>{
                draft.stateRep.firstName = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_LAST_NAME:
            return produce(stateRepState, draft=>{
                draft.stateRep.lastName = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_EMAIL:
            return produce(stateRepState, draft=>{
                draft.stateRep.email = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_ADDRESS:
            return produce(stateRepState, draft=>{
                draft.stateRep.streetAddress = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_CITY:
            return produce(stateRepState, draft=>{
                draft.stateRep.city = action.payload;
            })

        case STATE_REP_FIELDS.ZIP_CODE:
            return produce(stateRepState, draft=>{
                draft.stateRep.zipCode = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_CAPITAL_RM:
            return produce(stateRepState, draft=>{
                draft.stateRep.capitolRoom = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_DISTRICT:
            return produce(stateRepState, draft=>{
                draft.stateRep.district = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_PICTURE:
            return produce(stateRepState, draft=>[
                draft.stateRep.picture = action.payload
            ])

        case STATE_REP_FIELDS.STATE_REP_COUNTIES:
            return produce(stateRepState, draft=>{
                draft.stateRep.counties = [...stateRepState, action.payload]
            })

        case STATE_REP_FIELDS.COUNTIES:
            return produce(stateRepState, draft=>{
                draft.formData.countiesList = [...action.payload]
            })

        case STATE_REP_FIELDS.CITIES:
            return produce(stateRepState, draft=>{
                draft.formData.citiesList = [...action.payload];
            })

        case STATE_REP_FIELDS.ZIP_CODE:
            return produce(stateRepState, draft=>{
                draft.formData.zipCodeList = [...action.payload];
            })

        case STATE_REP_FIELDS.DISTRICTS:
            return produce(stateRepState, draft=>{
                draft.formData.districtList = [...action.payload];
            })

        case STATE_REP_FIELDS.STATE_REP_LIST:
            return produce(stateRepState, draft=>{
                draft.stateRepList = [...action.payload];
            })

    }
}

const STATE_REP_FIELDS ={

    STATE_REP_FIRST_NAME: 'firstName',
    STATE_REP_LAST_NAME: 'lastName',
    STATE_REP_EMAIL: 'email',
    STATE_REP_PICTURE: 'picture',
    STATE_REP_ADDRESS: 'address',
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


const CreateStateRep = props =>{

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

                const 

            }catch(error){


            }
        })()

    },[])

    return(
        <div>
            <Header/>
            <SiteForm
                stateRepInfo={stateRepInfo}
                dispatchStateRepInfo={dispatchStateRepInfo}
                formLabel = {}
                buttonLabel = {}
            />
        </div>
    )
}

export default CreateStateRep;