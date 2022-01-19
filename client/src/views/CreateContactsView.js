import React, { useReducer, useEffect } from "react";
import axios from "axios";
import produce from "immer";
import Header from "../components/Header";
import ContactForm from "../components/ContactForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
import Button from "../components/Button";
import {useParams} from "react-router";
const { isValidCharacter, phoneNumberBuilder } = require('../helper/generalFunctions')
const { isContact } = require('../helper/contactValidation')

const contactReducer = (contactState, action) =>{
    console.log(contactState);
    switch(action.type){

        case FORM_FIELDS.FIRST_NAME:
            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 25){

                    console.log(contactState);
                    return produce(contactState, draft=>{

                        draft.contact.firstName = action.payload;
                    })

                }else{

                    return contactState;
                }

            }else if(action.payload.length < 1){

                return produce(contactState, draft=>{

                    draft.contact.firstName = action.payload;
                })

            }else{

                return contactState;

            }

        case FORM_FIELDS.LAST_NAME:
            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 25){

                    return produce(contactState, draft=>{

                        draft.contact.lastName = action.payload;
                    })

                }else{

                }

            }else if(action.payload.length < 1 ){

                return produce(contactState, draft=>{

                    draft.contact.lastName = action.payload;
                })

            }else{

                return contactState;

            }

        case FORM_FIELDS.EMAIL:

            if(action.payload.length <=150){
                return produce(contactState, draft=>{

                    draft.contact.email = action.payload;
                })
            }else{

                return contactState;
            }

        case FORM_FIELDS.TITLE:

            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 25){

                    return produce(contactState, draft=>{

                        draft.contact.title = action.payload;

                    })
                }else{

                    return contactState;
                }
            }else if (action.payload.length < 1){

                return produce(contactState, draft=>{

                    draft.contact.title = action.payload;
                })

            }else{

                return contactState;

            }

        case FORM_FIELDS.HEALTH_CENTER:

            return produce(contactState, draft=>{

                draft.contact.healthCenter = action.payload;
            })

        case FORM_FIELDS.NETWORK_GRP:
            const {checked, value} = action.payload;

            if(checked){

                if(!contactState.contact.networkingGroups.includes(value)){
                    return produce(contactState, draft=>{

                        draft.contact.networkingGroups = [...contactState.contact.networkingGroups, value];

                        console.log(contactState.contact.networkingGroups)
                    })
                }else{

                    return contactState;
                }

            }else{

                if(contactState.contact.networkingGroups.includes(value)){
                    return produce(contactState, draft=>{

                        const removeAtIndex = contactState.contact.networkingGroups.indexOf(value);

                        let networkingGroupList = [...contactState.contact.networkingGroups]

                        networkingGroupList.splice(removeAtIndex, 1)

                        draft.contact.networkingGroups = [...networkingGroupList];

                    })
                }else{

                    return contactState;
                }

            }


        case FORM_FIELDS.HEALTH_CENTER_LIST:

            return produce(contactState, draft=>{

                draft.healthCenters = [...action.payload]
            })

        case FORM_FIELDS.NETWORK_GRP_LIST:

            return produce(contactState, draft=>{

                draft.networkingGroupsList = [...action.payload];
            })

        case FORM_FIELDS.PHONE_NUMBER:

            return produce(contactState, draft=>{

                draft.phoneNumber.number = phoneNumberBuilder(action.payload, contactState.phoneNumber.number);
            })

        case FORM_FIELDS.PHONE_DESCRIPTION:

            if(action.payload.length <= 25){

                if(isValidCharacter(action.payload)){

                    return produce(contactState, draft=>{

                        draft.phoneNumber.description = action.payload;
                    })


                }else if(action.payload.length < 1){

                    return produce(contactState, draft=>{

                        draft.phoneNumber.description = action.payload;
                    })


                }else{

                    return contactState;
                }


            }else{

                return contactState;

            }

        case FORM_FIELDS.PHONE_NUMBER_LIST:

            return produce(contactState, draft=>{

                draft.contact.phoneNumbers = [...contactState.contact.phoneNumbers, contactState.phoneNumber];

                draft.phoneNumber = clearData.phoneNumber;
            })

        case FORM_FIELDS.CLEAR_CONTACT:

            return produce(contactState, draft=>{

                draft.contact = clearData.contact;
            })

    }
}


const clearData={


    contact:{
        firstName: ''.trim().toLowerCase(),
        lastName: ''.trim().toLowerCase(),
        email: ''.trim().toLowerCase(),
        title: ''.trim().toLowerCase(),
        healthCenter: ''.trim(),
        phoneNumbers: [],
        networkingGroups: []
    },

    phoneNumber:{
        number: ''.trim(),
        description: ''.trim()
    }

}


const FORM_FIELDS={

    FIRST_NAME: 'firstName',
    LAST_NAME: 'lastName',
    EMAIL: 'email',
    TITLE: 'title',
    HEALTH_CENTER: 'healthCenter',
    HEALTH_CENTER_LIST: 'healthCenterList',
    NETWORK_GRP: 'networkGroup',
    NETWORK_GRP_LIST: 'networkGroupList',
    CLEAR_CONTACT: 'clearContact',
    PHONE_NUMBER: 'phone numbers',
    PHONE_DESCRIPTION: 'phone description',
    PHONE_NUMBER_LIST: 'phone number list',

}

const CreateContactsView = props =>{
    const { id } = useParams()
    const [contactInfo, dispatchContactInfo] = useReducer(contactReducer, {

        contact:{
            firstName: ''.trim().toLowerCase(),
            lastName: ''.trim().toLowerCase(),
            email: ''.trim().toLowerCase(),
            title: ''.trim().toLowerCase(),
            healthCenter: ''.trim(),
            phoneNumbers: [],
            networkingGroups: []
        },

        phoneNumber: {
            number: ''.trim(),
            description: ''.trim()
        },

        networkingGroupsList: [],

        healthCenters: []

    })


    useEffect(()=>{

        console.log('top')
        console.log(id);
        console.log('bottom')
        if(id !== undefined && id !== null)
            dispatchContactInfo({type: FORM_FIELDS.NETWORK_GRP, payload: {checked: true, value: id}})

    }, [id])


    useEffect(()=>{

        (async ()=>{

            try{

                const networkingListResponse = await axios.get('http://localhost:8080/networkingGroups', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(networkingListResponse.data);
                dispatchContactInfo({type: FORM_FIELDS.NETWORK_GRP_LIST, payload: [...networkingListResponse.data]})


            }catch(error){

                console.log(error.response)

            }

        })()

    },[])


    useEffect(()=>{

        (async()=>{

            try{

              const  healthCenterResponse = await axios.get('http://localhost:8080/healthCenters', {

                  headers:{
                      Authorization: localStorage.getItem('token')
                  }
              })

              console.log(healthCenterResponse.data)
              dispatchContactInfo({type: FORM_FIELDS.HEALTH_CENTER_LIST, payload: [...healthCenterResponse.data]})

            }catch(error){

                console.log(error.response)

            }
        })()
    }, [])





    const submitContact = async (e) =>{

        e.preventDefault();

        try{

            const savedContactResponse = await axios.post('http://localhost:8080/contacts', contactInfo.contact, {

                headers:{

                    Authorization: localStorage.getItem('token')
                }
            })

            dispatchContactInfo({type: FORM_FIELDS.CLEAR_CONTACT})
            console.log('read this')
            console.log(savedContactResponse.data);

        }catch(error){

            console.log(error.response);

        }
    }

    return(
        <div>
            <Header/>
            <div className={'mt-5 pt-5'}>
                <ContactForm
                    dispatchFunction={dispatchContactInfo}
                    formFields={FORM_FIELDS}
                    contactData={contactInfo}
                    label={"New Contact"}
                    divProps={'w-50 m-auto'}
                />
            </div>
            <div className={'m-auto mt-5'}>
                <h6 className={'border w-50 m-auto'}>Phone Numbers</h6>
                <PhoneNumberForm
                    dispatchFunction={dispatchContactInfo}
                    formFields={FORM_FIELDS}
                    phoneNumber={contactInfo.phoneNumber}
                    phoneNumberList={contactInfo.contact.phoneNumbers}
                    divClass={'m-auto w-50'}
                />
            </div>
            <Button
                label={'Save Contact'}
                action={submitContact}
                disable={!isContact(contactInfo.contact)}

            />
        </div>
    )
}

export default CreateContactsView