import React, {useState, useEffect, useReducer} from "react";
import {useNavigate} from 'react-router'
import axios from "axios";
import produce from "immer";
import HealthCenterForm from "../components/HealthCenterForm";
import Header from "../components/Header";
import SiteForm from "../components/SiteForm";
import '../css/style.css'
import SiteListComponent from "../components/SiteListComponent";







function healthCenterReducer(healthCenterState, action){

    switch(action.type){
        case HEALTH_CENTER_FIELDS.HEALTH_CENTER_NAME:
            console.log(action.payload)
            return produce(healthCenterState, draft=>{
                draft.healthCenter.name = action.payload;
            })

        case HEALTH_CENTER_FIELDS.HEALTH_CENTER_ABBR:
            console.log(action.payload)
            return produce(healthCenterState, draft=>{
                draft.healthCenter.nameAbbr = action.payload;
            })

        case HEALTH_CENTER_FIELDS.CONTACTS:
            return produce(healthCenterState, draft=>{
                draft.healthCenter.contacts = [...healthCenterState, ...action.payload]
            })
        case HEALTH_CENTER_FIELDS.USERS:
            return produce(healthCenterState, draft=>{
                draft.users = [...healthCenterState, ...action.payload];
            })

        case HEALTH_CENTER_FIELDS.SITE_NAME:
            return produce(healthCenterState, draft =>{
                draft.site.name = action.payload;
            })

        case HEALTH_CENTER_FIELDS.STREET_ADDRESS:
            return produce(healthCenterState, draft =>{
                draft.streetAddress = action.payload;
            })

        case HEALTH_CENTER_FIELDS.CITY:
            const cityString = action.payload.split('/');
            const cityObj = {
                id: cityString[0],
                name: cityString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.city = cityObj;
            })

        case HEALTH_CENTER_FIELDS.COUNTY:
            const countyString = action.payload.split('/');
            const countyObj = {
                id: countyString[0],
                name: countyString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.county = countyObj;
            })

        case HEALTH_CENTER_FIELDS.ZIP_CODE:
            const zipCodeString = action.payload.split('/')
            const zipCodeObj ={
                id: zipCodeString[0],
                name: zipCodeString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.zipCode = zipCodeObj;
            })

        case HEALTH_CENTER_FIELDS.SITE_FUNDING:
            const fundingObj = {
                id: action.payload.target.value,
                name: action.payload.name
            }
            console.log(fundingObj);
            if(action.payload.target.checked){
                return produce(healthCenterState, draft=>{
                    draft.funding = [...healthCenterState.funding, fundingObj]
                })
            }

            if(!action.payload.target.checked){
                const funding = healthCenterState.site.funding;
                console.log(funding)
                let newArray = [];
                for(let i = 0; i < funding.length; i++){
                    if(funding[i].id !== fundingObj.id){
                        newArray.push(funding[i]);
                    }
                }
                return produce(healthCenterState, draft=>{
                    draft.site.funding = [...draft.site.funding, ...newArray];
                })
            }

        case HEALTH_CENTER_FIELDS.SITE_SERVICES:
            const serviceObj = {
                id: action.payload.target.value,
                name: action.payload.name
            }
            if(action.payload.target.checked){
                return produce(healthCenterState, draft=>{
                    draft.site.services = [...healthCenterState.site.services, serviceObj];
                })
            }
            if(!action.payload.target.checked){
                const services = healthCenterState.site.services;
                let newArray = [];
                for(let i = 0; i < services.length; i++){
                    if(services[i].id !== serviceObj.id){
                        newArray.push(services[i]);
                    }
                }
                return produce(healthCenterState, draft=>{
                    draft.site.services = [...newArray];
                })

            }

        case HEALTH_CENTER_FIELDS.PARENT_HEALTH_CENTER:
            return produce(healthCenterState, draft=>{
                draft.healthCenter = action.payload;
            })

        case HEALTH_CENTER_FIELDS.FORM_INCREMENT:

            return produce(healthCenterState, draft =>{
                draft.formData.healthCenterProcess += 1
            });

        case HEALTH_CENTER_FIELDS.FORM_DECREMENT:

            if(healthCenterState.formData.healthCenterProcess > 0){

                return produce(healthCenterState, draft =>{
                    draft.formData.healthCenterProcess -= 1;
                });
            }else{
                return produce(healthCenterState, draft =>{

                    draft.formData.healthCenterProcess = 0;
                })
            }

        case HEALTH_CENTER_FIELDS.CITY_LIST:

            return produce(healthCenterState, draft=>{
                draft.formData.city_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.SERVICE_LIST:

            return produce(healthCenterState, draft=>{
                draft.formData.service_list = [...healthCenterState.formData.service_list,...action.payload];
            })

        case HEALTH_CENTER_FIELDS.FUNDING_LIST:
            return produce(healthCenterState, draft=>{
                draft.formData.funding_list = [...healthCenterState.formData.funding_list,...action.payload];
            })

        case HEALTH_CENTER_FIELDS.COUNTY_LIST:
            console.log(action.payload)
            return produce(healthCenterState, draft=>{
                draft.formData.county_list = [healthCenterState.formData.county_list, ...action.payload];
            })

        case HEALTH_CENTER_FIELDS.NEW_SITE_LIST:

            console.log(action.payload);
            produce(healthCenterState, draft=>{

                draft.formData.newSites_list = [...healthCenterState.formData.newSites_list, action.payload]
            })

        case HEALTH_CENTER_FIELDS.ZIP_CODE_LIST:
            return produce(healthCenterState, draft=>{
                draft.formData.zipCode_list = [...healthCenterState.formData.zipCode_list, ...action.payload];
            })

        default:
            return healthCenterState;
    }
}


const HEALTH_CENTER_FIELDS ={

    HEALTH_CENTER_NAME: 'healthCenterName',
    HEALTH_CENTER_ABBR: 'healthCenterAbbr',
    CONTACTS: 'contacts',
    USERS: 'users',
    SITE_NAME: 'siteName',
    STREET_ADDRESS: 'streetAddress',
    CITY: 'city',
    COUNTY: 'county',
    ZIP_CODE: 'zipCode',
    PARENT_HEALTH_CENTER: '',
    SITE_SERVICES : 'SERVICES',
    SITE_FUNDING: 'funding',
    NM_HOUSE_DISTRICT: 'nmHouseDistrict',
    SENATE_DISTRICT: 'senateDistrict',
    CONGRESSIONAL_DISTRICT: 'congressionalDistricts',
    FORM_DATA_SERVICES : 'services',
    FUNDING_LIST: 'fundList',
    SERVICE_LIST: 'serviceList',
    NM_HOUSE_DISTRICT_LIST: 'nmHouseDistricts',
    SENATE_DISTRICT_LIST: 'senateDistricts',
    CONGRESSIONAL_DISTRICT_LIST: 'congressionalDistricts',
    COUNTY_LIST: 'county_list',
    CITY_LIST : 'cities',
    ZIP_CODE_LIST: 'zipCodes',
    NEW_SITE_LIST: 'newSites',
    CONTACT_LIST: 'contact_list',
    USER_LIST: 'user_list',
    FORM_INCREMENT: 'increment',
    FORM_DECREMENT: 'decrement'

}

const CreateHealthCenterView = props =>{
    const navigate = useNavigate();

    const [healthCenterInfo, dispatchHealthCenterInfo] = useReducer(healthCenterReducer, {

        healthCenter:{
            name: ''.trim().toLowerCase(),
            nameAbbr: ''.trim().toLowerCase(),
            contacts: [],
            users: []
        },

        site:{
            name: ''.trim().toLowerCase(),
            streetAddress: ''.trim().toLowerCase(),
            city: {id: '', name: ''},
            county: {id: '', name: ''},
            zipCode: {id: '', name: ''},
            healthCenter: {id: '', name: ''},
            nmHouseDistrict: {id: '', name: ''},
            senateDistrict:{id: '', name: ''},
            congressionalDistrict: {id: '', name: ''},
            services: [],
            funding:  []
        },

        formData:{
            service_list : [],
            funding_list: [],
            nmHouse_districts: [],
            senate_districts: [],
            congressional_districts: [],
            county_list: [],
            city_list : [],
            zipCode_list: [],
            newSites_list: [],
            contact_list: [],
            user_list: [],
            healthCenterProcess: 0
        }

});


    useEffect(()=>{

        (async ()=>{

            try{

                const service = await axios.get('http://localhost:8080/services/',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.SERVICE_LIST, payload: [...service.data]})


            }catch(error){


                console.log('error')

            }

        })()


    },[])


    useEffect(()=>{

        (async ()=>{

            try{

                const cities = await axios.get('http://localhost:8080/cities/',{
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.CITY_LIST, payload: [...cities.data]})

            }catch(error){
                console.log(error);
            }
        })()
    },[])



    useEffect(()=>{

        (async ()=>{

            try{

                const zipCodes = await axios.get('http://localhost:8080/zipCodes',{
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.ZIP_CODE_LIST, payload: [...zipCodes.data]})

            }catch(error){

                console.log(error);

            }
        })()

    },[])


    useEffect(()=>{
        (async  ()=>{

            try{

                const counties = await axios.get('http://localhost:8080/counties', {
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.COUNTY_LIST, payload: [...counties.data]})


            }catch(error){

                console.log(error);

            }
        })()

    },[])

    useEffect(()=>{

        (async ()=>{

            try{

                const funding = await axios.get('http://localhost:8080/funds/',{
                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(funding.data);
                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.FUNDING_LIST, payload: [...funding.data]})

            }catch(error){
                console.log(error);
            }


        })()


    }, [])


    const healthCenterHandler = async (e) =>{

        e.preventDefault()


        try{

            const healthCenterResponse = await axios.post('http://localhost:8080/contacts', {

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            } )

            navigate('/yeoman/organizations/')


        }catch(error){

            console.log(error);

        }

    }





    return(
        <div className={'heightFullPage align-middle'}>
            <Header/>

            {
               healthCenterInfo.formData.healthCenterProcess === 0?
                    <HealthCenterForm
                        healthCenterState={healthCenterInfo.healthCenter}
                        dispatchHealthCenter={dispatchHealthCenterInfo}
                        userList={healthCenterInfo.formData.user_list}
                        contact={healthCenterInfo.formData.contact_list}
                        healthCareFields={HEALTH_CENTER_FIELDS}
                    />
                    : null
            }
            {
                healthCenterInfo.formData.healthCenterProcess === 1?
                    <div  className={'d-inline-flex m-auto'}>
                        <SiteListComponent
                            healthCenterName={healthCenterInfo.healthCenter.name}
                            siteListing={healthCenterInfo.formData.newSites_list}
                        />
                        <SiteForm
                            siteState={healthCenterInfo.site}
                            siteDataFields={HEALTH_CENTER_FIELDS}
                            dispatchSite={dispatchHealthCenterInfo}
                            formData={healthCenterInfo.formData}
                        />
                    </div>
                    : null
            }
            {

            }

        </div>
    )
}

export default CreateHealthCenterView