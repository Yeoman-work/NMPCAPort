import React from "react";
const {characterCount} = require('../helper/generalFunctions')



const CommitteeForm = props =>{
    const {divProps,
          inputChange,
          committee,
          formFields
    } = props;



    return(
        <div className={divProps}>
            <div className={'form-group'}>
                <label>Committee Name</label>
                <input type="text"
                    className={'form-control'}
                    name={formFields.NAME}
                    value={committee.name}
                    onChange={(e)=>inputChange(e)}
                />
            </div>
            <div className={'form-group'}>
                <textarea name={'description'}
                          className={'form-control'}
                          name={formFields.DESCRIPTION}
                          value={committee.description}
                          onChange={(e)=>inputChange(e)}
                          cols="30"
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