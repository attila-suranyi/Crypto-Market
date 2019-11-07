import React, { Component } from 'react';
import BuyCrypto from './BuyCrypto';

export default class TradeCrypto extends Component {
    render() {
        return (
            <div className="trade-crypto-container">
                {/* Header */}
                <div className="trade-crypto-header">

                </div>

                {/* Buy crypto */}
                <div >
                    <BuyCrypto />
                </div>

                {/* Sell crypto */}
                <div>

                </div>
            </div>
        )
    }
}
