import React, { Component } from "react";
import { Form, Button } from "react-bootstrap";
import { CryptoDataContext } from "../contexts/CryptoDataContext";

export default class BuyCrypto extends Component {
  static contextType = CryptoDataContext;

  state = {
    symbol: "",
    price: "",
    amount: "",
    total: ""
  };

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();

    let transaction = {
      symbol: this.props.symbol,
      price: this.context.singleCryptoData.quote.usd.price,
      amount: this.state.amount,
      total: this.state.amount * this.context.singleCryptoData.quote.usd.price
    };

    this.context.sendDataToBackend("http://localhost:8080/buy", transaction);
  };

  render() {
    return (
      <div>
        <div><p>{this.props.tradeDir} {this.props.symbol}</p></div>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="formPrice">
            <Form.Label>Price</Form.Label>
            <Form.Control
              readOnly
              value={
                this.context.singleCryptoData.quote
                  ? this.context.singleCryptoData.quote.usd.price
                  : 0
              }
            />
          </Form.Group>
          <Form.Group controlId="formAmount">
            <Form.Label>Amount</Form.Label>
            <Form.Control
              placeholder="Amount"
              onChange={this.handleChange}
              name="amount"
            />
          </Form.Group>
          <Form.Group controlId="formTotal">
            <Form.Label>Total</Form.Label>
            <Form.Control
              readOnly
              placeholder="Total"
              value={
                this.context.singleCryptoData.quote
                  ? this.context.singleCryptoData.quote.usd.price *
                  this.state.amount
                  : 0
              }
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
