import React, { useReducer, useEffect } from "react";
import axios from "axios";
import produce from "immer";
import { useParams } from "react-router";
import Header from "../components/Header";
import CongressionalRep from "../components/CongressionalRep";
import StaffForm from "../components/StaffForm";
import StaffElement from "../components/StaffElement";
import PhoneNumberForm from "../components/PhoneNumberForm";
const {isValidCharacter,
       phoneNumberBuilder,
       fieldLengthRequired,
       fieldLengthNotRequired,
       emailValidation
} = require('../helper/generalFunctions')


const staffReducer = (staffInfoState, action) =>{

    switch(action.type){

        case STAFF_FIELDS.FIRST_NAME:

            console.log(staffInfoState);
            if(action.payload.length <=25){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.staffMember.firstName = action.payload;
                    })

                }else if(action.payload.length < 1){

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

                }else if(action.payload.length < 1){

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

                }else if(action.payload.length < 1){

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

                }else if(action.payload.length < 1){

                    return produce(staffInfoState, draft =>{

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

                draft.phoneNumber.number = phoneNumberBuilder(action.payload, staffInfoState.phoneNumber.number);
            })

        case STAFF_FIELDS.PHONE_DESCRIPTION:

            if(action.payload.length <= 25){

                if(isValidCharacter(action.payload)){

                    return produce(staffInfoState, draft=>{

                        draft.phoneNumber.description = action.payload;
                    })

                }else if(action.payload.length < 1){

                    return produce(staffInfoState, draft =>{

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
            console.log(staffInfoState)
            return produce(staffInfoState, draft=>{

                draft.staffMember.phoneNumberList = [...staffInfoState.staffMember.phoneNumberList, staffInfoState.phoneNumber];

                draft.phoneNumber = {...clearPhoneNumber};
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
    PHONE_NUMBER: 'number',
    PHONE_DESCRIPTION: 'description',
    PHONE_NUMBER_LIST: 'phone number list'
}

const clearPhoneNumber ={

        number: ''.trim(),
        description: ''.trim()
}



const USSenatorStaff = props =>{
    const params = useParams();
    const [staffInfo, dispatchStaffInfo] = useReducer(staffReducer, {

        staffMember:{
            title: ''.trim(),
            firstName: ''.trim().toLowerCase(),
            lastName: ''.trim().toLowerCase(),
            email: ''.trim().toLowerCase(),
            senator: params.id,
            phoneNumberList: [],
        },

        senator: {},

        phoneNumber:{
            number: ''.trim(),
            description: ''.trim()
        },




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

                console.log(error.response)
            }

        })()

    }, [params])


    const saveStaffMember = async (e)=>{
        e.preventDefault();
        console.log(staffInfo.staffMember)
        try{

            const savedStaffMember = await axios.post('http://localhost:8080/staff', {...staffInfo.staffMember}, {

                headers:{

                    Authorization: localStorage.getItem('token')
                }
            })

            console.log(savedStaffMember);


        }catch(error){

            console.log(error.response);

        }

    }



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
                phoneNumberList={staffInfo.staffMember.phoneNumberList}
                dispatchFunction={dispatchStaffInfo}
            />
            <button onClick={(e)=>saveStaffMember(e)} >Save Staff Member</button >
        </div>
    )
}

export default USSenatorStaff