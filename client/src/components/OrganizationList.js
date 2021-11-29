import React, {useState} from "react";
import {DragDropContext, Droppable} from "react-beautiful-dnd";



const OrganizationList = props =>{
    const [healthCenters, setHealthCenters] = useState([]);

    return(
        <DragDropContext onDragEnd={}>
            <Droppable droppableId={'page'}>
                {(provided)=>(
                    <div>
                        {
                            healthCenters?
                                healthCenters.map((healthCenter, index)=>{


                                    return(
                                        <Droppable key={index} dragableId={healthCenter.healthCenterId}>
                                            {(provided)=>(
                                                <div {...provided.draggableProps} {...provided.dragHandleProps} ref={provided.innerRef}>

                                                </div>
                                            )}

                                        </Droppable>
                                    )
                                })
                                : null
                        }
                    </div>
                )}

            </Droppable>
        </DragDropContext>

    )
}

export default OrganizationList