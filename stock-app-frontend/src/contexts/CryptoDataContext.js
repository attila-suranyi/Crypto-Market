import React, { Component, createContext } from "react";
import Axios from "axios";
var storages = require('store/storages/localStorage')
	
export const CryptoDataContext = createContext();

export default class CryptoDataContextProvider extends Component {
  state = {
    backendIp: "localhost:8762",
    cryptoData: [],
    singleCryptoData: {},

    userOpenOrders: [],
    userOrderHistory: [],

    userWallet: null,

    userBalance: null,

    userId: "",

    setUserId: (userId) => {
      this.setState({userId : userId});
      storages.write("userId", userId);
    },

    fetchDataWithCallback: (URL, callback) => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL).then(res => callback(res));
    },

    fetchAllCryptoData: URL => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL).then(res => this.setState({ cryptoData: res.data.data }));
    },

    fetchSingleCryptoDataById: URL => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL).then(res => {
        this.setState({ singleCryptoData: res.data });
      });
    },

    fetchUserOrderHistory: URL => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL)
        .then(res => this.setState({ userOrderHistory: res.data }))
    },

    fetchUserOpenOrders: URL => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL)
        .then(res => this.setState({ userOpenOrders: res.data }))
    },

    fetchUserWallet: URL => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL)
        .then(res => this.setState({ userWallet: res.data }))
    },

    fetchUserBalance: URL => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL)
        .then(res => this.setState({ userBalance: res.data }))
    },

    sendDataToBackendWithCallback: (URL, data, callback) => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.post(URL, data).then(res => {
        callback(res.data);
      });
    },

    sendDataToBackend: (URL, data) => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.post(URL, data);
    },

    getQueryParam(key) {
      let search = window.location.search;
      let params = new URLSearchParams(search);
      let param = params.get(key);
      return param;
    },

    clearSingleCryptoData: () => {
      this.setState({ singleCryptoData: {} });
    },

    clearUserWallet: () => {
      this.setState({ userWallet: null });
    },
  };

  render() {
    return (
      <CryptoDataContext.Provider value={{ ...this.state }}>
        {this.props.children}
      </CryptoDataContext.Provider>
    );
  }
}
