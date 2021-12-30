import React from "react";
import PhoneNumberForm from "./PhoneNumberForm";






const CongressionalRep = props =>{
    const { rep,
            repType
    } = props;

    return(
        <div className={''}>
            {
            rep?
            <div className={''}>
                <img src={rep.picture} alt={`picture of ${rep.firstName} ${rep.lastName}`} width={'500'} height={'500'}/>
                <h5>{repType?'Representative' : 'Senator'} {rep.firstName} {rep.lastName}</h5>
                {!repType? <h5>{`Elected: ${rep.elected}`} </h5> : null}
                {!repType? <h5>{`Next Election: ${rep.nextElection}`}</h5> : null}
                <h6>Email: {rep.email? rep.email: 'N/A'}</h6>
                <h6>Website: {rep.website? <a href={rep.website}>Official website</a>: 'N/A'}</h6>
            </div>

                : null
            }
        </div>
    )
}

export default CongressionalRep;