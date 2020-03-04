import React from 'react';
import './UpdateTicket.css';
import {TextArea, Select} from '../../utils/CustomTags';
import { Field, reduxForm } from 'redux-form';
import {maxLengthCreator} from '../../validator/CrUpdValidator';

const maxLength100 = maxLengthCreator(100);
const maxLength500 = maxLengthCreator(500);

let UpdateTable = (props) => {
    debugger
    const handleSubmit = (formData) => {
        props.putTicket( 
            {
            id : props.id,
            category : formData.category,
            ticketName : formData.ticketName,
            description : formData.description,
            urgency : formData.urgency, 
            desiredResolutionDate : formData.desiredResolutionDate,
            comment : formData.comment,
            state : formData.state
            }
        )
    }
debugger
    return(
        <div>
          <UpdateTicketReduxForm onSubmit = {handleSubmit}
                                 currentTicket = {props.currentTicket}
                                 enumsMap = {props.enumsMap}/>
        </div>
    )
}

const UpdateTicketForm = (props) => {
let ticket = props.currentTicket;
debugger
    return(
        <form>
            <div><Field placeholder = {`${ticket.category}`} name = {'category'}
                        component = {Select} enum = {props.enumsMap.categories} /></div>
            <div><Field placeholder = {`${ticket.ticketName}`} name = {'ticketName'}
                        component = {TextArea} className='ticketInput' validate = {[ maxLength100]}/></div>   
            <div><Field placeholder = {`${ticket.description}`} name = {'description'}
                        component = {TextArea} className='ticketInput' validate = {[maxLength500]}/></div>  
            <div><Field placeholder = {`${ticket.urgency}`} name = {'urgency'}
                        component = {Select} enum = {props.enumsMap.urgency} /></div>  
            <div><Field placeholder = {`${ticket.desiredResolutionDate}`} name = {'desiredResolutionDate'}
                        component = {TextArea} className='ticketInput'/></div>  
            <div><Field placeholder = {`${ticket.comment}`} name = {'comment'}
                        component = {TextArea} className='ticketInput' validate = {[maxLength500]}/></div>   
            <div>
                    <button onClick={props.handleSubmit(values => props.onSubmit({ 
                        ...values, state: 'DRAFT' }))} className='overviewButton'>Save as Draft</button>
                    <button onClick={props.handleSubmit(values => props.onSubmit({ 
                        ...values, state: 'NEW' }))} className='overviewButton' >Submit</button>  
            </div>
        </form>
    )
}

const UpdateTicketReduxForm = reduxForm( { form : 'updateTicket' } ) (UpdateTicketForm)
    
export default UpdateTable;
