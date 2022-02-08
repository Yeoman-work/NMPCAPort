import React from "react";
const {federalValidation} = require('../helper/FederalRepFunctions')



const FederalRepForm = props =>{
    const {
            formLabel,
            federalRepInfo,
            setFederalRepInfo,
            dispatchFederalRep,
            congressionalDistrictList,
            partyList,
            districtList,
            formFields,
            repType
            } = props;

    const inputChange = (e, federalRepInfo, setFederalRepInfo) =>{
        console.log('in here');
        console.log('name ' + e.target.name);
        console.log('value ' + e.target.value);
        console.log(federalRepInfo);
        setFederalRepInfo(federalValidation(e, federalRepInfo));
    }

    return(
        <form className={'w-75 mt-5 p-3 m-auto'}>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>First Name</label>
                    <input type="text"
                            className={'form-control'}
                            name={'firstName'}
                            value={federalRepInfo.firstName}
                            onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text"
                            className={'form-control'}
                            name={'lastName'}
                            value={federalRepInfo.lastName}
                            onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                    />
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Email</label>
                    <input type="email"
                        className={'form-control'}
                        name={'email'}
                        value={federalRepInfo.email}
                        onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text"
                        className={'form-control'}
                        name={'picture'}
                        value={federalRepInfo.picture}
                        onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                    />
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Website</label>
                    <input type="text"
                        className={'form-control'}
                        name={'website'}
                        value={federalRepInfo.website}
                        onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                    />
                </div>

                

                    {
                        repType?
                        <div className={'col form-group'}>
                            <label hidden={!repType}>District</label>
                            <select className={'form-control'}
                                    hidden={!repType}
                                    disabled={!repType}
                                    name={'district'}
                                    value={federalRepInfo.district}
                                    onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                                    
                            >
                                    <option value="">Choose a District</option>

                                {
                                    districtList?
                                        districtList.map((district, index)=>{

                                            return(
                                                <option key={index} value={district.congressionalDistrictId}>{district.name}</option>
                                            )
                                        })
                                        : null
                                }
                            </select>
                        </div>
                        :
                        <div className={'col form-group'}>
                            <label hidden={repType}>Elected</label>
                            <input type="date"
                                hidden={repType}
                                className={'form-control'}
                                name={'elected'}
                                value={federalRepInfo.elected}
                                pattern={'yyyy-MM-dd'}
                                onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                            />
                        </div>
                    }
                    
                    
                        

                    
                
            </div>
            <div className={'form-group'}>
                <label>Party</label>
                <select className={'form-control w-50 m-auto'}
                        name={'party'}
                        value={federalRepInfo.party}
                        onChange={(e)=>inputChange(e, federalRepInfo, setFederalRepInfo)}
                        
                >
                    <option value="">Choose a Party</option>
                    {
                        partyList?
                            partyList.map((party, index)=>{

                                return(
                                    <option key={index} value={party.partyId}>{party.name}</option>
                                )
                            })
                            : null
                    }
                </select>
            </div>
        </form>
    )
}

export default FederalRepForm;