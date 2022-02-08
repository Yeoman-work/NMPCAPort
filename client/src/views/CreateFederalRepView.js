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
const {submitRep, submitSen, clearCongresionalRep, clearUsSenator} = require('../helper/FederalRepFunctions')
const {toogleSwitch} = require('../helper/generalFunctions')

const CreateFederalRepView = props =>{

    const params = useParams();
    const navigate = useNavigate();

    const [senator, setSenator] = useState({
            
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            party: ''.trim(),
            elected: ''.trim(),
            locations: [],
            
    })
    const [congressionalRep, setCongressionalRep] = useState({

            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            party: ''.trim(),
            district: ''.trim(),
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

                    console.log(districtResponse.data);
                    setDistrictList(districtResponse.data);
    
                }catch(error){

                    console.log(error.response);
    
                }
            }
        })()

        return ()=>{}

    }, [repType])

    useEffect(()=>{

        (async ()=>{

            try{

                const partyResponse = await axios.get('http://localhost:8080/politicalParties',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                console.log('party')
                console.log(partyResponse.data);
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
        e.preventDefault();
        
        if(repType){

            setCongressionalRep(clearCongresionalRep);

        }else{

            setSenator(clearUsSenator);

        }

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
                    partyList={partyList}
                    districtList={districtList}
                    repType={repType}
                    
                />
                {/* <OfficeLocationForm
                    cities={cityList}
                    zipCodes={zipCodeList}
                    stateWithLocation={repType? congressionalRep : senator}
                    setStateWithLocation={repType? setCongressionalRep : setSenator}

                /> */}
                <button hidden={!repType} disabled={!canSubmitRep(congressionalRep)} onClick={(e)=>submitHandler(e)}>{'Create Congress Person'}</button>
                <button hidden={repType} disabled={!canSubmitSen(senator)} onClick={(e)=>submitHandler(e)}>{'Create Senator'}</button>
            </div>
        </div>
    )
}

export default CreateFederalRepView