import React, {useState} from "react";
const {
    fieldLength,
    fieldLengthErrorMessage
        } = require('../helper/generalFunctions')
const {addPhoneNumberToList, inputPhoneNumber, submitPhoneNumber, clearPhoneNumber} = require('../helper/PhoneNumberFunctions')


const PhoneNumberForm = props =>{
        const {
            phoneNumber,
            stateWithPhoneNumber,
            setStateWithPhoneNumber,
            setPhoneNumber,
            divClass,
            } = props
        
        const [phoneNumberState, setPhoneNumberState] = useState(
            {
            number: ''.trim(),
            description: ''.trim()
        })

        const addPhoneNumberToList = (e, phoneNumberState, stateWithPhoneNumber) =>{
            e.preventDefault()
            let phoneNumberObj = JSON.parse(JSON.stringify(stateWithPhoneNumber));
            phoneNumberObj.phoneNumbers = [...stateWithPhoneNumber.phoneNumbers, phoneNumberState];
            setStateWithPhoneNumber(phoneNumberObj);
            setPhoneNumberState(clearPhoneNumber);
        }

    
        const inputChange = (e) =>{
            console.log(stateWithPhoneNumber);
            console.log('check phone number')
            console.log(inputPhoneNumber(e, phoneNumberState));
            console.log('check from above')
            setPhoneNumberState(inputPhoneNumber(e, phoneNumberState));
        }

        

    return(
        <div className={divClass}>
            <form className={'mb-3'}>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>Add PhoneNumber</label>
                        <div className={'form-group'}>
                            <label>Number</label>
                            <input 
                                type="tel"
                                pattern={'[0-9]{3}-[0-9]{3}-[0-9]{4}'}
                                name={'number'}
                                className={'form-control'}
                                value={phoneNumberState.number}
                                onChange={(e)=>inputChange(e)}
                            />

                        </div>
                        <div className={'form-group mb-3'}>
                            <label>Description</label>
                            <input 
                                type="text"
                                name={'description'}
                                className={'form-control'}
                                value={phoneNumberState.description}
                                onChange={(e)=>inputChange(e)}
                            />

                            { phoneNumber.number.length && fieldLength(5, 25, phoneNumberState.description)? <div className={'text-danger'}>{fieldLengthErrorMessage(5, 25, 'description')}</div> : null}
                        </div>
                        <button 
                            disabled={!submitPhoneNumber(phoneNumberState)}
                            onClick={(e)=>addPhoneNumberToList(e, phoneNumberState, stateWithPhoneNumber)}
                        >
                            Add Phone Number
                        </button>
                    </div>
                    <div className={'col'}>
                        <label>Added Phone Number</label>
                        <div className={'pt-2 height200 overflow-auto'}>
                            {
                            stateWithPhoneNumber?
                            stateWithPhoneNumber.phoneNumbers.map((number, index)=>{

                                return(
                                    <div key={index} className={'mt-2 border w-50 m-auto'}>
                                    <p className={'w-75 overflow-hidden m-auto'}>
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