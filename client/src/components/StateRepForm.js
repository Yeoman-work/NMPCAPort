import React from "react";
import { RawZoomHelpers } from "victory";
import '../css/style.css'
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
            cityList,
            districtList,
            partyList,
            zipCodeList,
            formLabel,
            formFields,
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

        let stateRepObj = {...stateRep};

        stateRepObj[e.target.name] = e.target.value;

        setStateRep(stateRepObj);
    }


    const searchParams = (e) =>{

        let searchObj = {...search};
        console.log(search);

        searchObj[e.target.name] = e.target.value;

        setIndex(searchObj);

        setSearch(searchObj);

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
                    <select className={'form-control'}
                            name={'city'}
                            onChange={(e)=>inputChange(e)}
                    >
                        <option>Select a City</option>
                        {
                            cityList?
                            cityList.map((city, index)=>{

                                return(
                                    <option key={index} value={city.cityId}>{city.name}</option>
                                )
                            })
                            : null
                        }
                    </select>
                </div>
                <div className={'col form-group border'}>
                    <label>Zip Code</label>
                    <div>
                        <div className="">
                            <input name={'name'} className={'form-control w-50 d-inline-block'} onChange={(e)=>searchParams(e)}/>
                            <select name='range' className="form-control w-25 d-inline-block" onChange={(e)=>searchParams(e)}>
                                <option value={'10'}>10</option>
                                <option value={'25'}>25</option>
                                <option value={'50'}>50</option>
                            </select>
                            <button className="btn bg-primary" onClick={(e)=>zipCodeSearch(e)}>Search</button>
                        </div>
                    </div>
                    <div className={'overflow-auto mt-3 mb-3 height200'}>
                        
                        {
                            zipCodeList?
                            zipCodeList.map((zipCode, index)=>{

                                return(
                                    <div key={index}>
                                        <button value={zipCode.zipCodeId}>{zipCode.name}</button>
                                    </div>
                                        
                                )
                            })
                                : null
                        }
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
                    <select className={'form-control'}
                            name={'district'}
                            onChange={(e)=>inputChange(e)}
                    >
                        <option>Select a District</option>
                        {
                            districtList?
                                districtList.map((district, index)=>{

                                    return(
                                        <option key={index} value={repType? district.houseDistrictId: district.senateDistrictId}>{district.name}</option>
                                    )
                                })
                                : null
                        }
                    </select>
                </div>
            </div>
        </div>
    )
}

export default StateRepForm