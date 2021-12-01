import React, {useState, useEffect} from "react";
import {useNavigate} from 'react-router'
import axios from "axios";
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
            cityId: {},
            countyId: {},
            zipCodeId: {},
            healthCenterId: {},
            nmHouseDistrictId: {},
            senateDistrictId: {},
            congressionalDistrictId: {},
        },

        nmHouseDistricts: [],
        senateDistricts: [],
        congressionalDistricts: [],
        counties: [],
        cities : [],
        zipCodes: [],
        newSites: [],
        addedContacts: [],
        contacts: [],
        healthCenterProcess: 0

    })

    const healthcareFormProgression = e =>{


        console.log(e.target.name);

        let healthCenterInfoObj = {...healthCenterInfo};


        if(e.target.name === 'decrement' && healthCenterInfoObj['healthCenterProcess'] > 0){

            healthCenterInfoObj["healthCenterProcess"] -= 1;
        }

        if(e.target.name === 'increment'){

            healthCenterInfoObj['healthCenterProcess'] += 1;
        }


        setHealthCenterInfo(healthCenterInfoObj);
        console.log('marker')
        console.log(healthCenterInfo)
    }

    useEffect(()=>{


        (async()=>{

            try{

                const nmHouseDistrictResponse = await axios.get('http://localhost:8080/nmHouseDistricts',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })



                let healthCenterInfoObj = {...healthCenterInfo};

                healthCenterInfoObj['nmHouseDistricts'] = nmHouseDistrictResponse.data;

                const senateDistrictResponse = await axios.get('http://localhost:8080/senateDistricts', {

                    headers:{

                        Authorization: localStorage.getItem('token')

                    }
                })



                healthCenterInfoObj['senateDistricts'] = senateDistrictResponse.data;

                const congressionalDistrictResponse = await axios.get('http://localhost:8080/congressionalDistricts' ,{

                    headers:{

                        Authorization: localStorage.getItem('token')
                    }
                })




                healthCenterInfoObj['congressionalDistricts'] = congressionalDistrictResponse.data;

                setHealthCenterInfo(healthCenterInfoObj);

            }catch(error){

                console.log(error)

            }


        })()


    }, [])

    useEffect(()=>{

        (async ()=>{

            try{

                const contactsResponse = await axios.get('http://localhost:8080/contacts', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(contactsResponse.data);

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
                    <HealthCenterForm healthCenterInfo={healthCenterInfo} setHealthCenterInfo={setHealthCenterInfo} formProgression={healthcareFormProgression}/>
                    : null
            }
            {
                healthCenterInfo.healthCenterProcess === 1?
                    <SiteForm healthCenterInfo={healthCenterInfo} setHealthCenterInfo={setHealthCenterInfo} formProgression={healthcareFormProgression}/>
                    : null
            }
            {

            }

        </div>
    )
}

export default CreateHealthCenterView