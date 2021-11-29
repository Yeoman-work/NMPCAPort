import React, {useState} from "react";
import {useNavigate} from "react-router";
import axios from "axios";
import RegistrationForm from "../components/RegistrationForm";


const RegistrationView = props =>{
    const navigate = useNavigate();
    const [registration, setRegistration] = useState({
        email: '',
        firstName: '',
        lastName: '',
        password: '',
        confirmPassword: ''
    });

    const register = async (e)=>{
        e.preventDefault()

        try{

            const registrationResponse = await axios.post('http://localhost:8080/users', registration,{
                responseType: "json"
            })


            console.log(registrationResponse.data);
            navigate('/yeoman/login');

        }catch(error){

            console.log(error);
        }

    }


    return(
        <div>
            <RegistrationForm
                registration={registration}
                setRegistration={setRegistration}
                registrationHandler={register}
            />
        </div>
    )
}

export default RegistrationView