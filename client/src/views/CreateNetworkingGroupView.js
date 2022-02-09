import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import NetworkingGroupForm from "../components/NetworkingGroupForm";
import Header from "../components/Header";
import NetworkingGroupWithContacts from "../components/NetworkingGroupWithContacts";
import Button from "../components/Button";




const CreateNetworkingGroupView = props =>{
    const { id } = useParams();
    const navigate = useNavigate();

    const [networkingGroup, setNetworkingGroup] = useState({
        name: ''.trim(),
        description: ''.trim(),
        memberIds: [],
    });
    const [contacts , setContacts] = useState([]);
    




    useEffect(()=>{


            (async () => {

                try {

                    const contactResponse = await axios.get('http://localhost:8080/contacts/essentials', {


                        headers: {
                            Authorization: localStorage.getItem('token')
                        }
                    })


                    console.log(contactResponse.data);
                    setContacts(contactResponse.data);


                } catch(error) {

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


                    console.log(networkingGroupResponse.data)

                    setNetworkingGroup(networkingGroupResponse.data);



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

            const createNetworkingGroupResponse = await axios.post('http://localhost:8080/networkingGroups/', networkingGroup, {

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


            networkingGroup?
                <div>
                    <Header/>
                    <div className={'m-auto mt-5 w-50'}>
                        <NetworkingGroupForm
                            label={id? 'Update Group' : 'New Group'}
                            networkingGroup={networkingGroup}
                            setNetworkingGroup={setNetworkingGroup}
                            id={id}
                        />
                    </div>
                    <div>
                        <NetworkingGroupWithContacts
                            contacts={contacts}
                            networkingGroup={networkingGroup}
                            setNetworkingGroup={setNetworkingGroup}
                            divProps={'m-auto w-50 border'}
                        />
                    </div>
                    <Button
                        action={submitHandler}
                        label={id? 'Update Group': 'Create Group' }
                    />
                </div>

                :
                null


    )
}

export default CreateNetworkingGroupView