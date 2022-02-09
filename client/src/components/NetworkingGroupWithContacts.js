import React from "react";
const {changeMembership} = require('../helper/NetworkingGroupFunctions')




const NetworkingGroupWithContacts = props =>{
    const { 
        divProps,
        contacts,
        networkingGroup,
        setNetworkingGroup
    } = props


    const inputChange = (e, networkingGroupState) =>{

        setNetworkingGroup(changeMembership(e, networkingGroupState));
        console.log(networkingGroup);
    }

    return(
        <div className={'row ' + divProps}>
            <div className={'col'}>
                <h4>Contacts:</h4>
                <div className={'height200 w-75 m-auto'}>
                    {
                        contacts?
                        contacts.map((contact, index)=>{

                            return(
                                networkingGroup?
                                !networkingGroup.memberIds.hasOwnProperty(contact.contactId)?

                                    <div key={index} className={'border ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                            className={'form-check-input'}
                                            checked={networkingGroup.memberIds.hasOwnProperty(contact.contactId)}
                                            name={'contact'}
                                            value={contact.contactId}
                                            onChange={(e)=>inputChange(e, networkingGroup)}
                                            
                                        />{`${contact.firstName} ${contact.lastName}`}</div>

                                    : null
                                    : null


                            )
                        })
                            :
                            null
                    }
                </div>
            </div>
            <div className={'col'}>
                <h4>Group Members</h4>
                <div className={'height200'}>
                    {
                        contacts?
                            contacts.map((contact, index)=>{

                                return(
                                    networkingGroup?
                                    networkingGroup.memberIds.hasOwnProperty(contact.contactId)?
                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                            className={'form-check-input'}
                                            checked={networkingGroup.memberIds.hasOwnProperty(contact.contactId)}
                                            name={'contact'}
                                            value={contact.contactId}
                                            onChange={(e)=>inputChange(e, networkingGroup)}
                                        />{`${contact.firstName} ${contact.lastName}`}</div>

                                        : null
                                        : null
                                )
                            })
                            :
                            null
                    }
                </div>
            </div>
        </div>
    )
}

export default NetworkingGroupWithContacts;