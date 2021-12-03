import React from "react";





const SiteForm = props =>{

   const {healthCenterInfo, setHealthCenterInfo, formProgression} = props;


    const checkObj =(targetValue, array, type)=>{
        console.log(targetValue.name)
        for(let item of array){

            if( item[type] === targetValue.value){

                healthCenterInfo['newSite'][targetValue.name] = {'id': item[type], 'name': item['name']};

                return
            }
        }

    }


    const siteInputChange = (e) =>{
       e.preventDefault();

       let healthCenterInfoObj = {...healthCenterInfo};

       healthCenterInfoObj['newSite'][e.target.name] = e.target.value;


       if(e.target.name === 'city'){
           checkObj(e.target, healthCenterInfoObj['cities'], 'cityId')
       }

       if(e.target.name === 'county'){
        checkObj(e.target, healthCenterInfoObj['counties'], 'countyId')
       }

       if(e.target.name === 'zipCode'){

           checkObj(e.target, healthCenterInfoObj['zipCodes'], 'zipCodeId')
       }

        console.log(healthCenterInfo)
       setHealthCenterInfo(healthCenterInfoObj);

    }

    const addSiteToList = (e) =>{
        e.preventDefault()

        let healthCenterInfoObj = {...healthCenterInfo};

        healthCenterInfoObj['newSites'].push(healthCenterInfoObj['newSite']);

        healthCenterInfoObj['newSite'] = {
            name: ''.trim().toLowerCase(),
            streetAddress: ''.trim().toLowerCase(),
            city: {id: '', name: ''},
            county: {id: '', name: ''},
            zipCode: {id: '', name: ''},
            healthCenter: {id: '', name: ''},
            nmHouseDistrict: {id: '', name: ''},
            senateDistrict:{id: '', name: ''},
            congressionalDistrict: {id: '', name: ''},
        }
        console.log(healthCenterInfoObj)
        setHealthCenterInfo(healthCenterInfoObj);
    }




// Site form component adds Site to a state list until saved to DB

    return(
        <div className={'w-50 m-auto d-inline-block'}>
            <div className={'align-top'}>
                <form >
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Site name</label>
                            <input type="text"  className={'form-control w-50 m-auto'} value={healthCenterInfo.newSite.name} name={'name'} onChange={(e)=>siteInputChange(e)}/>
                        </div>
                    </div>
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Street Address</label>
                            <input type="text" className={'form-control'} value={healthCenterInfo.newSite.streetAddress} name={'streetAddress'} onChange={(e)=>siteInputChange(e)} />
                        </div>
                        <div className={'col'}>
                            <label>City</label>
                            <select className={'form-control'} name={'city'} value={healthCenterInfo.newSite.city.id} onChange={(e)=>siteInputChange(e)} >
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
                                <select className={'form-control'} name={'county'} value={healthCenterInfo.newSite.county.id} onChange={(e)=>siteInputChange(e)}>
                                    <option>Choose a County</option>
                                    {
                                        healthCenterInfo?
                                            healthCenterInfo.counties.map(({countyId, name}, index)=>{

                                                return(
                                                    <option key={index} value={countyId}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Zip Code</label>
                                <select name={'zipCode'} className={'form-control'} value={healthCenterInfo.newSite.zipCode.id} onChange={(e)=>siteInputChange(e)}>
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
                                <label>Service</label>
                                {

                                }
                            </div>
                            <div className={'col'}>

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
                    <button>Finish</button>
                </form>
            </div>
        </div>



    )
}

export default SiteForm