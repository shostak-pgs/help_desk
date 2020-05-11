import React from 'react';
import logo from './logo.svg';
import Login from './components/login/Login';
import TicketsContainer from './components/allTickets/TicketsContainer';
import TicketOverviewContainer from './components/ticketOverview/TicketOverviewContainer';
import FeedbackContainer from './components/feedback/FeedbackContainer';
import CrUpdTicketContainer from './components/crUpdTicket/CrUpdTicketContainer.js';
import './App.css';
import  { Route } from 'react-router-dom';

const App =() => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Route exact path='/' render = { () => <Login />}/>  
        <Route path='/ticketConstructor/:id?' render = { () => <CrUpdTicketContainer />}/>
        <Route path='/tickets' render = { () => <TicketsContainer />}/>  
        <Route path='/ticket/:ticketId/:comments?' render = { () => <TicketOverviewContainer />}/>  
        <Route path='/feedbacks/:ticketId/:create?' render = { () => <FeedbackContainer />}/>  
      </header>
    </div>
  );
}

export default App;