import React, {useEffect, useState} from "react";
import axios from "axios";
import LegislationElement from "../components/LegislationElement";
import Header from "../components/Header";
import '../css/style.css'
import { GiScrollQuill } from 'react-icons/gi'
import {Link} from "react-router-dom";




const LegislationDashBoard = props =>{
    const [legislation, setLegislation] = useState([]);

    useEffect(()=>{

        (async ()=>{

            try{

                const legislationResponse = await axios.get('http://localhost:8080/legislation',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                setLegislation(legislationResponse.data);
                console.log(legislationResponse.data);

            }catch(error){

            }

        })()


    },[])

    return(
        <div className={'heightFullPage container-fluid'}>
            <Header/>
            <h1 className={'mt-4 p-4'}>Legislation <Link to={'/yeoman/legislation/createLegislation'}><GiScrollQuill/></Link></h1>
            <div className={' m-auto height700 overflow-auto w-50 '}>
                {
                    legislation?
                        legislation.map((bill, index)=>{

                            return(
                                <LegislationElement
                                    key={index}
                                    legislation={bill}
                                />
                            )
                        })
                        : null
                }
            </div>

        </div>
    )
}

export default LegislationDashBoard
