import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";

import CryptoDataContextProvider from "./contexts/CryptoDataContext";
import CryptoList from "./components/CryptoList";
import TradeCrypto from "./components/TradeCrypto";
import StockNavBar from "./components/StockNavBar";
import OrderHistory from "./components/OrderHistory";
import OpenOrder from "./components/OpenOrder";
import Wallet from "./components/Wallet";
<<<<<<< HEAD
import PrivateRoute from "./security/PrivateRoute";
=======
import Registration from "./components/Registration";
import Login from "./components/Login";
>>>>>>> f_multiple_users

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <CryptoDataContextProvider>
          <StockNavBar></StockNavBar>

          <div>
            <Router>
              <Route exact path="/">
                <CryptoList />
              </Route>
              <Route path="/trade" component={TradeCrypto}></Route>
              <Route path="/sorted" component={CryptoList}></Route>
              <Route path="/order-history" component={OrderHistory}></Route>
              <Route path="/open-order" component={OpenOrder}></Route>
              {/*<Route path="/wallet" component={Wallet}></Route>*/}
              <PrivateRoute path="/protected" component={Wallet}></PrivateRoute>
              <Route path="/wallet" component={Wallet}></Route>
              <Route path="/registration" component={Registration}></Route>
              <Route path="/signin" component={Login}></Route>
            </Router>
          </div>

        </CryptoDataContextProvider>
      </div>
    );
  }
}

export default App;
