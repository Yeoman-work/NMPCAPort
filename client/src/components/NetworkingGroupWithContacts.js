import React from "react";




const NetworkingGroupWithContacts = props =>{
    const { divProps, group } = props


    return(
        <div className={divProps}>
            <div>
                <h1>{ group.name }</h1>
                <p>{ group.description }</p>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <h4>Contacts:</h4>
                    <div className={'height200'}>
                        {
                            group.nonMembers.map((contact, index)=>{

                                return(
                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                               className={'form-check-input'}
                                               checked={}
                                               name={}
                                               value={contact.contactId}
                                               onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target})}
                                        />{`${contact.firstName} ${contact.lastName}`}</div>
                                )
                            })
                        }
                    </div>
                </div>
                <div className={'col'}>
                    <h4>Group Members</h4>
                </div>
            </div>
        </div>
    )
}

export default NetworkingGroupWithContacts;