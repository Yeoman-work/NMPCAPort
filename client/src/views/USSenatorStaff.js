import React, { useReducer, useEffect } from "react";
import axios from "axios";
import produce from "immer";
import { useParams } from "react-router";
import Header from "../components/Header";
import CongressionalRep from "../components/CongressionalRep";
import StaffForm from "../components/StaffForm";
import StaffElement from "../components/StaffElement";
import PhoneNumberForm from "../components/PhoneNumberForm";
const {isValidCharacter, phoneNumberBuilder} = require('../helper/generalFunctions')


const staffReducer = (staffInfoState, action) =>{

    switch(action.type){

        case STAFF_FIELDS.FIRST_NAME:

            if(action.payload.length <=25){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.staffMember.firstName = action.payload;
                    })

                }else{

                    return staffInfoState;
                }

            }else{

                return staffInfoState;
            }

        case STAFF_FIELDS.LAST_NAME:

            if(action.payload.length <= 25){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.staffMember.lastName = action.payload;
                    })

                }else{

                    return staffInfoState
                }

            }else{

                return staffInfoState;
            }

        case STAFF_FIELDS.EMAIL:

            if(action.payload.length <= 150){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.staffMember.email = action.payload;
                    })

                }else{

                    return staffInfoState;

                }

            }else{

                return staffInfoState;
            }

        case STAFF_FIELDS.TITLE:

            if(action.payload.length <= 50){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.staffMember.title = action.payload;
                    })

                }else{

                    return staffInfoState;

                }

            }else{

                return staffInfoState;
            }

        case STAFF_FIELDS.PHONE_NUMBER:

            return produce(staffInfoState, draft=>{

                draft.phoneNumber.number = phoneNumberBuilder(action.payload, staffInfoState);
            })

        case STAFF_FIELDS.PHONE_DESCRIPTION:

            if(action.payload.length <= 25){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.phoneNumber.description = action.payload;
                    })

                }else{

                    return staffInfoState;

                }

            }else{

                return staffInfoState;
            }

        case STAFF_FIELDS.SENATOR:

            return produce(staffInfoState, draft=>{

                draft.senator = {...action.payload};
            })

        case STAFF_FIELDS.PHONE_NUMBER_LIST:

            return produce(staffInfoState, draft=>{

                draft.phoneNumberList = [...staffInfoState.phoneNumberList, action.payload];
            })

        default:
            return staffInfoState;
    }
}

const STAFF_FIELDS ={

    FIRST_NAME: 'first name',
    LAST_NAME: 'last name',
    EMAIL: 'email',
    TITLE: 'title',
    SENATOR: 'senator',
    PHONE_NUMBER: 'phone number',
    PHONE_DESCRIPTION: 'phone description',
    PHONE_NUMBER_LIST: 'phone number list'
}



const USSenatorStaff = props =>{
    const params = useParams();
    const [staffInfo, dispatchStaffInfo] = useReducer(staffReducer, {

        staffMember:{

            title: ''.trim(),
            firstName: ''.trim().toLowerCase(),
            lastName: ''.trim().toLowerCase(),
            email: ''.trim().toLowerCase()
        },

        senator: {},

        phoneNumber:{
            number: ''.trim(),
            description: ''.trim()
        },

        phoneNumberList: [],

    })



    useEffect(()=>{


        (async ()=>{

            try{

                const senatorResponse = await axios.get('http://localhost:8080/usSenators/' + params.id,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(senatorResponse.data);
                dispatchStaffInfo({type: STAFF_FIELDS.SENATOR, payload: {...senatorResponse.data}});

            }catch(error){

            }

        })()

    }, [params])


    return(
        <div>
            <Header/>
            <CongressionalRep
                rep={staffInfo.senator}
                repType={false}
            />
            <StaffElement
                staffMembers={staffInfo.senator.staffResponses}
            />
            <StaffForm
                formFields={STAFF_FIELDS}
                dispatchFunction={dispatchStaffInfo}
                staffMember={staffInfo.staffMember}
                divProps={'w-50 m-auto mt-5'}
                formLabel={'New Staff Member'}
            />
            <PhoneNumberForm
                formFields={STAFF_FIELDS}
                divClass={'w-50 m-auto pb-3 mt-5'}
                phoneNumber={staffInfo.phoneNumber}
                phoneNumberList={staffInfo.phoneNumberList}
                dispatchFunction={dispatchStaffInfo}
            />
        </div>
    )
}

export default USSenatorStaff