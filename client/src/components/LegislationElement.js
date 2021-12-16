import React from "react";
const {dateAndTime} = require('../helper/generalFunctions')





const LegislationElement = props =>{
    const {legislation} = props;

    return(
        <div className={'mt-5 w-75 m-auto'}>
            <h2 className={'mb-5'}>{legislation.name}</h2>
            <div className={'row'}>
                <div className={'col'}>

                    <h6>{`House Status: ${legislation.houseStatus}`}</h6>
                    <p>{`As Of: ${dateAndTime(legislation.houseStatusDate)}`}</p>

                </div>
                <div className={'col'}>

                    <h6>{`Senate Status: ${legislation.senateStatus}`}</h6>
                    <p>{`As Of: ${dateAndTime(legislation.senateStatusDate)}`}</p>

                </div>
                <div className={'col'}>

                    <h6>{`Governor Status: ${legislation.governorStatus}`}</h6>
                    <p>{`As Of: ${dateAndTime(legislation.governorStatusDate)}`}</p>

                </div>
            </div>
            <div className={'m-auto'}>
                <p>
                    {legislation.description}
                </p>
            </div>
            <div className={'text-left'}>
                { legislation.updatedAt? dateAndTime(legislation.updatedAt) : dateAndTime(legislation.createdAt) }
            </div>
        </div>
    )
}

export default LegislationElement