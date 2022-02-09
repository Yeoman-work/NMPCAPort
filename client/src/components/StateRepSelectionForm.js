import React from "react";
import { changeMemberShip } from "../helper/GeneralCommitteeFunctions";




const StateRepSelectionForm = props =>{
    const {
        reps,
        committee,
        setCommittee,
        divProps
    } = props

    const inputChange = (e) =>{

        setCommittee(changeMemberShip(e, committee));

        console.log(committee);
    }


    
    return(
        <div className={divProps}>
            <div className={'d-inline-block align-top me-3 ms-3 p-2 m-1 border rounded w-25  height200 overflow-auto'}>
                <h6>Add Members</h6>
                {
                    reps?
                        reps.map((member, index)=>{
                            
                            return(
                                !committee.repIds.hasOwnProperty(member.stateRepId)?
                                <div className={'text-start'}
                                    key={index}
                                >
                                    <input type="checkbox"
                                        checked={committee.repIds.includes(member.stateRepId)}
                                        name={'repIds'}
                                        className={'form-check-input'}
                                        value={ member.stateRepId }
                                        onChange={(e)=>inputChange(e)}
                                        

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
                                committee.repIds.hasOwnProperty(member.stateRepId)?
                                <div className={'text-start'}
                                    key={index}
                                >
                                    <input type="checkbox"
                                        checked={committee.repIds.includes(member.stateRepId)}
                                        name={'repIds'}
                                        className={'form-check-input'}
                                        value={ member.stateRepId }
                                        onChange={(e)=>inputChange(e)}
                                        

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