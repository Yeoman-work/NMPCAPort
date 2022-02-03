import React from "react";
import {IoMdArrowDropright, IoMdArrowDropleft} from 'react-icons/io'
import { RawZoomHelpers } from "victory";
import '../css/style.css'
const {districtPerPageSearch, districtsPerPage} = require('../helper/DistrictSearch')
const {next, previous, setIndex} =require('../helper/paginationFunctions')


const clearStateRep ={

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



const StateRepForm = props =>{

    const {
            cityPage,
            setCityPage,
            citySearch,
            citySearchParams,
            cityPageable,
            setCitySearchParams,
            districtPage,
            districtPageable,
            setDistrictPage,
            districtSearchParams,
            setDistrictSearchParams,
            partyList,
            zipCodePage,
            setZipCodePage,
            zipCodePageable,
            formLabel,
            formFields,
            toogleSearch,
            setToogleSearch,
            search,
            setSearch,
            zipCodeSearch,
            stateRep,
            setStateRep,
            handler,
            fieldLengthErrorMessages,
            fieldLength,
            emailValidation,
            fieldLengthNotRequired,
            repType
    } = props;


    const inputChange = (e) =>{

        console.log(e.target.name);
        let stateRepObj = {...stateRep};

        stateRepObj[e.target.name] = e.target.value;
        
        console.log(stateRepObj);

        setStateRep(stateRepObj);
    }


    const searchParams = (e) =>{
        e.preventDefault();

        
        let searchObj = {...search};

        searchObj[e.target.name] = e.target.value;
        
        if(searchObj.name.length === 0){

            if(toogleSearch){
                
                searchObj.search = false;

            }else{

                searchObj.search = true;

            }
        }

        setSearch(searchObj);


    }


    const districtPagination = (e) =>{
        e.preventDefault();
        
        
        setDistrictSearchParams(districtPerPageSearch(e, districtSearchParams));

        setDistrictPage(districtsPerPage(e, districtPage));
        
    }
    
    const zipCodesPerPage = (e) =>{
        e.preventDefault();
        
        let zipCodePageObj = JSON.parse(JSON.stringify(zipCodePage));
        let searchObj = {...search};
        
        zipCodePageObj.size = Number(e.target.value);

        searchObj.size = Number(e.target.value);
        
        searchObj.endIndex = (searchObj.startIndex + searchObj.size) - 1; 

        setZipCodePage(zipCodePageObj);
        setSearch(searchObj);
    }

    const citiesPerPage = (e)=>{
        e.preventDefault()


        let cityPageObj = JSON.parse(JSON.stringify(cityPage));
        let citySearchParamsObj = JSON.parse(JSON.stringify(citySearchParams));
        
        cityPageObj.size = Number(e.target.value);
        citySearchParamsObj.size = Number(e.target.value);
        citySearchParamsObj.endIndex = (Number(citySearchParamsObj.startIndex + citySearchParamsObj.size));

        if(citySearchParams.search){
            
            citySearchParams.search = false;

        }else{
            
            citySearchParams.search = true;
        }

        console.log(citySearchParamsObj);
        setCitySearchParams(citySearchParamsObj);
        setCityPage(cityPageObj);

    }



    const citySearchAction = (e) =>{
        e.preventDefault();

        let citySearchObj = JSON.parse(JSON.stringify(citySearchParams));

        citySearchObj[e.target.name] = e.target.value;

        if(citySearchObj.city.length <= 0){

            if(citySearchParams.search){

                citySearchObj.search = false;

            }else{

                citySearchObj.search = true;
            }
        }

        console.log(e.target.name)
        console.log(citySearchObj); 

        setCitySearchParams(citySearchObj);

    }



    return(
        <div className={'w-50 m-auto mt-3 pt-3'} onSubmit={handler}>
            <h1>{formLabel}</h1>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>First Name</label>
                    <input type="text"
                        className={'form-control'}
                        name={'firstName'}
                        value={stateRep.firstName}
                        onChange={(e)=>inputChange(e)}
                    />
                    {fieldLength(3, 50, stateRep.firstName)? <span className={'text-danger'}>{fieldLengthErrorMessages(3, 50, 'firstName')}</span>  : null}
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text" className={'form-control'}
                        name={'lastName'}
                        value={stateRep.lastName}
                        onChange={(e)=>inputChange(e)}
                />
                    {fieldLength(3, 50, stateRep.lastName)? <span className={'text-danger'}>{fieldLengthErrorMessages(3, 50, 'lastName')}</span> : null }
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Email</label>
                    <input type="email" className={'form-control'}
                        name={'email'}
                        value={stateRep.email}
                        onChange={(e)=>inputChange(e)}
                    />
                    {!fieldLengthNotRequired(0, 150, stateRep.email)? <div className={'text-danger'}>{fieldLengthErrorMessages(0, 150, 'email')}</div> : null}
                    {!emailValidation(stateRep.email)? <div className={'text-danger'}>Please enter a valid email</div> : null}
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text" className={'form-control'}
                        name={'picture'}
                        value={stateRep.picture}
                        onChange={(e)=>inputChange(e)}
                    />
                    {!fieldLengthNotRequired(5, 250, stateRep.picture)? <span className={'text-danger'}>{fieldLengthErrorMessages(5, 250, 'picture')}</span> : null}
                </div>
            </div>
            <div className={'row'}>
                <div className={' col form-group'}>
                    <label>Address:</label>
                    <input type="text" className={'form-control'}
                        name={'streetAddress'}
                        value={stateRep.streetAddress}
                        onChange={(e)=>inputChange(e)}
                    />
                    {!fieldLengthNotRequired(5, 150, stateRep.streetAddress)? <span className={'text-danger'}>{fieldLengthErrorMessages(5, 150, 'streetAddress')}</span> : null}
                </div>
                <div className={'col form-group'}>
                    <label>Capital Office</label>
                    <input type="text" className={'form-control'}
                    name={'capitolRoom'}
                    value={stateRep.capitolRoom}
                    onChange={(e)=> inputChange(e)}
                    />
                    {!fieldLengthNotRequired(0, 8, stateRep.capitolRoom)? <span className={'text-danger'}>{fieldLengthErrorMessages(5, 150, stateRep.capitolRoom)}</span> : null}
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>City:</label>
                    <div>
                        <div>
                            <input name={'city'} className={'form-control w-50 d-inline-block'} onChange={(e)=>citySearchAction(e)}/>
                            <button className="btn bg-primary" onClick={(e)=>citySearch(e)}>Search</button>
                        </div>
                    </div>
                    <div className="mt-3 mb-3 overflow-auto height200">
                        {
                            cityPage.cities && cityPage.cities.length > 0?
                            cityPage.cities.map((city, index)=>{

                                return(
                                <div key={index}>
                                    <button 
                                        value={city.cityId}
                                        name={'city'}
                                        onClick={(e)=>inputChange(e)}
                                        disabled={stateRep.city === city.cityId}
                                    >
                                        {city.name}
                                    </button>
                                </div>
                                )
                            })
                            : 
                            
                            citySearchParams.cities.cities.map((city, index)=>{

                                return(
                                    <div key={index}>
                                        <button 
                                            value={city.cityId}
                                            name={'city'}
                                            onClick={(e)=>inputChange(e)}
                                            disabled={stateRep.city === city.cityId}
                                        >
                                            {city.name}
                                        </button>
                                    </div>
                                )

                            })
                        }
                    </div>
                        <div className="row">
                        <div className="col text-start ">
                            <select name='size' 
                                    className="form-control w-50"
                                    onChange={(e)=>citiesPerPage(e)}
                                    
                            >
                                <option value={10}>10</option>
                                <option value={25}>25</option>
                                <option value={50}>50</option>
                            </select>
                        </div>
                        <div className="col">
                            <button disabled={citySearchParams.cities.cities.length > 0? citySearchParams.cities.firstPage :  cityPage.firstPage}
                                    onClick={citySearchParams.cities.cities.length > 0? (e)=>citySearch(e, 'previous') : (e)=>cityPageable(e, 'previous') }
                                    name="previous"
                                
                            >
                                <IoMdArrowDropleft/>

                            </button>
                            {
                                citySearchParams.cities && citySearchParams.cities.cities.length > 0?
                                <p className="d-inline-block">{citySearchParams.cities?`${citySearchParams.cities.number + 1} of ${citySearchParams.cities.totalPages}`: null}</p>
                                :
                                <p className="d-inline-block">{cityPage?`${cityPage.number + 1}  of ${cityPage.totalPages}`: null}</p>
                            }
                            
                            <button disabled={citySearchParams.cities.cities.length > 0? citySearchParams.cities.lastPage : cityPage.lastPage}
                                    onClick={citySearchParams.cities.cities.length > 0? (e)=>citySearch(e, 'next') : (e)=>cityPageable(e, 'next')}
                            >
                                <IoMdArrowDropright/>
                            </button>
                        </div>
                    </div>
                </div>
                <div className={'col form-group border'}>
                    <label>Zip Code</label>
                    <div>
                        <div className="">
                            <input name={'name'} className={'form-control w-50 d-inline-block'} onChange={(e)=>searchParams(e)} />
                            <button className="btn bg-primary" onClick={(e)=>zipCodeSearch(e)}>Search</button>
                        </div>
                        <div></div>
                    </div>
                    <div className={'overflow-auto mt-3 mb-3 height200'}>
                        
                        {
                            zipCodePage.zipCodes && zipCodePage.zipCodes.length > 0?
                            zipCodePage.zipCodes.map((zipCode, index)=>{

                                return(
                                    <div key={index}>
                                        <button 
                                            value={zipCode.zipCodeId}
                                            name={'zipCode'}
                                            onClick={(e)=>inputChange(e)}
                                            disabled={stateRep.zipCode === zipCode.zipCodeId}
                                        >
                                            {zipCode.name}
                                        </button>
                                    </div>
                                        
                                )
                            })

                            : 

                            search.zipCodes.zipCodes.map((zipCode, index)=>{

                                return(
                                    <div key={index}>
                                        <button value={zipCode.zipCodeId}>{zipCode.name}</button>
                                    </div>
                                        
                                )
                            })
                        
                                
                        }
                    </div>
                    <div className="row">
                        <div className="col text-start ">
                            <select name='size' 
                                    className="form-control w-50"
                                    onChange={search.name.length > 0? (e)=>zipCodeSearch(e) : (e)=>zipCodesPerPage(e)}
                            >
                                <option value={10}>10</option>
                                <option value={25}>25</option>
                                <option value={50}>50</option>
                            </select>
                        </div>
                        <div className="col">
                            <button disabled={search.zipCodes.zipCodes.length > 0? search.zipCodes.firstPage :  zipCodePage.firstPage}
                                    name="previous"
                                    onClick={search.name.length > 0? (e)=>zipCodeSearch(e, 'previous') :(e)=>zipCodePageable(e, 'previous')}
                            >
                                <IoMdArrowDropleft/>

                            </button>
                            {
                                search.name.length > 0?
                                <p className="d-inline-block">{search.zipCodes?`${search.zipCodes.number + 1} of ${search.zipCodes.totalPages}`: null}</p>
                                :
                                <p className="d-inline-block">{zipCodePage?`${zipCodePage.number + 1} of ${zipCodePage.totalPages}`: null}</p>
                            }
                            
                            <button disabled={search.zipCodes.zipCodes.length > 0? search.zipCodes.lastPage : zipCodePage.lastPage}
                                    onClick={search.name.length > 0? (e)=>zipCodeSearch(e, 'next') :(e)=>zipCodePageable(e, 'next')}
                            >
                                <IoMdArrowDropright/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div className={'row mb-3 pb-2'}>
                <div className={'col'}>
                    <label>Political Party</label>
                    <select className={'form-control'}
                            name={'party'}
                            onChange={(e)=>inputChange(e)}
                    >
                        <option value="">Choose a party</option>
                    {
                            partyList?
                            partyList.map((party, index)=>{

                                return(
                                    <option key={index} value={party.partyId}>{party.name.toUpperCase()}</option>
                                )
                            })
                                : null
                        }
                    </select>
                </div>
                <div className={'col'}>
                    <label>District</label>
                    <div>
                        <div>
                            <input className="form-control"/>
                        </div>
                    </div>
                    <div className={'height200 mt-2 mb-2 overflow-auto'}>
                        {
                            districtPage.districts?
                                districtPage.districts.map((district, index)=>{

                                    return(
                                        <div key={index}>
                                            <button value={repType? district.houseDistrictId: district.senateDistrictId}>{district.name}</button>
                                        </div>
                                    )
                                })
                                : null
                        }
                    </div>
                    <div className="row">
                        <div className="col text-start w-25">
                            <select className="form-control"
                                    onChange={(e)=>districtPagination(e)}
                            >
                                <option value={10}>10</option>
                                <option value={25}>25</option>
                                <option value={50}>50</option>
                            </select>
                        </div>
                        <div className="col">
                        <button disabled={districtSearchParams.districts.districts.length > 0? districtSearchParams.districts.number + 1 :  districtPage.firstPage}
                                    name="previous"
                                    onClick={districtSearchParams.district.length > 0? (e)=>districtPageable(e, 'previous', districtPage) :(e)=>districtPageable(e, 'previous', districtPage)}
                            >
                                <IoMdArrowDropleft/>

                            </button>
                            {
                                districtSearchParams.district.length > 0?
                                <p className="d-inline-block">{districtSearchParams.districts?`${districtSearchParams.districts.number + 1} of ${districtPage.totalPages}`: null}</p>
                                :
                                <p className="d-inline-block">{districtPage?`${districtPage.number + 1} of ${districtPage.totalPages}`: null}</p>
                            }
                            
                            <button disabled={districtSearchParams.districts.districts.length > 0? districtSearchParams.districts.lastPage : districtPage.lastPage}
                                    onClick={search.name.length > 0? (e)=>districtPageable(e, 'next', districtPage) :(e)=>districtPageable(e, 'next', districtPage)}
                            >
                                <IoMdArrowDropright/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default StateRepForm