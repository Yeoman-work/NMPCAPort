import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import Header from "../components/Header";
import StateRepForm from "../components/StateRepForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
const {searchNameIsEmpty, clearZipCodeSearch, zipCodePageable} = require('../helper/paginationFunctions');

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

    const [cityPage, setCityPage] = useState({});

    const [districtList, setDistrictList] = useState([]);

    const [partyList, setPartyList] = useState([]);

    const [repType, setRepType] = useState(true);

    const [search, setSearch] = useState({

        name: ''.trim(),
        size: 10,
        startIndex: 0,
        endIndex: 9,
        zipCodes: {
            zipCodes: []
        }
    })
    
    const [citySearchParams, setCitySearchParams] = useState({

        city: ''.trim(),
        size: 10,
        startIndex: 0,
        endIndex: 9,
        cities:{
            cities: []
        }
    })
    
    const [toogleSearch, setToogleSearch ] = useState(false);

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
    
            console.log(zipCodeResponse.data);
            return zipCodeResponse.data
    
            
    
    
        }catch(error){
            
            console.log(error.response);
    
        }
    
    }
    
    const citySearch = async (e, direction)=>{
        e.preventDefault()

        let citySearchObj = {...citySearch};

        if(direction === 'previous'){

            citySearchObj.endIndex -= citySearchObj.size;
            citySearchObj.startIndex -= citySearchObj.size;

            setCitySearchParams(citySearchObj);

        }else if(direction === 'next'){

            citySearchObj.startIndex = citySearchObj.endIndex + 1;
            citySearchObj.endIndex = citySearchObj.startIndex + (citySearchObj.size - 1);
            setSearch(citySearchObj);

        }else if(e.target.name === 'size'){

            console.log(typeof(e.target.value))
            citySearchObj.size = Number(e.target.value);
            citySearchObj.startIndex = 0;
            citySearchObj.endIndex = (citySearchObj.size - 1);
            setCitySearchParams(citySearchObj);
        }


        try{

            const cityResponse = await axios.get('http://localhost:8080/cities/search/' + citySearchParams.city ,{

        headers:{

            Authorization: localStorage.getItem('token')
        },

        params:{

            startIndex: citySearchParams.startIndex,
            endIndex: citySearchParams.endIndex
        }

    })
            console.log(cityResponse.data);
            let cityPageObj = JSON.parse(JSON.stringify(cityPage));

            let citySearchParamsObj = JSON.parse(JSON.stringify(cityPage));
            cityPageObj.cities = [];
            setCityPage(cityPageObj);
            setCitySearchParams(cityResponse.data);


        }catch(error){

            console.log(error.response);

        }
    }

    const zipCodeSearch = async (e, direction)=>{
        e.preventDefault();
        
        let searchObj = {...search};
        let zipCodePageObj = JSON.parse(JSON.stringify(zipCodePage));

        
        if(direction === 'previous'){
            
            searchObj.endIndex -= searchObj.size;
            searchObj.startIndex -= searchObj.size;
            setSearch(searchObj);

        }else if(direction === 'next'){

            searchObj.startIndex = searchObj.endIndex + 1;
            searchObj.endIndex = searchObj.startIndex + (searchObj.size - 1);
            setSearch(searchObj);

        }else if(e.target.name === 'size'){

            console.log(typeof(e.target.value))
            searchObj.size = Number(e.target.value);
            searchObj.startIndex = 0;
            searchObj.endIndex = (searchObj.size - 1);
            setSearch(searchObj);
        }

        console.log(searchObj);

        try{

            const zipCodeSearchResponse = await axios.get('http://localhost:8080/zipCodes/search/' + searchObj.name,{

            
                headers:{
                    
                    Authorization: localStorage.getItem('token'),
                    
                },

                params:{

                    startIndex: searchObj.startIndex,
                    endIndex: searchObj.endIndex
                }
            })

            console.log(zipCodeSearchResponse.data);  
            searchObj.zipCodes = {...zipCodeSearchResponse.data};
            zipCodePageObj.zipCodes = [];
            setSearch(searchObj);
            setZipCodePage(zipCodePageObj);

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
                setCityPage(cityListResponse.data);

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
                    },

                    params:{
                        limit: search.endIndex
                    }

                })

                console.log('zipCodes')
                console.log(zipCodeListResponse.data);
                setZipCodePage(zipCodeListResponse.data);
                setSearch(clearZipCodeSearch(search));

            }catch(error){

                console.log(error.response)

            }
        })()

        return ()=>{};

    },[zipCodePage.size, toogleSearch])

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
                    toogleSearch={toogleSearch}
                    setToogleSearch={setToogleSearch}
                    zipCodeSearch={zipCodeSearch}
                    citySearch={citySearch}
                    citySearchParams={citySearchParams}
                    setCitySearchParams={setCitySearchParams}
                    cityPage={cityPage}
                    setCityPage={setCityPage}
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