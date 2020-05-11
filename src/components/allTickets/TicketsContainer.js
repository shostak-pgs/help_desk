import React, {Component} from 'react';
import './Tickets.css';
import { withRouter } from 'react-router-dom';
import Preloader from './../../components/preloader/Preloader';
import Tickets from './Tickets';
import {connect} from 'react-redux';
import {refreshUserTickets, switchIsFetching, refreshAllTickets, refreshTickets, selectButton} from './../store/TicketsReducer'
import TicketsService from './../service/TicketsService';

class TicketsContainer extends Component {

    constructor(props){
        super(props);
        this.refreshAllTickets=this.refreshAllTickets.bind(this);
        this.refreshUserTickets=this.refreshUserTickets.bind(this);
        this.componentDidMount=this.componentDidMount.bind(this);
    }

    componentDidMount(){
        this.props.switchIsFetching({isFetching : true});
        TicketsService.retrieveAllTickets().then(data => {
            this.props.refreshTickets({tickets : data});
            this.props.switchIsFetching({isFetching : false});
        });
    }

    refreshUserTickets(props){
        this.props.switchIsFetching({isFetching : true});
        TicketsService.retrieveUserTickets().then(data => {
            this.props.refreshUserTickets({tickets : data,
                button : props
            });
            this.props.switchIsFetching({isFetching : false});
        });
    }

    refreshAllTickets(props){
        this.props.switchIsFetching({isFetching : true});
        TicketsService.retrieveAllTickets().then(data => {
            this.props.refreshAllTickets({tickets : data,
                button : props 
            });
            this.props.switchIsFetching({isFetching : false});
        });
    }

    render() {
        return( <div> {this.props.ticketsPage.isFetching ? <Preloader/> : null}
            <Tickets refreshAllTickets = {this.refreshAllTickets}
                     refreshUserTickets = {this.refreshUserTickets}
                     myTickets = {this.props.match.params.myTickets} 
                     ticketsPage = {this.props.ticketsPage}/>
               </div>      
        )
    }
}

let mapStateToProps = (state) => {
    return {
        ticketsPage: state.ticketsPage
    }
}

let WithRouteTicketsContainer = withRouter(TicketsContainer);

export default connect(mapStateToProps, {
    refreshUserTickets, refreshAllTickets,
    refreshTickets, selectButton,
    switchIsFetching
    })(WithRouteTicketsContainer);
