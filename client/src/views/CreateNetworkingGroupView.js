import React, { useReducer } from "react";
import axios from "axios";
import {useNavigate} from "react-router";
import NetworkingGroupForm from "../components/NetworkingGroupForm";
import Header from "../components/Header";
import produce from "immer";
const {isValidCharacter} = require('../helper/generalFunctions')


const networkGroupReducer = (netGrp, action) =>{

    switch(action.type){

        case FORM_FIELDS.NAME:
            console.log(netGrp);
            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 50){

                    return produce(netGrp, draft=>{
                        console.log(netGrp);
                        draft.name = action.payload;
                    })

                }else{

                    return netGrp;

                }

            }else if(action.payload.length < 1){

                return produce(netGrp, draft=>{

                    draft.name = action.payload;
                })

            }else{

                return netGrp
            }

        case FORM_FIELDS.GRP_DESCRIPTION:

            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 250){

                    return produce(netGrp, draft=>{

                        draft.description = action.payload;
                    })
                }else{

                    return netGrp;
                }

            }else if(action.payload.length < 1){

                return produce(netGrp, draft=>{

                    draft.description = action.payload;
                })

            }else{

                return netGrp;

            }
    }

}

const FORM_FIELDS={

    NAME: 'name',
    GRP_DESCRIPTION: 'description'
}


const CreateNetworkingGroupView = props =>{

    const navigate = useNavigate();
    const [netGrp, dispatchNetGrp] = useReducer(networkGroupReducer, {

        name: ''.trim(),
        description: ''.trim()
    })



    const submitHandler = async (e) =>{
        e.preventDefault()
        console.log('this')
        try {

            const createNetworkingGroupResponse = await axios.post('http://localhost:8080/networkingGroups', netGrp, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

            console.log(createNetworkingGroupResponse.data);
            navigate('/yeoman/')



        }catch(error){

            console.log(error.response)

        }
    }

    return(
        <div>
            <Header/>
            <div className={'m-auto mt-5 w-50'}>
                <NetworkingGroupForm
                    label={"New Group"}
                    grp={netGrp}
                    onChange={dispatchNetGrp}
                    handler={submitHandler}
                    fields={FORM_FIELDS}
                />
            </div>

        </div>
    )
}

export default CreateNetworkingGroupView