import React from "react";




const StatePoliticianElement = props =>{
    const { electedOfficial } = props;




    return(
        <div className={''}>
            <div>
                <div>

                </div>
                <div className={''}>
                    <h3 className={''}>{` ${electedOfficial.lastName} , ${electedOfficial.firstName}`}</h3>
                    <h4>{`Party: ${electedOfficial.party.name}`}</h4>
                    { electedOfficial.capitolRoom.length > 0? <h5><strong>Capitol Room:</strong>{electedOfficial.capitolRoom}</h5> <h5><strong>Capitol Room:</strong> Not provided</h5> }
                    { electedOfficial.streetAddress?<h5 className={''}>Address:{electedOfficial.streetAddress}</h5> : <h5>Address: Not Provided</h5>}
                    { electedOfficial.cityResponse?<h5><strong>City:</strong>{electedOfficial.cityResponse.name}</h5> : <h5>city Not provided</h5>}
                    { electedOfficial.zipCode? <h5><strong>Zip Code:</strong>{electedOfficial.zipCode.name}</h5> : <h5>Zip Code not provided</h5>}
                    { electedOfficial.email.length > 0? <}
                </div>
            </div>
        </div>
    )
}

export default StatePoliticianElement