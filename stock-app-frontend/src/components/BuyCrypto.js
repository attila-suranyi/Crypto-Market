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

    handleChange = event => {
        this.setState({ [event.target.name]: event.target.value });}


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
                    <Form.Control placeholder="Amount" onChange={this.handleChange} name="amount" />
                </Form.Group>
                <Form.Group controlId="formTotal">
                    <Form.Label>Total</Form.Label>
                    <Form.Control placeholder="Total" value={this.context.currentCryptoData * this.state.amount} onChange={this.handleChange} name="total" />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Buy
                </Button>
            </Form>
        )
    }
}


