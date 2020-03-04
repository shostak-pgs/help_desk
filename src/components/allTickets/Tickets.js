import React from 'react';
import './Tickets.css';
import Content from './content/Content.js';
import Header from './header/Header.js';
import  { Route } from 'react-router-dom';

let Tickets = (props) => {
    return (
        <div>
            <div>
                <Header refreshUserTickets = {props.refreshUserTickets}
                        refreshAllTickets = {props.refreshAllTickets} 
                        button = {props.ticketsPage.button}/>
            </div>
            <div>
                <Route exact path='/myTickets' component = {Content} tickets = {props.ticketsPage.tickets}/> 
                <Route path='/allTickets' component = {Content} tickets = {props.ticketsPage.tickets}/> 
                <Content tickets = {props.ticketsPage.tickets} />
            </div>
        </div>
    )
 
}

export default Tickets;