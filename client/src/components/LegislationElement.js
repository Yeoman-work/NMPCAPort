import React from "react";






const LegislationElement = props =>{
    const {legislation} = props;

    return(
        <div>
            <h2>{legislation}</h2>
            <div className={'m-auto'}>
                <p>{
                    legislation.description}
                </p>
            </div>
            <div className={'text-left'}>
                { legislation.updatedAt? legislation.updatedAt : legislation.createdAt }
            </div>
        </div>
    )
}

export default LegislationElement