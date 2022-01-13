import React from "react";
import TextInputField from "./TextInputField";
import produce from "immer";
import Button from "./Button";
const { characterCount } = require('../helper/generalFunctions')



const NetworkingGroupForm = props =>{
   const {grp, label, onChange, fields} = props;




    return(
        <form className={'w-50 m-auto'}>
            <h1>{label}</h1>
            <TextInputField
                type={'text'}
                label={'Group Name'}
                fieldClass={'form-control'}
                fieldValue={grp.group.name}
                name={fields.NAME}
                onChange={onChange}
            />
            <div>
                <textarea className={'form-control'}
                          name={fields.GRP_DESCRIPTION}
                          value={grp.group.description}
                          cols="30" rows="10"
                          onChange={(e)=>onChange({type: e.target.name, payload: e.target.value})}
                ></textarea>
                <p>{characterCount(grp.description)} /250 characters</p>
            </div>
        </form>
    )
}

export default NetworkingGroupForm