var candleChart;
function drawChart(itemCode, chartKind, mktGrpId) {
	if(candleChart != null) {
		candleChart.setSymbol(itemCode, chartKind);
	} else {
		let thema = cu.getCookie('style').substr(2);
		
		candleChart = new TradingView.widget({
			width: 960,
			height: 472,
			symbol: itemCode,
			interval: chartKind,
			container_id: "candleChart",
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
			custom_css_url: '/resources/css/tradingview/tradingview.css',
			charts_storage_url: 'https://saveload.tradingview.com',
		    charts_storage_api_version: "1.1",
			client_id: 'www.ali-bit.com',
			user_id: 'coinis',
			timezone: 'Asia/Seoul',
			theme: thema,
			overrides: {
				"volumePaneSize": "medium",
				"mainSeriesProperties.style": 1,
				"mainSeriesProperties.candleStyle.upColor": "#f8413d",
				"mainSeriesProperties.candleStyle.downColor": "#0058d0",
				"mainSeriesProperties.candleStyle.borderUpColor": "#f8413d",
				"mainSeriesProperties.candleStyle.borderDownColor": "#0058d0",
				"mainSeriesProperties.candleStyle.wickUpColor": 'rgba( 248, 65, 61, 1)',
				"mainSeriesProperties.candleStyle.wickDownColor": 'rgba( 0, 88, 208, 1)',
				"paneProperties.topMargin" : 20,
			},
			studies_overrides: {
				"volume.volume.color.1":'rgba( 248, 65, 61, 0.5)',
	  			"volume.volume.color.0":'rgba( 0, 88, 208, 0.5)',
	  			"volume.show ma": false,
	  		    "volume.volume ma.color": "rgba( 14, 134, 22, 0.25)",
	  		    "volume.volume ma.linewidth": 1,
	  		    "volume.ma length": 15
			}
		});

		candleChart.onChartReady(function() {
			candleChart.chart(0).createStudy('Moving Average', false, false, [15], null, {"Plot.color" : "rgb(255,96,0)"});
			candleChart.chart(0).createStudy('Moving Average', false, false, [60], null, {"Plot.color" : "rgb(1,168,68)"});
		});
	}
}

//area chart 초기화
var areaInterval = null;
var areaChart;
function createAreaChart(itemCode, mktGrpId) {
	clearInterval(areaInterval);
	getAreaData(itemCode, mktGrpId);
	
	areaInterval = setInterval(function () {
		getAreaData(itemCode, mktGrpId);
	}, 60000);
}

function getAreaData(itemCode, mktGrpId) {
	var url = __G_tv_datafeed_url + mktGrpId + '/history?';
	url += 'symbol=' + itemCode;
	url += '&resolution=1';
	url += '&from=' + epochTime(true);
	url += '&to=' + epochTime(false);
	
	$.ajax({
		url : url,
		type : 'GET',
		dataType : 'json',
		error : function(data,status,error) {
		},
		success : function(data, textStatus) {
			var up = ['up'], down = ['down'];
			var price = data.c;
			var prevFlag = 0, flag = 0, count = 0;

			if(price != undefined) {
				for(var i=0; i<price.length; i++) {
					var cal = price[i] - selected.prevClosePrc;

					if( i == 0 ) {
						if( cal > 0 ) { prevFlag = 1; up.push(cal); down.push(null); } 
						else { prevFlag = -1; up.push(null); down.push(cal); }
					} else {
						if( cal > 0 ) { flag = 1; } else { flag = -1; }
						if(prevFlag != flag) { up.push(0); down.push(0); prevFlag = flag; count++; } 
						
						if( cal > 0 ) { up.push(cal); down.push(null); } 
						else { up.push(null); down.push(cal); }
					}
				}
				
				for(var i=up.length; i<48+count; i++) { up.push(null); down.push(null); }

				if(document.visibilityState == 'visible') {
					drawAreaChart(up, down);
				}
			} else {
				if(areaChart != undefined) {
					areaChart.destroy();
					areaChart = undefined;
				}
			}
		}
	});
}

function drawAreaChart(up, down) {
	if(areaChart != undefined) {
		areaChart.load({
			columns:[ up, down ]
		});
	} else {
		areaChart = c3.generate({
			bindto: '#areaChart',
			size: { width: 140, height: 58 },
		    data: { columns: [ up, down ], types: { up: 'area', down: 'area' }, colors: { up: '#f8413d', down: '#0058d0' }, },
		    area: { zerobased: false },
		    axis: { y: { show: false }, x: { show: false } },
	        legend: { show: false },
	        tooltip: { show: false },
		    point: { show: false },
		    transition: {
	            duration: 0
	        }
		});
	}
}

function epochTime(init) {
	var d = new Date();
	
	if(init) {
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		d.setMilliseconds(0);
	}
	
	return d.getTime()/1000;
}