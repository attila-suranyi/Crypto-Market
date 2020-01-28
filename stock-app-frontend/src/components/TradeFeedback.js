import React, {Component} from 'react';
// import {Alert, AlertTitle} from '@material-ui/lab/Alert';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

const TradeFeedback = (props) => {
    if (props.result === "success") {
        return successful;
    }
    if (props.result === "error") {
        return failed;
    }
}

const successful =
    <Alert severity="success">
        <AlertTitle>Success</AlertTitle>
        Transaction successful!
    </Alert>;

const failed =
    <Alert severity="error">
        <AlertTitle>Error</AlertTitle>
        An error occurred during transaction.
    </Alert>;

export default TradeFeedback;