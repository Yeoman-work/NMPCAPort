import React from "react";
import axios from "axios";
import TextInputField from "./TextInputField";



const ContactForm = props =>{


    return(
        <form>
            <div className={'row'}>
                <TextInputField
                    label={'First Name'}
                    type={'text'}
                    fieldClass={"form-control"}
                    divClass={'form-group col'}
                />
                <TextInputField
                    label={'Last Name'}
                    type={'text'}
                    fieldClass={'form-control'}
                    divClass={'form-group col'}
                />
            </div>
            <div className={'row'}>
                <TextInputField
                    label={'Email'}
                    type={'email'}
                    fieldClass={'form-control'}
                    divClass={'form-group col'}
                />
                <TextInputField
                label={'Title'}
                type={'text'}
                fieldClass={'form-control'}
                divClass={'form-group col'}
                />
            </div>
        </form>
    )
}

export default ContactForm