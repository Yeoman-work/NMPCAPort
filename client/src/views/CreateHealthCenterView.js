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
        case (HEALTH_CARE_FIELDS.NAME):
            console.log(healthCenterState)
            return produce(healthCenterState, draft=>{
                draft[HEALTH_CARE_FIELDS.NAME] = action.payload;
            })

        case HEALTH_CARE_FIELDS.NAME_ABBR:
            return produce(healthCenterState, draft=>{
                draft[HEALTH_CARE_FIELDS.NAME_ABBR] = action.payload;
            })

        case HEALTH_CARE_FIELDS.CONTACT_IDS:

            return produce(healthCenterState, draft=>{
                draft[HEALTH_CARE_FIELDS.CONTACT_IDS].push(action.payload);
            })

        case HEALTH_CARE_FIELDS.USER_IDS:
            return produce(healthCenterState, draft=>{
                draft[HEALTH_CARE_FIELDS.USER_IDS].push(action.payload);
            })

        default:
            return healthCenterState;
    }
}

const siteReducer = (siteState, action)=>{
    console.log(siteState);
    switch(action.type){

        case SITE_FIELDS.NAME:
            return produce(siteState, draft =>{
                draft[SITE_FIELDS.NAME] = action.payload;
            })

        case SITE_FIELDS.STREET_ADDRESS:
            return produce(siteState, draft =>{
                draft.streetAddress = action.payload;
            })

        case SITE_FIELDS.CITY:
            const cityString = action.payload.split('/');
            const cityObj = {
                id: cityString[0],
                name: cityString[1]
            }
            return produce(siteState, draft=>{
                draft.city = cityObj;
            })

        case SITE_FIELDS.COUNTY:
            const countyString = action.payload.split('/');
            const countyObj = {
                id: countyString[0],
                name: countyString[1]
            }
            return produce(siteState, draft=>{
                draft.county = countyObj;
            })

        case SITE_FIELDS.ZIP_CODE:
            const zipCodeString = action.payload.split('/')
            const zipCodeObj ={
                id: zipCodeString[0],
                name: zipCodeString[1]
            }
            return produce(siteState, draft=>{
                draft.zipCode = zipCodeObj;
            })

        case SITE_FIELDS.FUNDING:
            const fundingObj = {
                id: action.payload.target.value,
                name: action.payload.name
            }
            console.log(fundingObj);
            if(action.payload.target.checked){
                return produce(siteState, draft=>{
                    draft.funding = [...siteState.funding, fundingObj]
                })
            }

            if(!action.payload.target.checked){
                const funding = siteState.funding;
                console.log(funding)
                let newArray = [];
                for(let i = 0; i < funding.length; i++){
                    if(funding[i].id !== fundingObj.id){
                        newArray.push(funding[i]);
                    }
                }

                return produce(siteState, draft=>{
                    draft.funding = [...newArray];
                })
            }

        case SITE_FIELDS.SERVICES:
            const serviceObj = {
                id: action.payload.target.value,
                name: action.payload.name
            }
            if(action.payload.target.checked){
                return produce(siteState, draft=>{
                    draft.services = [...siteState.services, serviceObj];
                })
            }
            if(!action.payload.target.checked){
                const services = siteState.services;
                let newArray = [];
                for(let i = 0; i < services.length; i++){
                    if(services[i].id !== serviceObj.id){
                        newArray.push(services[i]);
                    }
                }
                return produce(siteState, draft=>{
                    draft.services = [...newArray];
                })

            }

        case SITE_FIELDS.HEALTH_CENTER:
            return produce(siteState, draft=>{
                draft[SITE_FIELDS.HEALTH_CENTER] = action.payload;
            })

        default:
            return siteState;

    }
}

const formDataReducer = (formDataState, action)=>{

    switch(action.type){
        case FORM_DATA_FIELDS.FORM_INCREMENT:
            console.log(action.type);
            return produce(formDataState, draft =>{
                draft.healthCenterProcess += 1
            });

        case FORM_DATA_FIELDS.FORM_DECREMENT > 0:
                return produce(formDataState, draft =>{
                    draft.healthCenterProcess -= 1;
                });
        case FORM_DATA_FIELDS.CITIES:
            console.log(formDataState);
            return produce(formDataState, draft=>{
                draft[FORM_DATA_FIELDS.CITIES] = [...action.payload];
            })

        case FORM_DATA_FIELDS.SERVICES:
            console.log('service')
            return produce(formDataState, draft=>{
                draft[FORM_DATA_FIELDS.SERVICES] = [...formDataState[FORM_DATA_FIELDS.SERVICES],...action.payload];
            })
        case FORM_DATA_FIELDS.FUNDING:
            return produce(formDataState, draft=>{
                draft[FORM_DATA_FIELDS.FUNDING] = [...action.payload];
            })

        case FORM_DATA_FIELDS.COUNTIES:
            return produce(formDataState, draft=>{
                draft[FORM_DATA_FIELDS.COUNTIES] = [...action.payload];
            })

        case FORM_DATA_FIELDS.NEW_SITES:
            return  produce(formDataState, draft=>{
                draft[FORM_DATA_FIELDS.NEW_SITES].push(action.payload);
            })
        case FORM_DATA_FIELDS.ZIP_CODES:
            return produce(formDataState, draft=>{
                draft[FORM_DATA_FIELDS.ZIP_CODES] = [...action.payload];
            })

        default:
            console.log('ere');

    }
}

const HEALTH_CARE_FIELDS ={

    NAME: 'name',
    NAME_ABBR: 'nameAbbr',
    CONTACT_IDS: 'contactIds',
    USER_IDS: 'userIds'

}

const SITE_FIELDS={

    NAME: 'name',
    STREET_ADDRESS: 'streetAddress',
    CITY: 'city',
    COUNTY: 'county',
    ZIP_CODE: 'zipCode',
    HEALTH_CENTER: 'healthCenter',
    SERVICES : 'SERVICES',
    FUNDING: 'funding',
    ID: 'id'
}

const FORM_DATA_FIELDS={

    SERVICES : 'services',
    FUNDING: 'funding',
    NM_HOUSE_DISTRICTS: 'nmHouseDistricts',
    SENATE_DISTRICTS: 'senateDistricts',
    CONGRESSIONAL_DISTRICTS: 'congressionalDistricts',
    COUNTIES: 'counties',
    CITIES : 'cities',
    ZIP_CODES: 'zipCodes',
    NEW_SITES: 'newSites',
    ADDED_CONTACTS: 'addContacts',
    CONTACTS: 'contacts',
    FORM_INCREMENT: 0,
    FORM_DECREMENT: 0
}

const CreateHealthCenterView = props =>{
    const navigate = useNavigate();

    const [healthCenterState, dispatchHealthCenter] = useReducer(healthCenterReducer, {

            name: ''.trim().toLowerCase(),
            nameAbbr: ''.trim().toLowerCase(),
            contactIds: [],
            userIds: []
        });

    const [siteState, dispatchSite] = useReducer(siteReducer, {

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
    });

    const [formDataState, dispatchFormData] = useReducer(formDataReducer ,{

        services : [],
        funding: [],
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
    });

    useEffect(()=>{

        (async ()=>{

            try{

                const service = await axios.get('http://localhost:8080/services/',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchFormData({type: FORM_DATA_FIELDS.SERVICES, payload: [...service.data]})


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

                dispatchFormData({type: FORM_DATA_FIELDS.CITIES, payload: [...cities.data]})

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

                dispatchFormData({type: FORM_DATA_FIELDS.ZIP_CODES, payload: [...zipCodes.data]})

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

                dispatchFormData({type: FORM_DATA_FIELDS.COUNTIES, payload: [...counties.data]})


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
                dispatchFormData({type: FORM_DATA_FIELDS.FUNDING, payload: [...funding.data]})

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
                formDataState.healthCenterProcess === 0?
                    <HealthCenterForm
                        healthCenterState={healthCenterState}
                        dispatchHealthCenter={dispatchHealthCenter}
                        formDataState={formDataState}
                        dispatchFormData={dispatchFormData}
                        formDataFields = {FORM_DATA_FIELDS}
                        healthCareFields={HEALTH_CARE_FIELDS}
                    />
                    : null
            }
            {
                formDataState.healthCenterProcess === 1?
                    <div  className={'d-inline-flex m-auto'}>
                        <SiteListComponent
                            healthCenterName={healthCenterState.name}
                            siteListing={formDataState.newSites}
                        />
                        <SiteForm
                            siteState={siteState}
                            siteDataFields={SITE_FIELDS}
                            dispatchSite={dispatchSite}
                            formData={formDataState}
                            formDataFields={FORM_DATA_FIELDS}
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