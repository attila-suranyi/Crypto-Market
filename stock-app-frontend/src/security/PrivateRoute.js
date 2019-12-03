import React, { Component } from "react";
import { Route, Redirect } from "react-router-dom";

const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      localStorage.getItem("token") ? (
        <Component to={{pathname: '/'}} {...props} />
      ) : (
        <Redirect to={{ pathname: "/signin" }} />
      )
    }
  />
);

export default PrivateRoute;
