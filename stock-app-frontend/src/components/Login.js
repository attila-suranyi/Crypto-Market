import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import { MDBContainer, MDBRow, MDBCol, MDBInput, MDBBtn } from "mdbreact";

export default class Login extends Component {
  static contextType = CryptoDataContext;

  state = {
    username: "",
    password: ""
  };

  handleChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  saveTokenToLocalStorage = (data) => {
    localStorage.setItem("token", data.token);
    this.props.history.push("/protected");
    this.context.setUserId(data.id);
  }

  handleSubmit = event => {
    event.preventDefault();

    let userDetails = {
      username: this.state.username,
      password: this.state.password
    };

    this.context.sendDataToBackendWithCallback(
      "http://localhost:8080/auth/signin",
      userDetails,
      this.saveTokenToLocalStorage
    );
  };

  render() {
    return (
      <MDBContainer>
        <MDBRow center true>
          <MDBCol md="6">
            <form>
              <p className="h5 text-center mb-4">Login</p>
              <div className="grey-text">
                <MDBInput
                  label="Type your username"
                  icon="envelope"
                  group
                  type="email"
                  validate
                  error="wrong"
                  success="right"
                  onInput={this.handleChange}
                  name="username"
                />
                <MDBInput
                  label="Type your password"
                  icon="lock"
                  group
                  type="password"
                  validate
                  onInput={this.handleChange}
                  name="password"
                />
              </div>
              <div className="text-center">
                <MDBBtn onClick={this.handleSubmit}>Login</MDBBtn>
              </div>
            </form>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    );
  }
}
