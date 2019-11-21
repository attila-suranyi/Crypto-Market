import React, { Component } from "react";

export default class OrderHistoryItem extends Component {
  formatNumber(num) {
    return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
  }

  colorTransactionType(type) {
    if (type === "BUY") {
      return "green";
    } else {
      return "red";
    }
  }

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
          <p>${(this.props.orderData.price).toLocaleString()}</p>
        </td>
        <td>
          <p>{this.props.orderData.date}</p>
        </td>
      </tr>
    );
  }
}
