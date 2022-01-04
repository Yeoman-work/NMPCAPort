import React from "react";





const TextInputField = props =>{
    const {
        label,
        fieldClass,
        divClass,
        onChange,
        fieldValue,
        name,
        type
    } = props


    return(
        <div className={divClass}>
            <label>{label}</label>
            <input type={type}
                   name={name}
                   value={fieldValue}
                   className={fieldClass}
                   onChange={(e)=>onChange({type: e.target.name, payload: e.target.value})}/>
        </div>
    )
}

export default TextInputField;