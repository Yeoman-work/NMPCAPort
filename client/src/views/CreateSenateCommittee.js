import React, {useState, useEffect} from "react";
import axios from "axios";
import CommitteeForm from "../components/CommitteeForm";
import Header from "../components/Header";
import StateSenatorSelectionForm from "../components/StateSenatorSelectionForm"



const CreateSeanteCommittee = props =>{
    const [committee, setCommittee] = useState({
        name: ''.trim(),
        description: ''.trim(),
        senatorIds: {}
    });
    const [senators, setSenators ] = useState([]);

    useEffect(()=>{

        (async()=>{

            try{

                const senatorResponse = await axios.get('http://localhost:8080/stateSenators/essentials', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                setSenators(senatorResponse.data);

            }catch(error){

                console.log(error.reponse);
            }

        })()

    }, [])

    return(
        <div>
            <Header/>
            <h1 className={'mt-5 mb-5'}>Create Senate Committee</h1>
            <CommitteeForm
                divProps={'m-auto w-25'}
                committee={committee}
                setCommittee={setCommittee}
            />
            <StateSenatorSelectionForm
                senators={senators}
                committee={committee}
                setCommittee={setCommittee}
            />
        </div>
    )
}

export default CreateSeanteCommittee;