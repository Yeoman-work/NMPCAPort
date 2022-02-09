import React from "react";
import TextInputField from "./TextInputField";
import produce from "immer";
import Button from "./Button";
const { characterCount } = require('../helper/generalFunctions')
const {networkingGroupInputValidation} = require('../helper/NetworkingGroupFunctions')


const NetworkingGroupForm = props =>{
    const {
        networkingGroup,
        setNetworkingGroup,
        label, 
        onChange, 
        fields, 
        id
    } = props;


    const inputChange = (e, networkingGroupState) =>{
        
        setNetworkingGroup(networkingGroupInputValidation(e, networkingGroupState));
        console.log(networkingGroup);
    }



    return(
        <form className={'w-50 m-auto'}>
            <h1>{label}</h1>
            <div className={'formControl'}>
                <label>Group Name</label>
                <input
                    className={'form-control'}
                    value={networkingGroup.name}
                    name={'name'}
                    onChange={(e)=>inputChange(e, networkingGroup)}
                />
            </div>
            <div>
                <textarea className={'form-control'}
                        name={'description'}
                        value={networkingGroup.description}
                        cols="30" rows="10"
                        onChange={(e)=>inputChange(e, networkingGroup)}
                ></textarea>
                <p>{characterCount(networkingGroup.description)} /250 characters</p>
            </div>
        </form>
    )
}

export default NetworkingGroupForm