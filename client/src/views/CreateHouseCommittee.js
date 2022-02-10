import axios from "axios";
import React, {useState, useEffect} from "react";
import CommitteeForm from "../components/CommitteeForm";
import StateRepSelectionForm from '../components/StateRepSelectionForm'
import Header from '../components/Header'



const CreateHouseCommittee = props =>{
    const [committee, setCommittee] = useState({
        name: ''.trim(),
        description: ''.trim(),
        houseRepIds:{}
    });
    const [houseReps, setHouseReps] = useState([]);

    useEffect(()=>{

        (async()=>{

            try{

                const houseResponse = await axios.get('http://localhost:8080/stateReps/essentials', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })
                
                console.log(houseResponse.data);
                setHouseReps(houseResponse.data);

            }catch(error){

                console.log(error.reponse);
            }
        })()

    }, [])

    return(
        <div>
            <Header/>
            <h1 className={'mt-5 mb-5'}>Create Committee</h1>
            <CommitteeForm
                committee={committee}
                setCommittee={setCommittee}
                divProps={'m-auto w-25'}
            />
            <StateRepSelectionForm
                committee={committee}
                setCommittee={setCommittee}
                houseReps={houseReps}
            />
        </div>
    )
}

export default CreateHouseCommittee;