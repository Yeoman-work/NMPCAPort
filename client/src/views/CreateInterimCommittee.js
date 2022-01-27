import React, { useEffect, useState } from "react";
import CommitteeForm from "../components/CommitteeForm";
import StateRepSelectionForm from "../components/StateRepSelectionForm";
import StateSenatorSelectionForm from "../components/StateSenatorSelectionForm";
import Header from "../components/Header";
import axios from "axios";
import Button from "../components/Button";
const {isValidCharacter,
       fieldLengthRequired,
       fieldLengthNotRequired} = require('../helper/generalFunctions')






const FORM_FIELDS={

    NAME: 'name',
    DESCRIPTION: 'description',
    MEMBER_ID: 'memberIds',
    REP_IDS: 'rep_ids',
    SENATOR_IDS: 'senator_id',
    STATE_REPS: 'state reps',
    STATE_SENATORS: 'state senators'

}


const CreateInterimCommittee = props =>{

    const [committee, setCommittee] = useState({
        name: ''.trim().toLowerCase(),
        description: ''.trim().toLowerCase(),
        repIds: [],
        senatorIds: []
    });
    const [stateReps, setStateReps] = useState([])
    const [stateSenators, setStateSenators] = useState([])


    const inputChangeCommittee = (e) =>{

        let committeeObj = {...committee};

        switch (e.target.name){

            case FORM_FIELDS.NAME:

                if(isValidCharacter(e.target.value)){

                    if(e.target.value.length <= 75){

                        committeeObj.name = e.target.value;

                        return setCommittee(committeeObj);

                    }else{

                        return committee;

                    }

                }else if(e.target.value.length < 1){

                    committeeObj.name = e.target.value;

                    return setCommittee(committeeObj);

                }else{

                    return committee

                }

            case FORM_FIELDS.DESCRIPTION:

                if(isValidCharacter(e.target.value)){

                    if(e.target.value.length <= 150){

                        committeeObj.description = e.target.value;

                        return setCommittee(committeeObj);
                    }else{

                        return committee;
                    }

                }else if(e.target.value.length < 1){

                    committeeObj.description = e.target.value;

                    return setCommittee(committeeObj);

                }else{

                    return committee;

                }

            case FORM_FIELDS.REP_IDS:

                if(e.target.checked){

                    committeeObj.repIds.push(e.target.value);

                    return setCommittee(committeeObj);

                }else{

                    const index = committeeObj.repIds.indexOf(e.target.value);

                    committeeObj.repIds.splice(index, 1);

                    return setCommittee(committeeObj);

                }

            case FORM_FIELDS.SENATOR_IDS:

                console.log(e.target.checked)
                if(e.target.checked){

                    committeeObj.senatorIds.push(e.target.value);

                    return setCommittee(committeeObj);

                }else{

                    const index = committeeObj.senatorIds.indexOf(e.target.value);

                    committeeObj.senatorIds.splice(index, 1);

                    return setCommittee(committeeObj);
                }
        }
    }



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


    const committeeValidation = (committee) =>{

        let isValid = true;

        if(fieldLengthRequired(3, 75, committee.name)){
            if(fieldLengthNotRequired(0, 150, committee.description)){

                isValid  = false
            }
        }

        return isValid;
    }

    return(
        <div className={'m-auto heightFullPage'}>
            <Header/>
            <h1 className={'mt-5 mb-5'}>Create Interim Committee</h1>
            <CommitteeForm
                divProps={'w-25 m-auto'}
                committee={committee}
                formFields={FORM_FIELDS}
                inputChange={inputChangeCommittee}
            />
            <div className={'m-auto border'}>
                <StateRepSelectionForm
                 divProps={' border w-75 m-auto'}
                 reps={stateReps}
                 memberIds={committee.repIds}
                 formFields={FORM_FIELDS}
                 inputChange={inputChangeCommittee}
                />
                <StateSenatorSelectionForm
                divProps={'m-auto w-75 border m-auto'}
                senators={stateSenators}
                memberIds={committee.senatorIds}
                formFields={FORM_FIELDS}
                inputChange={inputChangeCommittee}
                />

            </div>
            <Button
                action={interimCommitteeHandler}
                label={'Create Committee'}
                disable={committeeValidation(committee)}
            />
        </div>
    )
}

export default CreateInterimCommittee