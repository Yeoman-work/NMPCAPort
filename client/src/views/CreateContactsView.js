import React, { useEffect, useState } from "react"
import { useNavigate } from 'react-router'
import axios from "axios";
import Header from "../components/Header";
import ContactForm from "../components/ContactForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
import Button from "../components/Button";
import {useParams} from "react-router";
const { isContact } = require('../helper/contactValidation')

const CreateContactsView = props =>{
    const navigate = useNavigate();
    const { id } = useParams()
    const [contact, setContact] = useState({
            
            firstName: ''.trim().toLowerCase(),
            lastName: ''.trim().toLowerCase(),
            email: ''.trim().toLowerCase(),
            title: ''.trim().toLowerCase(),
            healthCenter: ''.trim(),
            phoneNumbers: [],
            networkingGroups: {}
    })
    
    const [networkingGroups, setNetworkingGroups] = useState([]);
    const [healthCenters, setHealthCenters] = useState([]);




    useEffect(()=>{

        (async ()=>{

            try{

                const networkingListResponse = await axios.get('http://localhost:8080/networkingGroups', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(networkingListResponse.data);
                setNetworkingGroups(networkingListResponse.data);


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
                setHealthCenters(healthCenterResponse.data);

            }catch(error){

                console.log(error.response)

            }
        })()
    }, [])





    const submitContact = async (e) =>{

        e.preventDefault();

        try{

            const savedContactResponse = await axios.post('http://localhost:8080/contacts', contact, {

                headers:{

                    Authorization: localStorage.getItem('token')
                }
            })

            

            if(savedContactResponse.data){

                navigate('/yeoman/contacts/dashboard')
            }

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
                    contact={contact}
                    setContact={setContact}
                    healthCenters={healthCenters}
                    networkingGroups={networkingGroups}
                    label={"New Contact"}
                    divProps={'w-50 m-auto'}
                />
            </div>
            <div className={'m-auto mt-5'}>
                <h6 className={'border w-50 m-auto'}>Phone Numbers</h6>
                <PhoneNumberForm
                    stateWithPhoneNumber={contact}
                    setStateWithPhoneNumber={setContact}
                    divClass={'m-auto w-50'}
                />
            </div>
            <Button
                label={'Save Contact'}
                action={submitContact}
                disable={!isContact(contact)}
            />
        </div>
    )
}

export default CreateContactsView