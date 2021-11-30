import React, {useState, useEffect} from "react";
import {useNavigate} from 'react-router'
import axios from "axios";
import HealthCenterList from "../components/HealthCenterList";
import HealthCenterForm from "../components/HealthCenterForm";
import Header from "../components/Header";
import SiteForm from "../components/SiteForm";



const CreateHealthCenterView = props =>{
    const navigate = useNavigate();
    const [healthCenterInfo, setHealthCenterInfo] = useState({

        newHealthCenter:{
            name: ''.trim().toLowerCase(),
            nameAbbr: ''.trim().toLowerCase(),
            siteIds: [],
            contactIds: [],
            userIds: []
        },

        newSite:{
            name: ''.trim().toLowerCase(),
            streetAddress: ''.trim().toLowerCase(),
            cityId: '',
            countyId: '',
            zipCodeId: '',
            healthCenterId: ''
        },

        counties: [],
        cities : [],
        zipCodes: [],
        newSites: [],
        addedContacts: [],
        contacts: [],
        healthCenterProcess: 0

    })


    useEffect(()=>{

        (async ()=>{

            try{

                const contactsResponse = await axios.get('http://localhost:8080/contacts', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                let healthCareResponseObj = {...healthCenterInfo};

                healthCareResponseObj['contacts'] = contactsResponse.data;

                const citiesResponse = await axios.get('http://localhost:8080/cities', {

                    headers:{

                        Authorization: localStorage.getItem('token')
                    }
                })

                healthCareResponseObj['cities'] = citiesResponse.data;

                const countiesResponse = await axios.get('http://localhost:8080/counties', {
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })


                console.log(contactsResponse.data);
                healthCareResponseObj['counties'] = countiesResponse.data


                const zipCodesResponse = await axios.get('http://localhost:8080/zipCodes',{
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                healthCareResponseObj['zipCodes'] = zipCodesResponse.data;

                setHealthCenterInfo(healthCareResponseObj);

            }catch(error){

                console.log(error);

            }

        })()

    }, [])


    const healthCenterHandler = async (e) =>{

        e.preventDefault()
        console.log('name ' + e.target.name);
        console.log('value ' + e.target.value);
        console.log('checked ' + e.target.checked);

        try{

            const healthCenterResponse = await axios.post('http://localhost:8080/contacts', healthCenterInfo.newHealthCenter, {

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            } )

            navigate('/yeoman/organizations/' + healthCenterResponse.data.healthCenterId)


        }catch(error){

            console.log(error);

        }

    }


    return(
        <div>
            <Header/>
            {
                healthCenterInfo.healthCenterProcess === 0?
                    <HealthCenterForm healthCenterInfo={healthCenterInfo} setHealthCenterInfo={setHealthCenterInfo}/>
                    : null
            }
            {
                healthCenterInfo.healthCenterProcess === 1?
                    <SiteForm healthCetnerInfo={healthCenterInfo} setHealthCenterInfo={setHealthCenterInfo}/>
                    : null
            }

        </div>
    )
}

export default CreateHealthCenterView