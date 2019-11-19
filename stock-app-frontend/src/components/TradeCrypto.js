import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import BuyCrypto from "./BuyCrypto";
import '../assets/css/TradeCrypto.css';

export default class TradeCrypto extends Component {
  state = {
    symbol: "",
    id: 1,
  };

  static contextType = CryptoDataContext;

  componentDidMount() {
    const symbol = this.context.getQueryParam("symbol");
    const id = this.context.getQueryParam("id");

    this.setState({ symbol });
    this.setState({ id });
    this.context.fetchSingleCryptoDataById(
      `http://localhost:8080/trade?id=${id}`
    );
  }

  render() {
    return (
      <div className="trade-container">
        {/* Header */}
        <div className="trade-crypto-header"><h3>{this.state.symbol}</h3></div>
        <div className="trade-crypto-container">

          {/* Buy crypto */}
          <div className="trade-dir">
            <BuyCrypto symbol={this.state.symbol} tradeDir="Buy" />
          </div>

          {/* Sell crypto */}
          <div className="trade-dir">
            <BuyCrypto symbol={this.state.symbol} tradeDir="Sell" />
          </div>
        </div>
      </div>
    );
  }
}
