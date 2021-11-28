import React from "react";



const RegistrationForm = props =>{

    const {registration, setRegistration, registrationHandler} = props;

    const inputChange = (e) =>{
        console.log('name ' + e.target.name);
        console.log('value ' + e.target.value);

        let registrationObj = {...registration};

        registrationObj[e.target.name] = e.target.value;

        setRegistration(registrationObj);

    }


    return(
        <form onSubmit={registrationHandler} className={'w-25 m-auto'}>
            <div className={'form-group'}>
                <label htmlFor="">Email</label>
                <input type="text" name={'email'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <label htmlFor="">First Name</label>
                    <input type="text" name={'firstName'}  className={'form-control'} onChange={(e)=>inputChange(e)}/>
                </div>
                <div className={'col'}>
                    <label htmlFor="">Last Name</label>
                    <input type="text" name={'lastName'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <label htmlFor="">Password</label>
                    <input type="password" name={'password'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
                </div>
                <div className={'col'}>
                    <label htmlFor="">Confirm Password</label>
                    <input type="password" name={'confirmPassword'} className={'form-control'} onChange={(e)=>inputChange(e)}/>
                </div>
            </div>
            <button>Register</button>
        </form>
    )
}

export default RegistrationForm