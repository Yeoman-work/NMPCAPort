import React from "react";
const {changeMembership} = require('../helper/SenateCommittee')



const StateSenatorSelectionForm = props =>{
    const {
        committee,
        setCommitee,
        senators,
        divProps
        } = props;

    const inputChange = (e, committeeState) =>{

        setCommitee(changeMembership(e, committeeState));
        console.log(committee);
    }

    return(
        <div className={divProps}>
            <div className={'d-inline-block me-3 align-top w-25 border height200 p-2'}>
                <h6>Add Members</h6>
                {
                    senators?
                        senators.map((senator, index)=>{

                            return(

                                !committee.senatorIds.hasOwnProperty(senator.stateSenatorId)?

                                    <div className={'text-start'}
                                        key={index}>
                                        <input type="checkbox"

                                            className={'form-check-input'}
                                            name={'senatorId'}
                                            checked={committee.senatorIds.hasOwnProperty(senator.stateSenatorId)}
                                            value={senator.stateSenatorId }
                                            onChange={(e)=>inputChange(e, committee)}

                                        />
                                        <label className={'ms-1'}>{`${senator.firstName} ${senator.lastName}`}</label>
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
                        senators.map((senator, index)=>{

                            return(
                                
                                committee.senatorIds.hasOwnProperty(senator.stateSenatorId)?
                                    <div className={'text-start'}
                                        key={index}
                                    >
                                    <input type="checkbox"
                                            className={'form-check-input'}
                                            name={'senatorIds'}
                                            checked={committee.senatorIds.hasOwnProperty(senator.stateSenatorId)}
                                            value={ senator.stateSenatorId }
                                            onChange={(e)=>inputChange(e, committee)}

                                        />
                                        <label className={'ms-1'}>{`${senator.firstName} ${senator.lastName}`}</label>
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