import React, { Component } from "react";

export default class OpenOrderItem extends Component {
  state = {
    cryptoData: {}
  };

  colorTransactionType(type) {
    return type === "BUY"? "green": "red";
  }

  render() {
    return (
      <tr>
        <td>
          <p
            style={{
              color: this.colorTransactionType(
                this.props.openOrderData.transactionType
              )
            }}
          >
            {this.props.openOrderData.transactionType}
          </p>
        </td>
        <td>
          <p>{this.props.openOrderData.symbol}</p>
        </td>
        <td>
          <p>{this.props.openOrderData.amount}</p>
        </td>
        <td>
          <p>${this.props.openOrderData.total.toLocaleString()}</p>
        </td>
        <td>
          <p>${this.props.openOrderData.price.toLocaleString()}</p>
        </td>
        <td>
          <p>${this.props.openOrderData.currentPrice.toLocaleString()}</p>
        </td>
        <td>
          <p>{this.props.openOrderData.date}</p>
        </td>
      </tr>
    );
  }
}
