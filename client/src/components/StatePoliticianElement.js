import React from "react";
import MailToButton from "./MailToButton";




const StatePoliticianElement = props =>{
    const { electedOfficial } = props;
    let district;
    let politicianBackgroundColor;

    if(electedOfficial.nmHouseDistrictResponse){
        district = electedOfficial.nmHouseDistrictResponse;

    }else if(electedOfficial.senateDistrictResponse){

        district = electedOfficial.senateDistrictResponse;

    }

    switch (electedOfficial.politicalPartyEssentials.name){

        case 'republican':
            politicianBackgroundColor = 'bg-danger'
            break;

        case 'democratic':
            politicianBackgroundColor = 'bg-primary'
            break;

        case 'libertarian':
            politicianBackgroundColor = 'bg-warning'
            break;

        default:
            politicianBackgroundColor = 'bg-secondary'
    }


    return(
        <div className={'m-auto mb-5 mt-5 p-3 w-75 border border-dark rounded border row ' + politicianBackgroundColor}>
            <div className={'col align-top'}>
                <img src={electedOfficial.picture} alt={`picture of ${electedOfficial.firstName} ${electedOfficial.lastName}`}/>
            </div>
            <div className={'col align-bottom text-start'}>
                {electedOfficial.nmHouseDistrictResponse? <h3>Representative</h3>: <h3>Senator</h3>}
                <h3 className={''}>{electedOfficial.firstName.toUpperCase()} {electedOfficial.lastName.toUpperCase()} </h3>
                <h4>Party:{electedOfficial.politicalPartyEssentials.name.toUpperCase()}</h4>
                {electedOfficial.capitolRoom? <h5><strong>Capitol Room:</strong> {electedOfficial.capitolRoom}</h5>: <h5><strong>Capitol Room:</strong> Not provided</h5> }
                <div className={'m-3'}>
                    <h6>Address:</h6>
                    <p>
                        {electedOfficial.streetAddress? electedOfficial.streetAddress : ''}<br/>
                        { electedOfficial.city? electedOfficial.city.toUpperCase() + ', ' : ''}
                        { electedOfficial.zipCode? electedOfficial.zipCode: ''}
                    </p>
                </div>
                { electedOfficial.email? <MailToButton mailto={electedOfficial.email} label={electedOfficial.email}/>: <h5>No Email Provided</h5>}
            </div>
            <div className={'col'}>
                <h4><strong>District:</strong>{electedOfficial.nmHouseDistrictEssentialResponse? electedOfficial.nmHouseDistrictEssentialResponse.name : electedOfficial.senateDistrictEssentialResponse.name}</h4>
            </div>
        </div>
    )
}

export default StatePoliticianElement