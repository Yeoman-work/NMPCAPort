import React from "react";




const HealthCenterForm = props =>{
    const {healthCenterInfo, setHealthCenterInfo, formProgression} = props;
    console.log('health form')
    console.log(healthCenterInfo)
    const inputChange = (e) =>{
        e.preventDefault();

        console.log('name ' + e.target.name);
        console.log('value ' + e.target.value);

        let healthCenterObj = {...healthCenterInfo};

        healthCenterObj['newHealthCenter'][e.target.name] = e.target.value;

        setHealthCenterInfo(healthCenterObj);
    }


    return(
        healthCenterInfo?
        <form className={'w-50 m-auto'}>

            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>HealthCenter Name</label>
                    <input type="text" value={healthCenterInfo.newHealthCenter.name} name={'name'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
                </div>
                <div className={'col form-group'}>
                    <label>Name Abbreviation</label>
                    <input type="text" value={healthCenterInfo.newHealthCenter.nameAbbr} name={'nameAbbr'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
                </div>
            </div>
            <div className={'row mt-4'}>
                <div className={'col form-group'} >
                        <h6 className={'mb-3'}>Assign Contacts</h6>
                        {
                            healthCenterInfo?
                            healthCenterInfo.contacts.map((contact, index)=>{

                                return(
                                    <div key={index} className={'btn-group'} role={'group'} aria-label={'Basic checkbox toggle button group'}>
                                        <input type="checkbox" id={contact.contactId} name={'contactList'} className={'btn-check'}/>
                                        <label className={'btn btn-outline-primary'} for={contact.contactId}>{` ${contact.lastName}, ${contact.firstName} (${contact.email})`}</label>
                                    </div>

                                )
                            })
                            :
                                <h5>No Contacts listed</h5>
                        }
                </div>
                <div className={'col form-groups'}>
                    <h6 className={'mb-3'}>Add Contacts</h6>
                    {
                        healthCenterInfo?
                        healthCenterInfo.addedContacts.map((contact, index)=>{

                            return(
                                <div>
                                    <input type="checkbox" id={contact.contactId} name={'addedContact'} value={contact.contactId} className={'btn-check'}/>
                                    <label className={'btn btn-outline-primary'} htmlFor={contact.contactId}>{` ${contact.lastName}, ${contact.firstName} (${contact.email})`}</label>
                                </div>
                            )
                        })
                            :
                            <h5>No contacts listed</h5>
                    }
                </div>
            </div>
                              <button name={'decrement'} onClick={(e)=>formProgression(e)}>Back</button>  <button name={'increment'} onClick={(e)=>formProgression(e)}>Next</button>



        </form>

            :<div>
                This is false
            </div>
    )
}

export default HealthCenterForm