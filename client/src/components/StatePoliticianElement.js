import React from "react";
import MailToButton from "./MailToButton";




const StatePoliticianElement = props =>{
    const { electedOfficial } = props;


    console.log(electedOfficial.email)


    return(
        <div className={'d-inline-block ms-5, me-5 border  p-3'}>
            <img src={electedOfficial.picture} alt={`picture of ${electedOfficial.firstName} ${electedOfficial.lastName}`}/>
            <div className={'text-left'}>
                <h3 className={''}><strong>Rep:</strong> {electedOfficial.firstName.toUpperCase()} {electedOfficial.lastName.toUpperCase()} </h3>
                <h4><strong>District:</strong> {electedOfficial.nmHouseDistrictResponse.name}</h4>
                <h4><strong>Party:</strong> {electedOfficial.party.name.toUpperCase()}</h4>
                {electedOfficial.capitolRoom? <h5><strong>Capitol Room:</strong> {electedOfficial.capitolRoom}</h5>: <h5><strong>Capitol Room:</strong> Not provided</h5> }
                { electedOfficial.streetAddress?<h5 className={''}><strong>Address:</strong> {electedOfficial.streetAddress}</h5> : <h5>Address: Not Provided</h5>}
                { electedOfficial.cityResponse?<h5><strong>City:</strong> {electedOfficial.cityResponse.name.toUpperCase()}</h5> : <h5>City Not provided</h5>}
                { electedOfficial.zipCodeResponse? <h5><strong>Zip Code:</strong>{electedOfficial.zipCodeResponse.name}</h5> : <h5>Zip Code not provided</h5>}
                { electedOfficial.email? <MailToButton mailto={electedOfficial.email} label={electedOfficial.email}/>: <h5>No Email Provided</h5>}
            </div>
        </div>
    )
}

export default StatePoliticianElement