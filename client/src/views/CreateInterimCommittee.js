import React, { useEffect, useReducer } from "react";
import CommitteeForm from "../components/CommitteeForm";
import StateRepSelectionForm from "../components/StateRepSelectionForm";
import StateSenatorSelectionForm from "../components/StateSenatorSelectionForm";
import Header from "../components/Header";
import produce from "immer";
import axios from "axios";
import Button from "../components/Button";


const committeeReducer = (committeeState, action) => {

    switch (action.type){

        case FORM_FIELDS.NAME:
            console.log(committeeState);
            return produce(committeeState, draft=>{

                draft.committee.name = action.payload;
            })

        case FORM_FIELDS.DESCRIPTION:

            return produce(committeeState, draft=>{

                draft.committee.description = action.payload;
            })

        case FORM_FIELDS.STATE_SENATORS:

            return produce(committeeState, draft=>{

                draft.stateSenators = [...action.payload];
            })

        case FORM_FIELDS.STATE_REPS:

            return produce(committeeState, draft=>{

                draft.stateReps = [...action.payload];
            })

        case FORM_FIELDS.COMMITTEE:

            return produce(committeeState, draft=>{

                draft.committeeInfo = {...action.payload}
            })

        case FORM_FIELDS.REP_SELECT:

            if(action.payload.checked){

                return produce(committeeState, draft=>{

                    draft.committee.repIds = [...committeeState.committee.repIds, action.payload.value];
                })

            }else{

                return produce(committeeState, draft=>{

                    draft.committee.repIds.splice(
                        committeeState.committee.repIds.indexOf(action.value), 1);
                })

            }

        case FORM_FIELDS.SENATOR_SELECT:

            if(action.payload.checked){

                return produce(committeeState, draft=>{

                    draft.committee.senatorIds = [...committeeState.committee.repIds, action.payload.value];
                })

            }else{

                return produce(committeeState, draft =>{

                    draft.committee.senatorIds.splice(
                        committeeState.committee.senatorIds.indexOf(action.value), 1)
                })
            }

        default:
            return committeeState;

    }

}

const FORM_FIELDS ={

    NAME: 'name',
    DESCRIPTION: 'description',
    MEMBER_IDS: 'member_ids',
    STATE_REPS: 'state reps',
    STATE_SENATORS: 'state senators',
    COMMITTEE: 'committee',
    REP_SELECT: 'rep select',
    SENATOR_SELECT: 'senator select'

}


const CreateInterimCommittee = props =>{

    const [committeeInfo, dispatchCommittee] = useReducer(committeeReducer, {

        committee:{
            name: ''.trim().toLowerCase(),
            description: ''.trim().toLowerCase(),
            repIds: [],
            senatorIds: []
        },
        stateReps: [],
        stateSenators: []

    })

    useEffect(()=>{

        (async ()=>{

            try{

                const stateRepResponse = await axios.get('http://localhost:8080/stateReps/essentials', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(stateRepResponse.data);
                dispatchCommittee({type: FORM_FIELDS.STATE_REPS, payload: [...stateRepResponse.data]})

            }catch(error){

                console.log(error.response);


            }


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

                dispatchCommittee({type: FORM_FIELDS.STATE_SENATORS, payload: [...senatorResponse.data]})

            }catch(error){

                console.log(error.response)

            }


        })()

    },[])


    const interimCommitteeHandler = async (e) =>{
        e.preventDefault();

        try{

            const committee = await axios.post('http://localhost:8080/', committeeInfo.committee, {
                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

        }catch (error){
            console.log(error.response);
        }
    }

    return(
        <div className={'m-auto heightFullPage'}>
            <Header/>
            <h1 className={'mt-5 mb-5'}>Create Interim Committee</h1>
            <CommitteeForm
                divProps={'w-25 m-auto'}
                formFields={FORM_FIELDS}
                dispatch={dispatchCommittee}
                committee={committeeInfo.committee}
            />
            <div className={'m-auto border'}>
                <StateRepSelectionForm
                 divProps={' border w-75 m-auto'}
                 dispatch={dispatchCommittee}
                 reps={committeeInfo.stateReps}
                 memberIds={committeeInfo.committee.repIds}
                 formFields={FORM_FIELDS}
                />
                <StateSenatorSelectionForm
                divProps={'m-auto w-75 border m-auto'}
                dispatch={dispatchCommittee}
                senators={committeeInfo.stateSenators}
                memberIds={committeeInfo.committee.senatorIds}
                formFields={FORM_FIELDS}
                />
            </div>
            <Button
                action={interimCommitteeHandler}
                label={'Create Committee'}
            />
        </div>
    )
}

export default CreateInterimCommittee