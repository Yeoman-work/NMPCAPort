import React from "react";






const StaffElement = props =>{
    const { staffMembers } = props;
    return(
        <div>
            {
                staffMembers?
                staffMembers.map((member, index)=>{

                    return(
                        <div>
                            <h6>{`${member.firstName}  ${member.lastName}`}</h6>
                            <p>{member.email}</p>
                            
                        </div>
                    )

                })
                    : null
            }
        </div>
    )


}

export default StaffElement