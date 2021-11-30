import React from "react";




const SiteForm = props =>{

   const {healthCenterInfo, setHealthCenterInfo} = props


    const inputChange = (e) =>{
       e.preventDefault();
       console.log('name ' +  e.target.name);
       console.log('value ' + e.target.value);

       let healthCenterInfoObj = {...healthCenterInfo};


    }

    return(
        <form>
            <div className={'row'}>
                <div className={'col'}>
                    <span>Health Center</span>
                    <h6>{healthCenterInfo.newHealthCenter.name}</h6>
                </div>
                <div className={'col'}>
                    <label>Site name</label>
                    <input type="text" className={'form-control'} name={'name'} value={healthCenterInfo.newSite.name}/>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <label>Street Address</label>
                    <input type="text" className={'form-control'} name={'streetAddress'} value={healthCenterInfo.newSite.streetAddress}/>
                </div>
                <div className={'col'}>
                    <label>City</label>
                    <select className={'form-control'} name={'cityId'} onChange={}>
                        <option>Choose a City</option>
                        {
                            healthCenterInfo.location.cities.map((city, index)=>{

                                return(
                                    <option key={index} value={city.cityId}>{city.name}</option>
                                )
                            })
                        }
                    </select>
                </div>
                <div className={'row'}>
                    <div className={'col'}>
                        <label>County</label>
                        <select className={'form-control'} name={'countyId'}>
                            {
                                healthCenterInfo.location.counties.map((county, index)=>{

                                    return(
                                        <option key={index} value={county.countyId}>{county.name}</option>
                                    )
                                })
                            }
                        </select>
                    </div>
                    <div className={'col'}>
                        <label>Zip Code</label>
                        <select name={'zipCodeId'} className={'form-control'}>
                            <option>Select a zip Code</option>
                            {
                                healthCenterInfo.location.zipCodes.map((zipCode, index)=>{

                                    return(
                                        <option key={index} value={zipCode.zipCodeId}></option>
                                    )
                                })
                            }
                        </select>
                    </div>
                </div>
                <div className={'row'}>
                    <div className={'col'}>
                        <label> NM House district</label>
                        <select name={''}></select>
                    </div>
                </div>
            </div>
            <div className={'row'}>
                <div className={'col'}>

                </div>
            </div>
        </form>
    )
}

export default SiteForm