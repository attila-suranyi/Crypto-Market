import React, { Component } from 'react';
import {CryptoDataContext} from '../contexts/CryptoDataContext';
//import CryptoItem from 'CryptoItem.js';

export default class CryptoList extends Component {
    static contextType = CryptoDataContext;

    render() {
        /*return this.props.cryptoData.map((cryptoCurrency) => (
            <p>{cryptoCurrency.name}</p>
        ))*/

        return (
            <p>{this.context.name}</p>
        )
    }
}
