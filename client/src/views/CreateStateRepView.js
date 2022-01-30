import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import Header from "../components/Header";
import StateRepForm from "../components/StateRepForm";
import {number} from "../helper/generalFunctions";
import PhoneNumberForm from "../components/PhoneNumberForm";

const { phoneNumberPattern,
        characters,
        fieldLengthErrorMessage,
        fieldLength,
        emailValidation,
        fieldLengthNotRequired,
    } = require('../helper/generalFunctions')


const clearData = {

    phoneNumber:{
        number: ''.trim(),
        description: ''.trim()
    },

    stateRep:{
        firstName: ''.trim(),
        lastName: ''.trim(),
        streetAddress: ''.trim(),
        city: ''.trim(),
        zipCode: ''.trim(),
        capitolRoom: ''.trim(),
        email: ''.trim(),
        picture: ''.trim(),
        houseDistrict: ''.trim(),
        senateDistrict: ''.trim(),
        party: ''.trim()
    }

}


const CreateStateRepView = props =>{

    const [stateRep, setStateRep] = useState({

        firstName: ''.trim(),
        lastName: ''.trim(),
        streetAddress: ''.trim(),
        city: ''.trim(),
        zipCode: ''.trim(),
        capitolRoom: ''.trim(),
        email: ''.trim(),
        picture: ''.trim(),
        houseDistrict: ''.trim(),
        senateDistrict: ''.trim(),
        party: ''.trim()

    });

    const [phoneNumber, setPhoneNumber] = useState({

        number: ''.trim(),
        description: ''.trim()

    })

    const [phoneNumberList, setPhoneNumberList] = useState([]);

    const [zipCodePage, setZipCodePage] = useState({});

    const [cityList, setCityList] = useState([]);

    const [districtList, setDistrictList] = useState([]);

    const [partyList, setPartyList] = useState([]);

    const [repType, setRepType] = useState(true);

    const [search, setSearch] = useState({

        name: ''.trim(),
        startIndex: 0,
        endIndex: 9,
    })

    let params = useParams();
    const navigate = useNavigate();




    const changeRepType = (e) =>{

        if(repType){

            setRepType(false);

        }else{

            setRepType(true);
        }
    }


    const zipCodePageable = async (e, direction)=>{
        console.log('in here')
        e.preventDefault();

        let pageNo = zipCodePage.number;
        let limit = zipCodePage.size;
        
        if(direction === 'next'){

            if(zipCodePage.next){
                
                pageNo += 1;
                
            }
        }

        if(direction === 'previous'){

            if(zipCodePage.previous){

                pageNo -= 1
            }
        }

        try{

            const zipCodeResponse = await axios.get('http://localhost:8080/zipCodes',{

            headers:{

                Authorization: localStorage.getItem('token')
            },

            params:{

                pageNo: pageNo,
                limit: limit
            }

            })

            console.log(zipCodeResponse.data)
            setZipCodePage(zipCodeResponse.data);


        }catch(error){
            
            console.log(error.response);

        }

    }

    const zipCodeSearch = async (e)=>{
        e.preventDefault();
    
        try{

            const zipCodeSearchResponse = await axios.get('http://localhost:8080/zipCodes/search/' + search.name,{

            
                headers:{
                    
                    Authorization: localStorage.getItem('token'),
                    
                },

                params:{

                    startIndex: search.startIndex,
                    endIndex: search.endIndex
                }
            })

            console.log(zipCodeSearchResponse.data);
            setZipCodePage(zipCodeSearchResponse.data);

        }catch(error){

            console.log(error);
        }
    }


    useEffect(()=>{

        (async ()=>{

            try{

                const cityListResponse = await axios.get('http://localhost:8080/cities', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(cityListResponse.data);
                setCityList(cityListResponse.data);

            }catch(error){

                console.log(error.response)

            }

        })()
        
        return () =>{};
    
    },[])

    useEffect(()=>{

        (async ()=>{

            try{

                const partyListResponse = await axios.get('http://localhost:8080/politicalParties',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                setPartyList(partyListResponse.data);

            }catch(error){

                console.log(error.response);
            }


        })()

        return ()=>{}
    }, [])

    useEffect(()=>{

        (async ()=>{

            try{

                const zipCodeListResponse = await axios.get('http://localhost:8080/zipCodes', {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log('zipCodes')
                console.log(zipCodeListResponse.data);
                setZipCodePage(zipCodeListResponse.data);

            }catch(error){

                console.log(error.response)

            }
        })()

        return ()=>{};

    },[])

    useEffect(()=>{

        if(repType){

            (async ()=>{

                try{

                    const houseDistrictResponse = await axios.get('http://localhost:8080/houseDistricts', {

                        headers:{
                            Authorization: localStorage.getItem('token')
                        }

                    })

                    console.log(houseDistrictResponse.data);

                    setDistrictList(houseDistrictResponse.data);

                }catch(error){

                    console.log(error.response);
                }
            })()

        }else{


            (async ()=>{

            try{

                const senateDistrictResponse = await axios.get('http://localhost:8080/senateDistricts', {

                headers:{
                    Authorization: localStorage.getItem('token')
                }

                })
                console.log(senateDistrictResponse.data);

                setDistrictList(senateDistrictResponse.data);

            }catch(error){

                console.log(error.response)

            }

            })()


        }

        return ()=>{}

    }, [repType])


    const createRep = async (e)=>{
        e.preventDefault()

        if(repType){

            try{

                const createRepResponse = await  axios.post('http://localhost:8080/stateReps', stateRep, {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                setStateRep(clearData.stateRep);

                navigate('/yeoman/government/stateRepDashboard');

            }catch(error){

                console.log(error)

            }

        }else{


            try{

                const createSenatorResponse = await axios.post('http://localhost:8080/stateSenators', stateRep,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })


                setStateRep(clearData.stateRep);

                navigate('/yeoman/government/stateSenatorDashboard');



            }catch(error){

                console.log(error)
            }



        }

        return ()=>{}
    }

    const addPhoneNumber = (e) =>{
        e.preventDefault();

        let phoneNumberObjList = [...phoneNumberList, phoneNumber];

        setPhoneNumberList(phoneNumberObjList);

        setPhoneNumber(clearData.phoneNumber);

    }

    const stateLength = (state, lengthMin, lengthMax, required) =>{
        let isValid = false;

        if(required){

            if(state.length >= lengthMin && state.length <= lengthMax){

                isValid = true;
            }
        }else{

            if((state.length === 0) || (state.length >= lengthMin && state.length <= lengthMax)){

                isValid = true
            }
        }

        return isValid;
    }


    const canSubmit = (stateRep) =>{

        let isDisabled = true;

        if(!fieldLength(3, 50, stateRep.firstName)){
            if(!fieldLength(3, 50, stateRep.lastName)){
                if(emailValidation(stateRep.email)){
                    if(fieldLengthNotRequired(0, 150, stateRep.email)){
                        if(fieldLengthNotRequired(5, 250, stateRep.picture)){
                            if(fieldLengthNotRequired(5, 150, stateRep.streetAddress)){
                                if(fieldLengthNotRequired(0, 8, stateRep.capitolRoom)){
                                    isDisabled = false;
                                }
                            }
                        }
                    }
                }
            }

        }

        console.log(isDisabled)
        return isDisabled;
    }


    return(
        <div>
            <Header/>
            <div className={''}>
                <div className={'mt-5 pt-5'}>
                    <button disabled={repType} onClick={(e)=>changeRepType(e)} className={'p-2'}>Representative</button>
                    <button disabled={!repType} className={'p-2'} onClick={(e)=>changeRepType(e)}>Senator</button>
                </div>
                <StateRepForm
                    stateRep={stateRep}
                    setStateRep={setStateRep}
                    zipCodePageable={zipCodePageable}
                    zipCodeSearch={zipCodeSearch}
                    cityList={cityList}
                    zipCodePage={zipCodePage}
                    setZipCodePage={setZipCodePage}
                    partyList={partyList}
                    search={search}
                    setSearch={setSearch}
                    districtList={districtList}
                    handler={createRep}
                    formLabel={repType?  'New State Rep': 'New State Senator'}
                    fieldLength={fieldLength}
                    fieldLengthErrorMessages={fieldLengthErrorMessage}
                    fieldLengthNotRequired={fieldLengthNotRequired}
                    emailValidation={emailValidation}
                    repType={repType}
                />
                <PhoneNumberForm
                    phoneNumber={phoneNumber}
                    phoneNumberList={phoneNumberList}
                    setPhoneNumber={setPhoneNumber}
                    setPhoneNumberList={setPhoneNumberList}
                />
            </div>
            <button disabled={stateRep?canSubmit(stateRep) : true} onClick={(e)=>createRep(e)}>Save State</button>
        </div>
    )
}

export default CreateStateRepView;