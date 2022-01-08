import React, { useState, useEffect } from "react";
import axios from "axios";
import Header from "../components/Header";
import HealthCenterList from "../components/HealthCenterList";


const DashBoard = props =>{

    const [healthCenters, setHealthCenters] = useState([]);


    useEffect(()=>{

        (async ()=>{
            try{

                const healthCentersResponse = await axios.get('http://localhost:8080/healthCenters', {
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })
                console.log(healthCentersResponse.data);
                setHealthCenters(healthCentersResponse.data);

            }catch(error){

                console.log(error.response);

            }

        })()

       return ()=>{}
    }, [])



    return(
        <div className={'container-fluid m-auto align-items-center'}>
            <Header/>
            <HealthCenterList healthCenters={healthCenters} setHealthCenters={setHealthCenters}/>
        </div>
    )
}

export default DashBoard