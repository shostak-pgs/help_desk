import { NavLink } from 'react-router-dom';
import React from 'react';
import './SwitchBar.css';

const SwitchBar = (props) => {

    let firstClicked = () => {
        let button1 = document.getElementById('Button1');
        let button2 = document.getElementById('Button2');  
        button1.disabled = true;
        button2.disabled = false;
    }

    let renderAllTickets = () => {
        props.refreshTickets()
    }

    let renderMyTickets = () => {
        props.refreshUserTickets();
    }

    let secondClicked = () => {
        let button1 = document.getElementById('Button2');
        let button2 = document.getElementById('Button1');  
        button1.disabled = true;
        button2.disabled = false;
     }

    return(
        <nav className='switchBar'>
            <div >
                <NavLink to ='/tickets'>
                    <button onClick = {firstClicked, renderAllTickets} id = 'Button1' className ='button'>All Tickets</button>
                </NavLink>
                <NavLink to ='/tickets'>
                    <button onClick = {secondClicked, renderMyTickets} id = 'Button2' className ='button'>My Tickets</button>
                </NavLink>
            </div>
        </nav>
    )
}

export default SwitchBar;