import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import * as CanvasJSReact from '../assets/canvasjs.react';
//var CanvasJSReact = require('../assets/canvasjs.react');
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;


export default class WalletChart extends Component {
	static contextType = CryptoDataContext;

	state = {
		//defaultWallet: [{y: 100, label: "fetching"}],
		wallet: []
	}

	componentDidMount() {
		// this.context.fetchUserWallet(
		// 	`http://localhost:8080/wallet?userId=${this.context.userId}`
		// );
		this.buildChartObject();
	}

	buildChartObject = () => {
		let chartObj = [];

		for (let currency of this.props.userWallet) {
			let singleCurrencyData = {"y": Math.round(currency.usdValue * 100) / 100, "label": currency.symbol};
			chartObj.push(singleCurrencyData);
		}
		this.setState({wallet : chartObj});
	}

	render() {
		const options = {
			exportEnabled: true,
			animationEnabled: true,
			title: {
				text: "Portfolio"
			},
			data: [{
				type: "pie",
				startAngle: 75,
				toolTipContent: "<b>{label}</b>: {y} USD",
				showInLegend: "true",
				legendText: "{label}",
				indexLabelFontSize: 16,
				indexLabel: "{label} - {y} USD",
				dataPoints: this.state.wallet
			}]
		}
		return (
			<div>
				<CanvasJSChart options={options}
				/* onRef={ref => this.chart = ref} */
				/>
				{/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
			</div>
		);
	}
}