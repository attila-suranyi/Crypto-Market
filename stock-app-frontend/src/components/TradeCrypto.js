import React, { Component } from "react";

import {CryptoDataContext} from '../contexts/CryptoDataContext';
import BuyCrypto from "./BuyCrypto";

export default class TradeCrypto extends Component {
  
  state = {
    symbol: ""
  };

  static contextType = CryptoDataContext;

  getQueryParam() {
    const queryString = require("query-string");
    const parsed = queryString.parse(this.props.location.search);
    return parsed.symbol;
  }

  componentDidMount() {
    const symbol = this.getQueryParam();
    this.setState({symbol: symbol});
    this.context.fetchCurrentCryptoData(`http://10.44.9.244:8080/${symbol}` );
    console.log(this.context.currentCryptoData)
  }

  render() {

    return (
      <div className="trade-crypto-container">
      
        {/* Header */}
        <div className="trade-crypto-header"></div>
          <h3>{this.context.currentCryptoData.symbol}</h3>

        {/* Buy crypto */}
        <div>
          <BuyCrypto symbol={this.state.symbol}/>
        </div>

        {/* Sell crypto */}
        <div>

        </div>
      </div>
    );
  }
}
