import React, { Component } from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from 'react-bootstrap/Nav';
import NavDropdown from 'react-bootstrap/NavDropdown';

import { CryptoDataContext } from "../contexts/CryptoDataContext";

export default class StockNavBar extends Component {

  static contextType = CryptoDataContext;

  handleEvent = event => {
    this.context.fetchData("http://localhost:8080/open_order?userId=1", this.context.userOpenOrders)
  }

  render() {
    return (
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
        <Navbar.Brand href="#home">Crypto Market</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="#features">Wallet</Nav.Link>
            <Nav.Link href="#pricing">Portfolio</Nav.Link>
            <NavDropdown title="Orders" id="collasible-nav-dropdown">
              <NavDropdown.Item href="#action/3.1" onClick={this.handleEvent}>Open Orders</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Order History
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            <Nav.Link href="#deets">User</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    );
  }
}
