import React, { Component } from 'react'
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import { MDBContainer, MDBRow, MDBCol, MDBInput, MDBBtn } from 'mdbreact';

export default class Registration extends Component {
    static contextType = CryptoDataContext;

    state = {
        firstname: "",
        lastname: "",
        username: "",
        email: "",
        password: "",
        confirmedPassword: ""
    };

    componentDidMount() {

    }

    handleChange = event => {
        this.setState({ [event.target.name]: event.target.value });
    };

    handleSubmit = event => {
        event.preventDefault();

        let user = {
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            username: this.state.username,
            email: this.state.email,
            password: this.state.password
        };

        this.context.sendDataToBackend("http://localhost:8080/auth/registration", user);
    };


    render() {
        return (
            <MDBContainer>
                <MDBRow>
                    <MDBCol md="6">
                        <form>
                            <p className="h5 text-center mb-4">Registration</p>
                            <div className="grey-text">
                                <MDBInput
                                    label="First name"
                                    icon="user"
                                    group
                                    type="text"
                                    validate
                                    error="wrong"
                                    success="right"
                                    onInput={this.handleChange}
                                    name="firstname"

                                />
                                <MDBInput
                                    label="Last name"
                                    icon="user"
                                    group
                                    type="text"
                                    validate
                                    error="wrong"
                                    success="right"
                                    onInput={this.handleChange}
                                    name="lastname"
                            
                                />
                                <MDBInput
                                    label="Username"
                                    icon="user"
                                    group
                                    type="text"
                                    validate
                                    error="wrong"
                                    success="right"
                                    onInput={this.handleChange}
                                    name="username"

                                />
                                <MDBInput
                                    label="Your email"
                                    icon="envelope"
                                    group
                                    type="email"
                                    validate
                                    error="wrong"
                                    success="right"
                                    onInput={this.handleChange}
                                    name="email"
                                />
                                <MDBInput
                                    label="Your password"
                                    icon="lock"
                                    group
                                    type="password"
                                    validate
                                    onInput={this.handleChange}
                                    name="password"
                                />
                                <MDBInput
                                    label="Confirm password"
                                    icon="lock"
                                    group
                                    type="password"
                                    validate
                                    onInput={this.handleChange}
                                    name="confirmedPassword"
                                />
                            </div>
                            <div className="text-center">
                                <MDBBtn onClick={this.handleSubmit} color="primary">Registration</MDBBtn>
                            </div>
                        </form>
                    </MDBCol>
                </MDBRow>
            </MDBContainer>
        );
    };
}
