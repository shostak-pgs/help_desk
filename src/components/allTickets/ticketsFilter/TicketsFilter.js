import React from 'react';
import './TicketsFilter.css';
import TicketsService from '../../../service/TicketsService';

const TicketsFilter = (props) => {

    let newTicket = React.createRef();

    let onChange = () => {
        let text = newTicket.current.value;
        TicketsService.find(text);
    }
    
    return(
        <div className='ticketFileterBar'>
            <textarea className='ticketsFilterInput' ref={newTicket} onChange={onChange} value={props.newTicket} placeholder='Enter ticket for search here'/>
        </div>
    )
}

export default TicketsFilter;