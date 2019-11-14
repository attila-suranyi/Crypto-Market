import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import {CryptoDataContext} from '../contexts/CryptoDataContext';
import CryptoItem from './CryptoItem';

export default class CryptoList extends Component {
    static contextType = CryptoDataContext;

    state = {
        sort_dir: "asc"
    }

    getQueryParam(key) {
        let search = window.location.search;
        let params = new URLSearchParams(search);
        let param = params.get(key);
        return param
    }

    sortByProperty = () => {

        let URL = "http://localhost:8080/sorted?sort_by=" + this.getQueryParam("sort_by") 
                    + "&sort_dir=" + this.getQueryParam("sort_dir");

        this.context.fetchAllCryptoData(URL);

        if (this.state.sort_dir === "desc") {
            this.setState({sort_dir: "asc"})
            console.log(this.state.sort_dir)
            return;
        } else {
            this.setState({sort_dir: "desc"})
            return;
        } 
    }

    componentDidMount() {
        if (this.getQueryParam("sort_dir")) {
            this.sortByProperty();
        }
    }

    render() {
        return  (
            <div className="table-responsive text-nowrap">
                <table className="table table-striped table-hover">
                    <thead className="black white-text">
                        <tr>
                            <th><p>#</p></th>
                            <th><p>Name</p></th>
                            <th><p><Link to={`/sorted?sort_by=symbol&sort_dir=${this.state.sort_dir}`} 
                                        >Symbol</Link></p></th>
                            <th><p>Price</p></th>
                            <th><p>Change (24h)</p></th>
                            <th><p>Market Cap</p></th>
                            <th><p>Volume (24h)</p></th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.context.cryptoData.map((cryptoCurrency) =>
                        <CryptoItem key={cryptoCurrency.name} cryptoData={cryptoCurrency}/>)}
                    </tbody>
                </table>
            </div>
        )
    }
}
