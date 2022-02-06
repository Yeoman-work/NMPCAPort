import React from "react";
import {IoMdArrowDropright, IoMdArrowDropleft} from 'react-icons/io'
import { RawZoomHelpers } from "victory";
import '../css/style.css'
const {districtPerPageSearch, districtsPerPage, districtSearchText} = require('../helper/DistrictSearch')
const {adjustZipCodesPerPage, adjustZipCodesPerSearchPage} = require('../helper/zipCodeSearch')
const {zipCodeSearchField} = require('../helper/zipCodeSearch')
const {inputChangeField, toogleSwitch} = require('../helper/generalFunctions')
const {citiesPerPage, citiesPerPageSearch, citySearchField} = require('../helper/CitySearchSearch')
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
            districtPageableSearch,
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


    //form input change updates form state
    const inputChange = (e) =>{
        setStateRep(inputChangeField(e, stateRep));
    }

    //changes the number of districts per look up
    const districtsPerPage = (e) =>{
        e.preventDefault();
        setDistrictSearchParams(districtPerPageSearch(e, districtSearchParams));
    }

    //search for district
    const districtSearchAction = (e) =>{
        e.preventDefault()
        setDistrictSearchParams(districtSearchText(e, districtSearchParams));
    }

    //change zip code search parameters
    const searchParams = (e) =>{
        e.preventDefault();
        setSearch(zipCodeSearchField(e, search));
    }


    //changes the number of zip codes per lookup
    const zipCodesPerPage = (e) =>{
        e.preventDefault();
        setZipCodePage(adjustZipCodesPerPage(e, zipCodePage));
        setSearch(adjustZipCodesPerSearchPage(e, search));
    }

    //changes cities per look-up
    const cityPerPage = (e)=>{
        e.preventDefault();
        setCitySearchParams(citiesPerPageSearch(e, citySearchParams));
        setCityPage(citiesPerPage(e, cityPage));
    }

    //change city search params
    const citySearchAction = (e) =>{
        e.preventDefault();
        setCitySearchParams(citySearchField(e, citySearchParams));
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
                            <input name={'city'} value={citySearchParams.city} placeholder={'search city'} className={'form-control'} onChange={(e)=>citySearchAction(e)}/>
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
                                    onChange={(e)=>cityPerPage(e)}
                                    
                            >
                                <option value={10}>10</option>
                                <option value={25}>25</option>
                                <option value={50}>50</option>
                            </select>
                        </div>
                        <div className="col">
                            <button disabled={citySearchParams.city.length > 0? citySearchParams.cities.firstPage :  cityPage.firstPage}
                                    onClick={citySearchParams.cities.cities.length > 0? (e)=>citySearch(e, 'previous') : (e)=>cityPageable(e, 'previous') }
                                    name="previous"
                                
                            >
                                <IoMdArrowDropleft/>

                            </button>
                            {
                                citySearchParams.city.length > 0?
                                <p className="d-inline-block">{citySearchParams.cities?`${citySearchParams.cities.number + 1} of ${citySearchParams.cities.totalPages}`: null}</p>
                                :
                                <p className="d-inline-block">{cityPage?`${cityPage.number + 1}  of ${cityPage.totalPages}`: null}</p>
                            }
                            
                            <button disabled={citySearchParams.city.length > 0? citySearchParams.cities.lastPage : cityPage.lastPage}
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
                            <input name={'name'} value={search.name} placeholder={'search zipcode'} className={'form-control'} onChange={(e)=>searchParams(e)} />
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
                            <button disabled={search.name.length > 0? search.zipCodes.firstPage :  zipCodePage.firstPage}
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
                            
                            <button disabled={search.name > 0? search.zipCodes.lastPage : zipCodePage.lastPage}
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
                            <input className="form-control" placeholder={'search districts'} value={districtSearchParams.district} onChange={(e)=>districtSearchAction(e)}/>
                        </div>
                    </div>
                    <div className={'height200 mt-2 mb-2 overflow-auto'}>
                        {
                            districtPage.districts && districtPage.districts.length > 0?
                                districtPage.districts.map((district, index)=>{

                                    return(
                                        <div key={index}>
                                            <button value={repType? district.houseDistrictId: district.senateDistrictId}>{district.name}</button>
                                        </div>
                                    )
                                })
                                : 
                            
                            districtSearchParams.districts.districts.map((district, index)=>{

                                return(
                                    <div key={index}>
                                        <button value={repType? district.houseDistrictId: district.senateDistrictId}>{district.name}</button>
                                    </div>
                                )
                            })
                        }
                    </div>
                    <div className="row">
                        <div className="col text-start ">
                            <select className="form-control w-50"
                                    onChange={(e)=>districtsPerPage(e)}
                            >
                                <option value={10}>10</option>
                                <option value={25}>25</option>
                                <option value={50}>50</option>
                            </select>
                        </div>
                        <div className="col">
                        <button disabled={districtSearchParams.district.length > 0? districtSearchParams.districts.firstPage :  districtPage.firstPage}
                                    name="previous"
                                    onClick={districtSearchParams.district.length > 0? (e)=>districtPageableSearch(e, 'previous', districtSearchParams) :(e)=>districtPageable(e, 'previous', districtPage)}
                            >
                                <IoMdArrowDropleft/>

                            </button>
                            {
                                districtSearchParams.district.length > 0?
                                <p className="d-inline-block">{`${districtSearchParams.districts.number + 1} of ${districtSearchParams.districts.totalPages}`}</p>
                                :
                                <p className="d-inline-block">{`${districtPage.number + 1} of ${districtPage.totalPages}`}</p>
                            }
                            
                            <button disabled={districtSearchParams.district.length > 0? districtSearchParams.districts.lastPage : districtPage.lastPage}
                                    onClick={districtSearchParams.district.length > 0? (e)=>districtPageableSearch(e, 'next', districtSearchParams) :(e)=>districtPageable(e, 'next', districtPage)}
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