import React from "react";
import axios from "axios";
import TextInputField from "./TextInputField";



const ContactForm = props =>{
  const {formFields,
         dispatchFunction,
         contactData,
         divProps,
         label
  } = props;

    return(
        <form className={divProps}>
            <h1>{label}</h1>
            <TextInputField
                label={'Title'}
                type={'text'}
                fieldClass={'form-control'}
                divClass={'form-group m-auto w-25'}
                fieldValue={contactData.contact.title}
                onChange={dispatchFunction}
            />
            <div className={'row'}>
                <TextInputField
                    label={'First Name'}
                    type={'text'}
                    fieldClass={"form-control"}
                    divClass={'form-group col'}
                    fieldValue={contactData.contact.firstName}
                    onChange={dispatchFunction}
                />
                <TextInputField
                    label={'Last Name'}
                    type={'text'}
                    fieldClass={'form-control'}
                    divClass={'form-group col'}
                    fieldValue={contactData.contact.lastName}
                    onChange={dispatchFunction}
                />
            </div>
            <div className={'row'}>
                <TextInputField
                    label={'Email'}
                    type={'email'}
                    fieldClass={'form-control'}
                    divClass={'form-group col'}
                    fieldValue={contactData.contact.email}
                    onChange={dispatchFunction}
                />
                <div className={'col'}>
                    <label>HealthCenter</label>
                    <select className={'form-control'}
                            name={formFields.HEALTH_CENTER}
                            onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                    >
                        {
                         contactData.healthCenters.map((healthCenter, index)=>{

                             return(
                                 <option key={index} value={healthCenter.healthCenterId}>{healthCenter.name}</option>
                             )
                         })
                        }
                    </select>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    {
                        contactData.networkingGroupsList.map((group, index)=>{

                            return(
                                <label key={index} className={'form-check-label'}><input type="checkbox" className={'form-check-input'} value={group.networkingGroupId}/>{group.name}</label>
                            )
                        })
                    }
                </div>
                <div className={'col'}>

                </div>
            </div>
        </form>
    )
}

export default ContactForm