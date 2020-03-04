import React from 'react';
import './Content.css';
import RenderTableData from './RenderTableData.js';

const Content = (props) => {
    return (
        <table className="table">
            <tbody>
                <RenderTableData tickets = {props.tickets} />
            </tbody>
        </table>
    )
}
export default Content;

