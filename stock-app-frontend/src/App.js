import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";

import CryptoDataContextProvider from "./contexts/CryptoDataContext";
import CryptoList from "./components/CryptoList";
import TradeCrypto from "./components/TradeCrypto";
import StockNavBar from "./components/StockNavBar";
import OrderHistory from "./components/OrderHistory";
import OpenOrder from "./components/OpenOrder";

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
            </Router>
          </div>
        </CryptoDataContextProvider>
      </div>
    );
  }
}

export default App;
