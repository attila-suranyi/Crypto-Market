import React, { Component } from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";

import { CryptoDataContext } from "../contexts/CryptoDataContext";

export default class StockNavBar extends Component {
  static contextType = CryptoDataContext;

  render() {
    return (
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
        <Navbar.Brand href="/">Crypto Market</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/wallet">Wallet</Nav.Link>
            <Nav.Link href="#pricing">Portfolio</Nav.Link>
            <NavDropdown title="Orders" id="collasible-nav-dropdown">
              <NavDropdown.Item href="/open-order">
                Open Orders
              </NavDropdown.Item>
              <NavDropdown.Item href="/order-history">
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
