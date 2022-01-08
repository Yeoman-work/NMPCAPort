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
        <div className={divProps}>
            <h1>{label}</h1>
            <TextInputField
                label={'Title'}
                type={'text'}
                name={formFields.TITLE}
                fieldClass={'form-control'}
                divClass={'form-group m-auto w-25'}
                fieldValue={contactData? contactData.contact.title : null}
                onChange={dispatchFunction}
            />
            <div className={'row'}>
                <TextInputField
                    label={'First Name'}
                    type={'text'}
                    name={formFields.FIRST_NAME}
                    fieldClass={"form-control"}
                    divClass={'form-group col'}
                    fieldValue={contactData? contactData.contact.firstName : null}
                    onChange={dispatchFunction}
                />
                <TextInputField
                    label={'Last Name'}
                    type={'text'}
                    name={formFields.LAST_NAME}
                    fieldClass={'form-control'}
                    divClass={'form-group col'}
                    fieldValue={contactData? contactData.contact.lastName : null}
                    onChange={dispatchFunction}
                />
            </div>
            <div className={'row'}>
                <TextInputField
                    label={'Email'}
                    type={'email'}
                    name={formFields.EMAIL}
                    fieldClass={'form-control'}
                    divClass={'form-group col'}
                    fieldValue={contactData? contactData.contact.email : null}
                    onChange={dispatchFunction}
                />
                <div className={'col'}>
                    <label>HealthCenter</label>
                    <select className={'form-control'}
                            name={formFields.HEALTH_CENTER}
                            onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target.value})}
                    >
                        <option>Choose a HealthCenter</option>
                        {
                        contactData?
                         contactData.healthCenters.map((healthCenter, index)=>{

                             return(
                                 <option key={index} value={healthCenter.healthCenterId}>{healthCenter.name}</option>
                             )
                         })
                            : null
                        }
                    </select>
                </div>
            </div>
            <div className={'row'}>
                <h6 className={'border'}>Networking Groups</h6>
                <div className={'col'}>
                    <label>Select Networking groups</label>
                    {
                        contactData?
                        contactData.networkingGroupsList.map((group, index)=>{

                            if(!contactData.contact.networkingGroups.includes(group.networkingGroupId)){
                                return(

                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                               name={formFields.NETWORK_GRP}
                                               checked={contactData.contact.networkingGroups.includes(group.networkingGroupId)}
                                               className={'form-check-input'}
                                               value={group.networkingGroupId}
                                               onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target})}
                                        />{group.name}</div>
                                )
                            }
                        })
                            : null
                    }
                </div>
                <div className={'col'}>
                    <label>Added Groups</label>
                    {
                        contactData?
                        contactData.networkingGroupsList.map((group, index)=>{

                            if(contactData.contact.networkingGroups.includes(group.networkingGroupId)){
                                return(

                                    <div key={index} className={'border text-start ps-3 m-auto w-75'}>
                                        <label className={'form-check-label'}></label>
                                        <input type="checkbox"
                                               className={'form-check-input'}
                                               checked={contactData.contact.networkingGroups.includes(group.networkingGroupId)}
                                               name={formFields.NETWORK_GRP}
                                               value={group.networkingGroupId}
                                               onChange={(e)=>dispatchFunction({type: e.target.name, payload: e.target})}
                                        />{group.name}</div>

                                )
                            }

                        })
                            : null
                    }
                </div>
            </div>
        </div>
    )
}

export default ContactForm