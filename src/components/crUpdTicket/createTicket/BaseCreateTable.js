import React from 'react';
import './CreateTicket.css';
import {Select, TextArea} from '../../utils/CustomTags';
import { Field, reduxForm } from 'redux-form';
import {required, maxLengthCreator} from './../../validator/CrUpdValidator';

const maxLength100 = maxLengthCreator(100);
const maxLength500 = maxLengthCreator(500);

let BaseCreateTable = (props) => {

    const handleSubmit = (formData) => {
        props.postTicket( 
            {
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

    return(
        <div>
          <CreateTicketReduxForm onSubmit = {handleSubmit}
                                 newTicket = {props.newTicket}
                                 enumsMap = {props.enumsMap}/>
        </div>
    )
}

const CreateTicketForm = (props) => {
    return(
        <form>
            <div><Field placeholder = {'Category'} name = {'category'}
                        component = {Select} enum = {props.enumsMap.categories} validate = {[required]} /></div>
            <div><Field placeholder = {'Name'} name = {'ticketName'}
                        component = {TextArea} className='ticketInput' validate = {[required, maxLength100]}/></div>   
            <div><Field placeholder = {'Description'} name = {'description'}
                        component = {TextArea} className='ticketInput' validate = {[maxLength500]}/></div>  
            <div><Field placeholder = {'Urgency'} name = {'urgency'}
                        component = {Select} enum = {props.enumsMap.urgency} validate = {[required]}/></div>  
            <div><Field placeholder = {'Desired Resolution Date in format DD/MM/YYYY'} name = {'desiredResolutionDate'}
                        component = {TextArea} className='ticketInput'/></div>  
            <div><Field placeholder = {'Comment'} name = {'comment'}
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

const CreateTicketReduxForm = reduxForm( { form : 'createTicket' } ) (CreateTicketForm)
    
export default BaseCreateTable;
