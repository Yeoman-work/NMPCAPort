import React, { useState, useEffect } from "react";
import axios from "axios";
import {useNavigate} from "react-router";
import Header from "../components/Header";
import LegislationForm from "../components/LegislationForm";




const CreateLegislationView = props =>{

    const [ legislation, setLegislation] = useState({
        name:'',
        description: ''
    })

    const legislationHandler = async (e) =>{
        e.preventDefault()

        try{

            const legislationResponse = await axios.post('http://localhost:8080/legislation/', legislation, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

        }catch(error){


        }



    }



    return(
        <div className={'container-fluid'}>
            <Header/>
            <div className={'mt-5 pt-5 container'}>
                <LegislationForm
                    formLabel={'Create Legislation'}
                    legislation={legislation}
                    setLegislation={setLegislation}
                    handler={legislationHandler}
                />
            </div>

        </div>
    )
}

export default CreateLegislationView