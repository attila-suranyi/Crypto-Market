import React, { Component, createContext } from "react";
import Axios from "axios";

export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
  state = {
    cryptoData: [],
    currentCryptoData: [],

    fetchAllCryptoData: URL => {
      Axios.get(URL).then(res => 
        this.setState({ cryptoData: res.data.data }));
    },
    fetchCurrentCryptoData: URL => {
      Axios.get(URL).then(res =>
        this.setState({ currentCryptoData: res.data[0] })
      );
    },
    fetchCurrentCryptoById: URL => {
      Axios.get(URL).then(res =>
        this.setState({ currentCryptoData: res.data })
      );
    },
    sendDataToBackend: (URL, data) => {
      Axios.post(URL, data).then(res => {
        console.log(res);
        console.log(res.data);
      });
    },
    getQueryParam(key) {
      let search = window.location.search;
      let params = new URLSearchParams(search);
      let param = params.get(key);
      return param;
    }
  };

  render() {
    return (
      <CryptoDataContext.Provider value={{ ...this.state }}>
        {this.props.children}
      </CryptoDataContext.Provider>
    );
  }
}
