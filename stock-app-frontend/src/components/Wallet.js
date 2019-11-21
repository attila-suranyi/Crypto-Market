import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import WalletItem from "./WalletItem"

export default class Wallet extends Component {
  static contextType = CryptoDataContext;

  componentDidMount() {
    this.context.fetchUserWallet(
      "http://localhost:8080/wallet"
    );
  }

  render() {
    return (
      <div>
        <h4>Wallet</h4>
        <div className="table-responsive text-nowrap">
          <table className="table table-striped table-hover">
            <thead className="black white-text">
              <tr>
                <th>
                  <p>Symbol</p>
                </th>
                <th>
                  <p>Total Amount</p>
                </th>
                <th>
                  <p>Available Amount</p>
                </th>
                <th>
                  <p>In Order Amount</p>
                </th>
                <th>
                  <p>USD</p>
                </th>
              </tr>
            </thead>
            <tbody>
              {this.context.userWallet.map(userWallet => (
                <WalletItem key={userWallet.symbol} userWallet={userWallet} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
