import React, { Component, createContext } from 'react';
import Axios from 'axios';

export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
    
    state = {
        cryptoData: [],
        fetchCryptoData: () => {
            Axios.get('https://jsonplaceholder.typicode.com/todos?_limit=10')
        .then(res => this.setState({ cryptoData: res.data }))
        }
    }

    componentDidMount() {
        this.state.fetchCryptoData();
    }

    //TODO axios fetch method in the state

    render() {
        return (
            <CryptoDataContext.Provider value={{...this.state}}>
                {this.props.children}
            </CryptoDataContext.Provider>
        )
    }
}
