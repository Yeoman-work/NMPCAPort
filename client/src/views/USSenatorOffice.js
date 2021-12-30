import React, { useState, useEffect } from "react";
import axios from "axios";
import {useParams} from "react-router";
import Header from "../components/Header";
import LocationForm from "../components/LocationForm";
import CongressionalRep from "../components/CongressionalRep";




const USSenatorOffice = props =>{
    const params = useParams();
    const [senator, setSenator] = useState({})

    useEffect(()=>{

        (async ()=>{


            try{

                const senatorResponse = await axios.get('http://localhost:8080/usSenators/' + params.id,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })


                setSenator(senatorResponse.data);

            }catch(error){


            }

        })()


    }, [])

    const submitHandler = async (e, locationList, senatorId) =>{
        e.preventDefault();

        try{

            const locationResponse = await axios.post('http://localhost:8080/locations/usSenator/' + senatorId, locationList, {

                headers:{

                    Authorization: localStorage.getItem('token')
                }
            })

        }catch(error){


        }
    }


    return(
        <div className={''}>
            <Header/>
            <div className={'mt-5 pt-5 mb-5 height800 overflow-auto'}>
                <CongressionalRep
                    rep={senator}
                    repType={false}
                />
                <div className={'w-75 m-auto mt-5 pt-5'}>
                    <LocationForm
                        senator={senator}
                        handler={submitHandler}
                    />
                </div>

            </div>
        </div>
    )
}

export default USSenatorOffice