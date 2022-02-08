import React from "react";




const FederalRepForm = props =>{
    const {
            formLabel,
            federalRepInfo,
            setFederalRepInfo,
            dispatchFederalRep,
            congressionalDistrictList,
            partiesList,
            formFields,
            repType
            } = props;


    return(
        <form className={'w-75 mt-5 p-3 m-auto'}>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>First Name</label>
                    <input type="text"
                            className={'form-control'}
                            name={formFields.FEDERAL_REP_FIRST_NAME}
                            value={federalRepInfo.repType? federalRepInfo.firstName : federalRepInfo.firstName}
                            onChange={}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text"
                            className={'form-control'}
                            name={formFields.FEDERAL_REP_LAST_NAME}
                            value={federalRepInfo.repType? federalRepInfo.lastName : federalRepInfo.lastName}
                            onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Email</label>
                    <input type="email"
                        className={'form-control'}
                        name={formFields.FEDERAL_REP_EMAIL}
                        value={federalRepInfo.repType? federalRepInfo.email : federalRepInfo.email}
                        onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text"
                        className={'form-control'}
                        name={formFields.FEDERAL_REP_PICTURE}
                        value={federalRepInfo.repType? federalRepInfo.picture : federalRepInfo.picture}
                        onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
            </div>
            <div className={'row'}>
                <div className={'col form-group'}>
                    <label>Website</label>
                    <input type="text"
                        className={'form-control'}
                        name={formFields.FEDERAL_REP_WEBSITE}
                        value={ federalRepInfo.repType? federalRepInfo.website : federalRepInfo.website}
                        onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label hidden={!repType}>District</label>
                    <select className={'form-control'}
                            hidden={!repType}
                            disabled={!repType}
                            name={formFields.FEDERAL_REP_DISTRICT}
                            
                    >
                        {repType?  <option value="">Choose a District</option> : <option value="">Not Applicable!!</option>}

                        {
                            congressionalDistrictList?
                                congressionalDistrictList.map((district, index)=>{

                                    return(
                                        <option key={index} value={district.congressionalDistrictId}>{district.name}</option>
                                    )
                                })
                                : null
                        }
                    </select>
                    <div className={'row'}>
                        <div className={'col form-group'}>
                            <label hidden={federalRepInfo.repType}>Elected</label>
                            <input type="date"
                                hidden={repType}
                                className={'form-control'}
                                name={formFields.FEDERAL_REP_ELECTED}
                                value={federalRepInfo.elected}
                                pattern={'yyyy-MM-dd'}
                            />
                        </div>

                    </div>
                </div>
            </div>
            <div className={'form-group'}>
                <label>Party</label>
                <select className={'form-control w-50 m-auto'}
                        name={formFields.PARTY_AFFILIATION}
                        
                >
                    <option value="">Choose a Party</option>
                    {
                        partiesList?
                            partiesList.map((party, index)=>{

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