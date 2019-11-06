import React, { Component } from 'react';
import {CryptoDataContext} from '../contexts/CryptoDataContext';
import Button from 'react-bootstrap/Button';

export default class CryptoItem extends Component {
    static contextType = CryptoDataContext;
    
    render() {
        return (
            <tr>
                <td><p>{ this.props.cryptoData.cmc_rank }</p></td>
                <td><p>{ this.props.cryptoData.name }</p></td>
                <td><p>{ this.props.cryptoData.symbol }</p></td>
                <td><p>{ this.props.cryptoData.quote.usd.price }</p></td>
                <td><p>{ this.props.cryptoData.quote.usd.percent_change_24h }</p></td>
                <td><p>{ this.props.cryptoData.quote.usd.market_cap }</p></td>
                <td><p>{ this.props.cryptoData.quote.usd.volume_24h }</p></td>
                <td><p><Button>Trade</Button></p></td>
            </tr>
        )
    }
}
