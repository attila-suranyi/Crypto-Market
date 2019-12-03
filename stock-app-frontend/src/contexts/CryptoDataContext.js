import React, { Component, createContext } from "react";
import Axios from "axios";

export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
  state = {
    cryptoData: [],
    singleCryptoData: {},

    userOpenOrders: [],
    userOrderHistory: [],

    userWallet: [],

    fetchDataWithCallback: (URL, callback) => {
      Axios.get(URL).then(res => callback(res));
    },

    fetchAllCryptoData: URL => {
      Axios.get(URL).then(res => this.setState({ cryptoData: res.data.data }));
    },
    fetchSingleCryptoDataById: URL => {
      Axios.get(URL).then(res => {
        this.setState({ singleCryptoData: res.data });
      });
    },

    fetchUserOrderHistory: URL => {
      Axios.get(URL)
        .then(res => this.setState({ userOrderHistory: res.data }))
        .then(res => console.log(this.state.userOrderHistory));
    },

    fetchUserOpenOrders: URL => {
      Axios.get(URL)
        .then(res => this.setState({ userOpenOrders: res.data }))
        .then(res => console.log(this.state.userOpenOrders));
    },

    fetchUserWallet: URL => {
      Axios.get(URL)
        .then(res => this.setState({ userWallet: res.data }))
        .then(res => console.log(this.state.userWallet));
    },

    sendDataToBackend: (URL, data, callback) => {
      Axios.post(URL, data).then(res => {
        callback(res.data.token);
      });
    },

    getQueryParam(key) {
      let search = window.location.search;
      let params = new URLSearchParams(search);
      let param = params.get(key);
      return param;
    },
    clearSingleCryptoData: () => {
      this.setState({ singleCryptoData: {} });
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
