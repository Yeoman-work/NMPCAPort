import React, {useState} from "react";
import axios from "axios";
import RegistrationForm from "../components/RegistrationForm";


const RegistrationView = props =>{

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

            const registrationResponse = await axios.post('http://localhost:8080/users', registration)

            console.log(registrationResponse);


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