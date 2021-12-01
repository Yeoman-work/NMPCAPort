import React from "react";





const SiteForm = props =>{

   const {healthCenterInfo, setHealthCenterInfo, formProgression} = props;



    const siteInputChange = (e) =>{
       e.preventDefault();


       let healthCenterInfoObj = {...healthCenterInfo};

       healthCenterInfoObj['newSite'][e.target.name] = e.target.value;

       setHealthCenterInfo(healthCenterInfoObj);

    }

    const addSiteToList = (e) =>{
        e.preventDefault()

        let healthCenterInfoObj = {...healthCenterInfo};

        healthCenterInfoObj['newSites'].push(healthCenterInfoObj['newSite']);

        healthCenterInfoObj['newSite'] = {
            name: ''.trim().toLowerCase(),
                streetAddress: ''.trim().toLowerCase(),
                cityId: {},
                countyId: {},
                zipCodeId: {},
                healthCenterId: {},
                nmHouseDistrictId: {},
                senateDistrictId: {},
                congressionalDistrictId: {},
        }
        console.log(healthCenterInfoObj)
        setHealthCenterInfo(healthCenterInfoObj);
    }



    return(

        <form className={'w-50 m-auto'}>
            <div className={'row'}>
                <div className={'col'}>
                    <span>Health Center</span>
                    <h6>{healthCenterInfo.newHealthCenter.name}</h6>
                </div>
                <div className={'col'}>
                    <label>Site name</label>
                    <input type="text"  className={'form-control'} value={healthCenterInfo.newSite.name} name={'name'} onChange={(e)=>siteInputChange(e)}/>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <label>Street Address</label>
                    <input type="text" className={'form-control'} value={healthCenterInfo.newSite.streetAddress} name={'streetAddress'} onChange={(e)=>siteInputChange(e)} />
                </div>
                <div className={'col'}>
                    <label>City</label>
                    <select className={'form-control'} name={'cityId'} value={healthCenterInfo.newSite.cityId.name} onChange={(e)=>siteInputChange(e)} >
                        <option>Choose a City</option>
                        {
                            healthCenterInfo?
                            healthCenterInfo.cities.map(({name, cityId}, index)=>{

                                return(
                                    <option key={index} value={cityId}>{name}</option>
                                )
                            })
                                : null
                        }
                    </select>
                </div>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>County</label>
                        <select className={'form-control'} name={'countyId'} value={healthCenterInfo.newSite.countyId} onChange={(e)=>siteInputChange(e)}>
                            <option>Choose a County</option>
                            {
                                healthCenterInfo?
                                healthCenterInfo.counties.map((county, index)=>{

                                    return(
                                        <option key={index} value={county.countyId}>{county.name}</option>
                                    )
                                })
                                    : null
                            }
                        </select>
                    </div>
                    <div className={'col'}>
                        <label>Zip Code</label>
                        <select name={'zipCodeId'} className={'form-control'} value={healthCenterInfo.newSite.zipCodeId} onChange={(e)=>siteInputChange(e)}>
                            <option>Select a zip Code</option>
                            {
                                healthCenterInfo?
                                healthCenterInfo.zipCodes.map((zipCode, index)=>{

                                    return(
                                        <option key={index} value={zipCode.zipCodeId}>{zipCode.name}</option>
                                    )
                                })
                                    : null
                            }
                        </select>
                    </div>
                </div>
                <div className={'row'}>
                    <div className={'col'}>
                        <label> NM House district</label>
                        <select name={'nmHouseDistrictId'}
                                className={'form-control'}
                                onChange={(e)=>siteInputChange(e)}
                        >
                            <option>Choose a District</option>
                            {
                                healthCenterInfo?
                                healthCenterInfo.nmHouseDistricts.map((district, index)=>{

                                    return(
                                        <option key={index} value={district.houseDistrictId}>{district.name}</option>
                                    )
                                })
                                    : null
                            }
                        </select>
                    </div>
                    <div className={'col'}>
                        <label>Senate District</label>
                        <select name={'senateDistrictId'}
                                className={'form-control'}
                                onChange={(e)=>siteInputChange(e)}
                        >
                            <option >Choose a Senate District</option>
                            {
                                healthCenterInfo?
                                healthCenterInfo.senateDistricts.map((district, index)=>{

                                    return(
                                        <option value={district.senateDistrictId}>{district.name}</option>
                                    )
                                })
                                    : null
                            }
                        </select>
                    </div>
                    <div className={'col'}>
                        <label>Congressional District</label>
                        <select name={'congressionalDistrictId'}
                                className={'form-control'}
                                onChange={(e)=>siteInputChange(e)}
                        >
                            <option>Choose a district</option>
                            {
                                healthCenterInfo?
                                healthCenterInfo.congressionalDistricts.map((district, index)=>{

                                    return(
                                        <option value={district.congressionalDistrictId}>{district.name}</option>
                                    )
                                })
                                    : null
                            }
                        </select>
                    </div>
                </div>
            </div>
            <button name={'decrement'} onClick={(e)=>formProgression(e)}>Back</button>
            <button onClick={(e)=>addSiteToList(e)}>Add Another Site</button>
            <button>Next</button>
        </form>

    )
}

export default SiteForm