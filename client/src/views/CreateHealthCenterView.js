import React, {useEffect, useReducer} from "react";
import {useNavigate} from 'react-router'
import axios from "axios";
import produce from "immer";
import HealthCenterForm from "../components/HealthCenterForm";
import Header from "../components/Header";
import SiteForm from "../components/SiteForm";
import '../css/style.css'
import SiteListComponent from "../components/SiteListComponent";
const {characters}= require('../helper/generalFunctions')



const clearSiteData ={

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
        service: [],
        funding:  []
    },

    siteJson:{
        name: ''.trim().toLowerCase(),
        streetAddress: ''.trim().toLowerCase(),
        city: '',
        county: '',
        zipCode: '',
        healthCenter: '',
        nmHouseDistrict: '',
        senateDistrict: '',
        congressionalDistrict: '',
        fund: [],
        service: [],
    }


}


function healthCenterReducer(healthCenterState, action){
    console.log(healthCenterState);
    switch(action.type){
        case HEALTH_CENTER_FIELDS.HEALTH_CENTER_NAME:

            const healthCenterString = action.payload;

            if(healthCenterString.length <= 50 ){

                if( healthCenterString.length === 0 || characters.includes(healthCenterString[healthCenterString.length - 1])){

                    return produce(healthCenterState, draft=>{
                        draft.healthCenter.name = action.payload;
                    })
                }else{
                    return healthCenterState;
                }
            }else{

                return healthCenterState;
            }

        case HEALTH_CENTER_FIELDS.HEALTH_CENTER_ABBR:

            if(action.payload.length <= 8){

                return produce(healthCenterState, draft=>{
                    draft.healthCenter.nameAbbr = action.payload;
                })

            }else{

                return healthCenterState;

            }


        case HEALTH_CENTER_FIELDS.CONTACTS:

            return produce(healthCenterState, draft=>{
                draft.healthCenter.contacts = [...healthCenterState, ...action.payload]
            })

        case HEALTH_CENTER_FIELDS.USERS:
            return produce(healthCenterState, draft=>{
                draft.users = [...healthCenterState, ...action.payload];
            })

        case HEALTH_CENTER_FIELDS.SITE_NAME:
            console.log(healthCenterState);
            const name = action.payload;

            if(action.payload.length <= 25 ){
                if( name.length === 0 ||characters.includes(name[name.length - 1])){
                    return produce(healthCenterState, draft =>{
                        draft.siteJson.name = name;
                        draft.site.name = name;
                    })
                }else{

                    return healthCenterState;
                }

            }else{

                return healthCenterState;
            }

        case HEALTH_CENTER_FIELDS.STREET_ADDRESS:
            const streetAddress = action.payload;
            if(action.payload.length <= 50){
                if(streetAddress.length === 0 || characters.includes(streetAddress[streetAddress.length - 1])){
                    console.log(streetAddress[streetAddress.length - 1]);
                    return produce(healthCenterState, draft =>{
                        draft.siteJson.streetAddress = streetAddress;
                        draft.site.streetAddress = streetAddress;
                    })
                }else{
                    return healthCenterState;
                }
            }else{
                return healthCenterState;
            }


        case HEALTH_CENTER_FIELDS.CITY:
            console.log(action.payload)
            const cityString = action.payload.split('/');

            const cityObj = {
                id: cityString[0],
                name: cityString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.siteJson.city = cityString[0];
                draft.site.city = {...cityObj};
            })

        case HEALTH_CENTER_FIELDS.COUNTY:
            const countyString = action.payload.split('/');
            const countyObj = {
                id: countyString[0],
                name: countyString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.siteJson.county = countyString[0];
                draft.site.county = countyObj;
            })

        case HEALTH_CENTER_FIELDS.ZIP_CODE:
            console.log(action.payload)
            const zipCodeString = action.payload.split('/')
            const zipCodeObj ={
                id: zipCodeString[0],
                name: zipCodeString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.siteJson.zipCode = zipCodeString[0];
                draft.site.zipCode = {...zipCodeObj};
            })

        case HEALTH_CENTER_FIELDS.SITE_FUNDING:

            const fundingObj = {
                id: action.payload.target.value,
                name: action.payload.name
            }
            console.log(fundingObj);
            if(action.payload.target.checked){
                return produce(healthCenterState, draft=>{
                    draft.siteJson.funding = [...healthCenterState.siteJson.fund, fundingObj.id]
                    draft.site.funding = [...healthCenterState.site.funding, fundingObj]
                })
            }

            if(!action.payload.target.checked){
                const funding = healthCenterState.site.funding;

                let newArray = [];
                let newArrayIds = [];
                for(let i = 0; i < funding.length; i++){
                    if(funding[i].id !== fundingObj.id){
                        newArray.push(funding[i]);
                        newArrayIds.push(funding[i].id);
                    }
                }
                console.log(newArray);
                return produce(healthCenterState, draft=>{
                    draft.site.funding = [...newArray];
                })
            }
            break;

        case HEALTH_CENTER_FIELDS.SITE_SERVICES:
            const {value, checked} = action.payload
            const siteList = value.split('/')
            console.log(healthCenterState);
            const siteObj ={
                id: siteList[0],
                name: siteList[1],
            }

            if(checked){
                console.log('this')
                if(!healthCenterState.siteJson.service.includes(siteList[0]) && !healthCenterState.site.service.includes(siteList[1])){

                    return produce(healthCenterState, draft=>{

                        draft.siteJson.service = [...healthCenterState.siteJson.service, siteList[0]];

                        draft.site.service = [...healthCenterState.site.service, siteObj];
                    })

                }else{

                    return healthCenterState;
                }


            }else{

                if(healthCenterState.siteJson.service.includes(siteList[0]) && healthCenterState.site.service.includes(siteList[1])){

                    return  produce(healthCenterState, draft=>{

                        let serviceList = [...healthCenterState.siteJson.service];
                        let serviceListDisplay = [...healthCenterState.site.service];

                        const removeServiceIdIndex =  serviceList.indexOf(siteList[0])
                        const removeServiceNameIndex = serviceListDisplay.indexOf(siteList[1]);

                        serviceList.splice(serviceList.indexOf(siteList[0]), 1);
                        serviceListDisplay.splice(removeServiceNameIndex, 1)

                        draft.siteJson.service = [...serviceList];
                        draft.site.service = [...serviceListDisplay];

                    })

                }else{

                    return healthCenterState;
                }
            }

            return healthCenterState;

        case HEALTH_CENTER_FIELDS.PARENT_HEALTH_CENTER:
            return produce(healthCenterState, draft=>{
                draft.site.healthCenter = action.payload;
            })

        case HEALTH_CENTER_FIELDS.CONGRESSIONAL_DISTRICT:
            const congressionalDistrictString = action.payload.split('/');
            const congressionalDistrictObj = {
                id: congressionalDistrictString[0],
                name: congressionalDistrictString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.siteJson.congressionalDistrict = congressionalDistrictObj.id;
                draft.site.congressionalDistrict = {...congressionalDistrictObj};
            })

        case HEALTH_CENTER_FIELDS.NM_HOUSE_DISTRICT:
            console.log(action.type)
            console.log(action.payload)
            const nmHouseDistrictString = action.payload.split('/');
            const nmHouseDistrictObj = {
                id: nmHouseDistrictString[0],
                name: nmHouseDistrictString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.siteJson.nmHouseDistrict = nmHouseDistrictObj.id;
                draft.site.nmHouseDistrict = {...nmHouseDistrictObj}
            })

        case HEALTH_CENTER_FIELDS.SENATE_DISTRICT:
            const senateDistrictString = action.payload.split('/');
            const senateDistrictObj ={
                id: senateDistrictString[0],
                name: senateDistrictString[1]
            }
            return produce(healthCenterState, draft=>{
                draft.siteJson.senateDistrict = senateDistrictObj.id;
                draft.site.senateDistrict = {...senateDistrictObj};
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
                draft.formData.service_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.FUNDING_LIST:
            return produce(healthCenterState, draft=>{
                draft.formData.funding_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.COUNTY_LIST:
            console.log(action.payload)
            return produce(healthCenterState, draft=>{
                draft.formData.county_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.NEW_SITE_LIST:

            return produce(healthCenterState, draft=>{

                draft.healthCenter.sitesRequest = [...healthCenterState.healthCenter.sitesRequest, healthCenterState.siteJson];
                draft.formData.newSites = [...healthCenterState.formData.newSites, healthCenterState.site];

                draft.siteJson = {...clearData.siteJson}
                draft.site = {...clearData.site}
            })

        case HEALTH_CENTER_FIELDS.ZIP_CODE_LIST:
            return produce(healthCenterState, draft=>{
                draft.formData.zipCode_list = [...healthCenterState.formData.zipCode_list, ...action.payload];
            })

        case HEALTH_CENTER_FIELDS.REMOVE_CLINIC:
            console.log(action.payload);
            return produce(healthCenterState, draft=>{
                draft.formData.newSiteFormat.splice(action.payload, 1);
                draft.formData.newSites_list.splice(action.payload, 1);
            })

        case HEALTH_CENTER_FIELDS.SENATE_DISTRICT_LIST:
            return produce(healthCenterState,draft=>{
                draft.formData.senate_districts = [...action.payload]
            });

        case HEALTH_CENTER_FIELDS.CONGRESSIONAL_DISTRICT_LIST:
            console.log('here')
            return produce(healthCenterState, draft=>{
                draft.formData.congressional_districts = [...action.payload]
            })

        case HEALTH_CENTER_FIELDS.NM_HOUSE_DISTRICT_LIST:
            return produce(healthCenterState, draft=>{
                draft.formData.nmHouse_districts = [...action.payload]
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
    CONGRESSIONAL_DISTRICT: 'congressionalDistrict',
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
    FORM_DECREMENT: 'decrement',
    REMOVE_CLINIC: 'remove_clinic'

}

const clearData ={

    site:{

        name: ''.trim().toLowerCase(),
        streetAddress: ''.trim().toLowerCase(),
        city: ''.trim(),
        county: ''.trim(),
        zipCode: ''.trim(),
        healthCenter: ''.trim(),
        nmHouseDistrict: ''.trim(),
        senateDistrict: ''.trim(),
        congressionalDistrict: ''.trim(),
        service: [],
        funding:  []
    },

    siteJson:{
        name: ''.trim().toLowerCase(),
        streetAddress: ''.trim().toLowerCase(),
        city: '',
        county: '',
        zipCode: '',
        healthCenter: '',
        nmHouseDistrict: '',
        senateDistrict: '',
        congressionalDistrict: '',
        funding : [],
        service: [],
    },

}

const CreateHealthCenterView = props =>{
    const navigate = useNavigate();

    const [healthCenterInfo, dispatchHealthCenterInfo] = useReducer(healthCenterReducer, {

        healthCenter:{
            id: ''.trim(),
            name: ''.trim().toLowerCase(),
            nameAbbr: ''.trim().toLowerCase(),
            sitesRequest: [],
        },

        site:{

            name: ''.trim().toLowerCase(),
            streetAddress: ''.trim().toLowerCase(),
            city: ''.trim(),
            county: ''.trim(),
            zipCode: ''.trim(),
            healthCenter: ''.trim(),
            nmHouseDistrict: ''.trim(),
            senateDistrict: ''.trim(),
            congressionalDistrict: ''.trim(),
            service: [],
            funding:  []
        },

        siteJson:{
          name: ''.trim().toLowerCase(),
          streetAddress: ''.trim().toLowerCase(),
          city: '',
          county: '',
          zipCode: '',
          healthCenter: '',
          nmHouseDistrict: '',
          senateDistrict: '',
          congressionalDistrict: '',
          funding : [],
          service: [],
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
            contact_list: [],
            user_list: [],
            healthCenterProcess: 0,
            newSites: []
        },

        errors:{

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

                console.log('get this thing')


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

    useEffect(()=>{

        (async ()=>{

            try{

                const senateDistrict = await axios.get('http://localhost:8080/senateDistricts/',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.SENATE_DISTRICT_LIST, payload: [...senateDistrict.data]})

                const nmHouseDistrict = await axios.get('http://localhost:8080/nmHouseDistricts/',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.NM_HOUSE_DISTRICT_LIST, payload: [...nmHouseDistrict.data]})

                const congressionalDistrict = await axios.get('http://localhost:8080/congressionalDistricts',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.CONGRESSIONAL_DISTRICT_LIST, payload: [...congressionalDistrict.data]})

            }catch(error){

                console.log(error.response);
            }
        })()

    }, [])



    const healthCenterHandler = async (e) =>{

        e.preventDefault()
        console.log(healthCenterInfo.healthCenter);

        try{


            const healthCenterResponse = await axios.post('http://localhost:8080/healthCenters', healthCenterInfo.healthCenter,{

                headers:{
                    Authorization: localStorage.getItem('token')
                }
            } )

            navigate('/yeoman/healthCenter/')


        }catch(error){

            console.log(error.response.data);

        }

    }





    return(
        <div className={'heightFullPage align-middle'}>
            <Header/>

            {
               healthCenterInfo.formData.healthCenterProcess === 0?
                    <HealthCenterForm
                        formLabel={'Create Health Center'}
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
                            siteListing={healthCenterInfo.formData.newSites}
                            dispatchHealthCenterInfo={dispatchHealthCenterInfo}
                            removeField={HEALTH_CENTER_FIELDS.REMOVE_CLINIC}
                        />
                        <SiteForm
                            siteState={healthCenterInfo.site}
                            siteJson={healthCenterInfo.siteJson}
                            siteDataFields={HEALTH_CENTER_FIELDS}
                            dispatchSite={dispatchHealthCenterInfo}
                            formData={healthCenterInfo.formData}
                            healthCenterHandler={healthCenterHandler}
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