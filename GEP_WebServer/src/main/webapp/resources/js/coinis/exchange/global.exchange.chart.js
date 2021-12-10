var chart;
function drawChart(itemCode, mktGrpId) {
	if(chart != null) {
		chart.setSymbol(itemCode, 30);
	} else {
		let thema = cu.getCookie('style').substr(2);
		
		chart = new TradingView.widget({
			width: 960,
			height: 472,
			symbol: itemCode,
			interval: 30,
			container_id: "chart",
			datafeed: new Datafeeds.UDFCompatibleDatafeed(__G_tv_datafeed_url + mktGrpId),
			library_path: '/resources/js/tradingview/charting_library/',
			locale: 'ko',
			drawings_access: { type: 'black', tools: [ { name: "Regression Trend" } ] },
			disabled_features: [
				"left_toolbar",
				"volume_force_overlay", 
				"header_saveload", 
				"header_compare",
				"header_interval_dialog_button",
				"show_interval_dialog_on_key_press",
				"header_symbol_search",
				"adaptive_logo",
				"header_undo_redo",
				"header_settings",
				"header_screenshot",
				"caption_buttons_text_if_possible",
				"use_localstorage_for_settings"
		    ],
			autosize: true,
			charts_storage_url: 'https://saveload.tradingview.com',
		    charts_storage_api_version: "1.1",
			client_id: 'www.coinis.com',
			user_id: 'coinis',
			timezone: 'Asia/Seoul',
			theme: 'dark',
			overrides: {
				"volumePaneSize": "medium",
				"mainSeriesProperties.style": 1,
				"mainSeriesProperties.candleStyle.upColor": "#f8413d",
				"mainSeriesProperties.candleStyle.downColor": "#0058d0",
				"mainSeriesProperties.candleStyle.borderUpColor": "#f8413d",
				"mainSeriesProperties.candleStyle.borderDownColor": "#0058d0",
				"mainSeriesProperties.candleStyle.wickUpColor": 'rgba(241,46,129,1)',
				"mainSeriesProperties.candleStyle.wickDownColor": 'rgba(77,120,242,1)',
				"paneProperties.topMargin" : 20,
			},
			studies_overrides: {
				"volume.volume.color.1":'rgba(241,46,129,0.5)',
		        "volume.volume.color.0":'rgba(77,120,242,0.5)',
		        "volume.show ma": false,
		        "volume.volume ma.color": "rgba( 14, 134, 22, 0.25)",
		        "volume.volume ma.linewidth": 1,
		        "volume.ma length": 15
			}
		});

		chart.onChartReady(function() {
			chart.chart(0).createStudy('Moving Average', false, false, [15], null, {"Plot.color" : "rgb(255,96,0)"});
			chart.chart(0).createStudy('Moving Average', false, false, [60], null, {"Plot.color" : "rgb(1,168,68)"});
		});
	}
}

