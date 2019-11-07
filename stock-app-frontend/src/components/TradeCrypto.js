import React, { Component } from 'react';
import BuyCrypto from './BuyCrypto';

export default class TradeCrypto extends Component {

    state = {
        symbol: this.getQueryParam()
    }

    getQueryParam() {
        const queryString = require('query-string');
        const parsed = queryString.parse(this.props.location.search);
        return parsed.symbol;
    }

    componentDidMount() {
        this.getQueryParam();
    }

    render() {
        return (
        
            <div className="trade-crypto-container">
                {/* Header */}
                <div className="trade-crypto-header">

                </div>

                {/* Buy crypto */}
                <div >
                    <BuyCrypto symbol={this.state.symbol}/>
                </div>

                {/* Sell crypto */}
                <div>

                </div>
            </div>
        )
    }
}
