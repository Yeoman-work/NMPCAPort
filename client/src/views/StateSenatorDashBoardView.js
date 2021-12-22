import React, { useState, useEffect } from "react";
import axios from "axios";
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

                //setStateSenatorList(...stateSenatorList, stateSenatorResponse.data);
                console.log(stateSenatorResponse.data);
            }catch(error){

                console.log(error.response.data);

            }

        })()


    })

    return(
        <div>
            <Header/>
            <div>
                {
                    stateSenatorList.length > 0?
                    stateSenatorList.map((official, index)=>{

                        return(
                            <StatePoliticianElement
                                key={index}
                                electedOfficical={official}
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