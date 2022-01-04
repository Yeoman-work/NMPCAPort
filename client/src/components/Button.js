import React from "react";



const Button = props =>{
    const {disable,
           action,
           label
    } = props;

    return(
        <button disabled={disable} onClick={(e)=>action(e)}>{label}</button>
    )
}

export default Button