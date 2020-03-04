import React, {Component} from 'react';
import './AllTicketsStyle.css';
import RenderTableData from './content/RenderTableData.js';
import TicketsService from './../service/TicketsService';
import USER_ID from '.././service/TicketsService';
import CreateBar from './createBar/CreateBar';
import SwitchBar from './switchBar/SwitchBar';
import TicketsFilter from './ticketsFilter/TicketsFilter';

class AllTickets extends Component {

    constructor(props) {
        super(props);
        this.state = {
            tickets: [],
            message: null
        };
        this.refreshTickets = this.refreshTickets.bind(this);
        this.refreshUserTickets = this.refreshUserTickets.bind(this);
    };
    
    componentDidMount() {
        this.refreshTickets();
    }

    refreshUserTickets(props) {
        TicketsService.retrieveUserTickets(USER_ID)//HARDCODED
            .then(
                response => {
                    this.setState({ tickets: response.data });
                }
            )
    };

    refreshTickets() {
        TicketsService.retrieveAllTickets()
            .then(
                response => {
                    this.setState({ tickets: response.data });
                }
            )
    };

    render() {
        return (
            <div>
                <div>
                    <div>
                        <CreateBar />
                        <SwitchBar refreshTickets = {this.refreshTickets} refreshUserTickets = {this.refreshUserTickets}/>
                        <TicketsFilter />
                    </div>
                    <table className="table">
                        <tbody>
                        <RenderTableData state = {this.state} />
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
   
export default AllTickets;