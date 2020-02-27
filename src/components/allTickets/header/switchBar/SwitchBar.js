import { NavLink } from 'react-router-dom';
import React from 'react';
import './SwitchBar.css';

const SwitchBar = (props) => {

    let firstClicked = () => {
        props.refreshAllTickets('Button1');
    }

    let clicked = () => {
        let button1 = document.getElementById(props.button);
        let enotherButton;
        if(props.button === 'Button1') {
            enotherButton = 'Button2';
        } else {
            enotherButton = 'Button1';
        }
        let button2 = document.getElementById(enotherButton);  
        if(button1 != null){
            button1.disable = true;
            button2.disable = false;
        }
    }

    let secondClicked = () => {
        props.refreshUserTickets('Button2');
     }

    const JSX = () => {
        return (
        <nav className='switchBar'>
            <div >
                <NavLink to ='/tickets/allTickets'>
                    <button onClick = {firstClicked} id = 'Button1' className ={props.button === 'Button1' ? 'disable' : 'button'} >All Tickets</button>
                </NavLink>
                <NavLink to ='/tickets/myTickets'>
                    <button onClick = {secondClicked} id = 'Button2' className ={props.button === 'Button2' ? 'disable' : 'button'} >My Tickets</button>
                </NavLink>
            </div>
        </nav>
        )
    }
    clicked();
    return (<JSX></JSX>);
}

export default SwitchBar;