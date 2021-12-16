import React from "react";



const StateRepForm = props =>{


    return(
        <form action="">
            <h1>form label2</h1>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>First Name</label>
                    <input type="text" className={'form-control'}/>
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text" className={'form-control'}/>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Email</label>
                    <input type="email" className={'form-control'}/>
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text" className={'form-control'}/>
                </div>
            </div>
            <div className={'form-group'}>
                <label>Address:</label>
                <input type="text" className={'form-control'}/>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>City:</label>
                    <select>
                        <option>Select a City</option>
                        {

                        }
                    </select>
                </div>
                <div className={'col form-group'}>
                    <label>Zip Code</label>
                    <select>
                        <option> Select a Zip Code</option>
                        {

                        }
                    </select>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <label>County</label>

                </div>
                <div className={'col'}>
                    <label>District</label>

                </div>
            </div>
            <button>button label</button>
        </form>
    )
}

export default StateRepForm