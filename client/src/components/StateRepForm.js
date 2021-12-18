import React from "react";
import '../css/style.css'




const StateRepForm = props =>{

    const {formButton,
           number,
           formLabel,
           formFields,
           stateRepInfo,
           dispatchStateRepInfo,
           handler
    } = props;


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

    const canSubmit = (stateRepInfo) =>{

        let isDisabled = true;

        if(stateLength(stateRepInfo.stateRep.firstName, 3, 50, true)){
            if(stateLength(stateRepInfo.stateRep.lastName, 3, 50, true)){
                if(stateLength(stateRepInfo.stateRep.email, 0, 150, false)){
                    if(stateLength(stateRepInfo.stateRep.picture, 5, 250, false)){
                        if(stateLength(stateRepInfo.stateRep.capitolRoom,0,8, false)){
                            if(stateLength(stateRepInfo.stateRep.streetAddress, 5, 150, false)){

                                isDisabled = false;
                            }
                        }
                    }
                }
            }
        }

        return isDisabled;
    }



    return(
        <form className={'w-50 m-auto mt-5 pt-5'} onSubmit={handler}>
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
            <div className={'row'}>
                <div className={' col form-group'}>
                    <label>Address:</label>
                    <input type="text" className={'form-control'}
                           name={formFields.STATE_REP_ADDRESS}
                           value={stateRepInfo.stateRep.streetAddress}
                           onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Capital Office</label>
                    <input type="text" className={'form-control'}
                    name={formFields.STATE_REP_CAPITAL_RM}
                    value={stateRepInfo.stateRep.capitolRoom}
                    onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}
                    />
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
                    <label>County</label>
                       <div className={'height100 overflow-auto mt-2'}>

                           {
                               stateRepInfo?
                                   stateRepInfo.formData.countiesList.map((county, index)=>{

                                       return(
                                           <div key={index} className={'d-flex justify-content-start ps-5'}>
                                               <input value={county.countyId}
                                                      className={'form-check-input ps-2'}
                                                      type={'checkbox'}
                                                      checked={stateRepInfo.stateRep.counties.includes(county.countyId)}
                                                      name={formFields.STATE_REP_COUNTIES}
                                                      onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: {checked: e.target.checked, value: e.target.value} })}
                                               />
                                               <label  className={'form-check-label ps-2'}>{county.name}</label>
                                           </div>

                                       )
                                   })
                                   : null
                           }
                       </div>
                </div>
                <div className={'col'}>
                    <label>District</label>
                    <select className={'form-control'}
                            name={formFields.STATE_REP_DISTRICT}
                            onChange={(e)=>dispatchStateRepInfo({type: e.target.name, payload: e.target.value})}>
                        <option>Select a District</option>
                        {
                            stateRepInfo?
                                stateRepInfo.formData.districtList.map((district, index)=>{

                                    return(
                                        <option key={index} value={district.houseDistrictId}>{district.name}</option>
                                    )
                                })
                                : null
                        }
                    </select>
                </div>
            </div>
            <button disabled={canSubmit(stateRepInfo)}>{formButton}</button>
        </form>
    )
}

export default StateRepForm