import React, { Component } from "react";

export default class OpenOrderItem extends Component {
  state = {
    cryptoData: {}
  };

  colorTransactionType(type) {
    if (type === "BUY") {
      return "green";
    } else {
      return "red";
    }
  }

  componentDidMount() {}

  render() {
    return (
      <tr>
        <td>
          <p
            style={{
              color: this.colorTransactionType(
                this.props.orderData.transactionType
              )
            }}
          >
            {this.props.orderData.transactionType}
          </p>
        </td>
        <td>
          <p>{this.props.orderData.symbol}</p>
        </td>
        <td>
          <p>{this.props.orderData.amount}</p>
        </td>
        <td>
          <p>${this.props.orderData.total.toLocaleString()}</p>
        </td>
        <td>
          <p>${this.props.orderData.price.toLocaleString()}</p>
        </td>
        <td>
          <p>${}</p>
        </td>
        <td>
          <p>{this.props.orderData.date}</p>
        </td>
      </tr>
    );
  }
}
