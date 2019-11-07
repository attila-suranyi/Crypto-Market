import React from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

import CryptoDataContextProvider from './contexts/CryptoDataContext';
import CryptoList from './components/CryptoList';
import TradeCrypto from './components/TradeCrypto';

class App extends React.Component {

  render() {
    return (
      <div className="App">
        <CryptoDataContextProvider>
          <Router>
            <Route exact path="/">
              <CryptoList />
            </Route>
            <Route path="/trade" component={TradeCrypto}>
            </Route>
          </Router>
        </CryptoDataContextProvider>
      </div>
    );
  }
}

export default App;
