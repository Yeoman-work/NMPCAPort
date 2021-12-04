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
        case HEALTH_CARE_FIELDS.NAME:
            console.log({...healthCenterState, name: action.payload})
            return {...healthCenterState, name: action.payload};

        case HEALTH_CARE_FIELDS.NAME_ABBR:
            console.log({...healthCenterState, nameAbbr: action.payload})
            return {...healthCenterState, nameAbbr: action.payload}

        case HEALTH_CARE_FIELDS.CONTACT_IDS:
            return {...healthCenterState, contactIds: action.value}

        case HEALTH_CARE_FIELDS.USER_IDS:
            return {...healthCenterState, userIds: action.value }

        default:
            return healthCenterState;
    }
}

const siteReducer = (siteState, action)=>{

    switch(action.type){
        case SITE_FIELDS.NAME:
            console.log(siteState);
            return produce(siteState, draft=>{
                draft.name = action.payload;
            })

        case SITE_FIELDS.STREET_ADDRESS:
            console.log(siteState);
            return produce(siteState, draft=>{
                draft.streetAddress = action.payload;
            })

        case SITE_FIELDS.CITY:
            return produce(siteState, draft=>{
                draft.city = action.payload;
            })

        case SITE_FIELDS.COUNTY:
            return produce(siteState, draft=>{
                draft.county = action.payload;
            })

        case SITE_FIELDS.ZIP_CODE:
            return produce(siteState, draft=>{
                draft.zipCode = action.payload;
            })

        case SITE_FIELDS.HEALTH_CENTER:
            return produce(siteState, draft=>{
                draft.healthCenter = action.payload;
            })

    }
}

const formDataReducer = (formDataState, action)=>{

    switch(action.type){
        case FORM_DATA_FIELDS.FORM_INCREMENT:
            console.log(action.type)
            return produce(formDataState, draft =>{
                draft.healthCenterProcess += 1
            });

        case FORM_DATA_FIELDS.FORM_DECREMENT:
            return produce(formDataState, draft =>{
               draft.healthCenterProcess -= 1;
            });

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
        services: []
    });

    const [formDataState, dispatchFormData] = useReducer( formDataReducer ,{

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