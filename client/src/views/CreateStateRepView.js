import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import Header from "../components/Header";
import StateRepForm from "../components/StateRepForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
const {districtPaging} = require('../helper/DistrictSearch')
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

    const [districtPage, setDistrictPage] = useState({});

    const [partyList, setPartyList] = useState([]);

    const [repType, setRepType] = useState(true);

    const [search, setSearch] = useState({

        name: ''.trim(),
        size: 10,
        startIndex: 0,
        endIndex: 9,
        search: false,
        zipCodes: {
            zipCodes: []
        }
    })
    
    const [citySearchParams, setCitySearchParams] = useState({

        city: ''.trim(),
        size: 10,
        startIndex: 0,
        endIndex: 9,
        search: false,
        cities:{
            cities: []
        }
    })

    const [districtSearchParams, setDistrictSearchParams] = useState({

        district: ''.trim(),
        size: 10,
        startIndex: 0,
        endIndex: 9,
        search: false,
        districts:{
            districts: []
        }

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

    const districtPageable = async (e, direction, districtPage) =>{
        e.preventDefault()

        console.log('read this');
        console.log(districtPage);
        let districtPageObj = districtPaging(e, direction, districtPage);
        console.log(districtPageObj);
console.log('read this')
        if(repType){

                try{

                    const districtResponse = await axios.get('http://localhost:8080/houseDistricts',{

                        headers:{

                            Authorization: localStorage.getItem('token')
                        },

                        params:{
                            
                            pageNo:districtPageObj.number,
                            limit: districtPageObj.size 
                            
                        }
                    })

                    
                    setDistrictPage(districtResponse.data)

                }catch(error){

                    console.log(error.response)

                }

        }else{
            
            try{

                const districtResponse = await axios.get('http://localhost:8080/senateDistricts', {

                    headers:{
                        Authorization : localStorage.getItem('token')
                    },

                    params:{
                        pageNo:districtPageObj.number,
                        limit: districtPageObj.size
                    }
                })
                
                
                setDistrictPage(districtResponse.data)

            }catch(error){

                console.log(error.response)

            }

        }
        
    }

    const cityPageable = async(e, direction)=>{
        e.preventDefault();

        let cityPageObj = JSON.parse(JSON.stringify(cityPage));

        if(direction === 'next'){

            console.log('in there')
            console.log(cityPageObj.number)
            cityPageObj.number = Number(cityPageObj.number + 1);
            console.log(cityPageObj.number)
        }
        
        if(direction === 'previous'){

            cityPageObj.number = Number(cityPageObj.number - 1)
        }

        
        try{

            const cityPageResponse = await axios.get('http://localhost:8080/cities',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    },

                    params:{

                        pageNo:cityPageObj.number,
                        limit: cityPageObj.size 
                    }

            })

            console.log(cityPageResponse.data)
            setCityPage(cityPageResponse.data);

        }catch(error){

            console.log(error.response);

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
                pageNo -= 1;
                
            }
        }console.log(limit);
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
            setZipCodePage(zipCodeResponse.data);
    
            
    
    
        }catch(error){
            
            console.log(error.response);
    
        }
    
    }
    
    const citySearch = async (e, direction)=>{
        e.preventDefault()

        let citySearchParamsObj = JSON.parse(JSON.stringify(citySearchParams));

        if(direction === 'previous'){

            citySearchParamsObj.endIndex -= citySearchParamsObj.size;
            citySearchParamsObj.startIndex -= citySearchParamsObj.size;

            

        }else if(direction === 'next'){

            citySearchParamsObj.startIndex = citySearchParamsObj.endIndex + 1;
            citySearchParamsObj.endIndex = citySearchParamsObj.startIndex + (citySearchParamsObj.size - 1);
            console.log('check this out ')
            console.log(citySearchParams);
        

        }else if(e.target.name === 'size'){

            
            citySearchParamsObj.size = Number(e.target.value);
            citySearchParamsObj.startIndex = 0;
            citySearchParamsObj.endIndex = (citySearchParamsObj.size - 1);
            
        }

        try{

            const cityResponse = await axios.get('http://localhost:8080/cities/search/' + citySearchParamsObj.city ,{

        headers:{

            Authorization: localStorage.getItem('token')
        },

        params:{

            startIndex: citySearchParamsObj.startIndex,
            endIndex: citySearchParamsObj.endIndex
        }

    })
            
            let cityPageObj = JSON.parse(JSON.stringify(cityPage));
            cityPageObj.cities = [];
            citySearchParamsObj.cities = {...cityResponse.data};
            setCityPage(cityPageObj);
            setCitySearchParams(citySearchParamsObj);


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
                    },

                    params:{
                        
                        limit: citySearchParams.size
                    }


                })
                console.log('trigger')
                console.log(cityListResponse.data);
                setCityPage(cityListResponse.data);

            }catch(error){

                console.log(error.response)

            }

        })()
        
        return () =>{};
    
    },[citySearchParams.search, cityPage.size])

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

    },[zipCodePage.size, zipCodePage.search])

    useEffect(()=>{
        console.log('here');
        if(repType){

            (async ()=>{

                try{

                    const houseDistrictResponse = await axios.get('http://localhost:8080/houseDistricts', {

                        headers:{
                            Authorization: localStorage.getItem('token')
                        },

                        params:{
                            
                            limit: districtSearchParams.size
                        }

                    })

                    console.log(houseDistrictResponse.data);

                    setDistrictPage(houseDistrictResponse.data);

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
                },

                params:{
                    limit: districtSearchParams.size
                }

                })
                console.log(senateDistrictResponse.data);

                setDistrictPage(senateDistrictResponse.data);

            }catch(error){

                console.log(error.response)

            }

            })()


        }

        return ()=>{}

    }, [repType, districtSearchParams.size])


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
                    districtPageable={districtPageable}
                    cityPageable={cityPageable}
                    zipCodePageable={zipCodePageable}
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
                    districtPage={districtPage}
                    setDistrictPage={setDistrictPage}
                    districtSearchParams={districtSearchParams}
                    setDistrictSearchParams={setDistrictSearchParams}
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