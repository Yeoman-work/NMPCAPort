import React, { useEffect, useState } from "react"
import axios from "axios";
import { Link } from 'react-router-dom'
import Header from "../components/Header";
import StatePoliticianElement from "../components/StatePoliticianElement";
import {RiGovernmentFill} from 'react-icons/ri'





const StateRepDashboard = props =>{
    const [ stateRepList, setStateRepList ] = useState([]);



    useEffect(()=>{

        (async ()=>{

            try{

                const stateRepsResponse = await axios.get('http://localhost:8080/stateReps', {


                    headers:{
                        Authorization: localStorage.getItem('token')

                    }
                })

                console.log(stateRepsResponse.data);
                setStateRepList(...stateRepList, stateRepsResponse.data);

            }catch(error){

                console.log(error.response);

            }

        })()

    },[])


    return(
        <div className={''}>
            <Header/>
            <h1 className={'mt-5 mb-5'}>State Representatives <Link to={'/yeoman/government/addStateRep/rep'}><RiGovernmentFill/></Link></h1>
            <div className={'w-75 m-auto'}>
                {
                    stateRepList.length > 0?
                    stateRepList.map((official, index)=>{

                        return(
                            <StatePoliticianElement
                              key={index}
                              electedOfficial={official}
                            />
                        )
                    })
                        : <div>Not working This</div>
                }
            </div>
        </div>
    )
}


export default StateRepDashboard;