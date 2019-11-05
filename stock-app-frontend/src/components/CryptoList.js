import React, { Component } from 'react';
import {CryptoDataContext} from '../contexts/CryptoDataContext';
import CryptoItem from './CryptoItem';

export default class CryptoList extends Component {
    static contextType = CryptoDataContext;

    render() {
        return this.context.cryptoData.map((cryptoCurrency) => (
            <CryptoItem cryptoData={cryptoCurrency}/>
        ))
    }
}
