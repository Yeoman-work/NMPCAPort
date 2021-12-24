import React from "react";
import { Link } from 'react-router-dom'




const MailToButton = ({mailto, label}) =>{


    return(
        <Link
            to={'#'}
            className={'text-dark'}
              onClick={(e)=>{
                window.open('mailto:' + mailto);
                e.preventDefault();
              }}
        >
            { label }
        </Link>
    );

};

export default MailToButton