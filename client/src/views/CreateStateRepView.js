import React, { useState, useEffect, useReducer } from "react";
import axios from "axios";
import Header from "../components/Header";
import produce from "immer";
import StateRepForm from "../components/StateRepForm";
import {number} from "../helper/generalFunctions";
import PhoneNumberForm from "../components/PhoneNumberForm";
const { phoneNumberPattern,
        characters,
        fieldLengthErrorMessage,
        fieldLength,
        emailValidation,
        fieldLengthNotRequired,
     } = require('../helper/generalFunctions')


const stateRepReducer = (stateRepState, action) =>{

    switch(action.type){
        case STATE_REP_FIELDS.STATE_REP_FIRST_NAME:

            if(characters.includes(action.payload[action.payload.length - 1]) || action.payload.length === 0){

                if(action.payload.length <= 50) {

                    return produce(stateRepState, draft => {
                        console.log('here')
                        draft.stateRep.firstName = action.payload;
                    })
                }

            }else{

                return stateRepState;
            }

        case STATE_REP_FIELDS.STATE_REP_LAST_NAME:


            if(characters.includes(action.payload[action.payload.length - 1]) || action.payload.length === 0){
                if(action.payload.length <= 50){

                    return produce(stateRepState, draft=>{
                        draft.stateRep.lastName = action.payload;
                    })

                }else{

                    return stateRepState;
                }
            }else{

                return stateRepState;
            }


        case STATE_REP_FIELDS.STATE_REP_EMAIL:
            console.log(stateRepState.stateRep);

            if(action.payload.length <= 150){
                return produce(stateRepState, draft=>{
                    draft.stateRep.email = action.payload;
                })
            }else{

                return stateRepState;
            }

        case STATE_REP_FIELDS.STATE_REP_ADDRESS:
            console.log(stateRepState.stateRep);
            if(action.payload.length <= 150){
                return produce(stateRepState, draft=>{
                    draft.stateRep.streetAddress = action.payload;
                })
            }else{

                return stateRepState;
            }

        case STATE_REP_FIELDS.STATE_REP_CITY:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.city = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_ZIP_CODE:
            console.log('sip code')
            console.log(action.payload);
            return produce(stateRepState, draft=>{
                draft.stateRep.zipCode = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_CAPITAL_RM:
            console.log(stateRepState.stateRep);
            if(characters.includes(action.payload[action.payload.length - 1])){
                if(action.payload.length <=8){
                    return produce(stateRepState, draft=>{
                        draft.stateRep.capitolRoom = action.payload;
                    })
                }else{

                    if(action.payload.length === 0){

                        return produce(stateRepState, draft=>{

                            draft.stateRep.capitolRoom = action.payload;
                        })
                    }
                    return stateRepState;
                }
            }



        case STATE_REP_FIELDS.STATE_REP_DISTRICT:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.stateRep.district = action.payload;
            })

        case STATE_REP_FIELDS.STATE_REP_PICTURE:

            if(action.payload.length <= 250){

                return produce(stateRepState, draft=>{
                    draft.stateRep.picture = action.payload
                })

            }else{

                return stateRepState;
            }


        case STATE_REP_FIELDS.STATE_REP_COUNTIES:

            if(action.payload.checked){
                return produce(stateRepState, draft=>{
                    draft.stateRep.counties = [...stateRepState.stateRep.counties, action.payload.value]
                })

            }else{
                return produce(stateRepState, draft=>{

                    let counties = draft.stateRep.counties;
                    const removeIndex = counties.indexOf(action.payload.value);
                    counties.splice(removeIndex, 1);
                    draft.stateRep.counties = [...counties];

                })
            }


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
            console.log('zip code list')
            console.log(stateRepState.formData);
            return produce(stateRepState, draft=>{
                draft.formData.zipCodeList = [...action.payload];
            })

        case STATE_REP_FIELDS.DISTRICTS:
            console.log(stateRepState.stateRep);
            return produce(stateRepState, draft=>{
                draft.formData.districtList = [...action.payload];
            })

        case STATE_REP_FIELDS.STATE_REP_PHONE_NUMBER:

            let phoneNumber = action.payload;

            if(number.includes(phoneNumber[phoneNumber.length - 1])){

                if(action.payload.length <= 12){

                    return produce(stateRepState, draft=>{
                        draft.phoneNumber.number = phoneNumberPattern(action.payload);
                        console.log(stateRepState);
                    })

                }else{

                    return stateRepState;
                }

            }else if(phoneNumber.length < 1){

                return produce(stateRepState, draft=>{

                    draft.phoneNumber.number = phoneNumberPattern(action.payload);

                })

            }else{

                return stateRepState;
            }


        case STATE_REP_FIELDS.STATE_REP_PHONE_DESCRIPTION:

            if(characters.includes(action.payload[action.payload.length - 1]) || action.payload.length === 0){
                if(action.payload.length <= 25){
                    return produce(stateRepState, draft=>{

                        draft.phoneNumber.description = action.payload;
                    })
                }else{

                    return stateRepState;
                }
            }else{

                return stateRepState;
            }

        case STATE_REP_FIELDS.Phone_Number_List:

            return produce(stateRepState, draft=>{

                draft.phoneNumberList = [...stateRepState.phoneNumberList, action.payload];
            })

        case STATE_REP_FIELDS.CLEAR_PHONE_NUMBER:
            const clearPhoneNumber ={
                number: '',
                description: ''
        }
            return produce(stateRepState, draft=>{

                draft.phoneNumber = {...clearPhoneNumber};
            })

        default:
            return stateRepState;

    }
}

const STATE_REP_FIELDS ={

    STATE_REP_FIRST_NAME: 'First Name',
    STATE_REP_LAST_NAME: 'Last Name',
    STATE_REP_EMAIL: 'email',
    STATE_REP_PICTURE: 'picture',
    STATE_REP_ADDRESS: 'streetAddress',
    STATE_REP_CITY: 'city',
    STATE_REP_ZIP_CODE: 'zipCode',
    STATE_REP_DISTRICT: 'district',
    STATE_REP_CAPITAL_RM: 'capitalRoom',
    STATE_REP_COUNTIES: 'counties',
    STATE_REP_PHONE_NUMBER: 'phoneNumber',
    STATE_REP_PHONE_DESCRIPTION: 'Phone Description',
    COUNTIES: 'countiesList',
    CITIES: 'citiesList',
    ZIP_CODE: 'zipCodeList',
    DISTRICTS: 'districtList',
    Phone_Number_List: 'phoneNumberList',
    CLEAR_PHONE_NUMBER: 'clearPhoneNumber'
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

        errors:{
          firstName: '',
          lastName: '',
          email: '',
          picture:'',
          streetAddress:'',
          city:'',
          capitolRoom: '',
          zipCode: '',
          nmHouseDistrict: '',
          counties: ''
        },

        phoneNumber:{
            number: '',
            description: '',
        },

        phoneNumberList: [],

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

                const countyListResponse = await axios.get('http://localhost:8080/counties',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchStateRepInfo({type: STATE_REP_FIELDS.COUNTIES, payload: [...countyListResponse.data]})

                console.log(countyListResponse.data);

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

                dispatchStateRepInfo({type: STATE_REP_FIELDS.CITIES, payload: [...cityListResponse.data]})
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

                console.log(zipCodeListResponse.data);
                dispatchStateRepInfo({type: STATE_REP_FIELDS.ZIP_CODE, payload: [...zipCodeListResponse.data]})

            }catch(error){

                console.log(error.response)

            }
        })()

    },[])

    useEffect(()=>{

        (async ()=>{

            try{

                const houseDistrictResponse = await axios.get('http://localhost:8080/nmHouseDistricts', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                dispatchStateRepInfo({type: STATE_REP_FIELDS.DISTRICTS, payload: [...houseDistrictResponse.data]})

            }catch(error){

                console.log(error.response);
            }
        })()
    }, [])


    const createRep = async (e)=>{
        e.preventDefault()

        try{

            const createRepResponse = await  axios.post('http://localhost:8080/stateReps', stateRepInfo.stateRep, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }

            })


        }catch(error){


        }
    }

    const addPhoneNumber = (e) =>{
        e.preventDefault();
        dispatchStateRepInfo({type: STATE_REP_FIELDS.Phone_Number_List, payload: stateRepInfo.phoneNumber})

        dispatchStateRepInfo({type: STATE_REP_FIELDS.CLEAR_PHONE_NUMBER})

    }

    const stateLength = (state, lengthMin, lengthMax, required) =>{
        let isValid = false;

        if(required){

            if(state.length >= lengthMin && state.length <= lengthMax){

                isValid = true;
            }
        }else{

            if((state.length === 0) || (state.length >= lengthMin && state.length <= lengthMax)){

                isValid = true
            }
        }

        return isValid;
    }


    const canSubmit = (stateRepInfo) =>{

        let isDisabled = true;

        if(!fieldLength(3, 50, stateRepInfo.stateRep.firstName)){
            if(!fieldLength(3, 50, stateRepInfo.stateRep.lastName)){
                if(emailValidation(stateRepInfo.stateRep.email)){
                    if(fieldLengthNotRequired(0, 150, stateRepInfo.stateRep.email)){
                        if(fieldLengthNotRequired(5, 250, stateRepInfo.stateRep.picture)){
                            if(fieldLengthNotRequired(5, 150, stateRepInfo.stateRep.streetAddress)){
                                if(fieldLengthNotRequired(0, 8, stateRepInfo.stateRep.capitolRoom)){
                                    isDisabled = false;
                                }
                            }
                        }
                    }
                }
            }

        }

        console.log(isDisabled)
        return isDisabled;
    }

    return(
        <div>
            <Header/>
            <div className={''}>
                <StateRepForm
                    stateRepInfo={stateRepInfo}
                    formFields={STATE_REP_FIELDS}
                    dispatchStateRepInfo={dispatchStateRepInfo}
                    handler={createRep}
                    formLabel={'Create State Rep'}
                    fieldLength={fieldLength}
                    fieldLengthErrorMessages={fieldLengthErrorMessage}
                    fieldLengthNotRequired={fieldLengthNotRequired}
                    emailValidation={emailValidation}
                />
                <PhoneNumberForm
                    formFields={STATE_REP_FIELDS}
                    dispatchStateRepInfo={dispatchStateRepInfo}
                    phoneNumber={stateRepInfo.phoneNumber}
                    phoneNumberList={stateRepInfo.phoneNumberList}
                    handler={addPhoneNumber}
                />
            </div>
            <button disabled={canSubmit(stateRepInfo)}>Save State</button>
        </div>
    )
}

export default CreateStateRepView;