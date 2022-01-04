import React from "react";






const StaffElement = props =>{
    const { staffMembers, divProps } = props;
    return(
        <div className={divProps}>
            {
                staffMembers?
                staffMembers.map((member, index)=>{

                    return(
                        <div key={index}>
                            <h6>{`${member.firstName}  ${member.lastName}`}</h6>
                            <p>{member.email}</p>

                            {
                                member.phoneNumberResponses?
                                member.phoneNumberResponses.map((number, index)=>{

                                    return(
                                        <p key={index}>{`${number.description}: ${number.number}`}</p>
                                    )
                                })
                                    : null
                            }

                        </div>
                    )

                })
                    : null
            }
        </div>
    )


}

export default StaffElement