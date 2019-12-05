import React from "react";
import { MDBContainer, MDBAlert } from 'mdbreact';

const AlertPage = () => {
  return (
    <MDBContainer>
      <MDBAlert color="warning" dismiss>
        <strong>Holy guacamole!</strong> You should check in on some of those fields below.
      </MDBAlert>
    </MDBContainer>
  );
};

export default AlertPage;