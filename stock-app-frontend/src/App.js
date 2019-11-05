import React from 'react';
import CryptoDataContextProvider from './contexts/CryptoDataContext';
import CryptoList from './components/CryptoList';

class App extends React.Component {

  render() {
    return (
      <div className="App">
        <CryptoDataContextProvider>
          <CryptoList />
        </CryptoDataContextProvider>
      </div>
    );
  }
}

export default App;
