import React from "react";




const FederalRepForm = props =>{
   const { formLabelRep,
           formLabelSen,
           federalRepInfo,
           dispatchFederalRep,
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
                           value={federalRepInfo.repType? federalRepInfo.rep.firstName : federalRepInfo.sen.firstName}
                           onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Last Name</label>
                    <input type="text"
                           className={'form-control'}
                           name={formFields.FEDERAL_REP_LAST_NAME}
                           value={federalRepInfo.repType? federalRepInfo.rep.lastName : federalRepInfo.sen.lastName}
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
                           value={federalRepInfo.repType? federalRepInfo.rep.email : federalRepInfo.sen.email}
                           onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label>Picture</label>
                    <input type="text"
                           className={'form-control'}
                           name={formFields.FEDERAL_REP_PICTURE}
                           value={federalRepInfo.repType? federalRepInfo.rep.picture : federalRepInfo.sen.picture}
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
                           value={ federalRepInfo.repType? federalRepInfo.rep.website : federalRepInfo.sen.website}
                           onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    />
                </div>
                <div className={'col form-group'}>
                    <label hidden={!federalRepInfo.repType}>District</label>
                    <select className={'form-control'}
                            hidden={!federalRepInfo.repType}
                            disabled={!federalRepInfo.repType}
                            name={formFields.FEDERAL_REP_DISTRICT}
                            onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                    >
                        {federalRepInfo.repType?  <option value="">Choose a District</option> : <option value="">Not Applicable!!</option>}

                        {
                            federalRepInfo?
                                federalRepInfo.formData.districtList.map((district, index)=>{

                                    return(
                                        <option key={index} value={district.congressionalDistrictId}>{district.name}</option>
                                    )
                                })
                                : null
                        }
                    </select>
                </div>
            </div>
            {
                !federalRepInfo.repType?
                    <div className={'row'}>
                        <div className={'col form-group'}>
                            <label>Elected</label>
                            <input type="date"
                                   className={'form-control'}
                                   name={formFields.FEDERAL_REP_ELECTED}
                                   value={federalRepInfo.sen.elected}
                                   pattern={'yyyy-MM-dd'}
                                   onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                        <div className={'col form-group'}>
                            <label>Next Election</label>
                            <input type="date"
                                   className={'form-control'}
                                   name={formFields.FEDERAL_REP_NEXT_ELECTION}
                                   value={federalRepInfo.sen.nextElection}
                                   pattern={'yyyy-MM-dd'}
                                   onChange={(e)=>dispatchFederalRep({type: e.target.name, payload: e.target.value})}
                            />
                        </div>
                    </div>
                    : null

            }

        </form>
    )
}

export default FederalRepForm;