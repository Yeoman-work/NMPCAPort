import React from "react";
import Header from "../components/Header";
import StateRepForm from "../components/StateRepForm";
import PhoneNumberForm from "../components/PhoneNumberForm";




const STATE_SENATOR_FIELDS ={

    STATE_SENATOR_FIRSTnAME
}


const CreateStateSenatorView = props =>{

    return(
        <div>
            <Header/>
            <div>
                <StateRepForm

                />
                <PhoneNumberForm

                />
            </div>
        </div>
    )
}

export default CreateStateSenatorView;