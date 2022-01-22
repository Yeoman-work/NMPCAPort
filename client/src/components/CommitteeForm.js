import React from "react";



const CommitteeForm = props =>{
    const {divProps,
          dispatch,
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
                    onChange={(e)=>dispatch({type:e.target.name, payload: e.target.value})}
                />
            </div>
            <div className={'form-group'}>
                <textarea name={'description'}
                          className={'form-control'}
                          name={formFields.DESCRIPTION}
                          value={committee.description}
                          onChange={(e)=>dispatch({type: e.target.name, payload: e.target.value})}
                          cols="30"
                          rows="10"
                ></textarea>
            </div>
        </div>
    )

}
export default CommitteeForm