import React from "react";
import { Link } from 'react-router-dom'
import {IoIosContact} from 'react-icons/io'




const NetworkingGroupElement = props =>{
    const { group, divProps } = props;
    return(
        <div className={'row ' + divProps}>
            <div className={'col'}>
                <h4>{group.name.toUpperCase()} Group</h4>
                <div>
                    <p>{group.description}</p>
                </div>
            </div>
            <div className={'col'}>
                <h4 className={'m-auto'}>Contacts <Link to={'/yeoman/networkingGroup/' + group.networkingGroupId}><IoIosContact/></Link></h4>
                <div className={'m-auto w-75 mt-3 height200 overflow-auto'}>
                    <ul className={'w-50 m-auto'}>
                        {
                            group.contactNestedResponses?
                                group.contactNestedResponses.map((contact, index)=>{

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