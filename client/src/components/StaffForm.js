import React from "react";
import TextInputField from "./TextInputField";




const StaffForm = props =>{
    const { divProps,
            staffMember,
            dispatchFunction,
            formFields,
            formLabel
    } = props;


    return(
        <div className={divProps}>
            <h3>{formLabel}</h3>
            <div className={'row'}>
                <TextInputField
                    divClass={'col'}
                    fieldClass={'form-control'}
                    label={'First Name'}
                    name={formFields.FIRST_NAME}
                    onChange={dispatchFunction}
                    fieldValue={ staffMember? staffMember.firstName: "waiting"}
                />
                <TextInputField
                    divClass={'col'}
                    name={formFields.LAST_NAME}
                    fieldClass={'form-control'}
                    label={'Last Name'}
                    onChange={dispatchFunction}
                    fieldValue={ staffMember? staffMember.lastName : "waiting"}
                />
            </div>
            <div className={'row'}>
                <TextInputField
                    divClass={'col'}
                    name={formFields.TITLE}
                    fieldClass={'form-control'}
                    label={'Title'}
                    onChange={dispatchFunction}
                    fieldValue={staffMember? staffMember.title: 'waiting'}
                />
                <TextInputField
                    divClass={'col'}
                    name={formFields.EMAIL}
                    fieldClass={'form-control'}
                    label={'Email'}
                    onChange={dispatchFunction}
                    fieldValue={staffMember? staffMember.email: 'waiting'}
                />
            </div>
        </div>
    )
}

export default StaffForm;