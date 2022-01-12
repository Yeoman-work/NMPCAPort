import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from 'react-router-dom'
import {AiFillContacts} from 'react-icons/ai'
import Header from "../components/Header";
import ContactElement from "../components/ContactElement";



const ContactDashboardView = props =>{
    const [contacts, setContacts] = useState([])

    useEffect(()=>{

        (async ()=>{

            try{

                const contactResponses = await axios.get('http://localhost:8080/contacts', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(contactResponses.data);
                setContacts(contactResponses.data);

            }catch(error){

                console.log(error.response)

            }

        })()



    }, [])



    return(
        <div>
            <Header/>
            <div className={'mt-5 pt-5 height800 overflow-auto'}>
                <h1 className={'m-auto mb-3'}>Contacts <Link to={'/yeoman/contacts/addContact'}><AiFillContacts/></Link></h1>
                {
                    contacts?
                    contacts.map((contact, index)=>{

                        return(
                            <ContactElement
                                key={index}
                                contact={contact}
                                divProps={'w-75 m-auto overflow-auto border rounded height400'}
                            />
                        )
                    })
                        : null
                }
            </div>
        </div>
    )
}

export default ContactDashboardView