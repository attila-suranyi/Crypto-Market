import React, { Component } from "react";

export default class WalletItem extends Component {
  state = {
    cryptoData: {}
  };

  componentDidMount() {}

  render() {
    return (
      <tr>
        <td>
          <p>{this.props.userWallet.symbol}</p>
        </td>
        <td>
          <p>{this.props.userWallet.totalAmount}</p>
        </td>
        <td>
          <p>{this.props.userWallet.availableAmount}</p>
        </td>
        <td>
          <p>{this.props.userWallet.inOrder}</p>
        </td>
        <td>
          <p>${this.props.userWallet.usdValue.toLocaleString()}</p>
        </td>
      </tr>
    );
  }
}
