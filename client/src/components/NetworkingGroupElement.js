import React from "react";
import { Link } from 'react-router-dom'
import {FaRegEdit} from 'react-icons/fa'




const NetworkingGroupElement = props =>{
    const { group, divProps } = props;
    return(
        <div className={'row ' + divProps}>
            <div className={'text-end fs-4'}><Link to={'/yeoman/networkingGroup/editGroup/' + group.networkingGroupId}><FaRegEdit/></Link></div>
            <div className={'col'}>
                <h4>{group.name.toUpperCase()} Group</h4>
                <div>
                    <p>{group.description}</p>
                </div>
            </div>
            <div className={'col'}>
                <h4 className={'m-auto d-inline-block'}>Contacts</h4>

                <div className={'m-auto w-75 mt-3 height200 overflow-auto'}>
                    <ul className={'w-50 m-auto'}>
                        {
                            group.contactEssentials?
                                group.contactEssentials.map((contact, index)=>{

                                    return(
                                        <li key={index} className={'text-start'}><Link to={'/'}>{`${contact.firstName} ${contact.lastName}`}</Link></li>
                                    )
                                })

                                :
                                null
                        }
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default NetworkingGroupElement