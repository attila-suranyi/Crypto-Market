import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import { CryptoDataContext } from '../contexts/CryptoDataContext';
import Axios from 'axios';


export default class BuyCrypto extends Component {

    static contextType = CryptoDataContext;

    state = {
        symbol: '',
        price: '',
        amount: '',
        total: ''
    }

    handleChangePrice = event => {
        this.setState({ price: event.target.value });
    }

    handleChangeAmount = event => {
        this.setState({ amount: event.target.value });
    }

    handleChangeTotal = event => {
        console.log(event.target.value);
        this.setState({ total: event.target.value });
    }


    handleSubmit = event => {
        event.preventDefault();

        Axios.post(`http://localhost:8080/buy`, {
            symbol: this.props.symbol,
            price: this.context.currentCryptoData,
            amount: this.state.amount,
            total: this.state.amount * this.context.currentCryptoData
        })
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }

    render() {
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formPrice">
                    <Form.Label>Price</Form.Label>
                    <Form.Control value={this.context.currentCryptoData} />
                </Form.Group>
                <Form.Group controlId="formAmount">
                    <Form.Label>Amount</Form.Label>
                    <Form.Control placeholder="Amount" onChange={this.handleChangeAmount} />
                </Form.Group>
                <Form.Group controlId="formTotal">
                    <Form.Label>Total</Form.Label>
                    <Form.Control placeholder="Total" 
                    value={this.context.currentCryptoData * this.state.amount} 
                    onChange={this.handleChangeTotal} />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Buy
                </Button>
            </Form>
        )
    }
}

