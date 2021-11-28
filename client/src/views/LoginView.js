import React, {useState} from "react";
import axios from "axios";
import LoginForm from "../components/LoginForm";



const LoginView = (props) =>{

    const [login, setLogin] = useState({
        email: '',
        password: ''
    })


    const loginHandler = async (e) =>{
        e.preventDefault()

        try{

            const loginResponse = await axios.post('http://localhost:8080/login' , login)

            console.log(loginResponse);

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