import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import CryptoItem from "./CryptoItem";

export default class CryptoList extends Component {
  static contextType = CryptoDataContext;

  state = {
    sort_dir: "desc",
    sort_by: "market_cap"
  };

  //we build the URL after getting the sorted data, so users can use the link
  loadCryptoData(sort_by, sort_dir) {
    const query_string = `?sort_by=${sort_by}&sort_dir=${sort_dir}`;
    const url = `http://localhost:8080/sorted${query_string}`;
    this.context.fetchAllCryptoData(url);

    this.setState({ sort_by, sort_dir });

    window.history.pushState(
      {},
      "Stock App",
      `http://localhost:3000/sorted${query_string}`
    );
  }

  //only the click event will navigate us here, thus reversing the sort_dir
  sortByProperty = sort_by => {
    const sort_dir = this.state.sort_dir === "desc" ? "asc" : "desc";

    this.loadCryptoData(sort_by, sort_dir);
  };

  // first we either get the params from the state (coming from the index page) or the URL (/sorted?sort_by...)
  // even if it comes from the URL, we store it in the state as well
  // so either way, the params will be in the state
  componentDidMount() {
    let sort_by = this.context.getQueryParam("sort_by");
    let sort_dir = this.context.getQueryParam("sort_dir");

    sort_by = sort_by ? sort_by : this.state.sort_by;
    sort_dir = sort_dir ? sort_dir : this.state.sort_dir;

    this.setState({ sort_by, sort_dir });

    this.loadCryptoData(sort_by, sort_dir);
  }

  render() {
    return (
      <div className="table-responsive text-nowrap">
        <table className="table table-striped table-hover">
          <thead className="black white-text">
            <tr>
              <th>
                <p>#</p>
              </th>
              <th>
                <p>Name</p>
              </th>
              <th>
                <p onClick={() => this.sortByProperty("symbol")}>Symbol</p>
              </th>
              <th>
                <p>Price</p>
              </th>
              <th>
                <p>Change (24h)</p>
              </th>
              <th>
                <p>Market Cap</p>
              </th>
              <th>
                <p>Volume (24h)</p>
              </th>
            </tr>
          </thead>
          <tbody>
            {this.context.cryptoData.map(cryptoCurrency => (
              <CryptoItem
                key={cryptoCurrency.name}
                cryptoData={cryptoCurrency}
              />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
