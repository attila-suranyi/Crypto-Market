import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import BuyCrypto from "./BuyCrypto";

export default class TradeCrypto extends Component {
  state = {
    symbol: "",
    id: 1
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
      <div className="trade-crypto-container">
        {/* Header */}
        <div className="trade-crypto-header"></div>
        <h3>{this.context.singleCryptoData.symbol}</h3>

        {/* Buy crypto */}
        <div>
          <BuyCrypto symbol={this.state.symbol} />
        </div>

        {/* Sell crypto */}
        <div></div>
      </div>
    );
  }
}
