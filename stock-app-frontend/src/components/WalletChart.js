import React, { Component } from "react";
import { CryptoDataContext } from "../contexts/CryptoDataContext";
import * as CanvasJSReact from '../assets/canvasjs.react';
//var CanvasJSReact = require('../assets/canvasjs.react');
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;


export default class WalletChart extends Component {
	static contextType = CryptoDataContext;

	state = {
		uploadedBalance: "",
		currentBalance: "",
		wallet: this.context.userWallet
	}

	componentDidMount() {
		this.context.fetchUserWallet(
		  "http://localhost:8080/wallet"
		);
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
				toolTipContent: "<b>{label}</b>: {y}%",
				showInLegend: "true",
				legendText: "{label}",
				indexLabelFontSize: 16,
				indexLabel: "{label} - {y}%",
				dataPoints: [
					{ y: 18, label: "Direct" },
					{ y: 49, label: "Organic Search" },
					{ y: 9, label: "Paid Search" },
					{ y: 5, label: "Referral" },
					{ y: 19, label: "Social" }
				]
			}]
		}
		return (
		<div>
			<CanvasJSChart options = {options}
				/* onRef={ref => this.chart = ref} */
			/>
			{/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
		</div>
		);
	}
}