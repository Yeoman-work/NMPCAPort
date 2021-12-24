import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from 'react-router-dom'
import {RiGovernmentFill} from 'react-icons/ri'
import Header from "../components/Header";
import StatePoliticianElement from "../components/StatePoliticianElement";




const StateSenatorDashBoardView = props =>{
    const [stateSenatorList, setStateSenatorList] = useState([]);


    useEffect(()=>{


        (async ()=>{

            try{

                const stateSenatorResponse = await axios.get('http://localhost:8080/stateSenators',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                setStateSenatorList(...stateSenatorList, stateSenatorResponse.data);
                console.log(stateSenatorResponse.data);
            }catch(error){



            }

        })()


    },[])

    return(
        <div>
            <Header/>
            <h1 className={'mt-5 mb-5'}>State Senators <Link to={'/yeoman/government/addStateRep/senator'}><RiGovernmentFill/></Link> </h1>
            <div className={'pt-3 w-75 m-auto'}>
                {
                    stateSenatorList.length > 0?
                    stateSenatorList.map((official, index)=>{

                        return(
                            <StatePoliticianElement
                                key={index}
                                electedOfficial={official}
                            />
                        )
                    })
                    :
                        <h1>test</h1>
                }
            </div>
        </div>
    )
}

export default StateSenatorDashBoardView