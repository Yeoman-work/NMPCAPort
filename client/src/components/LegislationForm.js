import React from "react";
import produce from "immer";

const LegislationForm = props =>{

    const { legislation,
            setLegislation,
            formLabel
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

                return setLegislation(produce(legislation, draft=>{
                    draft.description = e.target.value;
                })
            );

            default:
                return legislation;
        }
    }

    return(
        <form className={'w-50 m-auto'}>
            <h1 className={'mb-4'}>{formLabel}</h1>
            <div className={'form-group'}>
                <label>Name</label>
                <input type="text" name={'name'} value={legislation.name} className={'form-control'} onChange={(e)=>inputChange(e)}/>
            </div>
            <div className={'form-group mb-3'}>
                <label>Description</label>
                <textarea cols={4} className={'form-control'} value={legislation.description} rows={10} placeholder={'Add Description...'} name={'description'} onChange={(e)=>inputChange(e)} ></textarea>
            </div>
            <button>Save Legislation</button>
        </form>
    )
}

export default LegislationForm;
