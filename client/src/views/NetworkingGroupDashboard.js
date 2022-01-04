import React, { useState, useEffect} from "react"
import axios from "axios";
import Header from "../components/Header";



const NetworkingGroupDashboard = props =>{
    const [netGrp, setNetGrp] = useState([])

    useEffect(()=>{

        (async ()=>{
            try{

                const netGrpResponse = await axios.get('http://localhost:8080/networkingGroups', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

            }catch(error){


            }
        })()

    },[])

    return(
        <div>
            <Header/>
            <div>
                {

                }
            </div>
        </div>
    )
}

export default NetworkingGroupDashboard