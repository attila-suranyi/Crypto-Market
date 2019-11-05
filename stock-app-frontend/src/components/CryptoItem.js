import React, { Component } from 'react';
import {CryptoDataContext} from '../contexts/CryptoDataContext';

export default class CryptoItem extends Component {
    static contextType = CryptoDataContext;

    render() {
        return (
            <div>
                <h3>{ this.props.cryptoData.title }</h3>
                <p>{ this.props.cryptoData.symbol }</p>
                <p>{ this.props.cryptoData.price }$</p>
            </div>
        )
    }
}
