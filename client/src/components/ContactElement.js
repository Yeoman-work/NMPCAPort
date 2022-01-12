import React from "react";
import { Link } from 'react-router-dom'
const { dateAndTime } = require('../helper/generalFunctions')


const ContactElement = props =>{
    const { contact, divProps } = props


    return(
        <div className={'row ' + divProps}>
            <div className={'col p-3'}>
                <h4 className={'text-start mb-5'}><strong>Name: </strong>{`${contact.firstName} ${contact.lastName}`}</h4>
                <h4 className={'text-start mb-5'}><strong>Title: </strong>{contact.title}</h4>
                <h4 className={'text-start mb-5'}><strong>Email: </strong>{contact.email}</h4>
                <h4 className={'text-start mb-5'}><strong>Health Center: </strong>{contact.healthCenterNestedResponse.name}</h4>
                <h6 className={'text-start mt-5 pt-4'}>updated: {contact.updatedAt? dateAndTime(contact.updatedAt): dateAndTime(contact.createdAt)}</h6>
            </div>
            <div className={'col p-3'}>
                <h4>Networking Group(s)</h4>
                <div className={'m-auto w-50'}>
                    <ul>
                        {
                            contact.networkingGroupResponses?
                                contact.networkingGroupResponses.map(({name, networkingGroupId}, index)=>{

                                    return(
                                        <li key={index} className={'text-start'}><Link to={'/'}>{name.toUpperCase()}</Link></li>
                                    )

                                })
                                : null
                        }
                    </ul>
                </div>

            </div>
            <div className={'col height300 p-3'}>
                <h4>Phone Number(s)</h4>
                    <div className={'height200 overflow-auto'}>
                        {
                            contact.phoneNumberResponses?
                                contact.phoneNumberResponses.map((phone, index)=>{

                                    return(
                                        <div key={index}>
                                            <h6>{phone.description}</h6>
                                            <p>{phone.number}</p>
                                        </div>
                                    )
                                })
                                : null
                        }
                    </div>
            </div>
        </div>
    )
}

export default ContactElement;