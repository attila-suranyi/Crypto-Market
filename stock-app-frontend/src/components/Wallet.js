import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import WalletItem from "./WalletItem";
import WalletChart from "./WalletChart";
import Table from "react-bootstrap/Table";
import '../assets/css/OpenOrder.css';

export default class Wallet extends Component {
  static contextType = CryptoDataContext;

  componentDidMount() {
    this.context.clearUserWallet();
    this.context.fetchUserWallet(
      `http://localhost:8080/wallet?userId=${this.context.userId}`
    );
  }

  render() {
    return (
      <div>
        <div>
          <div className="title-container"><h4 className="title">Wallet</h4></div>
          <div className="table-responsive text-nowrap">
            <Table striped bordered hover variant="dark">
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
                {(this.context.userWallet||[]).map(userWallet => (
                  <WalletItem key={userWallet.symbol} userWallet={userWallet} />
                ))}
              </tbody>
            </Table>
          </div>
        </div>
        <div>
          {console.log(this.context.userWallet)}
          {this.context.userWallet ? (
            <WalletChart userWallet={this.context.userWallet}/>
          ) : (<div>Fetching...</div>)}
        </div>
      </div>
    );
  }
}
