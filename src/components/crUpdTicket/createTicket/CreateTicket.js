import React from 'react';
import {NavLink} from 'react-router-dom';
import './CreateTicket.css';
import BaseCreateTable from './BaseCreateTable';

const CreateTicket = (props) => {
    debugger
    return(
        <div>
            <div>
                <td className='overviewButtonsLeftcol'>
                    <NavLink to='/tickets'>
                        <button className='overviewButton'>Ticket List</button>
                    </NavLink>
                </td>
            </div>
            <h4>Create new Ticket</h4>
            <div><BaseCreateTable enumsMap = {props.enumsMap}
                                  newTicket = {props.newTicket}
                                  postTicket = {props.postTicket}/></div>
        </div>
    )
}

export default CreateTicket;