import React from "react";
const {phoneNumberCheck,
       fieldLength,
       fieldLengthErrorMessage
        } = require('../helper/generalFunctions')


const clearPhoneNumber = {

    phoneNumber:{
        number: ''.trim(),
        description: ''.trim()
    },



}

const PhoneNumberForm = props =>{
        const {phoneNumber,
               phoneNumberList,
               setPhoneNumberList,
               setPhoneNumber,
               divClass,
            } = props


        const inputChange = (e) =>{

            let phoneNumberObj = {...phoneNumber};

            phoneNumberObj[e.target.name] = e.target.value;

            setPhoneNumber(phoneNumberObj);
        }

        const addPhoneNumber = (e) =>{

            let phoneNumberObjList = [...phoneNumberList, e.target.value];

            setPhoneNumberList(phoneNumberObjList);
        }



    return(
        <div className={divClass}>
            <form className={'mb-3'}>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>Add PhoneNumber</label>
                        <div className={'form-group'}>
                            <label>Number</label>
                            <input type="tel"
                                   pattern={'[0-9]{3}-[0-9]{3}-[0-9]{4}'}
                                   name={'number'}
                                   className={'form-control'}
                                   value={phoneNumber.number}
                                    onChange={(e)=>inputChange(e)}
                            />

                        </div>
                        <div className={'form-group mb-3'}>
                            <label>Description</label>
                            <input type="text"
                                   name={'description'}
                                   className={'form-control'}
                                   value={phoneNumber.description}
                                   onChange={(e)=>inputChange(e)}
                            />

                            { phoneNumber.number.length && fieldLength(5, 25, phoneNumber.description)? <div className={'text-danger'}>{fieldLengthErrorMessage(5, 25, 'description')}</div> : null}
                        </div>
                        <button>Add Phone Number</button>
                    </div>
                    <div className={'col'}>
                        <label>Added Phone Number</label>
                        <div className={'pt-2 height200 overflow-auto'}>
                            {
                            phoneNumber?
                             phoneNumberList.map((number, index)=>{

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