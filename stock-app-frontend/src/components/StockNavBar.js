import React, { Component } from "react";
import { NavDropdown, Navbar, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

import { CryptoDataContext } from "../contexts/CryptoDataContext";

var storages = require('store/storages/localStorage')

export default class StockNavBar extends Component {
  static contextType = CryptoDataContext;

  removeToken = () => {
    storages.remove("userId");
  };

  render() {
    return (
      <div>
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
          <Navbar.Brand as={Link} to="/">
            Crypto Market
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            {storages.read("userId") ? (
              <React.Fragment>
                <Nav className="mr-auto">
                  <Nav.Link as={Link} to="/wallet">
                    Wallet
                  </Nav.Link>
                {/* <Nav.Link as={Link} to="/portfolio">
                Portfolio
                </Nav.Link> */}
                  <NavDropdown title="Orders" id="collasible-nav-dropdown">
                    <NavDropdown.Item as={Link} to="/open-order">
                      Open Orders
                    </NavDropdown.Item>
                    <NavDropdown.Item as={Link} to="/order-history">
                      Order History
                    </NavDropdown.Item>
                  </NavDropdown>
                </Nav>
                <Nav onSelect={this.removeToken}>
                  <Nav.Link href="/sorted">Logout</Nav.Link>
                </Nav>
              </React.Fragment>
            ) : (
              <React.Fragment>
                <Nav>
                  <Nav.Link as={Link} to="/signin">Login</Nav.Link>
                  <Nav.Link as={Link} to="/registration">Sign up</Nav.Link>
                </Nav>
              </React.Fragment>
            )}
          </Navbar.Collapse>
        </Navbar>
      </div>
    );
  }
}
