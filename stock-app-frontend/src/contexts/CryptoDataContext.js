import React, { Component, createContext } from 'react';
import Axios from 'axios';

export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
    
    state = {
        cryptoData: [],
        fetchCryptoData: () => {
            Axios.get('http://localhost:8080/')
            .then(res => this.setState({ cryptoData: res.data.data }))
        }
    }

    componentDidMount() {
        this.state.fetchCryptoData();
    }

    render() {
        return (
            <CryptoDataContext.Provider value={{...this.state}}>
                {this.props.children}
            </CryptoDataContext.Provider>
        )
    }
}
