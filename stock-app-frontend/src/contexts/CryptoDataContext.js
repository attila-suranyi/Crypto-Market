import React, { Component, createContext } from "react";
import Axios from "axios";

export const CryptoDataContext = createContext();

function createFetchFunction(url, state) {
  return () => {
    Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
    Axios.get(url).then(res => this.setState({ [state]: res.data.data }));
  }
}

function calculator(base, opponent) {
  return (opponent) => {
    return base**opponent;
  }
}

let func1 = calculator(2);
let func2 = calculator(3);
calculator(4);
calculator(5);
calculator(6);

func2(2)

let fetch1 = createFetchFunction('index.hu', 'indexResponse');
fetch1();

export default class CryptoDataContextProvider extends Component {
  state = {
    cryptoData: [],
    singleCryptoData: {},

    userOpenOrders: [],
    userOrderHistory: [],

    userWallet: null,

    userBalance: null,

    userId: "",

    setUserId: (userId) => {
      this.setState({userId : userId});
    },

    fetchDataWithCallback: (URL, callback) => {
      Axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
      Axios.get(URL).then(res => callback(res));
    },

    
    fetchAllCryptoData: (URL) => createFetchFunction(URL, 'cryptoData'),

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
