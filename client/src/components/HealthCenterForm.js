import React from "react";
const { characters } = require('../helper/generalFunctions')



const HealthCenterForm = props =>{
    const {healthCenterState,
            healthCareFields,
            dispatchHealthCenter,
            formLabel
        } = props;


    const nextButton = (healthCenterState) =>{
        let disable = true;

        if(healthCenterState.name.length >= 4 && healthCenterState.name.length <= 50){
            if(healthCenterState.nameAbbr.length >= 3 && healthCenterState.nameAbbr.length <= 8 ){
                disable = false;
            }
        }

        return disable;
    }


    return(

        <form className={'w-50 m-auto'}>
            <h1 className={'pt-5 pb-5'}>{formLabel}</h1>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>HealthCenter Name</label>
                    <input type="text"
                           value={healthCenterState.name}
                           name={healthCareFields.HEALTH_CENTER_NAME}
                           className={'form-control'}
                           onChange={(e)=>dispatchHealthCenter({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Name Abbreviation</label>
                    <input type="text"
                           value={healthCenterState.nameAbbr}
                           name={healthCareFields.HEALTH_CENTER_ABBR}
                           className={'form-control'}
                           onChange={(e)=>dispatchHealthCenter({type: e.target.name, payload: e.target.value})}
                    />
                </div>
            </div>
            <div className={'row mt-4'}>
                <div className={'col form-group'} >
                        <h6 className={'mb-3'}>Assign Contacts</h6>
                        {/*{*/}
                        {/*    healthCenterState?*/}
                        {/*    healthCenterState.contacts.map((contact, index)=>{*/}

                        {/*        return(*/}
                        {/*            <div key={index} className={'btn-group'} role={'group'} aria-label={'Basic checkbox toggle button group'}>*/}
                        {/*                <input type="checkbox" id={contact.contactId} name={'contactList'} className={'btn-check'}/>*/}
                        {/*                <label className={'btn btn-outline-primary'} for={contact.contactId}>{` ${contact.lastName}, ${contact.firstName} (${contact.email})`}</label>*/}
                        {/*            </div>*/}

                        {/*        )*/}
                        {/*    })*/}
                        {/*    :*/}
                        {/*        <h5>No Contacts listed</h5>*/}
                        {/*}*/}
                </div>
                <div className={'col form-groups'}>
                    <h6 className={'mb-3'}>Add Contacts</h6>
                    {
                        // healthCenterState?
                        // healthCenterInfo.addedContacts.map((contact, index)=>{
                        //
                        //     return(
                        //         <div>
                        //             <input type="checkbox" id={contact.contactId} name={'addedContact'} value={contact.contactId} className={'btn-check'}/>
                        //             <label className={'btn btn-outline-primary'} htmlFor={contact.contactId}>{` ${contact.lastName}, ${contact.firstName} (${contact.email})`}</label>
                        //         </div>
                        //     )
                        // })
                        //     :
                        //     <h5>No contacts listed</h5>
                    }
                </div>
            </div>
                              <button onClick={(e)=>dispatchHealthCenter({type: healthCareFields.FORM_DECREMENT})}>Back</button>
                              <button disabled={nextButton(healthCenterState)} onClick={(e)=>dispatchHealthCenter({type: healthCareFields.FORM_INCREMENT})}>Next</button>



        </form>


    )
}

export default HealthCenterForm