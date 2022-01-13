import React from "react";




const NetworkingGroupWithContacts = props =>{
    const { divProps,
        contacts,
        nonMemberIds,
        memberIds,
        formField,
        dispatchFunction
    } = props


    return(
        <div className={'row ' + divProps}>
            <div className={'col'}>
                <h4>Contacts:</h4>
                <div className={'height200 w-75 m-auto'}>
                    {
                        contacts?
                        contacts.map((contact, index)=>{

                            return(
                                memberIds?
                                !memberIds.includes(contact.contactId)?

                                    <div key={index} className={'border ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                               className={'form-check-input'}
                                               checked={memberIds.includes(contact.contactId)}
                                               name={formField.CONTACTS? formField.CONTACTS: null}
                                               value={contact.contactId}
                                               onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target})}
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
                                    memberIds?
                                    memberIds.includes(contact.contactId)?
                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                               className={'form-check-input'}
                                               checked={memberIds.includes(contact.contactId)}
                                               name={formField.CONTACTS? formField.CONTACTS: null}
                                               value={contact.contactId}
                                               onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target})}
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