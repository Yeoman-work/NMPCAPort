import React from "react";
import Header from "../components/Header";





const StateRepViewWithDistrict = props =>{
    const {rep, district} = props


    return(
        <div>
            <Header/>
            <div>
                <StateRepViewWithDistrict
                    rep={rep}
                    district={district}
                />
            </div>
        </div>
    )
}

export default StateRepViewWithDistrict