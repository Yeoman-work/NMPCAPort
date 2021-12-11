import React from "react";





const SiteForm = props =>{

   const {siteState,
          siteDataFields,
          dispatchSite,
          formData,
          healthCenterHandler
   } = props;

   const InList = (List, id) =>{
       let isPresent = false;

       for(let item of List){

           if(item['id'] === id){
               isPresent = true;
           }
       }

       return isPresent;
   }

   const addSite = (siteState, formData) =>{

       let disable = true;


       if(siteState.name.length >= 3 && siteState.name.length <= 50){
           if(siteState.streetAddress.length >= 5 && siteState.streetAddress.length <= 100 ){
               if(formData.city_list) {

                   for (let city of formData.city_list) {

                       if (city.cityId === siteState.city.id) {

                           if(formData.county_list){

                               for(let county of formData.county_list){
                                   console.log('this is it')
                                   if(county.countyId === siteState.county.id){

                                       if(formData.zipCode_list){

                                           for(let zipCode of formData.zipCode_list){

                                               if(zipCode.zipCodeId === siteState.zipCode.id){
                                                   disable = false
                                                   break;
                                               }
                                           }

                                       }
                                   }
                               }
                           }
                       }
                   }
               }
           }

       }else{

           disable = true;
       }

       return disable;
   }

   const saveHealthCenter = (formData) =>{
       let disable = true;
       const siteList = formData.newSiteFormat.length;


       if(siteList > 0) {

           disable = false;

       }

       return disable;
   }


// Site form component adds Site to a state list until saved to DB

    return(
        <div className={'w-50 m-auto d-inline-block'}>
            <div className={'align-top'}>
                <form >
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Site name</label>
                            <input type="text"
                                   className={'form-control w-50 m-auto'}
                                   value={siteState.name}
                                   name={siteDataFields.SITE_NAME}
                                   onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                    </div>
                    <div className={'row'}>
                        <div className={'col'}>
                            <label>Street Address</label>
                            <input type="text"
                                   className={'form-control'}
                                   value={siteState.streetAddress}
                                   name={siteDataFields.STREET_ADDRESS}
                                   onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                        <div className={'col'}>
                            <label>City</label>
                            <select className={'form-control'}
                                    name={siteDataFields.CITY}
                                    value={siteState.city.id + '/' + siteState.city.name}
                                    onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})} >
                                <option>Choose a City</option>
                                {
                                    formData?
                                        formData.city_list .map(({name, cityId}, index)=>{

                                            return(
                                                <option key={index} value={cityId + '/' + name}>{name}</option>
                                            )
                                        })
                                        : null
                                }
                            </select>
                        </div>
                        <div className={'row'}>
                            <div className={'col'}>
                                <label>County</label>
                                <select className={'form-control'}
                                        name={siteDataFields.COUNTY}
                                        value={siteState.county.id + '/' + siteState.county.name}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}>
                                    <option>Choose a County</option>
                                    {

                                        formData?
                                            formData.county_list.map(({countyId, name}, index)=>{

                                                return(
                                                    <option key={index} value={countyId + '/' + name}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Zip Code</label>
                                <select name={siteDataFields.ZIP_CODE}
                                        className={'form-control'}
                                        value={siteState.zipCode.id + '/' + siteState.zipCode.name}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}>
                                    <option>Select a zip Code</option>
                                    {
                                        formData?
                                            formData.zipCode_list.map(({zipCodeId, name}, index)=>{

                                                return(
                                                    <option key={index} value={zipCodeId + '/' + name}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                        </div>
                        <div className={'row mb-2 p-3'}>
                            <div className={'col overflow-auto'}>
                                <label>Service</label><br/>
                                {
                                    formData?
                                        formData.service_list.map(({serviceId, name}, index)=>{

                                            return(
                                                    <div key={index} className={'d-flex justify-content-start'}>

                                                        <label htmlFor={serviceId}>
                                                            <input type="checkbox"
                                                                   itemID={serviceId}
                                                                   name={siteDataFields.SITE_SERVICES}
                                                                   className={'form-check-input me-2'}
                                                                   checked={InList(siteState.service, serviceId)}
                                                                   value={serviceId}
                                                                   onChange={(e)=>dispatchSite({type: e.target.name,
                                                                       payload: {target : e.target, name: name}})}
                                                            />
                                                            {name}</label>
                                                    </div>
                                            )
                                        })
                                        : null
                                }

                            </div>
                            <div className={'col overflow-auto'}>
                                <label>Funding</label>
                                {
                                    formData?
                                        formData.funding_list.map(({ name, fundId }, index)=>{

                                            return(
                                                <div key={index} className={'d-flex justify-content-start'}>
                                                    <label htmlFor={fundId}>
                                                        <input
                                                            type="checkbox"
                                                            name={siteDataFields.SITE_FUNDING}
                                                            className={'form-check-input me-2'}
                                                            checked={InList(siteState.funding, fundId)}
                                                            value={fundId}
                                                            itemID={fundId}
                                                            onChange={(e)=>dispatchSite({type: e.target.name,
                                                                payload: {target: e.target , name: name}})}
                                                        />
                                                        {name}</label>
                                                </div>
                                            )
                                        })
                                        : null
                                }
                            </div>
                        </div>
                        <div className={'row'}>
                            <div className={'col'}>
                                <label> NM House district</label>
                                <select name={siteDataFields.NM_HOUSE_DISTRICT}
                                        value={siteState.nmHouseDistrict.id + '/' + siteState.nmHouseDistrict.name}
                                        className={'form-control'}
                                        onChange={(e)=>dispatchSite({type: e.target.name,
                                                                                        payload: e.target.value})}
                                >
                                    <option>Choose a District</option>
                                    {
                                        formData?
                                            formData.nmHouse_districts.map(({houseDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={houseDistrictId + '/' + name}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Senate District</label>
                                <select name={siteDataFields.SENATE_DISTRICT}
                                        className={'form-control'}
                                        value={siteState.senateDistrict.id + '/' + siteState.senateDistrict.name}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                                >
                                    <option >Choose a Senate District</option>
                                    {
                                        formData?
                                            formData.senate_districts.map(({senateDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={senateDistrictId + '/' + name}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                            <div className={'col'}>
                                <label>Congressional District</label>
                                <select name={siteDataFields.CONGRESSIONAL_DISTRICT}
                                        className={'form-control'}
                                        value={siteState.congressionalDistrict.id + '/' + siteState.congressionalDistrict.name}
                                        onChange={(e)=>dispatchSite({type: e.target.name, payload: e.target.value})}
                                >
                                    <option>Choose a district</option>
                                    {
                                        formData?
                                            formData.congressional_districts.map(({congressionalDistrictId, name}, index)=>{

                                                return(
                                                    <option key={index} value={congressionalDistrictId + '/' + name}>{name}</option>
                                                )
                                            })
                                            : null
                                    }
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
                <button onClick={(e)=>dispatchSite({type: siteDataFields.FORM_DECREMENT})}>Back</button>
                <button disabled={addSite(siteState, formData)} onClick={(e)=>dispatchSite({type: siteDataFields.NEW_SITE_LIST, payload: {...siteState}, option: e })}>Add Another Site</button>
                <button disabled={saveHealthCenter(formData)} onClick={(e)=>healthCenterHandler(e)}>Finish</button>
            </div>
        </div>



    )
}

export default SiteForm