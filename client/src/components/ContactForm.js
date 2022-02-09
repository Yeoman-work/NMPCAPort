import React from "react";
import axios from "axios";
import TextInputField from "./TextInputField";
const {contactInputValidation} = require('../helper/contactValidation')



const ContactForm = props =>{
    const {
        formFields,
        healthCenters,
        networkingGroups,
        contact,
        setContact,
        divProps,
        label
    } = props;

    const inputChange = (e, contact) =>{
        setContact(contactInputValidation(e, contact));

        console.log(contact)
    
    }

    return(
        <div className={divProps}>
            <h1>{label}</h1>
            <div className={"form-group m-auto w-25"}>
                    <label>Title</label>
                    <input
                        name={'title'}
                        className={'form-control'}
                        value={contact.title}
                        onChange={(e)=>inputChange(e, contact)}
                    />
            </div>
            <div className={'row'}>
                <div className={"col form-group"}>
                    <label>First Name</label>
                    <input
                        name={'firstName'}
                        className={'form-control'}
                        value={contact.firstName}
                        onChange={(e)=>inputChange(e, contact)}
                    />
                </div>
                <div className={"col form-group"}>
                    <label>Last Name</label>
                    <input
                        name={'lastName'}
                        className={'form-control'}
                        value={contact.lastName}
                        onChange={(e)=>inputChange(e, contact)}
                    />
                </div>
            </div>
            <div className={'row'}>
            <div className={"col form-group"}>
                    <label>Email</label>
                    <input
                        name={'email'}
                        className={'form-control'}
                        value={contact.email}
                        onChange={(e)=>inputChange(e, contact)}
                    />
                </div>
                <div className={'col'}>
                    <label>HealthCenter</label>
                    <select className={'form-control'}
                            name={'healthCenter'}
                            value={contact.healthCenter}
                            onChange={(e)=>inputChange(e, contact)}
                    >
                        <option value={''}>Choose a HealthCenter</option>
                        {
                        healthCenters?
                            healthCenters.map((healthCenter, index)=>{

                                return(
                                    <option key={index} value={healthCenter.healthCenterId}>{healthCenter.name}</option>
                                )
                            })
                            : null
                        }
                    </select>
                </div>
            </div>
            <div className={'row'}>
                <h6 className={'border'}>Networking Groups</h6>
                <div className={'col'}>
                    <label>Select Networking groups</label>
                    {
                        networkingGroups?
                        networkingGroups.map((group, index)=>{

                            if(!contact.networkingGroups.hasOwnProperty(group.networkingGroupId)){
                                return(

                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                            name={'networkingGroups'}
                                            checked={contact.networkingGroups.hasOwnProperty(group.networkingGroupId)}
                                            className={'form-check-input'}
                                            value={group.networkingGroupId}
                                            onChange={(e)=>inputChange(e, contact)}
                                        />{group.name}</div>
                                )
                            }
                        })
                            : null
                    }
                </div>
                <div className={'col'}>
                    <label>Added Groups</label>
                    {
                        networkingGroups?
                        networkingGroups.map((group, index)=>{

                            if(contact.networkingGroups.hasOwnProperty(group.networkingGroupId)){
                                return(

                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                            className={'form-check-input'}
                                            checked={contact.networkingGroups.hasOwnProperty(group.networkingGroupId)}
                                            name={'networkingGroups'}
                                            value={group.networkingGroupId}
                                            onChange={(e)=>inputChange(e, contact)}
                                        />{group.name}</div>

                                )
                            }

                        })
                            : null
                    }
                </div>
            </div>
        </div>
    )
}

export default ContactForm