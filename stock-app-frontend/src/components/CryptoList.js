import React, { Component } from "react";

import { CryptoDataContext } from "../contexts/CryptoDataContext";
import CryptoItem from "./CryptoItem";

export default class CryptoList extends Component {
  static contextType = CryptoDataContext;

  state = {
    sort_dir: "desc",
    sort_by: "market_cap"
  };

  getQueryParam(key) {
    let search = window.location.search;
    let params = new URLSearchParams(search);
    let param = params.get(key);
    return param;
  }

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

  sortByProperty = sort_by => {
    const sort_dir = this.state.sort_dir === "desc" ? "asc" : "desc";

    this.loadCryptoData(sort_by, sort_dir);
  };

  componentDidMount() {
    let sort_by = this.getQueryParam("sort_by");
    let sort_dir = this.getQueryParam("sort_dir");
    sort_by = sort_by ? sort_by : this.state.sort_by;
    sort_dir = sort_dir ? sort_dir : this.state.sort_dir;

    this.setState({
      sort_by: sort_by,
      sort_dir: sort_dir
    });

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
