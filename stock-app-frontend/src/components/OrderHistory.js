import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import OrderHistoryItem from "./OrderHistoryItem";
import Table from "react-bootstrap/Table";
import '../assets/css/OpenOrder.css';

var storages = require('store/storages/localStorage')

export default class OrderHistory extends Component {
  static contextType = CryptoDataContext;

  componentDidMount() {
    let userId = storages.read("userId") ? storages.read("userId") : this.context.userId;
    this.context.fetchUserOrderHistory(
      `http://${this.context.backendIp}/transaction/order_history?userId=${userId}`
    );
  }

  render() {
    return (
      <div>
        <div className="title-container"><h4 className="title">Order History</h4></div>
        <div className="table-responsive text-nowrap">
          <Table striped bordered hover variant="dark">
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
          </Table>
        </div>
      </div>
    );
  }
}
