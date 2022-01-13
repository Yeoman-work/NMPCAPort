import React, { useReducer, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import NetworkingGroupForm from "../components/NetworkingGroupForm";
import Header from "../components/Header";
import produce from "immer";
import NetworkingGroupWithContacts from "../components/NetworkingGroupWithContacts";
import Button from "../components/Button";
const {isValidCharacter} = require('../helper/generalFunctions')


const networkGroupReducer = (netGrp, action) =>{

    switch(action.type){

        case FORM_FIELDS.NAME:
            console.log(netGrp);
            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 50){

                    return produce(netGrp, draft=>{
                        console.log(netGrp);
                        draft.group.name = action.payload;
                    })

                }else{

                    return netGrp;

                }

            }else if(action.payload.length < 1){

                return produce(netGrp, draft=>{

                    draft.group.name = action.payload;
                })

            }else{

                return netGrp
            }

        case FORM_FIELDS.GRP_DESCRIPTION:

            if(isValidCharacter(action.payload)){

                if(action.payload.length <= 250){

                    return produce(netGrp, draft=>{

                        draft.group.description = action.payload;
                    })
                }else{

                    return netGrp;
                }

            }else if(action.payload.length < 1){

                return produce(netGrp, draft=>{

                    draft.group.description = action.payload;
                })

            }else{

                return netGrp;

            }

        case FORM_FIELDS.CONTACTS:
            const {value, checked} = action.payload;

            if(checked){

                return produce(netGrp, draft=>{

                    draft.group.memberIds = [...netGrp.group.memberIds, value];

                })

            }else{

                return produce(netGrp, draft=>{

                    let memberIds = [...netGrp.group.memberIds]

                    memberIds.splice(memberIds.indexOf(memberIds), 1);

                    draft.group.memberIds = [...memberIds]

                })

            }


        case FORM_FIELDS.POPULATE_CONTACTS:

            const {contactNestedResponses} = action.payload;

            return produce(netGrp, draft=>{

                draft.contacts = [...contactNestedResponses];

            })


    }


}

const FORM_FIELDS={

    NAME: 'name',
    GRP_DESCRIPTION: 'description',
    CONTACTS: 'contacts',
    MEMBER_IDS: 'member ids',
    POPULATE_CONTACTS: 'populate contacts',
    CURRENT_GROUP: ''


}


const CreateNetworkingGroupView = props =>{
    const { id } = useParams();
    const navigate = useNavigate();
    const [netGrp, dispatchNetGrp] = useReducer(networkGroupReducer, {

        group:{
            name: ''.trim(),
            description: ''.trim(),
            memberIds: [],
        },

        contacts: [],

    })




    useEffect(()=>{



            (async () => {

                try {

                    const contactResponse = await axios.get('http://localhost:8080/contacts/formContacts', {


                        headers: {
                            Authorization: localStorage.getItem('token')
                        }
                    })


                    console.log(contactResponse.data);
                    dispatchNetGrp({type: FORM_FIELDS.POPULATE_CONTACTS, payload: {...contactResponse.data}})


                } catch (error) {

                    console.log(error.response);

                }
            })()


        return ()=>{}

    },[])


    useEffect(()=>{

        if(id){

            (async ()=>{

                try{

                    const networkingGroupResponse = await axios.get('http://localhost:8080/networkingGroups/' + id, {

                        headers: {
                            Authorization: localStorage.getItem('token')
                        }
                    })


                    dispatchNetGrp()


                }catch (error){

                    console.log(error.response);


                }


            })()
        }

        return ()=>{}

    }, [id])



    const submitHandler = async (e) =>{
        e.preventDefault()
        console.log('submitted')
        try {

            const createNetworkingGroupResponse = await axios.post('http://localhost:8080/networkingGroups/', netGrp.group, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            })

            console.log(createNetworkingGroupResponse.data);
            navigate('/yeoman/networkingGroup/dashboard')



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
                    grp={netGrp.group}
                    onChange={dispatchNetGrp}
                    handler={submitHandler}
                    fields={FORM_FIELDS}
                />
            </div>
            <div>
                <NetworkingGroupWithContacts
                    contacts={netGrp.contacts}
                    memberIds={netGrp.group.memberIds}
                    formField={FORM_FIELDS}
                    dispatchFunction={dispatchNetGrp}
                    divProps={'m-auto w-50 border'}
                />
            </div>
            <Button
                action={submitHandler}
                label={'Create Group'}
            />
        </div>
    )
}

export default CreateNetworkingGroupView