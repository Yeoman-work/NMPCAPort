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
const { removeServiceFromSiteDisplay  } = require('../helper/siteValidation')



const clearSiteData ={

    site:{
        name: ''.trim().toLowerCase(),
        streetAddress: ''.trim().toLowerCase(),
        city: {id: '', name: ''},
        county: {id: '', name: ''},
        zipCode: {id: '', name: ''},
        healthCenter: {id: '', name: ''},
        houseDistrict: {id: '', name: ''},
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
        houseDistrict: '',
        senateDistrict: '',
        congressionalDistrict: '',
        funding: [],
        service: [],
    }


}


function healthCenterReducer(healthCenterState, action){

    switch(action.type){
        case HEALTH_CENTER_FIELDS.HEALTH_CENTER_NAME:
            console.log(healthCenterState);
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
                    })
                }else{
                    return healthCenterState;
                }
            }else{
                return healthCenterState;
            }

        case HEALTH_CENTER_FIELDS.CITY:

            console.log(action.payload)
            return produce(healthCenterState, draft =>{

                draft.siteJson.city = action.payload;
            })

        case HEALTH_CENTER_FIELDS.COUNTY:

            return produce(healthCenterState, draft =>{

                draft.siteJson.county = action.payload;
            })

        case HEALTH_CENTER_FIELDS.ZIP_CODE:

            return produce(healthCenterState, draft =>{

                return produce(healthCenterState, draft =>{

                    draft.siteJson.zipCode = action.payload;
                })
            })

        case HEALTH_CENTER_FIELDS.HOUSE_DISTRICT:

            return produce(healthCenterState, draft =>{

                draft.siteJson.houseDistrict = action.payload;
            })

        case HEALTH_CENTER_FIELDS.SENATE_DISTRICT:

            return produce(healthCenterState, draft =>{

                draft.siteJson.senateDistrict = action.payload;
            })

        case HEALTH_CENTER_FIELDS.CONGRESSIONAL_DISTRICT:

            return produce(healthCenterState, draft =>{

                draft.siteJson.congressionalDistrict = action.payload;
            })

        case HEALTH_CENTER_FIELDS.SITE_SERVICES:

            if(healthCenterState.siteJson.service.includes(action.payload)){

                return produce(healthCenterState, draft =>{

                    let changeList = [...healthCenterState.siteJson.service];

                    changeList.splice(changeList.indexOf(action.payload), 1);

                    draft.siteJson.service = [...changeList];
                })

            }else{

                return produce(healthCenterState, draft =>{

                    draft.siteJson.service.push(action.payload)
                })

            }

        case HEALTH_CENTER_FIELDS.SITE_FUNDING:
            console.log('right place')
            console.log(action.payload)

            if(healthCenterState.siteJson.funding.includes(action.payload)){

                return produce(healthCenterState, draft=>{

                    draft.siteJson.funding.splice(
                        healthCenterState.siteJson.funding.indexOf(action.payload), 1);
                });
            }else{

                return produce(healthCenterState, draft =>{

                    draft.siteJson.funding.push(action.payload);
                });
            }

        case HEALTH_CENTER_FIELDS.NEW_SITE_REQUEST:

            return produce(healthCenterState, draft =>{

                draft.healthCenter.sitesRequest.push(healthCenterState.siteJson);

                draft.siteJson = {...clearSiteData.siteJson};
            })

        case HEALTH_CENTER_FIELDS.REMOVE_CLINIC:

            return produce(healthCenterState, draft =>{

                draft.healthCenter.sitesRequest.splice(action.payload, 1);
            })

        case HEALTH_CENTER_FIELDS.FORM_INCREMENT:

            if(healthCenterState.formData.healthCenterProcess < 3){

                return produce(healthCenterState, draft=>{


                    draft.formData.healthCenterProcess += 1;
                })


            }else{

                return healthCenterState;
            }

        case HEALTH_CENTER_FIELDS.FORM_DECREMENT:

            if(healthCenterState.formData.healthCenterProcess > 0){

                return produce(healthCenterState, draft =>{

                    draft.formData.healthCenterProcess -= 1;
                })



            }else{

                return healthCenterState;
            }

        case HEALTH_CENTER_FIELDS.CITY_LIST:

            return produce(healthCenterState, draft =>{

                draft.formData.city_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.COUNTY_LIST:

            return produce(healthCenterState, draft =>{

                draft.formData.county_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.ZIP_CODE_LIST:

            return produce(healthCenterState, draft =>{

                draft.formData.zipCode_list = [...action.payload];
            })


        case HEALTH_CENTER_FIELDS.SERVICE_LIST:

            return produce(healthCenterState, draft =>{

                draft.formData.service_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.FUNDING_LIST:

            return produce(healthCenterState, draft=>{

                draft.formData.funding_list = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.HOUSE_DISTRICT_LIST:

            return produce(healthCenterState, draft=>{

                draft.formData.house_districts = [...action.payload];
            })

        case HEALTH_CENTER_FIELDS.SENATE_DISTRICT_LIST:

            return produce(healthCenterState, draft=>{

                draft.formData.senate_districts = [...action.payload]
            })

        case HEALTH_CENTER_FIELDS.CONGRESSIONAL_DISTRICT_LIST:

            return produce(healthCenterState, draft =>{

                draft.formData.congressional_districts = [...action.payload];
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
    HOUSE_DISTRICT: 'houseDistrict',
    SENATE_DISTRICT: 'senateDistrict',
    CONGRESSIONAL_DISTRICT: 'congressionalDistrict',
    FORM_DATA_SERVICES : 'services',
    FUNDING_LIST: 'fundList',
    SERVICE_LIST: 'serviceList',
    HOUSE_DISTRICT_LIST: 'houseDistricts',
    SENATE_DISTRICT_LIST: 'senateDistricts',
    CONGRESSIONAL_DISTRICT_LIST: 'congressionalDistricts',
    COUNTY_LIST: 'county_list',
    CITY_LIST : 'cities',
    ZIP_CODE_LIST: 'zipCodes',
    NEW_SITE_REQUEST: 'site request',
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
        houseDistrict: ''.trim(),
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
        houseDistrict: '',
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

        siteJson:{
          name: ''.trim().toLowerCase(),
          streetAddress: ''.trim().toLowerCase(),
          city: '',
          county: '',
          zipCode: '',
          healthCenter: '',
          houseDistrict: '',
          senateDistrict: '',
          congressionalDistrict: '',
          funding : [],
          service: [],
        },

        formData:{
            service_list : [],
            funding_list: [],
            house_districts: [],
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


                console.log(senateDistrict.data)

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.SENATE_DISTRICT_LIST, payload: [...senateDistrict.data]})

                const houseDistrict = await axios.get('http://localhost:8080/houseDistricts/',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                dispatchHealthCenterInfo({type: HEALTH_CENTER_FIELDS.HOUSE_DISTRICT_LIST, payload: [...houseDistrict.data]})

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

            navigate('/yeoman/dashboard/')


        }catch(error){

            console.log(error.response.data);

        }

    }





    return(
        <div className={'heightFullPage align-middle'}>
            <Header/>
            {
                healthCenterInfo.formData?
                    <div>
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
                                        siteListing={healthCenterInfo.healthCenter.sitesRequest}
                                        formData={healthCenterInfo.formData}
                                        dispatchHealthCenterInfo={dispatchHealthCenterInfo}
                                        removeField={HEALTH_CENTER_FIELDS.REMOVE_CLINIC}
                                    />
                                    <SiteForm
                                        siteJson={healthCenterInfo.siteJson}
                                        healthCenter={healthCenterInfo.healthCenter}
                                        siteDataFields={HEALTH_CENTER_FIELDS}
                                        dispatchSite={dispatchHealthCenterInfo}
                                        formData={healthCenterInfo.formData}
                                        healthCenterHandler={healthCenterHandler}
                                    />
                                </div>
                                : null
                        }
                    </div>
                    : null
            }



            {

            }

        </div>
    )
}

export default CreateHealthCenterView