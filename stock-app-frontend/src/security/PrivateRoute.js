import React, { Component } from "react";
import { Route, Redirect } from "react-router-dom";

var storages = require('store/storages/localStorage')

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      storages.read("userId") ? (
        <Component to={{pathname: '/'}} {...props} />
      ) : (
        <Redirect to={{ pathname: "/signin" }} />
      )
    }
  />
);

export default PrivateRoute;
