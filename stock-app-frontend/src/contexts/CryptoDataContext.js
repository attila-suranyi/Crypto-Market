import React, { Component, createContext } from 'react';

export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
    
    /*state = {
        cryptoData: [
            {
                name: "Bitcoin",
                symbol: "BTC",
                market_cap: 54321,
                price: 250.0,
                percent_change: -2.1,
                volume: 3333
            },
            {
                name: "Etherium",
                symbol: "ETH",
                market_cap: 1000,
                price: 230.0,
                percent_change: 10.1,
                volume: 444
            }
        ]
    
    }*/

    //TODO axios fetch method in the state
    state = {
        name: "Bitcoin",
        symbol: "BTC",
        market_cap: 54321,
        price: 250.0,
        percent_change: -2.1,
        volume: 3333
    }
    
    render() {
        return (
            <CryptoDataContext.Provider value={{...this.state}}>
                {this.props.children}
            </CryptoDataContext.Provider>
        )
    }
}
