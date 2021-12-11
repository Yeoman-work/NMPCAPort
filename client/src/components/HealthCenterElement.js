import React from "react";
import {dateAndTime} from "../helper/generalFunctions";


const HealthCenterElement = props =>{

    const {healthCenter} = props


    return(

        <div className={'row m-auto'}>
            <div className={'col'}>
                <h3>{healthCenter.name} ({healthCenter.nameAbbr})</h3>
                <h6 className={'align-bottom'}>Updated: {healthCenter.updatedAt? dateAndTime(healthCenter.updatedAt): dateAndTime(healthCenter.createdAt)}</h6>
            </div>
            <div className={'col overflow-auto'}>
                <h4>Locations:</h4>
                {/*<ul>*/}
                {/*    {*/}
                {/*        healthCenter.map((site, index)=>{*/}


                {/*            return(*/}
                {/*                <li key={index}>{site.name}</li>*/}
                {/*            )*/}
                {/*        })*/}
                {/*    }*/}

                {/*</ul>*/}
            </div>
            <div className={'overflow-auto col'}>
                <h4>District</h4>
                <h6><strong>NM House District:</strong> </h6>
                <h6><strong>NM Senate District:</strong> </h6>
                <h6><strong>Congressional District:</strong> </h6>
                {/*<ul>*/}
                {/*    {*/}
                {/*        healthCenter.nmHouseDistrictNestedResponseList.map((district, index)=>{*/}


                {/*            return(*/}
                {/*                <li key={index}>{district.name}</li>*/}
                {/*            )*/}
                {/*        })*/}
                {/*    }*/}
                {/*</ul>*/}
            </div>

        </div>
    )
}

export default HealthCenterElement