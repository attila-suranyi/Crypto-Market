import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import BuyCrypto from "./BuyCrypto";

export default class TradeCrypto extends Component {
  state = {
    symbol: ""
  };

  static contextType = CryptoDataContext;

  componentDidMount() {
    const symbol = this.context.getQueryParam("symbol");
    console.log(this.props.location.state);
    this.setState({ symbol });
    this.context.fetchCurrentCryptoData(`http://localhost:8080/${symbol}`);
  }

  render() {
    return (
      <div className="trade-crypto-container">
        {/* Header */}
        <div className="trade-crypto-header"></div>
        <h3>{this.state.symbol}</h3>

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
