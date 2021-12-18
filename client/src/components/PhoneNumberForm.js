import React from "react";




const PhoneNumberForm = props =>{
        const { dispatchStateRepInfo,
                formFields,
                phoneNumber,
                phoneNumberList
        } = props;

    return(
        <div className={}>
            <form>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>Add PhoneNumber</label>
                        <div className={'form-group'}>
                            <label>Number</label>
                            <input type="tel"
                                   pattern={'[0-9]{3}-[0-9]{2}-[0-9]{3}'}
                                   name={formFields.STATE_REP_PHONE_NUMBER}
                                   className={'form-control'}
                                   value={phoneNumber.number}
                                   onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                    </div>
                    <div className={'col'}>
                        <label>Phone Numbers</label>
                        <div className={''}>
                            {
                             phoneNumberList.map((number, index)=>{

                                 return(
                                     <div key={index}>

                                     </div>
                                 )
                             })
                            }
                        </div>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default PhoneNumberForm;