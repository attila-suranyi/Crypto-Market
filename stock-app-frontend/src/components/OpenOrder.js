import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import OpenOrderItem from "./OpenOrderItem"

export default class OpenOrder extends Component {
  static contextType = CryptoDataContext;

  componentDidMount() {
    this.context.fetchUserOpenOrders(
      "http://localhost:8080/open_order?userId=1"
    );
  }

  render() {
    return (
      <div>
        <h4>Open Orders</h4>
        <div className="table-responsive text-nowrap">
          <table className="table table-striped table-hover">
            <thead className="black white-text">
              <tr>
                <th>
                  <p>Transaction Type</p>
                </th>
                <th>
                  <p>Crypto Currency</p>
                </th>
                <th>
                  <p>Amount</p>
                </th>
                <th>
                  <p>Total Value</p>
                </th>
                <th>
                  <p>Price</p>
                </th>
                <th>
                  <p>Current Price</p>
                </th>
                <th>
                  <p>Profit</p>
                </th>
                <th>
                  <p>Date</p>
                </th>
              </tr>
            </thead>
            <tbody>
              {this.context.userOpenOrders.map(openOrderData => (
                <OpenOrderItem key={openOrderData.id} openOrderData={openOrderData} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
