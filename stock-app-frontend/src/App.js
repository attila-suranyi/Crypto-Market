import React from "react";
import { BrowserRouter as Router, Route, Redirect } from "react-router-dom";

import CryptoDataContextProvider from "./contexts/CryptoDataContext";
import CryptoList from "./components/CryptoList";
import TradeCrypto from "./components/TradeCrypto";
import StockNavBar from "./components/StockNavBar";
import OrderHistory from "./components/OrderHistory";
import OpenOrder from "./components/OpenOrder";
import Wallet from "./components/Wallet";
import PrivateRoute from "./security/PrivateRoute";
import Registration from "./components/Registration";
import Login from "./components/Login";

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <CryptoDataContextProvider>
          <Router>
            <StockNavBar></StockNavBar>

            <Route exact path="/">
              <CryptoList />
            </Route>
            <PrivateRoute path="/trade" component={TradeCrypto}></PrivateRoute>
            <Route path="/sorted" component={CryptoList}></Route>
            <PrivateRoute
              path="/order-history"
              component={OrderHistory}
            ></PrivateRoute>
            <PrivateRoute
              path="/open-order"
              component={OpenOrder}
            ></PrivateRoute>
            <PrivateRoute path="/wallet" component={Wallet}></PrivateRoute>
            <PrivateRoute path="/protected" component={Redirect}></PrivateRoute>
            <Route path="/registration" component={Registration}></Route>
            <Route path="/signin" component={Login}></Route>
          </Router>
        </CryptoDataContextProvider>
      </div>
    );
  }
}

export default App;
