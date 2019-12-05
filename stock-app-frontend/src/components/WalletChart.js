import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import * as CanvasJSReact from '../assets/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;


export default class WalletChart extends Component {
	static contextType = CryptoDataContext;

	state = {
		wallet: []
	}

	componentDidMount() {
		this.buildChartObject();
	}

	buildChartObject = () => {
		let chartObj = [];

		for (let currency of this.props.userWallet) {
			let singleCurrencyData = { "y": Math.round(currency.usdValue * 100) / 100, "label": currency.symbol };
			chartObj.push(singleCurrencyData);
		}
		this.setState({ wallet: chartObj });
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
				/>
			</div>
		);
	}
}