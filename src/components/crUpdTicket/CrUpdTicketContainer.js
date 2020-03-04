import React, { Component } from 'react';
import {switchIsFetching} from '../store/TicketsReducer';
import {setNewTicket, setEnums} from '../store/CrUpdReducer';
import Preloader from '../preloader/Preloader';
import CrUpdTicketService from '../service/CrUpdTicketService';
import { withRouter, Route} from 'react-router-dom';
import CreateTicket from './createTicket/CreateTicket';
import UpdateTicket from './updateTicket/UpdateTicket';
import { connect } from 'react-redux';

class CrUpdTicketContainer extends Component {
    
    constructor(props){
        super(props);
        this.componentDidMount = this.componentDidMount.bind(this);
        this.postTicket = this.postTicket.bind(this);
        this.getEnums = this.getEnums.bind(this);
        this.putTicket = this.putTicket.bind(this);
    }

    componentDidMount(){
        this.getEnums();
        if (this.props.match.params.id !== undefined) {
            this.props.switchIsFetching({isFetching : true});
            CrUpdTicketService.getTicket(this.props.match.params.id).then(data => {
                data === null ? this.props.setNewTicket({newTicket : []})
                : this.props.setNewTicket({newTicket : data})
            })
        this.props.switchIsFetching({isFetching : false});
        }      
    }

    postTicket(newTicket) {
        this.props.switchIsFetching({isFetching : true});  
        CrUpdTicketService.postTicket(newTicket).then(data => {
            if (data.state === 'NEW') {
                this.props.history.push(`/tickets/myTickets`)
            } else if (data.state === 'DRAFT') {
                this.props.history.push(`/ticket/${data.id}`)
            }
            this.props.switchIsFetching({isFetching : false});
        });
    }

    putTicket(newTicket) {
        this.props.switchIsFetching({isFetching : true});  
        debugger 
        CrUpdTicketService.putTicket(newTicket).then(data => {
            debugger
            if (data.state === 'NEW') {
                this.props.history.push(`/tickets/myTickets`)
            } else if (data.state === 'DRAFT') {
                this.props.history.push(`/ticket/${data.id}`)
            }
            this.props.switchIsFetching({isFetching : false});
        });
    }

    getEnums(){
        this.props.switchIsFetching({isFetching : true});
        CrUpdTicketService.getEnums().then(data => {
            this.props.setEnums({enumsMap : data})
        })
        this.props.switchIsFetching({isFetching : false});
    }      
  
    render() {
        let id = this.props.match.params.id;
        return(
            <div>{this.props.isFetching ? <Preloader/> : null}
                <Route exact path={`/ticketConstructor/${id}`} render = { () => <UpdateTicket enumsMap = {this.props.enumsMap}
                                                                                       id = {id}
                                                                                       currentTicket = {this.props.newTicket}
                                                                                       putTicket = {this.putTicket}/>}/> 
                <Route exact path='/ticketConstructor' render = { () => <CreateTicket ticket = {this.props.newTicket}
                                                                                        postTicket = {this.postTicket}
                                                                                        enumsMap = {this.props.enumsMap}/>}/>
            </div>
        )
    }

}

let WithRouteCrUpdTicketContainer = withRouter(CrUpdTicketContainer);

let mapStateToProps = (state) => {
    return {
        enumsMap : state.crUpdTicketPage.enumsMap,
        newTicket : state.crUpdTicketPage.newTicket,
        isFetching : state.ticketsPage.isFetching,
    }
}

export default connect(mapStateToProps, {
    setNewTicket, switchIsFetching, setEnums, 
    })(WithRouteCrUpdTicketContainer);