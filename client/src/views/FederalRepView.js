import React from "react";
import Header from "../components/Header";
import CongressionalRep from "../components/CongressionalRep";




const FederalRepView = props =>{
    const {rep, district} = props;

    return(
        <div>
            <Header/>
            <div>
                <CongressionalRep
                    rep={rep}
                    district={district}
                />
            </div>
        </div>
    )
}

export default FederalRepView