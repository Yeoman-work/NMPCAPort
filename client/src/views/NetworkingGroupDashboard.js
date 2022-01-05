import React, { useState, useEffect} from "react"
import axios from "axios";
import {Link} from 'react-router-dom'
import {GrGroup} from 'react-icons/gr'
import Header from "../components/Header";
import NetworkingGroupElement from "../components/NetworkingGroupElement";



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

                console.log(netGrpResponse.data);
                setNetGrp(netGrpResponse.data);

            }catch(error){

                console.log(error.response)

            }
        })()

    },[])

    return(
        <div>
            <Header/>
            <div className={'mt-5'}>
                <h1 className={'mb-5'}>Networking Groups <Link to={'/yeoman/networkingGroup/createNetworkingGroup'}><GrGroup/></Link></h1>
                <div className={' m-auto mt-5 pt-5'}>
                    {
                        netGrp?
                            netGrp.map((group, index)=>{

                                return(
                                    <NetworkingGroupElement
                                        key={index}
                                        group={group}
                                        divProps={'w-50 m-auto border rounded height300'}
                                    />
                                )
                            })
                            :

                            <h4>No Groups listed</h4>
                    }
                </div>
            </div>
        </div>
    )
}

export default NetworkingGroupDashboard