import { NavLink } from 'react-router-dom';
import React from 'react';
import './CreateBar.css';

const CreateBar = () => {
    return(
        <nav className='bar'>
            <div >
                <NavLink to ='/createTicket'>
                    <button className ='createNewTicketButton'>Create New Ticket</button>
                </NavLink>
            </div>
        </nav>
    )
}

export default CreateBar;