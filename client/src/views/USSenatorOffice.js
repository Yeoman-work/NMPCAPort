import React, { useState, useEffect } from "react";
import axios from "axios";
import {useParams} from "react-router";
import Header from "../components/Header";
import LocationForm from "../components/LocationForm";
import CongressionalRep from "../components/CongressionalRep";
import OfficeLocationElement from "../components/OfficeLocationElement";




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

                console.log(senatorResponse.data);
                setSenator(senatorResponse.data);

            }catch(error){


            }

        })()


    }, [params])

    const submitHandler = async (e, locationList, senatorId) =>{
        e.preventDefault();
        console.log(locationList)
        const  locationDetailsRequestWithSenatorList = [...locationList];
        try{

            const locationResponse = await axios.post('http://localhost:8080/locations/usSenator/' + senatorId,{

                locationDetailsRequestWithSenatorList

            }, {

                headers:{

                    Authorization: localStorage.getItem('token')
                }
            })

            console.log(locationResponse.data);

        }catch(error){

            console.log(error.response)

        }
    }


    return(
        <div>
            <Header/>
            <div className={'mt-5 pt-5 mb-5 height800 overflow-auto'}>
                <CongressionalRep
                    rep={senator}
                    repType={false}
                />
                <OfficeLocationElement
                    offices={senator.locationResponses}
                />
                <div className={'w-75 m-auto mt-5 pt-5'}>
                    <LocationForm
                        senatorId={senator.senatorId}
                        handler={submitHandler}
                    />
                </div>

            </div>
        </div>
    )
}

export default USSenatorOffice