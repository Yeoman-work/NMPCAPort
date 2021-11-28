import React from "react";


const LoginForm = props =>{

    const {login, setLogin, loginHandler} = props;

    const inputChange =(e)=>{
        console.log('name ' + e.target.name);
        console.log('value ' + e.target.value);

        let loginObj = {...login};

        loginObj[e.target.name] = e.target.value;

        setLogin(loginObj);

    }

    return(
        <form onSubmit={loginHandler} className={'w-25 m-auto'}>
            <div className={'form-group'}>
                <label>Email</label>
                <input type="text" name={'email'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
            </div>
            <div className={'form-group'}>
                <label htmlFor="">Password</label>
                <input type="password" name={'password'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
            </div>
            <button>Login</button>
        </form>
    )
}

export default LoginForm