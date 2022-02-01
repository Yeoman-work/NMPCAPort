import axios from "axios"




const getRequestSearchCity = async (citySearchParams) =>{

    const cityResponse = await axios.get('http://localhost:8080/cities/search/' + citySearchParams.city ,{

        headers:{

            Authorization: localStorage.getItem('token')
        },

        params:{

            startIndex: citySearchParams.startIndex,
            endIndex: citySearchParams.endIndex
        }

    })

    return cityResponse.data;

}


module.exports={
    getRequestSearchCity
}
