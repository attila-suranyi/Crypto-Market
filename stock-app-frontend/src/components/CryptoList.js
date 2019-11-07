import React, { Component } from 'react';
import {CryptoDataContext} from '../contexts/CryptoDataContext';
import CryptoItem from './CryptoItem';

export default class CryptoList extends Component {
    static contextType = CryptoDataContext;

    render() {
        return  (
            <div className="table-responsive text-nowrap">
                <table className="table table-striped table-hover">
                    <thead className="black white-text">
                        <tr>
                            <th><p>#</p></th>
                            <th><p>Name</p></th>
                            <th><p>Symbol</p></th>
                            <th><p>Price</p></th>
                            <th><p>Change (24h)</p></th>
                            <th><p>Market Cap</p></th>
                            <th><p>Volume (24h)</p></th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.context.cryptoData.map((cryptoCurrency) =>
                        <CryptoItem cryptoData={cryptoCurrency}/>)}
                    </tbody>
                </table>
            </div>
        )
    }
}
