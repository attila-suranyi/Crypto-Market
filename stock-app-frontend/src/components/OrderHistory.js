import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import OrderHistoryItem from "./OrderHistoryItem";

export default class OrderHistory extends Component {
  static contextType = CryptoDataContext;

  //TODO make user ID dynamic
  componentDidMount() {
    this.context.fetchUserOrderHistory('http://localhost:8080/order_history?userId=1')
  }

  render() {
    return (
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
                <p>Date</p>
              </th>
            </tr>
          </thead>
          <tbody>
            {this.context.userOrderHistory.map(orderData => (
              <OrderHistoryItem key={orderData.id} orderData={orderData} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
