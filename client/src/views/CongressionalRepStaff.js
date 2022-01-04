import React, {useReducer, useEffect} from "react";
import axios from "axios";
import Header from "../components/Header";
import CongressionalRep from "../components/CongressionalRep";
import StaffElement from "../components/StaffElement";
import StaffForm from "../components/StaffForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
import {useParams} from "react-router";
import {
    emailValidation,
    fieldLengthNotRequired,
    fieldLengthRequired,
    isValidCharacter,
    phoneNumberBuilder,
    staffMemberValidation
} from "../helper/generalFunctions";
import produce from "immer";


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

        case STAFF_FIELDS.REP:

            return produce(staffInfoState, draft=>{

                draft.rep = {...action.payload};
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
    REP: 'rep',
    PHONE_NUMBER: 'number',
    PHONE_DESCRIPTION: 'description',
    PHONE_NUMBER_LIST: 'phone number list'
}

const clearPhoneNumber ={

    number: ''.trim(),
    description: ''.trim()
}


const CongressionalRepStaff = props =>{
    const { id } = useParams()
    const [staffInfo, dispatchStaffInfo] = useReducer(staffReducer, {

        staffMember:{
            title: ''.trim(),
            firstName: ''.trim().toLowerCase(),
            lastName: ''.trim().toLowerCase(),
            email: ''.trim().toLowerCase(),
            rep: id,
            phoneNumberList: [],
        },

        rep: {},

        phoneNumber:{
            number: ''.trim(),
            description: ''.trim()
        },

    })

    useEffect(()=>{

        (async ()=>{

            try{

                const congressionalRepResponse = await axios.get('http://localhost:8080/congressionalReps/' + id,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(congressionalRepResponse.data);
                dispatchStaffInfo({type: STAFF_FIELDS.REP, payload: {...congressionalRepResponse.data}})

            }catch(error){


            }

        })()

    }, [id])


    const submitHandler = async (e)=>{
        e.preventDefault();

        try{

            const staffResponse = await axios.post('http://localhost:8080/staff/congressionalRep', staffInfo.staffMember, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

            console.log(staffResponse.data);

        }catch(error){


        }

    }


    return(
        <div>
            <Header/>
            <CongressionalRep
                rep={staffInfo.rep}
                repType={true}
            />
            <StaffElement
                staffMembers={staffInfo.rep.staffResponses}
                divProps={'height200 overflow-auto mt-4 p-3 border'}
            />
            <div>
                <StaffForm
                    formFields={STAFF_FIELDS}
                    dispatchFunction={dispatchStaffInfo}
                    staffMember={staffInfo.staffMember}
                    divProps={'w-50 m-auto mt-5'}
                    formLabel={'New Staff Member'}
                />
                <PhoneNumberForm
                    formFields={STAFF_FIELDS}
                    dispatchFunction={dispatchStaffInfo}
                    divClass={'w-50 m-auto pb-3'}
                    phoneNumber={staffInfo.phoneNumber}
                    phoneNumberList={staffInfo.staffMember.phoneNumberList}
                />
            </div>
            <button disabled={!staffMemberValidation(staffInfo)} onClick={(e)=>submitHandler(e)} >Save Staff Member</button>
        </div>
    )
}

export default CongressionalRepStaff