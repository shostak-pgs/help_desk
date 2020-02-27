import React from 'react';
import CreateBar from './createBar/CreateBar';
import SwitchBar from './switchBar/SwitchBar';
import TicketsFilter from './ticketsFilter/TicketsFilter';

const Header = (props) => {
    return (    
        <div>
            <CreateBar />
            <SwitchBar refreshUserTickets = {props.refreshUserTickets}
                       refreshAllTickets = {props.refreshAllTickets}
                       button = {props.button}/>
            <TicketsFilter />
        </div>
    )
}

export default Header;