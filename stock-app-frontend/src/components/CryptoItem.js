import React, { Component } from "react";
import { Link } from "react-router-dom";

import Button from "react-bootstrap/Button";

export default class CryptoItem extends Component {
  formatNumber(num) {
    return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
  }

  colorNumber(num) {
    if (num < 0) {
      return 'red';
    } else {
      return 'green';
    }
  }

  render() {
    return (
      <tr>
        <td>
          <p>{this.props.cryptoData.cmc_rank}</p>
        </td>
        <td>
          <p>{this.props.cryptoData.name}</p>
        </td>
        <td>
          <p>{this.props.cryptoData.symbol}</p>
        </td>
        <td>
          <p>${this.props.cryptoData.quote.usd.price.toFixed(4)}</p>
        </td>
        <td>
          <p
            style={{color : this.colorNumber(
              this.props.cryptoData.quote.usd.percent_change_24h
            )}}
          >
            {this.props.cryptoData.quote.usd.percent_change_24h.toFixed(2)}%
          </p>
        </td>
        <td>
          <p>
            ${this.formatNumber(this.props.cryptoData.quote.usd.market_cap)}
          </p>
        </td>
        <td>
          <p>
            ${this.formatNumber(this.props.cryptoData.quote.usd.volume_24h)}
          </p>
        </td>
        <td>
          <p>
            <Button>
              <Link
                to={`/trade?symbol=${this.props.cryptoData.symbol}&id=${this.props.cryptoData.id}`}
              >
                Trade
              </Link>
            </Button>
          </p>
        </td>
      </tr>
    );
  }
}
