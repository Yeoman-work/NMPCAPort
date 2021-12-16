import React from "react";



const StateRepForm = props =>{

    const {formButton,
           formLabel,
           formFields,
           stateRepInfo,
           dispatchStateRepInfo
    } = props;


    return(
        <form action="">
            <h1>{formLabel}</h1>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>First Name</label>
                    <input type="text" className={'form-control'}
                           name={formFields.STATE_REP_FIRST_NAME}
                           value={stateRepInfo.stateRep.firstName}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}/>
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text" className={'form-control'}
                           name={formFields.STATE_REP_LAST_NAME}
                           value={stateRepInfo.stateRep.lastName}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
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
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text" className={'form-control'}
                        name={formFields.STATE_REP_PICTURE}
                        value={stateRepInfo.stateRep.picture}
                        onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}

                    />
                </div>
            </div>
            <div className={'form-group'}>
                <label>Address:</label>
                <input type="text" className={'form-control'}
                    name={formFields.STATE_REP_ADDRESS}
                    value={stateRepInfo.stateRep.streetAddress}
                    onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                />
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>City:</label>
                    <select className={'form-control'}>
                        <option>Select a City</option>
                        {
                            stateRepInfo?
                            stateRepInfo.formData.citiesList.map((city, index)=>{

                                return(
                                    <option value={city.cityId}>{city.name}</option>
                                )
                            })
                            : null
                        }
                    </select>
                </div>
                <div className={'col form-group'}>
                    <label>Zip Code</label>
                    <select className={'form-control'}>
                        <option> Select a Zip Code</option>
                        {
                            stateRepInfo?
                            stateRepInfo.formData.zipCodeList.map((zipCode, index)=>{

                                return(
                                    <option value={zipCode.zipCodeId}>{zipCode.name}</option>
                                )
                            })
                                : null
                        }
                    </select>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <label>County</label>
                       <div>
                           <option>Select a County</option>
                           {
                               stateRepInfo?
                                   stateRepInfo.formData.countiesList.map((county, index)=>{

                                       return(
                                           <label key={index} className={'form-check-label'}><input value={county.countyId}
                                                                                                    className={'form-check-input'}
                                                                                                    type={'checkbox'}/>
                                                                                                    {county.name}</label>
                                       )
                                   })
                                   : null
                           }
                       </div>
                </div>
                <div className={'col'}>
                    <label>District</label>
                    <option>Select a District</option>
                    {
                        stateRepInfo?
                            stateRepInfo.formData.districtList.map((district, index)=>{

                                return(
                                    <option value={district.houseDistrictId}>{district.name}</option>
                                )
                            })
                            : null
                    }
                </div>
            </div>
            <button>{formButton}</button>
        </form>
    )
}

export default StateRepForm