import React, {useState} from "react";



const OfficeLocationForm = props =>{
    const {
        cities,
        zipCodes,
        stateWithLocation,
        setStateWithLocation,
        divClass,
    } = props;
    const [office, setOffice] = useState({
        
        name: ''.trim(),
        description: ''.trim(),
        streetAddress: ''.trim(),
        city: ''.trim(),
        zipCode: ''.trim()
    })

    return(
        <div className= { "row " + divClass} >
            <div className="col">
                <div>
                    <label>New Location</label>
                    <div className=" ">
                        <label>Name</label>
                        <input name={'name'} className={'form-control'} value={office.name}/>
                    </div>
                    <div>
                        <label>Description</label>
                        <textarea 
                            cols={'4'} 
                            rows={'10'}
                            name={'description'}
                            value={office.description}
                        ></textarea>
                    </div>
                    <div>
                        <label>Street Address</label>
                        <input name={'streetAddress'} className={'form-control'} value={office.streetAddress}/>
                    </div>
                    <div className="row">
                        <div className="col">
                            <label>City:</label>
                            <select
                                className="form-control"
                                value={office.city}
                                name={'city'}
                            >
                                <option>Choose a City</option>
                                {
                                    cities.map((city, index)=>{

                                        return (
                                            <option 
                                                key={index}
                                                value={city.cityId}
                                            >{city.name}</option>
                                        )
                                    })
                                }
                            </select>
                        </div>
                        <div className="col">
                            <label>Zip Code</label>
                            <select>
                                <option>Choose a zip code</option>
                                {
                                    
                                    zipCodes.map((zipCode, index)=>{

                                        return(

                                            <option 
                                                key={index}
                                                value={zipCode.zipCodeId}
                                            >
                                                {zipCode.name}
                                            </option>
                                        )
                                    })
                                }
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div className="col">
                    <label>New Locations</label>
                    {
                        stateWithLocation.map((location, index)=>{

                            return(
                                <div key={index}>
                                    <div>
                                        <label>{location.name}</label>
                                        <p>{location.description}</p>
                                    </div>
                                    <div>
                                        <p>{location.streetAddress}</p>
                                        <p>{`${location.city} ${location.zipCode}`}</p>
                                    </div>
                                </div>
                            )
                        })
                    }
            </div>
        </div>
    )
}

export default OfficeLocationForm;