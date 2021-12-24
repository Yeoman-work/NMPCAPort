import React, { useState, useEffect, useReducer } from "react";
import axios from "axios";
import produce from "immer";
import Header from "../components/Header";
import FederalRepForm from "../components/FederalRepForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
import LocationForm from "../components/LocationForm";
const {characters,
       emailValidation,
       isValidCharacter,
       phoneNumberBuilder,
       fieldLengthRequired,
       fieldLengthNotRequired,

} = require('../helper/generalFunctions')


const federalRepReducer = (federalRepState, action)=>{

    switch (action.type){

        case FEDERAL_REP_FIELDS.FEDERAL_REP_FIRST_NAME:
             console.log(federalRepState);
            if(isValidCharacter(action.payload)){

                if(federalRepState.repType){

                    return produce(federalRepState, draft=>{
                        draft.rep.firstName = action.payload;
                    })

                }else{

                    return produce(federalRepState,draft=>{

                        draft.sen.firstName = action.payload;
                    })

                }
            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_LAST_NAME:
            console.log(federalRepState);
            if(isValidCharacter(action.payload)){

                if(federalRepState.repType){

                    return produce(federalRepState, draft=>{

                        draft.rep.lastName = action.payload;
                    })
                }else{

                    return produce(federalRepState, draft=>{

                        draft.sen.lastName =action.payload;
                    })
                }

            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_EMAIL:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.rep.email = action.payload;
                })

            }else{

                return produce(federalRepState, draft=>{

                    draft.sen.email = action.payload;
                })
            }



        case FEDERAL_REP_FIELDS.FEDERAL_REP_PICTURE:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.rep.picture = action.payload;
                })
            }else{

                return produce(federalRepState, draft=>{

                    draft.sen.picture = action.payload;
                })
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_WEBSITE:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.rep.website = action.payload;
                })
            }else{

                return produce(federalRepState, draft=>{

                    draft.sen.website = action.payload;
                });
            }
        case FEDERAL_REP_FIELDS.FEDERAL_REP_DISTRICT:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.rep.congressionalDistrict = action.payload;
                })
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_ELECTED:

            if(!federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.sen.elected = action.payload;
                })

            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_NEXT_ELECTION:

            if(!federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.sen.nextElection = action.payload;
                })
            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.DISTRICT_LIST:

            return produce(federalRepState, draft=>{

                draft.formData.districtList = action.payload;
            })

        case FEDERAL_REP_FIELDS.REP_SELECTOR:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.repType = false;
                })

            }else{

                return produce(federalRepState, draft=>{

                    draft.repType = true;
                })
            }


        default:
            return federalRepState;

    }

}

const FEDERAL_REP_FIELDS ={

    FEDERAL_REP_FIRST_NAME: 'first name',
    FEDERAL_REP_LAST_NAME: 'last name',
    FEDERAL_REP_EMAIL: 'email',
    FEDERAL_REP_PICTURE: 'picture',
    FEDERAL_REP_WEBSITE: 'website',
    FEDERAL_REP_DISTRICT: 'district',
    FEDERAL_REP_ELECTED: 'elected',
    FEDERAL_REP_NEXT_ELECTION: 'next election',
    PHONE_NUMBER: 'Phone Number',
    PHONE_DESCRIPTION: 'Description',
    REP_SELECTOR: 'rep selector',
    LOCATION_NAME: 'location name',
    LOCATION_DESCRIPTION: 'location description',
    LOCATION_CITY: 'location city',
    LOCATION_COUNTY: 'location county',
    LOCATION_ZIP_CODE: 'location zip code',
    LOCATION_STREET_ADDRESS: 'location street address',
    CITY_LIST: 'cityList',
    COUNTY_LIST: 'countyList',
    ZIP_CODE_LIST: 'zipCodeList',
    PARTY_LIST: 'party list',
    LOCATION_LIST: 'location list',
    DISTRICT_LIST: 'district list'


}


const CreateFederalRepView = props =>{



    const [federalRepInfo, dispatchFederalRepInfo] = useReducer(federalRepReducer, {

        rep:{
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            congressionalDistrict: ''.trim(),


        },

        sen:{
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            elected: ''.trim(),
            nextElection: ''.trim()
        },

        phoneNumber:{
            number:''.trim(),
            description:''.trim(),

        },

        location:{

          name: ''.trim(),
          description: ''.trim(),
          streetAddress: ''.trim(),
          county: ''.trim(),
          city: ''.trim(),
          zipCode: ''.trim()

        },

        locationDisplay: [],

        locationList: [],

        phoneNumberList: [],

        formData:{
            partiesList : [],
            districtList: [],
            cityList: [],
            countyList: [],
            zipCodeList: []
        },


        repType: true,
    })



    useEffect(()=>{

        (async ()=>{

            try{

                const districtResponse = await axios.get('http://localhost:8080/congressionalDistricts',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })


                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.DISTRICT_LIST, payload: [...districtResponse.data]})


            }catch(error){


            }
        })()

        return ()=>{}

    }, [])

    const canSubmitRep = (federalRepInfo) =>{

        let isValid = false;

        if(fieldLengthRequired(3, 25, federalRepInfo.rep.firstName)){
            if(fieldLengthRequired(3, 25, federalRepInfo.rep.lastName)){
                if(fieldLengthNotRequired(0, 120, federalRepInfo.rep.email)){
                    if(federalRepInfo.rep.email.length > 0){
                        if(emailValidation(federalRepInfo.sen.email)){
                            if(fieldLengthNotRequired(0, 250, federalRepInfo.rep.picture)){
                                if(fieldLengthNotRequired(0, 300, federalRepInfo.rep.website)){

                                    isValid = true;
                                }
                            }
                        }
                    }else{

                        if(fieldLengthNotRequired(0, 250, federalRepInfo.rep.picture)){
                            if(fieldLengthNotRequired(0, 300, federalRepInfo.rep.website)){

                                isValid = true;
                            }
                        }

                    }
                }
            }
        }

        return isValid;
    }

    const canSubmitSen = (federalRepInfo) =>{

        let isValid = false;

        if(fieldLengthRequired(3, 25, federalRepInfo.sen.firstName)){
            if(fieldLengthRequired(3, 25, federalRepInfo.sen.lastName)){
                if(fieldLengthNotRequired(0, 120, federalRepInfo.sen.email)){
                    if(federalRepInfo.sen.email.length > 0){
                        if(emailValidation(federalRepInfo.sen.email)){
                            if(fieldLengthNotRequired(0, 250, federalRepInfo.sen.picture)){
                                if(fieldLengthNotRequired(0, 300, federalRepInfo.sen.website)){

                                    isValid = true;
                                }
                            }
                        }
                    }else{

                        if(fieldLengthNotRequired(0, 250, federalRepInfo.sen.picture)){
                            if(fieldLengthNotRequired(0, 300, federalRepInfo.sen.website)){

                                isValid = true;
                            }
                        }
                    }

                }
            }
        }

        return isValid;
    }

    const submitHandler = async (e) =>{
        e.preventDefault()

        if(federalRepInfo.repType){

            const createCongressPerson = await axios.post('http://localhost:8080/congressionalReps', federalRepInfo.federalRep, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }

            })


        }else{

            const createSenator = await axios.post('http://localhost:8080/stateSenators', federalRepInfo.federalRep,{

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

        }
    }


    return(
        <div>
            <Header/>
            <div className={'w-50 m-auto'}>
                <button disabled={federalRepInfo.repType} onClick={(e)=>dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_SELECTOR})}>{'Congressional Representatives'}</button>
                <button disabled={!federalRepInfo.repType} onClick={(e)=>dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_SELECTOR})}>{'US Senator'}</button>
                <FederalRepForm
                    formLabelRep={'New Representative'}
                    formLabelSen={'New Senator'}
                    federalRepInfo={federalRepInfo}
                    dispatchFederalRep={dispatchFederalRepInfo}
                    formFields={FEDERAL_REP_FIELDS}
                />
                { federalRepInfo.repType? <button disabled={!canSubmitRep(federalRepInfo)}>{'Create Congress Person'}</button>: <button disabled={!canSubmitSen(federalRepInfo)}>{'Create Senator'}</button> }

            </div>
        </div>
    )
}

export default CreateFederalRepView