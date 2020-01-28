import React from 'react';
// import {Alert, AlertTitle} from '@material-ui/lab/Alert';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

const TradeFeedback = (props) => {
    if (props.result === "success") {
        return <Alert severity="success" onClose={() =>{props.changeAlert(null)}}>
                   <AlertTitle>Success</AlertTitle>
                   Transaction successful!
               </Alert>;
    }

    if (props.result === "error") {
        return <Alert severity="error" onClose={() =>{props.changeAlert(null)}}>
                   <AlertTitle>Transaction Error</AlertTitle>
                   Not enough funds.
               </Alert>;
    }
};

export default TradeFeedback;