import React from "react";
import {Link} from 'react-router-dom'
import MailToButton from "./MailToButton";
import { MdOutlineLocationCity } from 'react-icons/md'
import { IoIosPeople } from 'react-icons/io'





const CongressionalRepElement = props =>{
    const {rep} = props;



    return(
        <div className={'row m-auto w-75 border rounded pt-4 pb-4'}>
            {
                rep.districtResponse?
                    <div className={'col'}>
                        <img src={rep.picture} alt={`picture of ${rep.firstName} ${rep.lastName}`} width={'400'} height={'500'}/>
                        <h3>{`Representative: ${rep.firstName} ${rep.lastName}`}</h3>
                        <h4>District: {rep.districtResponse.name}</h4>
                        <h5>{rep.email?  <MailToButton label={'Email'} mailto={rep.email}/> : "Email not provided"}</h5>
                        <h5><a href={rep.website}>Official Website</a></h5>
                    </div>

                    :

                    <div className={'col'}>
                        <img src={rep.picture} alt={`picture of ${rep.firstName} ${rep.lastName}`} width={'400'} height={'500'}/>
                        <h3>{`Senator: ${rep.firstName} ${rep.lastName}`}</h3>
                        <h4 className={''}>Elected: {rep.elected}</h4>
                        <h4>Next Election: {rep.nextElection}</h4>
                        <h5>{rep.email?  <MailToButton label={'Email'} mailto={rep.email}/> : null }</h5>
                        <h5><a href={rep.website}>Official Website</a></h5>
                    </div>

            }
            {
                rep.districtResponse?

                    <div className={'col'}>
                        <h3>Offices <Link to={''}><MdOutlineLocationCity/></Link></h3>

                    </div>

                    :

                    <div className={'col'}>
                        <h3>Offices <Link to={'/yeoman/location/senator/' + rep.senatorId}><MdOutlineLocationCity/></Link></h3>

                    </div>
            }
            {

                rep.districtResponse?

                    <div className={'col'}>
                        <h3>Staff <Link to={''}><IoIosPeople/></Link></h3>
                    </div>

                    :

                    <div className={'col'}>
                        <h3>Staff <Link to={''}><IoIosPeople/></Link></h3>
                    </div>

            }
        </div>
    )
}


export default CongressionalRepElement