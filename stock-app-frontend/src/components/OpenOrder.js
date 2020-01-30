import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import OpenOrderItem from "./OpenOrderItem";
import Table from "react-bootstrap/Table";
import '../assets/css/OpenOrder.css';

var storages = require('store/storages/localStorage')

export default class OpenOrder extends Component {
  static contextType = CryptoDataContext;

  componentDidMount() {
    let userId = storages.read("userId") ? storages.read("userId") : this.context.userId;
    this.context.fetchUserOpenOrders(
      `http://${this.context.backendIp}/transaction/open_order?userId=${userId}`
    );
  }

  getCurrentPriceForOpenOrderCryptos() {
      let currenyIdList = [];
      this.context.userOpenOrders.map(openOrderData => 
        currenyIdList.push(openOrderData.id))
  }

  render() {
    return (
      <div>
        <div className="title-container"><h4 className="title">Open Orders</h4></div>
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
                  <p>Current Price</p>
                </th>
                <th>
                  <p>Date</p>
                </th>
              </tr>
            </thead>
            <tbody>
              {this.context.userOpenOrders.map(openOrderData => (
                <OpenOrderItem
                  key={openOrderData.date}
                  openOrderData={openOrderData}
                />
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}
