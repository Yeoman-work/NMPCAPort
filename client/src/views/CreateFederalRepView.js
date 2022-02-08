import React, {useEffect, useState, useReducer } from "react";
import axios from "axios";
import produce from "immer";
import {useNavigate, useParams} from "react-router";
import Header from "../components/Header";
import FederalRepForm from "../components/FederalRepForm";
import CongressionalRep from "../components/CongressionalRep";
import PhoneNumberForm from "../components/PhoneNumberForm";
import CongressionalRepElement from "../components/CongressionalRepElement";
import SiteForm from "../components/SiteForm";
import OfficeLocationForm from "../components/OfficeLocationForm";
const {submitRep, submitSen} = require('../helper/FederalRepFunctions')
const {toogleSwitch} = require('../helper/generalFunctions')

const {
        emailValidation,
        isValidCharacter,
        fieldLengthRequired,
        fieldLengthNotRequired,

} = require('../helper/generalFunctions')




const FEDERAL_REP_FIELDS ={

    FEDERAL_REP_FIRST_NAME: 'first name',
    FEDERAL_REP_LAST_NAME: 'last name',
    FEDERAL_REP_EMAIL: 'email',
    FEDERAL_REP_PICTURE: 'picture',
    FEDERAL_REP_WEBSITE: 'website',
    FEDERAL_REP_DISTRICT: 'district',
    FEDERAL_REP_ELECTED: 'elected',
    FEDERAL_REP_NEXT_ELECTION: 'next election',
    PHONE_NUMBER: 'Phone Number',
    PHONE_DESCRIPTION: 'Description',
    REP_SELECTOR: 'rep selector',
    PARTY_AFFILIATION: 'party affiliation',
    LOCATION_NAME: 'location name',
    LOCATION_DESCRIPTION: 'location description',
    LOCATION_CITY: 'location city',
    LOCATION_COUNTY: 'location county',
    LOCATION_ZIP_CODE: 'location zip code',
    LOCATION_STREET_ADDRESS: 'location street address',
    CITY_LIST: 'cityList',
    COUNTY_LIST: 'countyList',
    ZIP_CODE_LIST: 'zipCodeList',
    PARTY_LIST: 'party list',
    LOCATION_LIST: 'location list',
    DISTRICT_LIST: 'district list',
    REP_RESPONSE: 'rep response',
    SENATOR_RESPONSE: 'senator response',
    FINISHED: 'finished',
    REP_TYPE: 'repType'


}


const CreateFederalRepView = props =>{

    const params = useParams();
    const navigate = useNavigate();

    const [senator, setSenator] = useState({
            
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            politicalParty: ''.trim(),
            elected: ''.trim(),
            nextElection: ''.trim(),
            locations: [],
            
    })
    const [congressionalRep, setCongressionalRep] = useState({

            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            politicalParty: ''.trim(),
            congressionalDistrict: ''.trim(),
            locations: [],
    });

    

    const [repType, setRepType] = useState(false);
    const [partyList, setPartyList] = useState([]);
    const [districtList, setDistrictList] = useState([]);
    const [cityList, setCityList] = useState([]);
    const [zipCodeList, setZipCodeList] = useState([]);
    


    





    useEffect(()=>{

        (async ()=>{
            
            if(repType){

                try{

                    const districtResponse = await axios.get('http://localhost:8080/congressionalDistricts',{
    
                        headers:{
                            Authorization: localStorage.getItem('token')
                        }
                    })
    
                }catch(error){

                    console.log(error.response);
    
                }
            }
        })()

        return ()=>{}

    }, [])

    useEffect(()=>{

        (async ()=>{

            try{

                const partyResponse = await axios.get('http://localhost:8080/politicalParties',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                setPartyList(partyResponse.data)

            }catch(error){

                console.log(error.response);

            }

        })()

    }, [])

    
    //check the status of congressional rep
    const canSubmitRep = (congressionalRep) =>{
        return submitRep(congressionalRep);
    }

    //check the status of the senator
    const canSubmitSen = (senator) =>{
        return submitSen(senator);
    }

    //toogle the rep type
    const toogleRepType = (e, repType) =>{
        e.preventDefault()

        console.log(repType)
        setRepType(toogleSwitch(repType));

    }

    const submitHandler = async (e) =>{
        e.preventDefault()

        if(repType){

            try{

                const createCongressPerson = await axios.post('http://localhost:8080/congressionalReps', congressionalRep, {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                setCongressionalRep(createCongressPerson);


            }catch(error){

                console.log(error.response);

            }


        }else{

            try{

                const createSenator = await axios.post('http://localhost:8080/usSenators', senator,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                

            }catch(error){

                console.log(error.response)

            }


        }
    }


    const finish = (e) =>{

        navigate('/yeoman/government/')
    }


    return(
        <div>
            <Header/>
            <div className={'m-auto'}>
                <button disabled={repType} onClick={(e)=>toogleRepType(e, repType)} >{'Congressional Representatives'}</button>
                <button disabled={!repType} onClick={(e)=>toogleRepType(e, repType)} >{'US Senator'}</button>
                <FederalRepForm
                    formLabel={repType? 'New Representative' : 'New Senator'}
                    federalRepInfo={repType? congressionalRep : senator}
                    setFederalRepInfo={repType? setCongressionalRep : setSenator }
                    politicalParties ={partyList}
                    repType={repType}
                    formFields={FEDERAL_REP_FIELDS}
                />
                {/* <OfficeLocationForm
                    cities={cityList}
                    zipCodes={zipCodeList}
                    stateWithLocation={repType? congressionalRep : senator}
                    setStateWithLocation={repType? setCongressionalRep : setSenator}

                /> */}
                <button disabled={!canSubmitRep(congressionalRep)} onClick={(e)=>submitHandler(e)}>{'Create Congress Person'}</button>
                <button disabled={!canSubmitSen(senator)} onClick={(e)=>submitHandler(e)}>{'Create Senator'}</button>
            </div>
        </div>
    )
}

export default CreateFederalRepView