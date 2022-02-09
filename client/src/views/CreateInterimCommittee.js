import React, { useEffect, useState } from "react";
import CommitteeForm from "../components/CommitteeForm";
import StateRepSelectionForm from "../components/StateRepSelectionForm";
import StateSenatorSelectionForm from "../components/StateSenatorSelectionForm";
import Header from "../components/Header";
import axios from "axios";
import Button from "../components/Button";
const {committeeValidation} = require('../helper/GeneralCommitteeFunctions')

const CreateInterimCommittee = props =>{

    const [committee, setCommittee] = useState({
        name: ''.trim().toLowerCase(),
        description: ''.trim().toLowerCase(),
        repIds: {},
        senatorIds: {}
    });
    const [stateReps, setStateReps] = useState([]);
    const [stateSenators, setStateSenators] = useState([]);


    

    useEffect(()=>{

        (async ()=>{

            try{

                const stateRepResponse = await axios.get('http://localhost:8080/stateReps/essentials', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })



                setStateReps(stateRepResponse.data);



            }catch(error){

                console.log(error.response);


            }
            return ()=>{}
        })()

    },[])

    useEffect(()=>{

        (async ()=>{

            try{

                const senatorResponse = await axios.get('http://localhost:8080/stateSenators/essentials',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })


                setStateSenators(senatorResponse.data);

            }catch(error){

                console.log(error.response)

            }


        })()

        return ()=>{}

    },[])


    const interimCommitteeHandler = async (e) =>{
        e.preventDefault();

        try{

            const saveCommittee = await axios.post('http://localhost:8080/interimCommittees', committee, {
                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

        }catch (error){
            console.log(error.response);
        }
    }


    const isValidcommittee = (committee) =>{

        return committeeValidation(committee);
    }

    return(
        <div className={'m-auto heightFullPage'}>
            <Header/>
            <h1 className={'mt-5 mb-5'}>Create Interim Committee</h1>
            <CommitteeForm
                divProps={'w-25 m-auto'}
                committee={committee}
                setCommittee={setCommittee}
            />
            <div className={'m-auto border'}>
                <StateRepSelectionForm
                    divProps={' border w-75 m-auto'}
                    reps={stateReps}
                    committee={committee}
                    setCommittee={setCommittee}
                    />
                <StateSenatorSelectionForm
                divProps={'m-auto w-75 border m-auto'}
                senators={stateSenators}
                committee={committee}
                setCommittee={setCommittee}
                />

            </div>
            <Button
                action={interimCommitteeHandler}
                label={'Create Committee'}
                disable={isValidcommittee(committee)}
            />
        </div>
    )
}

export default CreateInterimCommittee