import React from "react";
import { Document } from 'react-pdf';



const StateRepDisplay = props =>{
    const {rep, district} = props

    return(
        <div className={'row'}>
            <div className={'col'}>
                <img src={rep.picture} alt={`Picture of ${rep.firstName} ${rep.lastName}`}/>
                { rep.districtResponse? <h5>{`Representative ${rep.firstName} ${rep.lastName}`}</h5> : null }
                <h6>{`Email: ${rep.email}`}</h6>
                <h6>{`Website ${rep.website}`}</h6>
            </div>
            <div className={'col'}>
                {
                    district.name?
                        <h4>{`District: ${district.name}`}</h4>
                        : null
                }


                {
                    district.map?
                        <Document
                            file={district.map}
                        >

                        </Document>

                    :
                        null
                }


            </div>
        </div>
    )
}

export default StateRepDisplay;