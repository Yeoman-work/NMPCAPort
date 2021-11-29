import React, {useState} from "react";
import {useNavigate} from "react-router";
import axios from "axios";
import LoginForm from "../components/LoginForm";



const LoginView = (props) =>{
    const navigate = useNavigate();
    const [login, setLogin] = useState({
        email: '',
        password: ''
    })


    const loginHandler = async (e) =>{
        e.preventDefault()

        try{

            const loginResponse = await axios.post('http://localhost:8080/login' , login,{
                responseType: 'json',

            })


            localStorage.setItem('token', loginResponse.headers.authorization);

            navigate('')


        }catch(error){

            console.log(error);
        }
    }

    return(
        <div>
            <LoginForm login={login}
                       setLogin={setLogin}
                       loginHandler={loginHandler}
            />
        </div>
    )
}

export default LoginView