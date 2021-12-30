import React, {useEffect, useReducer } from "react";
import axios from "axios";
import produce from "immer";
import {useNavigate, useParams} from "react-router";
import Header from "../components/Header";
import FederalRepForm from "../components/FederalRepForm";
import CongressionalRep from "../components/CongressionalRep";
import PhoneNumberForm from "../components/PhoneNumberForm";
import CongressionalRepElement from "../components/CongressionalRepElement";

const {
       emailValidation,
       isValidCharacter,
       fieldLengthRequired,
       fieldLengthNotRequired,

} = require('../helper/generalFunctions')


const federalRepReducer = (federalRepState, action)=>{

    switch (action.type){

        case FEDERAL_REP_FIELDS.FEDERAL_REP_FIRST_NAME:
             console.log(federalRepState);
            if(action.payload.length < 25){
                if(isValidCharacter(action.payload)){

                    if(federalRepState.repType){

                        return produce(federalRepState, draft=>{

                            draft.rep.firstName = action.payload.trim();

                        })

                    }else{

                        return produce(federalRepState,draft=>{

                            draft.sen.firstName = action.payload.trim();

                        })

                    }

                }else if(action.payload.length < 1){

                    if(federalRepState.repType){

                        return produce(federalRepState, draft=>{

                            draft.rep.firstName = action.payload.trim();

                        })

                    }else{

                        return produce(federalRepState, draft=>{

                            draft.sen.lastName = action.payload.trim();

                        })
                    }

                }else{

                    return federalRepState
                }

            }else{

                return federalRepState;

            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_LAST_NAME:
            console.log(federalRepState);
           if(action.payload.length < 25){
               if(isValidCharacter(action.payload)){

                   if(federalRepState.repType){

                       return produce(federalRepState, draft=>{

                           draft.rep.lastName = action.payload.trim();
                       })

                   }else{

                       return produce(federalRepState, draft=>{

                           draft.sen.lastName =action.payload.trim();
                       })
                   }

               }else if(action.payload.length < 1){

                   if(federalRepState.repType){

                       return produce(federalRepState, draft=>{

                           draft.rep.firstName = action.payload.trim();

                       })

                   }else{

                       return produce(federalRepState, draft=>{

                           draft.sen.lastName = action.payload.trim();

                       })
                   }

               }else{

                   return federalRepState;

               }

           }else{

               return federalRepState;
           }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_EMAIL:

            if(action.payload.length <= 150){

                if(federalRepState.repType){

                    return produce(federalRepState, draft=>{

                        draft.rep.email = action.payload.trim();
                    })

                }else{

                    return produce(federalRepState, draft=>{

                        draft.sen.email = action.payload.trim();
                    })
                }

            }else{

                return federalRepState;
            }




        case FEDERAL_REP_FIELDS.FEDERAL_REP_PICTURE:

            if(action.payload.length <= 250){

                if(federalRepState.repType){

                    return produce(federalRepState, draft=>{

                        draft.rep.picture = action.payload;
                    })
                }else{

                    return produce(federalRepState, draft=>{

                        draft.sen.picture = action.payload.trim();
                    })
                }
            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_WEBSITE:

            if(action.payload.length <= 300){

                if(federalRepState.repType){

                    return produce(federalRepState, draft=>{

                        draft.rep.website = action.payload.trim();
                    })
                }else{

                    return produce(federalRepState, draft=>{

                        draft.sen.website = action.payload.trim();
                    });
                }
            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_DISTRICT:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.rep.congressionalDistrict = action.payload;
                })
            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_ELECTED:

            if(!federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.sen.elected = action.payload;
                })

            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.FEDERAL_REP_NEXT_ELECTION:

            if(!federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.sen.nextElection = action.payload;
                })
            }else{

                return federalRepState;
            }

        case FEDERAL_REP_FIELDS.DISTRICT_LIST:

            return produce(federalRepState, draft=>{

                draft.formData.districtList = action.payload;
            })

        case FEDERAL_REP_FIELDS.REP_SELECTOR:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.repType = false;
                })

            }else{

                return produce(federalRepState, draft=>{

                    draft.repType = true;
                })
            }

        case FEDERAL_REP_FIELDS.REP_RESPONSE:

            return produce(federalRepState, draft=>{
                console.log('read this');
                console.log(action.payload);
                draft.repResponse ={...action.payload}
            })

        case FEDERAL_REP_FIELDS.SENATOR_RESPONSE:

            return produce(federalRepState, draft=>{

                draft.senatorResponse = {...action.payload}
            })

        case FEDERAL_REP_FIELDS.PARTY_LIST:

            return produce(federalRepState, draft=>{

                draft.formData.partiesList = [...action.payload];

                console.log(federalRepState);
            })

        case FEDERAL_REP_FIELDS.PARTY_AFFILIATION:

            if(federalRepState.repType){

                return produce(federalRepState, draft=>{

                    draft.rep.politicalParty = action.payload;
                })

            }else{

                return produce(federalRepState, draft=>{

                    draft.sen.politicalParty = action.payload;
                })
            }
        case FEDERAL_REP_FIELDS.FINISHED:

            if(federalRepState.finish){

                return produce(federalRepState, draft=>{

                    draft.finish = false;
                })

            }else{

                return produce(federalRepState, draft=>{

                    draft.finish = true;
                })

            }

        case FEDERAL_REP_FIELDS.REP_TYPE:

            if(action.payload === 'senator'){

                return produce(federalRepState, draft=>{

                    draft.repType = false;
                })

            }else{

                return produce(federalRepState, draft=>{

                    draft.repType = true;
                })

            }


        default:
            return federalRepState;

    }

}

const FEDERAL_REP_FIELDS ={

    FEDERAL_REP_FIRST_NAME: 'first name',
    FEDERAL_REP_LAST_NAME: 'last name',
    FEDERAL_REP_EMAIL: 'email',
    FEDERAL_REP_PICTURE: 'picture',
    FEDERAL_REP_WEBSITE: 'website',
    FEDERAL_REP_DISTRICT: 'district',
    FEDERAL_REP_ELECTED: 'elected',
    FEDERAL_REP_NEXT_ELECTION: 'next election',
    PHONE_NUMBER: 'Phone Number',
    PHONE_DESCRIPTION: 'Description',
    REP_SELECTOR: 'rep selector',
    PARTY_AFFILIATION: 'party affiliation',
    LOCATION_NAME: 'location name',
    LOCATION_DESCRIPTION: 'location description',
    LOCATION_CITY: 'location city',
    LOCATION_COUNTY: 'location county',
    LOCATION_ZIP_CODE: 'location zip code',
    LOCATION_STREET_ADDRESS: 'location street address',
    CITY_LIST: 'cityList',
    COUNTY_LIST: 'countyList',
    ZIP_CODE_LIST: 'zipCodeList',
    PARTY_LIST: 'party list',
    LOCATION_LIST: 'location list',
    DISTRICT_LIST: 'district list',
    REP_RESPONSE: 'rep response',
    SENATOR_RESPONSE: 'senator response',
    FINISHED: 'finished',
    REP_TYPE: 'repType'


}


const CreateFederalRepView = props =>{

    const params = useParams();
    const navigate = useNavigate();


    const [federalRepInfo, dispatchFederalRepInfo] = useReducer(federalRepReducer, {

        rep:{

            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            politicalParty: ''.trim(),
            congressionalDistrict: ''.trim(),

        },

        sen:{
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            elected: ''.trim(),
            nextElection: ''.trim(),
            politicalParty: ''.trim()
        },

        phoneNumber:{
            number:''.trim(),
            description:''.trim(),

        },

        location:{

          name: ''.trim(),
          description: ''.trim(),
          streetAddress: ''.trim(),
          county: ''.trim(),
          city: ''.trim(),
          zipCode: ''.trim()

        },

        locationDisplay: [],

        locationList: [],

        phoneNumberList: [],

        formData:{
            partiesList : [],
            districtList: [],
            cityList: [],
            countyList: [],
            zipCodeList: []
        },

        repResponse:{

            congressionalRepId: '',
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            districtResponse: {},
            politicalParty: {}

        },

        senatorResponse: {
            senatorId: '',
            firstName: ''.toLowerCase().trim(),
            lastName: ''.toLowerCase().trim(),
            email: ''.trim(),
            picture: ''.trim(),
            website: ''.trim(),
            elected: ''.trim(),
            nextElection: ''.trim(),
            politicalParty: {}
        },

        finish: false,

        repType: true,
    });


    useEffect(()=>{

        if(params.type === 'senator'){

            dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_TYPE, payload: params.type})

        }else{

            dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_TYPE, payload: params.type})

        }

    }, [])





    useEffect(()=>{

        (async ()=>{

            try{

                const districtResponse = await axios.get('http://localhost:8080/congressionalDistricts',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })


                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.DISTRICT_LIST, payload: [...districtResponse.data]});



            }catch(error){


            }
        })()

        return ()=>{}

    }, [])

    useEffect(()=>{

        (async ()=>{

            try{

                const partyResponse = await axios.get('http://localhost:8080/politicalParties',{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })


                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.PARTY_LIST, payload: [...partyResponse.data]})




            }catch(error){


            }

        })()

    }, [])

    const canSubmitRep = (federalRepInfo) =>{

        let isValid = false;

        if(fieldLengthRequired(3, 25, federalRepInfo.rep.firstName)){
            if(fieldLengthRequired(3, 25, federalRepInfo.rep.lastName)){
                if(fieldLengthNotRequired(0, 120, federalRepInfo.rep.email)){
                    if(federalRepInfo.rep.email.length > 0){
                        if(emailValidation(federalRepInfo.rep.email)){
                            if(fieldLengthNotRequired(0, 250, federalRepInfo.rep.picture)){
                                if(fieldLengthNotRequired(0, 300, federalRepInfo.rep.website)){

                                    isValid = true;
                                }
                            }
                        }
                    }else{

                        if(fieldLengthNotRequired(0, 250, federalRepInfo.rep.picture)){
                            if(fieldLengthNotRequired(0, 300, federalRepInfo.rep.website)){

                                isValid = true;
                            }
                        }

                    }
                }
            }
        }

        return isValid;
    }

    const canSubmitSen = (federalRepInfo) =>{

        let isValid = false;

        if(fieldLengthRequired(3, 25, federalRepInfo.sen.firstName)){
            if(fieldLengthRequired(3, 25, federalRepInfo.sen.lastName)){
                if(fieldLengthNotRequired(0, 120, federalRepInfo.sen.email)){
                    if(federalRepInfo.sen.email.length > 0){
                        if(emailValidation(federalRepInfo.sen.email)){
                            if(fieldLengthNotRequired(0, 250, federalRepInfo.sen.picture)){
                                if(fieldLengthNotRequired(0, 300, federalRepInfo.sen.website)){

                                    isValid = true;
                                }
                            }
                        }
                    }else{

                        if(fieldLengthNotRequired(0, 250, federalRepInfo.sen.picture)){
                            if(fieldLengthNotRequired(0, 300, federalRepInfo.sen.website)){

                                isValid = true;
                            }
                        }
                    }

                }
            }
        }

        return isValid;
    }

    const submitHandler = async (e) =>{
        e.preventDefault()

        if(federalRepInfo.repType){

            try{

                const createCongressPerson = await axios.post('http://localhost:8080/congressionalReps', federalRepInfo.rep, {

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }

                })

                //console.log(createCongressPerson.data);
                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_RESPONSE, payload: {...createCongressPerson.data}});
                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.FINISHED});


            }catch(error){



            }


        }else{

            try{

                const createSenator = await axios.post('http://localhost:8080/usSenators', federalRepInfo.sen,{

                    headers:{
                        Authorization: localStorage.getItem('token')
                    }
                })

                console.log(createSenator.data)
                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.SENATOR_RESPONSE, payload: {...createSenator.data}})
                dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.FINISHED})

            }catch(error){


            }


        }
    }





    const finish = (e) =>{

        navigate('/yeoman/government/')
    }


    return(
        <div>
            <Header/>
            <div className={'m-auto'}>
                <button hidden={federalRepInfo.finish} disabled={federalRepInfo.repType} onClick={(e)=>dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_SELECTOR})}>{'Congressional Representatives'}</button>
                <button hidden={federalRepInfo.finish} disabled={!federalRepInfo.repType} onClick={(e)=>dispatchFederalRepInfo({type: FEDERAL_REP_FIELDS.REP_SELECTOR})}>{'US Senator'}</button>
                {
                    !federalRepInfo.finish?
                        <FederalRepForm
                            formLabel={federalRepInfo.repType? 'New Representative' : 'New Senator'}
                            federalRepInfo={federalRepInfo}
                            dispatchFederalRep={dispatchFederalRepInfo}
                            formFields={FEDERAL_REP_FIELDS}
                        />
                    :
                    <div>
                        <CongressionalRepElement
                            rep={federalRepInfo.repType? federalRepInfo.repResponse : federalRepInfo.senatorResponse}
                            repType={federalRepInfo.repType}
                        />
                        <button>Add Phone Numbers Location</button>
                        <button onClick={(e)=>finish(e)}>Finish</button>
                    </div>
                }
                <button hidden={!federalRepInfo.repType || federalRepInfo.finish} disabled={!canSubmitRep(federalRepInfo)} onClick={(e)=>submitHandler(e)}>{'Create Congress Person'}</button>
                <button hidden={federalRepInfo.repType || federalRepInfo.finish} disabled={!canSubmitSen(federalRepInfo)} onClick={(e)=>submitHandler(e)}>{'Create Senator'}</button>
            </div>
        </div>
    )
}

export default CreateFederalRepView