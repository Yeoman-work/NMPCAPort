import React, {useState, useEffect} from "react";
import axios from "axios";
import CongressionalRepElement from "../components/CongressionalRepElement";
import Header from "../components/Header";
import { GiCongress } from 'react-icons/gi'
import {Link} from "react-router-dom";




const USSenatorsDashboard = props =>{
    const [senators, setSenators] = useState([]);

    useEffect(()=>{

        (async ()=>{

            try{

                const senatorResponse = await axios.get('http://localhost:8080/usSenators',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(senatorResponse.data);
                setSenators(senatorResponse.data);

            }catch(error){

                console.log(error.response)

            }
        })()


    },[])

    return(
        <div>
            <Header/>
            <h1 className={'mt-5 mb-5'}>Senator Dashboard <Link to={'/yeoman/government/addFederalRep/senator'}><GiCongress/></Link> </h1>
            <div>
                {
                    senators.map((senator, index)=>{

                        return(
                            <CongressionalRepElement
                             key={index}
                             rep={senator}
                            />
                        )
                    })
                }
            </div>
        </div>
    )
}

export default USSenatorsDashboard