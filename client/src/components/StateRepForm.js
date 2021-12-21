import React from "react";
import '../css/style.css'




const StateRepForm = props =>{

    const {formButton,
           number,
           formLabel,
           formFields,
           stateRepInfo,
           dispatchStateRepInfo,
           handler,
           fieldLengthErrorMessages,
           fieldLength,
           emailValidation,
           fieldLengthNotRequired,
           repType
    } = props;






    return(
        <form className={'w-50 m-auto mt-3 pt-3'} onSubmit={handler}>
            <h1>{formLabel}</h1>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>First Name</label>
                    <input type="text"
                           className={'form-control'}
                           name={formFields.STATE_REP_FIRST_NAME}
                           value={ stateRepInfo.stateRep.firstName}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                    {fieldLength(3, 50, stateRepInfo.stateRep.firstName)? <span className={'text-danger'}>{fieldLengthErrorMessages(3, 50, formFields.STATE_REP_FIRST_NAME)}</span>  : null}
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text" className={'form-control'}
                           name={formFields.STATE_REP_LAST_NAME}
                           value={stateRepInfo.stateRep.lastName}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                    {fieldLength(3, 50, stateRepInfo.stateRep.lastName)? <span className={'text-danger'}>{fieldLengthErrorMessages(3, 50, formFields.STATE_REP_LAST_NAME)}</span> : null }
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Email</label>
                    <input type="email" className={'form-control'}
                           name={formFields.STATE_REP_EMAIL}
                           value={stateRepInfo.stateRep.email}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                    {!fieldLengthNotRequired(0, 150, stateRepInfo.stateRep.email)? <div className={'text-danger'}>{fieldLengthErrorMessages(0, 150, formFields.STATE_REP_EMAIL)}</div> : null}
                    {!emailValidation(stateRepInfo.stateRep.email)? <div className={'text-danger'}>Please enter a valid email</div> : null}
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text" className={'form-control'}
                        name={formFields.STATE_REP_PICTURE}
                        value={stateRepInfo.stateRep.picture}
                        onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                    {!fieldLengthNotRequired(5, 250, stateRepInfo.stateRep.picture)? <span className={'text-danger'}>{fieldLengthErrorMessages(5, 250, formFields.STATE_REP_PICTURE)}</span> : null}
                </div>
            </div>
            <div className={'row'}>
                <div className={' col form-group'}>
                    <label>Address:</label>
                    <input type="text" className={'form-control'}
                           name={formFields.STATE_REP_ADDRESS}
                           value={stateRepInfo.stateRep.streetAddress}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                    {!fieldLengthNotRequired(5, 150, stateRepInfo.stateRep.streetAddress)? <span className={'text-danger'}>{fieldLengthErrorMessages(5, 150, formFields.STATE_REP_ADDRESS)}</span> : null}
                </div>
                <div className={'col form-group'}>
                    <label>Capital Office</label>
                    <input type="text" className={'form-control'}
                    name={formFields.STATE_REP_CAPITAL_RM}
                    value={stateRepInfo.stateRep.capitolRoom}
                    onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                    {!fieldLengthNotRequired(0, 8, stateRepInfo.stateRep.capitolRoom)? <span className={'text-danger'}>{fieldLengthErrorMessages(5, 150, formFields.STATE_REP_CAPITAL_RM)}</span> : null}
                </div>
            </div>

            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>City:</label>
                    <select className={'form-control'}
                            name={formFields.STATE_REP_CITY}
                            onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    >
                        <option>Select a City</option>
                        {
                            stateRepInfo?
                            stateRepInfo.formData.citiesList.map((city, index)=>{

                                return(
                                    <option key={index} value={city.cityId}>{city.name}</option>
                                )
                            })
                            : null
                        }
                    </select>
                </div>
                <div className={'col form-group'}>
                    <label>Zip Code</label>
                    <select className={'form-control'}
                            name={formFields.STATE_REP_ZIP_CODE}
                            onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    >
                        <option> Select a Zip Code</option>
                        {
                            stateRepInfo?
                            stateRepInfo.formData.zipCodeList.map((zipCode, index)=>{

                                return(
                                    <option key={index} value={zipCode.zipCodeId}>{zipCode.name}</option>
                                )
                            })
                                : null
                        }
                    </select>
                </div>
            </div>
            <div className={'row mb-3 pb-2'}>
                <div className={'col'}>
                    <label>Political Party</label>
                    <select className={'form-control'}
                            name={formFields.STATE_REP_PARTY}
                            onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    >
                        <option value="">Choose a party</option>
                      {
                            stateRepInfo?
                            stateRepInfo.formData.partiesList.map((party, index)=>{

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
                            name={ repType? formFields.STATE_REP_DISTRICT: formFields.STATE_SEN_DISTRICT}
                            onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}>
                        <option>Select a District</option>
                        {
                            stateRepInfo?
                                stateRepInfo.formData.districtList.map((district, index)=>{

                                    return(
                                        <option key={index} value={repType? district.houseDistrictId: district.senateDistrictId}>{district.name}</option>
                                    )
                                })
                                : null
                        }
                    </select>
                </div>
            </div>
        </form>
    )
}

export default StateRepForm