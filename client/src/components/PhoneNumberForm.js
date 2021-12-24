import React from "react";
const {phoneNumberCheck,
       fieldLength,
       fieldLengthErrorMessage
        } = require('../helper/generalFunctions')



const PhoneNumberForm = props =>{
        const { dispatchFunction,
                formFields,
                phoneNumber,
                phoneNumberList,
                handler
        } = props;


    return(
        <div className={'w-75 m-auto pb-3'}>
            <form className={'mb-3'}>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>Add PhoneNumber</label>
                        <div className={'form-group'}>
                            <label>Number</label>
                            <input type="tel"
                                   pattern={'[0-9]{3}-[0-9]{2}-[0-9]{3}'}
                                   name={formFields.PHONE_NUMBER}
                                   className={'form-control'}
                                   value={phoneNumber.number}
                                   onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                            />

                        </div>
                        <div className={'form-group mb-3'}>
                            <label>Description</label>
                            <input type="text"
                                   name={formFields.PHONE_DESCRIPTION}
                                   className={'form-control'}
                                   value={phoneNumber.description}
                                   onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                            />
                            { phoneNumber.number.length && fieldLength(5, 25, phoneNumber.description)? <div className={'text-danger'}>{fieldLengthErrorMessage(5, 25, formFields.PHONE_DESCRIPTION)}</div> : null}
                        </div>
                        <button disabled={phoneNumberCheck(phoneNumber)} onClick={handler}>Add Phone Number</button>
                    </div>
                    <div className={'col'}>
                        <label>Added Phone Number</label>
                        <div className={'pt-2'}>
                            {
                            phoneNumber?
                             phoneNumberList.map((number, index)=>{

                                 return(
                                     <div key={index} className={'mt-2 border w-50 m-auto'}>
                                        <p>
                                            <strong>Number:</strong><br/>
                                             {number.number}<br/>
                                            <strong>Description:</strong><br/>
                                            {number.description}
                                        </p>
                                     </div>
                                 )
                             })
                                : null
                            }
                        </div>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default PhoneNumberForm;