import React from "react";
import PhoneNumberForm from "./PhoneNumberForm";






const CongressionalRep = props =>{
    const { rep,
            district,
    } = props;

    return(
        <div className={'row'}>
            <div className={'col'}>
                <img src={rep.picture} alt={`picture of ${rep.firstName} ${rep.lastName}`} width={'500'} height={'500'}/>
                <h5>{`Representative ${rep.firstName} ${rep.lastName}`}</h5>
                <h6>Email: {rep.email.length > 0? rep.email: 'N/A'}</h6>
                <h6>Website: {rep.website.length > 0? <a href={rep.website}>Official website</a>: 'N/A'}</h6>
            </div>
            <div className={'col'}>

                {
                    district && district !== null?
                        <h4>{`District: ${district.name}`}</h4>
                        : null
                }

                {
                    district.map && district.map !== null?
                    <iframe width="425" height="300" frameBorder="0" scrolling="no" marginHeight="0" marginWidth="0"
                    src={district.map}></iframe>
                        :
                        <h6>No map provided</h6>
                }


            </div>
        </div>
    )
}

export default CongressionalRep;