import React from "react";



const StateSenatorSelectionForm = props =>{
    const {
        formFields,
        inputChange,
        senators,
         memberIds,
         divProps
        } = props;

    return(
        <div className={divProps}>
            <div className={'d-inline-block me-3 align-top w-25 border height200 p-2'}>
                <h6>Add Members</h6>
                {
                    senators?
                        senators.map((member, index)=>{

                            return(

                                !memberIds.includes(member.stateSenatorId)?

                                    <div className={'text-start'}
                                         key={index}>
                                        <input type="checkbox"

                                               className={'form-check-input'}
                                               name={formFields.SENATOR_IDS}
                                               checked={memberIds.includes(member.stateSenatorId)}
                                               value={ member.stateSenatorId }
                                               onChange={(e)=>inputChange(e)}

                                        />
                                        <label className={'ms-1'}>{`${member.firstName} ${member.lastName}`}</label>
                                    </div>

                                    : null
                            )
                        })
                        : null
                }
            </div>
            <div className={'d-inline-block ms-3 align-top w-25 border height200 p-2'}>
                <h6>Members</h6>
                {
                    senators?
                        senators.map((member, index)=>{

                            return(

                                memberIds.includes(member.stateSenatorId)?

                                    <div className={'text-start'}
                                         key={index}
                                    >
                                        <input type="checkbox"
                                               className={'form-check-input'}
                                               name={formFields.SENATOR_IDS}
                                               checked={memberIds.includes(member.stateSenatorId)}
                                               value={ member.stateSenatorId }
                                               onChange={(e)=>inputChange(e)}

                                        />
                                        <label className={'ms-1'}>{`${member.firstName} ${member.lastName}`}</label>
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

export default StateSenatorSelectionForm