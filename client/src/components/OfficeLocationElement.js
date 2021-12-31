import React from "react";




const OfficeLocationElement = props =>{
    const { offices } = props;


    return(
        <div className={'height400 m-2 overflow-auto border w-75 m-auto mt-5 mb-2'}>
            {
                offices?
                offices.map((office, index)=>{

                    return(
                        <div key={index}
                            className={'mt-3 mb-3 border w-50 m-auto'}
                        >
                            <h4>{office.name}</h4>
                            <p>{office.description}</p>
                            <p>{office.streetAddress}</p>
                            <p>{`${office.cityResponse.name} ${office.zipCodeResponse.name}`}</p>
                            <p>{`${office.countyResponse.name} county`}</p>

                        </div>
                    )
                })
                    : null
            }
        </div>
    )
}

export default OfficeLocationElement;