import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import {CryptoDataContext} from '../contexts/CryptoDataContext';
import Button from 'react-bootstrap/Button';

export default class CryptoItem extends Component {
    /*static contextType = CryptoDataContext;*/
    
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
                <td><p><Button>
                    <Link to={'/trade?symbol=' + this.props.cryptoData.symbol}>Trade</Link></Button></p></td>
            </tr>
        )
    }
}
