import React, { Component } from 'react';
import {CryptoDataContext} from '../contexts/CryptoDataContext';

export default class CryptoItem extends Component {
    static contextType = CryptoDataContext;

    render() {
        return (
            <div>
                <h3>{ this.props.cryptoData.symbol }</h3>
                <p>{ this.props.cryptoData.cmc_rank }</p>
                <p>{ this.props.cryptoData.quote.usd.price }</p>
            </div>
        )
    }
}
