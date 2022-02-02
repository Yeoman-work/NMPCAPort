import React from "react";
import axios from "axios";



const cityPageable = async (e, direction, cityPage ) =>{
    e.preventDefault();

    try{

        const cityResponse = await axios.get('http://localhost:8080/cities', {

            headers:{

                Authorization: localStorage.getItem('token') 
            },

            params:{

                pageNo: cityPage.number,
                limit: cityPage.size
            }


        })

        console.log('another module')
        return cityPageable.data;

    }catch(error){

        console.log(error.response);
        return error.response;
    }

}

module.exports={

    cityPageable
}