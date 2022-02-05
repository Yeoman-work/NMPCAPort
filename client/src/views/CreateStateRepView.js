import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import Header from "../components/Header";
import StateRepForm from "../components/StateRepForm";
import PhoneNumberForm from "../components/PhoneNumberForm";
const {districtPaging, districtTransferList, districtPagingSearch} = require('../helper/DistrictSearch')
const {canSubmit} = require('../helper/StateRepValidation')
const {zipCodeSearchParams, zipCodeSearchFieldTransfer, zipCodePaging} = require('../helper/zipCodeSearch')
const {cityPageableRequest, cityPageableCityRequestSearch, citySearchListTransfer} = require('../helper/CitySearchSearch')
const {zipCodePagable} =require('../helper/zipCodeSearch')
const {searchNameIsEmpty, clearZipCodeSearch, zipCodePageable} = require('../helper/paginationFunctions');

const { phoneNumberPattern,
        characters,
        fieldLengthErrorMessage,
        fieldLength,
        emailValidation,
        fieldLengthNotRequired,
        toogleSwitch
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
        endIndex: 10,
        search: false,
        districts:{
            districts: []
        }

    })
    
    
    let params = useParams();
    const navigate = useNavigate();



    const changeRepType = (e) =>{

        setRepType(toogleSwitch(repType));
    }

    //paging through district
    const districtPageable = async (e, direction, districtPage) =>{
        e.preventDefault()

        const districtPageObj = districtPaging(e, direction, districtPage);
        
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

    const districtPageableSearch = async(e, direction, districtSearchParams)=>{
        e.preventDefault();

        let districtSearchObj = districtPagingSearch(e, direction, districtSearchParams);
        console.log("page")
        console.log(districtSearchObj)
        if(repType){
            try{

                const districtResponse = await axios.get('http://localhost:8080/houseDistricts/search/' + districtSearchObj.district,{

                    headers:{

                        Authorization: localStorage.getItem('token')
                    },

                    params:{
                        
                        startIndex: Number(districtSearchObj.startIndex),
                        endIndex: Number(districtSearchObj.endIndex) 
                        
                    }
                })

                console.log('search');
                //console.log(districtResponse.data);
                districtSearchObj.districts = {...districtResponse.data};
                console.log(districtSearchObj);
                setDistrictSearchParams(districtSearchObj);

            }catch(error){

                console.log(error.response)

            }
        }else{

            try{

                const districtResponse = await axios.get('http://localhost:8080/senateDistricts/search/'  + districtSearchObj.district , {

                    headers:{
                        Authorization : localStorage.getItem('token')
                    },

                    params:{
                        startIndex: Number(districtSearchObj.startIndex),
                        endIndex: Number(districtSearchObj.endIndex) 
                    }
                })
                
                console.log('senate district')
                districtSearchObj.districts = {...districtResponse.data};
                console.log(districtSearchObj);
                setDistrictSearchParams(districtSearchObj);

            }catch(error){

                console.log(error.response)

            }

        }
    }

    const cityPageable = async(e, direction)=>{
        e.preventDefault();

        const cityPageObj = cityPageableRequest(cityPage, direction);
        
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

            
            setCityPage(cityPageResponse.data);

        }catch(error){

            console.log(error.response);

        }
        
    }

    const zipCodePageable = async (e, direction)=>{
        
        e.preventDefault()

        
        const zipCodeObj = zipCodePaging(zipCodePage, direction);

        try{
    
            const zipCodeResponse = await axios.get('http://localhost:8080/zipCodes',{
    
            headers:{
    
                Authorization: localStorage.getItem('token')
            },
    
            params:{
    
                pageNo: zipCodeObj.number,
                limit: zipCodeObj.size
            }
    
            })
    
            setZipCodePage(zipCodeResponse.data);
    
        }catch(error){
            
            console.log(error.response);
    
        }
    
    }


    
    const citySearch = async (e, direction)=>{
        e.preventDefault()

        let citySearchParamsObj = cityPageableCityRequestSearch(e, citySearchParams, direction);

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
        citySearchParamsObj.cities = {...cityResponse.data}
        setCitySearchParams(citySearchParamsObj);
        setCityPage(citySearchListTransfer(cityPage));

        }catch(error){

            console.log(error.response);

        }
    }

    const zipCodeSearch = async (e, direction)=>{
        e.preventDefault();
        
        const searchObj = zipCodeSearchParams(e, direction, search);

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

            
            searchObj.zipCodes = {...zipCodeSearchResponse.data};
            setSearch(searchObj);
            setZipCodePage(zipCodeSearchFieldTransfer(zipCodePage));

        }catch(error){

            console.log(error);

        }
    }


    useEffect(()=>{
        
        
        if(search.name && search.name.length > 0){

        let zipCodeTimer = setTimeout(async()=>{
                
                let zipCodeSearchObj = JSON.parse(JSON.stringify(search));
                
                try{

                    const zipCodeSearchResponse = await axios.get('http://localhost:8080/zipCodes/search/' + zipCodeSearchObj.name, {

                        headers:{
                        
                            Authorization: localStorage.getItem('token'),
                            
                        },
        
                        params:{
        
                            startIndex: zipCodeSearchObj.startIndex,
                            endIndex: zipCodeSearchObj.endIndex
                        }

                    })
                    
                    zipCodeSearchObj.zipCodes = {...zipCodeSearchResponse.data};
                    setSearch(zipCodeSearchObj);
                    setZipCodePage(zipCodeSearchFieldTransfer(zipCodePage));
                    
                }catch(error){

                    console.log(error.response);

                }

            }, 500)

            return ()=>{clearTimeout(zipCodeTimer)}
        }
        

    }, [search.name])

    useEffect(()=>{
        
        if(citySearchParams.city && citySearchParams.city.length > 0){
            
            const cityTimer = setTimeout(async()=>{
                
                const citySearchParamsObj = JSON.parse(JSON.stringify(citySearchParams));

                try{
                    
                    const citySearchResponse = await axios.get('http://localhost:8080/cities/search/' + citySearchParamsObj.city,{

                        headers:{
                            
                            Authorization: localStorage.getItem('token')
                        },

                        params:{

                            startIndex: Number(citySearchParamsObj.startIndex),
                            endIndex: Number(citySearchParamsObj.endIndex)
                        }

                    })

                    citySearchParamsObj.cities = {...citySearchResponse.data}
                    setCitySearchParams(citySearchParamsObj);
                    setCityPage(citySearchListTransfer(cityPage));

                }catch(error){

                    console.log(error.response)

                }

                
            }, 500)

            return ()=>{clearTimeout(cityTimer)}
        }

        
    }, [citySearchParams.city])

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

                
                setZipCodePage(zipCodeListResponse.data);
                setSearch(clearZipCodeSearch(search));

            }catch(error){

                console.log(error.response)

            }
        })()

        return ()=>{};

    },[zipCodePage.size, search.search])

    useEffect(()=>{
        
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

    }, [repType, districtSearchParams.search])

    useEffect(()=>{

        let districtObj = JSON.parse(JSON.stringify(districtSearchParams));
        
        if(districtSearchParams.district.length > 0){
                if(repType){

                const districtTimmer = setTimeout(async()=>{

                    try{

                        const districtResponse = await axios.get('http://localhost:8080/houseDistricts/search/' + districtObj.district,{

                                headers:{

                                    Authorization: localStorage.getItem('token')
                                },

                                params:{

                                    startIndex: Number(districtObj.startIndex),
                                    endIndex: Number(districtObj.endIndex)

                                }
                        })

                        
                        districtObj.districts = {...districtResponse.data};
                        setDistrictSearchParams(districtObj);
                        setDistrictPage(districtTransferList(districtPage));

                    }catch(error){

                        console.log(error.response);

                    }
                }, 500)

                return ()=>{clearTimeout(districtTimmer)}


            }else{

                const districtTimer = setTimeout(async()=>{

                    try{

                        const districtResponse = await axios.get('http://localhost:8080/senateDistricts/search/' + districtObj.district,{

                                headers:{

                                    Authorization: localStorage.getItem('token')
                                },

                                params:{
                                    
                                    startIndex: Number(districtObj.startIndex),
                                    endIndex: Number(districtObj.endIndex)

                                }
                        })

                    
                        districtObj.districts ={...districtResponse.data};
                    
                        setDistrictSearchParams(districtObj);
                        setDistrictPage(districtTransferList(districtPage));

                    }catch(error){

                        console.log(error.response);

                    }

                }, 500)

                return ()=>{clearTimeout(districtTimer)}

            }
        }
        


    }, [districtSearchParams.district, districtSearchParams.size])

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


    const submitCheck = (stateRep) =>{
        return canSubmit(stateRep)
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
                    districtPageableSearch={districtPageableSearch}
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