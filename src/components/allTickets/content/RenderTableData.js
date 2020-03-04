import React from 'react';
import {NavLink} from 'react-router-dom';

let RenderTableData = (props) => {

    let header = () => {
        return (
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Desired Date</th>
                <th>Urgency</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        )
    };

    let body = (props) => {
        return (
            props.tickets.map(
                ticket =>
                    <tr key={ticket.id}>
                        <td>{ticket.id}</td>
                        <td><NavLink to={'/ticket/' + ticket.id}>{ticket.ticketName}</NavLink></td>
                        <td>{ticket.desiredResolutionDate}</td>
                        <td>{ticket.urgency}</td>
                        <td>{ticket.state}</td>
                    </tr>
            )
        )
    };

    return (
        <div>
            {header(props)}
            {body(props)}
        </div>
    )
};
export default RenderTableData;