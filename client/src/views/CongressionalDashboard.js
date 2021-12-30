import React, {useState, useEffect} from "react";
import axios from "axios";
import {Link} from 'react-router-dom'
import { GiCongress } from 'react-icons/gi'
import Header from "../components/Header";
import CongressionalRepElement from "../components/CongressionalRepElement";


const CongressionalDashboard = props =>{
    const [reps, setReps] = useState([])



    useEffect(()=>{

        (async ()=>{

            try{

                const repResponse = await axios.get('http://localhost:8080/congressionalReps',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                setReps(repResponse.data);

            }catch(error){


            }

        })()

    }, [])

    return(
        <div>
            <Header/>
            <h1 className={'mt-5'}>Congressional Representatives <Link to={'/yeoman/government/addFederalRep/congressionalRep'}><GiCongress/></Link> </h1>
            <div className={'mt-5 pt-3'}>
                {
                    reps?
                    reps.map((rep, index)=>{

                        return(
                            <CongressionalRepElement
                                key={index}
                                rep={rep}
                            />
                        )
                    })
                    :
                        <h3>No Reps provided</h3>
                }
            </div>
        </div>
    )
}

export default CongressionalDashboard