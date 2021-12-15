import React from "react";
import produce from "immer";

const {legislativeList, governorList} = require('../helper/generalFunctions')

const LegislationForm = props =>{

    const { legislation,
            setLegislation,
            formLabel,
            handler
            } = props;

    const inputChange = (e) =>{

        switch (e.target.name) {
            case 'name':
                console.log(e.target.value)
                return setLegislation(produce(legislation, draft => {
                        draft.name = e.target.value;
                    })
                );

            case 'description':
                console.log(e.target.value)
                return setLegislation(produce(legislation, draft=>{
                    draft.description = e.target.value;
                })
            );

            case 'houseStatus':
                console.log(e.target.value)
                return setLegislation(produce(legislation, draft=>{
                    draft.houseStatus = e.target.value;
                })
            );

            case 'houseStatusDate':
                console.log(e.target.value);
                return setLegislation(produce(legislation, draft=>{
                    draft.houseStatusDate = e.target.value;
                })
            );

            case 'senateStatus':
                console.log(e.target.value);
                return setLegislation(produce(legislation, draft=>{
                    draft.senateStatus = e.target.value;
                })
            );

            case 'senateStatusDate':
                console.log(e.target.value);
                return setLegislation(produce(legislation, draft=>{
                    draft.senateStatusDate = e.target.value;
                })
            );

            case 'governorStatus':
                console.log(e.target.value);
                return setLegislation(produce(legislation, draft=>{

                    draft.governorStatus = e.target.value;
                })
            )

            case 'governorStatusDate':
                console.log(e.target.value);
                return setLegislation(produce(legislation, draft=>{
                    draft.governorStatusDate = e.target.value;
                })
            )

            default:
                return legislation;
        }
    }



    return(
        <form className={'w-50 m-auto'} onSubmit={handler}>
            <h1 className={'mb-4'}>{formLabel}</h1>
            <div className={'form-group mb-4 pt-3'}>
                <label>Name</label>
                <input type="text" name={'name'} value={legislation.name} className={'form-control'} onChange={(e)=>inputChange(e)}/>
            </div>
            <div className={'row'}>
                <div className={'col'}>
                    <div className={'form-group'}>
                        <label>House Status</label>
                        <select className={'form-control'} name={'houseStatus'} onChange={(e)=>inputChange(e)}>
                            <option value={''}>Select a status</option>
                            {
                                legislativeList.map((legStatus, index)=>{

                                    return(
                                        <option key={index} value={legStatus}>{legStatus}</option>
                                    )
                                })
                            }
                        </select>
                    </div>
                    <div className={'form-group'}>
                        <label>As Of:</label>
                        <input type="date" className={'form-control'} name={'houseStatusDate'} onChange={(e)=>inputChange(e)}/>
                    </div>
                </div>
                <div className={'col'}>
                    <div className={'form-group'}>
                        <label>Senate Status</label>
                        <select className={'form-control'} name={'senateStatus'} onChange={(e)=>inputChange(e)}>
                            <option value={''}>Select a status</option>
                            {
                                legislativeList.map((legStatus, index)=>{

                                    return(
                                        <option key={index} value={legStatus}>{legStatus}</option>
                                    )
                                })
                            }
                        </select>
                    </div>
                    <div>
                        <label htmlFor="">As of:</label>
                        <input type={"date"} className={'form-control'} name={'senateStatusDate'} onChange={(e)=>inputChange(e)}/>
                    </div>
                </div>
                <div className={'col'}>
                    <div className={'form-group'}>
                        <label>Governor Status</label>
                        <select className={'form-control'} name={'governorStatus'} onChange={(e)=>inputChange(e)}>
                            <option value={''}>Select a status</option>
                            {
                                governorList.map((govStatus, index)=>{

                                    return(
                                        <option key={index} value={govStatus}>{govStatus}</option>
                                    )
                                })
                            }
                        </select>
                    </div>
                    <div className={'form-group'}>
                        <label>As Of:</label>
                        <input type={'date'} className={'form-control'} name={'governorStatusDate'} onChange={(e)=>inputChange(e)}/>
                    </div>
                </div>
            </div>
            <div className={'form-group mb-3 pt-3'}>
                <label>Description</label>
                <textarea cols={4} className={'form-control'} value={legislation.description} rows={10} placeholder={'Add Description...'} name={'description'} onChange={(e)=>inputChange(e)} ></textarea>
            </div>
            <button>Save Legislation</button>
        </form>
    )
}

export default LegislationForm;
