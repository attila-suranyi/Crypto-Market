import React, { Component, createContext } from 'react';
import Axios from 'axios';

export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
    
    state = {
        cryptoData: [],
        currentCryptoData: [],
        fetchAllCryptoData: (URL) => {
            Axios.get(URL)
            .then(res => this.setState({ cryptoData: res.data.data }))
        },
        fetchCurrentCryptoData: (URL) => {
            Axios.get(URL)
            .then(res => this.setState({currentCryptoData: res.data[0]}))
        },
    }

    componentDidMount() {
        this.state.fetchAllCryptoData('http://10.44.9.244:8080/');
    }

    render() {
        return (
            <CryptoDataContext.Provider value={{...this.state}}>
                {this.props.children}
            </CryptoDataContext.Provider>
        )
    }
}
