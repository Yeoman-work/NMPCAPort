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
                <h4>Contacts <Link to={'/'}><IoIosContact/></Link></h4>
            </div>
        </div>
    )
}

export default NetworkingGroupElement