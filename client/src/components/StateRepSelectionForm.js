import React from "react";




const StateRepSelectionForm = props =>{
    const {reps,
        formFields,
        memberIds,
        dispatch,
        divProps
    } = props
    
    return(
        <div className={divProps}>
            <div className={'d-inline-block align-top me-3 ms-3 p-2 m-1 border rounded w-25  height200 overflow-auto'}>
                <h6>Add Members</h6>
                {
                    reps?
                        reps.map((member, index)=>{
                            
                            return(
                                !memberIds.includes(member.stateRepId)?
                                <div className={'text-start'}
                                     key={index}
                                >
                                    <input type="checkbox"
                                           checked={memberIds.includes(member.stateRepId)}
                                           name={formFields.REP_SELECT}
                                           className={'form-check-input'}
                                           value={ member.stateRepId }
                                           onChange={(e)=>dispatch({type: e.target.name, payload: e.target})}

                                    /><label className={'ms-1'}>{` ${member.firstName} ${member.lastName}`}</label>
                                </div>
                                    : null

                            )
                        })
                        : null
                }
            </div>
            <div className={'d-inline-block align-top ms-3 p-2 m-1 border rounded w-25 height200 overflow-auto'}>
                <h6>Members</h6>
                {
                    reps?
                        reps.map((member, index)=>{

                            return(

                                memberIds.includes(member.stateRepId)?
                                <div className={'text-start'}
                                     key={index}
                                >
                                    <input type="checkbox"
                                           checked={memberIds.includes(member.stateRepId)}
                                           name={formFields.REP_SELECT}
                                           className={'form-check-input'}
                                           value={ member.stateRepId }
                                           onChange={(e)=>dispatch({type: e.target.name, payload: e.target})}

                                    /><label className={'ms-1'}>{` ${member.firstName} ${member.lastName}`}</label>
                                </div>
                            : null

                            )
                        })
                        : null
                }
            </div>
        </div>
    )
}

export default StateRepSelectionForm