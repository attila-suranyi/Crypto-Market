import React, { Component } from "react";
import { Form, Button } from "react-bootstrap";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import "../assets/css/TradeCryptoForm.css";

export default class BuyCrypto extends Component {
  static contextType = CryptoDataContext;

  state = {
    symbol: "",
    price: 0,
    amount: 0,
    total: "",
    transactionType: this.props.tradeDir === "Buy" ? "buy" : "sell",
    alert: ""
  };

  componentDidMount() {
    this.setState({
      price: this.props.singleCryptoData.quote.usd.price
    });
  }

  showAlert = (alertData) => {
    alertData == true ? this.setState({alert:"Order placed!"}) : this.setState({alert:"Not enough balance!"})
    alert(this.state.alert)
  }

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    
    let transaction = {
      symbol: this.props.symbol,
      currencyId: this.context.singleCryptoData.id,
      price: this.state.price,
      amount: this.state.amount,
      total: this.state.amount * this.state.price,
      closedTransaction: false
    };
    
    this.context.sendDataToBackendWithCallback(
      `http://localhost:8082/${this.state.transactionType}?userId=${this.context.userId}`,
      transaction,
      this.showAlert
      );
  };

  render() {
    return (
      <div className="trade-form">
        <div className="crypto-symbol">
          <p>
            {this.props.tradeDir} {this.props.symbol}
          </p>
        </div>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="formPrice">
            <Form.Label>Price</Form.Label>
            <Form.Control
              value={this.state.price}
              onChange={this.handleChange}
              name="price"
            />
          </Form.Group>
          <Form.Group controlId="formAmount">
            <Form.Label>Amount</Form.Label>
            <Form.Control
              placeholder="Amount"
              value={this.state.amount}
              onChange={this.handleChange}
              name="amount"
            />
          </Form.Group>
          <Form.Group controlId="formTotal">
            <Form.Label>Total</Form.Label>
            <Form.Control
              readOnly
              placeholder="Total"
              value={this.state.price * this.state.amount}
              name="total"
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            {this.props.tradeDir}
          </Button>
        </Form>
      </div>
    );
  }
}
