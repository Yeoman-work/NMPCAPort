import React from "react";
const {characterCount} = require('../helper/generalFunctions')
const {committeeInputValidation} = require('../helper/GeneralCommitteeFunctions')



const CommitteeForm = props =>{
    const {
        divProps,
        committee,
        setCommittee,
    } = props;


    const inputChange = (e, committeeState) =>{

        setCommittee(committeeInputValidation(e, committeeState));
        console.log(committee);
        
    }

    return(
        <div className={divProps}>
            <div className={'form-group'}>
                <label>Committee Name</label>
                <input type="text"
                    className={'form-control'}
                    name={'name'}
                    value={committee.name}
                    onChange={(e)=>inputChange(e, committee)}
                />
            </div>
            <div className={'form-group'}>
                <textarea 
                        name={'description'}
                        className={'form-control'}
                        value={committee.description}
                        onChange={(e)=>inputChange(e, committee)}
                        rows="10"
                ></textarea>
            </div>
            <div className={'text-end'}>
                <h6>{characterCount(committee.description)+`/150 characters`}</h6>
            </div>
        </div>
    )

}
export default CommitteeForm