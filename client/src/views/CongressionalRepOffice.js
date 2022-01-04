import React, { useEffect, useState } from "react";
import axios from "axios";
import {useParams} from "react-router";
import Header from "../components/Header";
import CongressionalRep from "../components/CongressionalRep";
import OfficeLocationElement from "../components/OfficeLocationElement";
import LocationForm from "../components/LocationForm";





const CongressionalRepOffice = props =>{
    const { id } = useParams();
    const [congressionalRep, setCongressionalRep] = useState({})

    useEffect(()=>{

        (async ()=>{

            try {

                const congressionalRepResponse = await axios.get('http://localhost:8080/congressionalReps/' + id,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(congressionalRepResponse.data);
                setCongressionalRep(congressionalRepResponse.data)

            }catch(error){

                console.log(error.response)

            }

        })()


    }, [id])


    const submitHandler = async (e, locationList) =>{
        e.preventDefault();

        const locationDetailsRequestWithRepList = [...locationList];

        try{

            const locationResponse = await axios.post('http://localhost:8080/locations/congressionalRep', locationDetailsRequestWithRepList,{

                headers:{

                    Authorization: localStorage.getItem('token')
                }
            })

        }catch(error){

            console.log(error.response);

        }
    }


    return(
        <div>
            <Header/>
            <div>
                <CongressionalRep
                rep={congressionalRep}
                repType={true}
                />
                <OfficeLocationElement
                    offices={congressionalRep.locationResponses}
                />
                <div>
                    <LocationForm
                        formLabel={"New Location"}
                        id={id}
                        handler={submitHandler}
                    />
                </div>
            </div>
        </div>
    )
}

export default CongressionalRepOffice