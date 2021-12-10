////////////////////////////////////////////////////////////////////
// Global 변수 선언
var yep_distribution_chart = null;
var yep_mining_chart = null;
var yep_staking_chart = null;
var yep_airdrop_chart = null;
var yep_freezing_chart = null;

var today    = new Date();
var curYear  = today.getFullYear();
var curMonth = today.getMonth() + 1;
var curDate  = today.getDate();
var currentDay = curYear + ((curMonth < 10) ? '0' + curMonth : curMonth + '' ) + ((curDate < 10) ? '0' + curDate : curDate );
var aaa = 1;

// Select Box(Year) Component
Vue.component('yahobit-year-selectbox', {
	props: {
		years: {type: Array, default: function () { return ['2019', '2020', '2021', '2022'] }},
		selectedYear: { type: String, default: "2019" }
	},
	template: 
		'<select class="select-box" @input="changeYear($event.target)">'
		+	'<option v-for="year in listYears" :selected="year == selectedYear" :value="year">{{ year }}년</option>'
		+ '</select>',
	computed: {
		listYears() {
			return ['2019', '2020', '2021', '2022'];
		},
	},
	data() {
		return {
			defaultSelectedYear: '2019'
		};
	},
	methods: {
		changeYear(item) {
			this.$emit("selected", item.id, item.value);
		}
	},
	watch: {
	}
});
// Select Box(Month) Component
Vue.component('yahobit-month-selectbox', {
	props: {
		months: {type: Array, default: function() { return ["01","02","03","04","05","06","07","08","09","10","11","12"] } },
		selectedMonth: { type: String, default: "01" }
	},
	template: 
		'<select class="select-box" @input="changeMonth($event.target)">'
		+	'<option v-for="month in listMonths" :selected="month == selectedMonth" :value="month">{{ month }} 월</option>'
		+ '</select>',
	computed: {
		listMonths() {
			return ["01","02","03","04","05","06","07","08","09","10","11","12"];
		},
	},
	data() {
		return {
			defaultSelectedMonth: '01'
		};
	},
	methods: {
		changeMonth(item) {
			//console.log(item.id);
			this.$emit("selected", item.id, item.value);
		}
	},
	watch: {
	}
});

// 전역 필터 등록
Vue.filter('numComma', function (value, prec, toFixed) {
	if (value == undefined || value == null) return '';
	value = '' + value;
	return nu.fixedCm(value, prec, (toFixed != undefined) ? true : false);
});
Vue.filter('dateFmt', function (value, formatStr, targetFormatStr) {
	if (value == undefined || value == null) return '';
	value = '' + value;
	return du.getCstmFrmt(value, formatStr, targetFormatStr);
});
Vue.filter('masking', function (value, showNum) {
	if (value == undefined || value == null) return '';
	value = '' + value;
	let split = value.split('@');
	return value.substring(0, showNum) + split[0].substr(showNum).replace(/(\w)|(\W)/g, '*') + '@';
});
Vue.filter('dateFmtMnt', function (date) {
	// console.log(moment(date).format('YYYY-MM-DD HH:mm:ss'));
	return moment(date).format('YYYY-MM-DD HH:mm:ss');
});
Vue.filter('nvl', function (value) {
	return nu.isN(value) ? value:'0.00';
});

////////////////////////////////////////////////////////////////////
// YEPRP-TAB-01 YEP 유통현황 Vue
var yep_distribution_status_vue = new Vue({
	el: '#vtab1',
	data: {
		total_qty: '100,000,000',
		undistribution_qty: '0',
		undistribution_per: '0',
		distribution_qty: '0',
		distribution_per: '0',
		staking_qty: '0',
		staking_per: '0',
		freeze_qty: '0',
		freeze_per: '0'
	},
	created : function () {
		$.ajax({
			url : "/alibit/yeprp/selectDistributionStatus",
			type : 'POST',
			dataType : 'json',
			error : function(data,status,error) {
				console.log("에러 >> " + JSON.stringify(data));
			},
			success : function(data, textStatus) {
				console.log(data);
				var base_day				= getParam(data, 'BASE_DAY', '');
				var sum_up_dt				= getParam(data, 'SUM_UP_DT', '');
				var tot_supply_qty			= parseInt(getParam(data, 'TOTAL_SUPPLY_QTY', '0'));
				var tot_un_distribute_qty	= parseInt(getParam(data, 'TOTAL_UN_DSTRBT_QTY', '0'));
				var tot_circulate_qty		= parseInt(getParam(data, 'TOTAL_CIRCUL_QTY', '0'));
				var tot_staking_qty			= parseInt(getParam(data, 'TOTAL_STAKING_QTY', '0'));
				var tot_freezing_qty		= parseInt(getParam(data, 'TOT_FREEZING_QTY', '0'));

				var tot_un_distr_per		= (tot_un_distribute_qty / tot_supply_qty) * 100;
				var tot_distr_per			= (tot_circulate_qty / tot_supply_qty) * 100;
				var tot_staking_per			= (tot_staking_qty / tot_supply_qty) * 100;
				var tot_freezing_per		= (tot_freezing_qty / tot_supply_qty) * 100;

				yep_distribution_status_vue.total_qty 				= tot_supply_qty;
				yep_distribution_status_vue.undistribution_qty 		= tot_un_distribute_qty;
				yep_distribution_status_vue.distribution_qty 		= tot_circulate_qty;
				yep_distribution_status_vue.staking_qty 			= tot_staking_qty;
				yep_distribution_status_vue.freeze_qty 				= tot_freezing_qty;

				yep_distribution_status_vue.undistribution_per 		= tot_un_distr_per;
				yep_distribution_status_vue.distribution_per 		= tot_distr_per;
				yep_distribution_status_vue.staking_per 			= tot_staking_per;
				yep_distribution_status_vue.freeze_per 				= tot_freezing_per;

				var ctx_dstrbt = document.getElementById('yep-rp-yep-distribution-status-chart').getContext('2d');
				yep_distribution_chart = new Chart(ctx_dstrbt, {
					type: 'pie',
					data: {
						datasets: [{
							data: [tot_un_distr_per, tot_distr_per, tot_staking_per, tot_freezing_per],
							backgroundColor: [
								"#607fd7",
								"#5db198",
								"#dbb460",
								"#e86ba1"
							],
							borderWidth: 2,
							weight:3
						}]
					},
					options: {
						title: {
							display: false,
							text: "YEP 유통현황",
							position: 'bottom',
							padding: 10
						},
						events: ['null']
					}
				});
				//yep_distribution_chart.data.datasets[0].data = [tot_un_distr_per, tot_distr_per, tot_staking_per, tot_freezing_per];
				//yep_distribution_chart.update();
			}
		});
	}
});

$('#vtab1').find('.yep_wrap').show();

////////////////////////////////////////////////////////////////////
// YEPRP-TAB-02 Mining Vue
var yep_mining_status_vue = new Vue({
	el: '#vtab2',
	data: {
		totMiningStatusInfo : {
			total_mining_qty: '0',
			total_mining_wait_qty: '0',
			total_mining_complete_qty: '0',
			total_mining_freezing_qty: '0',
			total_mining_wait_per: '0',
			total_mining_complete_per: '0',
			total_mining_freezing_per: '0',
			total_mining_compl_qty: '0',
			tot_freezing_qty: '0'
		},
		prevDayMiningDstrbInfo : { },
		toDayMiningDstrbInfo : { },
		todayRankerMinerList : [],
		prevDayMyMiningInfo : { },
		toDayMyMiningInfo : { },
		monthlyMiningList : [],
		myMiningList : [],
		myAutoMiningInfo : {
			total_usable_by_bc: '0'
		},

		selected_year: '',
		selected_month: '',

		yepRpAutoMiningReqQty: 0,
	},
	methods: {
		changeYear: function(id, val) {
			//console.log('id = ' + id + ', val = ' + val);
		},
		changeMonth: function(id, val) {
			//console.log('id = ' + id + ', val = ' + val);
			if(id == 'yep-rp-mining-daily-mining-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepMiningDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-mining-daily-mining-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MONTHLY_MINING_LIST', yep_mining_status_vue.monthlyMiningList );
			} else if(id == 'yep-rp-mining-daily-my-mining-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepMyMiningDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-mining-daily-my-mining-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MY_MINING_INFO_LIST', yep_mining_status_vue.myMiningList );
			}
		},
		// Auto Mining Max button click 처리
		setAutoMiningReqMax: function() {
			yep_mining_status_vue.yepRpAutoMiningReqQty = '' + parseFloat(yep_mining_status_vue.myAutoMiningInfo.total_usable_by_bc);
		},
		// Auto Mining 요청 버튼 클릭처리
		autoMiningRequestPopup: function() {
			/*alert('현재 성능개선 작업으로 잠시 기능을 이용하실 수 없습니다.');
			return false;*/

			var check_flag = true;
			if(yep_mining_status_vue.yepRpAutoMiningReqQty == undefined || yep_mining_status_vue.yepRpAutoMiningReqQty == '') check_flag = false;
			else if( parseFloat(yep_mining_status_vue.yepRpAutoMiningReqQty.replace(/,/g,'')) < 50000 ) check_flag = false;

			if(!check_flag) {
				lrt.client('최소재원보다 적어서는 안됩니다.', '요청수량 오류', function() {
					$('#yep-rp-auto-mining-request-qty').focus();
				}, null);
				return false;
			}
			
			// 2019-08-02 민성철 : 요청 가능 수량 확인 start
			if(parseFloat(yep_mining_status_vue.yepRpAutoMiningReqQty.replace(/,/g,'')) > yep_mining_status_vue.myAutoMiningInfo.total_usable_by_bc) {
				yep_mining_status_vue.yepRpAutoMiningReqQty = '' + parseFloat(yep_mining_status_vue.myAutoMiningInfo.total_usable_by_bc);
				lrt.client('요청 가능 수량을 초과하였습니다.', '요청수량 오류');
				return false;
			}
			// end
			
			$('.auto-mining-request-check').each(function() { $(this).prop('checked', false); });
			$('.auto-mining-request-all-check').prop('checked', false);
			$('.autom_pop').show();
			$('#yep-rp-auto-mining-popup-req-qty').val( yep_mining_status_vue.yepRpAutoMiningReqQty );
			yep_auto_mining_request_popup_vue.auto_mining_request_datetime = new Date();	// Auto Mining 요청시간을 버튼 눌렀을때로 변경
		},
	},
	watch:{
		yepRpAutoMiningReqQty(val) {
			// 2019-08-02 민성철 : 키 입력 받아 최대 수량 체크 구문 추가
			// this.yepRpAutoMiningReqQty = nu.fixedCm((''+val).replace(/,/g,''), 0);
			let numVal = (''+val).replace(/,/g,'');
			if(parseFloat(numVal) > yep_mining_status_vue.myAutoMiningInfo.total_usable_by_bc) {
				this.yepRpAutoMiningReqQty = nu.fixedCm(yep_mining_status_vue.myAutoMiningInfo.total_usable_by_bc, 0);
			} else {
				this.yepRpAutoMiningReqQty = nu.fixedCm(numVal, 0);
			}
			// end
		},
	},
	beforeCreate : function () {
		$.ajax({
			url : "/alibit/yeprp/selectYepMiningStatus",
			type : 'POST',
			dataType : 'json',
			data : {
				baseDate : currentDay
			},
			error : function(data,status,error) {
				console.log("에러 >> " + JSON.stringify(data));
			},
			success : function(data, textStatus) {
				console.log(data);
				var TOTAL_MINING_STATUS_MAP 			= getParam(data, 'TOTAL_MINING_STATUS_MAP', '');
				var PREV_DATE_MINING_DISTRIBUTION_MAP	= getParam(data, 'PREV_DATE_MINING_DISTRIBUTION_MAP', '');
				var CUR_DATE_MINING_DISTRIBUTION_MAP	= getParam(data, 'CUR_DATE_MINING_DISTRIBUTION_MAP', '');
				var TODAY_TOP50_YEP_MINERS_LIST			= getParam(data, 'TODAY_TOP50_YEP_MINERS_LIST', '');
				var MY_YESTERDAY_MINING_STATUS_MAP		= getParam(data, 'MY_YESTERDAY_MINING_STATUS_MAP', '');
				var MY_TODAY_MINING_STATUS_MAP			= getParam(data, 'MY_TODAY_MINING_STATUS_MAP', '');
				var MONTHLY_MINING_LIST					= getParam(data, 'MONTHLY_MINING_LIST', '');
				var MY_MINING_INFO_LIST					= getParam(data, 'MY_MINING_INFO_LIST', '');
				var MY_KRW_POSSES_MAP					= getParam(data, 'MY_KRW_POSSES_MAP', '');

				// 1. 상단 YEP Mining 현황 집계표 데이터 SET
				getDataObjFromObject(TOTAL_MINING_STATUS_MAP, yep_mining_status_vue.totMiningStatusInfo);
				var wait_per = 0, cmpl_per = 0, frz_per = 0;
				wait_per = parseInt(getParam(TOTAL_MINING_STATUS_MAP, 'TOTAL_MINING_WAIT_QTY', '0')) / parseInt(getParam(TOTAL_MINING_STATUS_MAP, 'TOTAL_MINING_QTY', '0')) * 100;
				cmpl_per = parseInt(getParam(TOTAL_MINING_STATUS_MAP, 'TOTAL_MINING_COMPL_QTY', '0')) / parseInt(getParam(TOTAL_MINING_STATUS_MAP, 'TOTAL_MINING_QTY', '0')) * 100;
				frz_per  = parseInt(getParam(TOTAL_MINING_STATUS_MAP, 'TOT_FREEZING_QTY', '0')) / parseInt(getParam(TOTAL_MINING_STATUS_MAP, 'TOTAL_MINING_QTY', '0')) * 100;
				
				var ctx_mining = document.getElementById('yep-rp-yep-mining-status-chart').getContext('2d');
				yep_mining_chart = new Chart(ctx_mining, {
					type: 'pie',
					data: {
						datasets: [{
							data: [wait_per, cmpl_per, frz_per],
							backgroundColor: [
								"#607fd7",
								"#5db198",
								"#dbb460"
							],
							borderWidth: 2,
							weight:3
						}]
					},
					options: {
						title: {
							display: false,
							text: "YEP Mining현황",
							position: 'bottom',
							padding: 10
						},
						events: ['null']
					}
				});
				
				//yep_mining_chart.data.datasets[0].data = [wait_per, cmpl_per, frz_per];
				//yep_mining_chart.update();

				// 2. 두번째 어제/오늘 발생수수료별 배분량 데이터 SET
				// 2.1 어제 데이터
				getDataObjFromObject(PREV_DATE_MINING_DISTRIBUTION_MAP, yep_mining_status_vue.prevDayMiningDstrbInfo);
				// 2.2 오늘 데이터
				getDataObjFromObject(CUR_DATE_MINING_DISTRIBUTION_MAP, yep_mining_status_vue.toDayMiningDstrbInfo);
				// 3. 세번째 오늘의 Top Ranker 데이터 SET
				getDataObjFromObject(TODAY_TOP50_YEP_MINERS_LIST, yep_mining_status_vue.todayRankerMinerList);
				// 4. 네번째 어제/오늘 나의 Mining 현황 데이터 SET
				// 4.1 어제 데이터
				getDataObjFromObject(MY_YESTERDAY_MINING_STATUS_MAP, yep_mining_status_vue.prevDayMyMiningInfo);
				// 4.2 오늘 데이터
				getDataObjFromObject(MY_TODAY_MINING_STATUS_MAP, yep_mining_status_vue.toDayMyMiningInfo);
				// 5. 월간 일별 Mining List 데이터 SET
				getDataObjFromObject(MONTHLY_MINING_LIST, yep_mining_status_vue.monthlyMiningList);
				// 6. 나의 Mining List 데이터 SET
				getDataObjFromObject(MY_MINING_INFO_LIST, yep_mining_status_vue.myMiningList);
				// 7. 나의 Auto Mining 설정 (자동채굴 기능)
				getDataObjFromObject(MY_KRW_POSSES_MAP, yep_mining_status_vue.myAutoMiningInfo);
			}
		});
	}
});

////////////////////////////////////////////////////////////////////
// YEPRP-TAB-03 Staking Vue
var yep_staking_status_vue = new Vue({
	el: '#vtab3',
	data: {
		totStakingStatusInfo : {
			total_distribution_qty: '0',
			total_circulate_qty: '0',
			total_circul_qty: '0',
			total_staking_qty: '0',
			total_freezing_qty: '0',
			tot_freezing_qty: '0'
		},
		prevDayStakingInfo : { },
		toDayStakingInfo : { },
		todayStakingRankerList : [],
		prevDayMyStakingInfo : { },
		toDayMyStakingInfo : { },
		myAvailableStakingInfo : {
			available_staking_qty: 0,
			available_unstaking_qty: 0
		},
		unStakingList : [{staking_mgt_no:0, req_qty:0, unstaking_req_qty:0, req_flag: false}],	// 회원의 staking 이력 list ... 여기서 선택하여 unstaking 요청을 진행함
		dailyStakingSummaryList : [],
		myStakingHistoryList : [],

		yepRpStakingReqQty: '',
		yepRpUnStakingReqQty: 0,
		yepRpUnStakingFreezeQty: 0,

		unStakingRequestMngNo: [],
	},
	methods: {
		setStakingReqMax: function() {
			yep_staking_status_vue.yepRpStakingReqQty = '' + parseFloat(yep_staking_status_vue.myAvailableStakingInfo.available_staking_qty);
		},
		setUnStakingReqMax: function() {
			yep_staking_status_vue.yepRpUnStakingReqQty = yep_staking_status_vue.myAvailableStakingInfo.available_unstaking_qty == null ? 0 : parseFloat(yep_staking_status_vue.myAvailableStakingInfo.available_unstaking_qty);
		},
		// Staking 요청 버튼 클릭처리
		stakingRequestPopup: function() {
			if(parseFloat('' + yep_staking_status_vue.yepRpStakingReqQty) > 0 
					&& parseFloat('' + yep_staking_status_vue.yepRpStakingReqQty.replace(/,/g,'')) <= parseFloat(yep_staking_status_vue.myAvailableStakingInfo.available_staking_qty)) {
				if(parseFloat('' + yep_staking_status_vue.yepRpStakingReqQty.replace(/,/g,'')) < yep_staking_status_vue.myAvailableStakingInfo.staking_min_qty) {
					//lrt.client('최소요청수량보다 적어서는 안됩니다.', '요청수량 오류', function() {
					lrt.client('Staking 최소수량 이상 요청하시기 바랍니다.', '요청수량 오류', function() {
						$('#yep-rp-staking-request-qty').focus();
					}, null);
					return false;
				}
				$('.staking-request-check').each(function() { $(this).prop('checked', false); });
				$('.staking-request-all-check').prop('checked', false);
				$('.staking_pop').show();
				$('#yep-rp-staking-popup-req-qty').val( yep_staking_status_vue.yepRpStakingReqQty );
				$('#yep-rp-staking-popup-balance-qty').val( nu.fixedCm( parseFloat(yep_staking_status_vue.myAvailableStakingInfo.available_staking_qty) - parseFloat(yep_staking_status_vue.yepRpStakingReqQty.replace(/,/g,'')) , 3) );
				yep_staking_request_popup_vue.staking_request_datetime = new Date();	// Staking 요청시간을 버튼 눌렀을때로 변경
			} else {
				lrt.client('요청수량을 확인하세요.', '요청수량 오류', function() {
					$('#yep-rp-staking-request-qty').focus();
				}, null);
			}
		},
		// Un Staking 요청 버튼 클릭처리
		unStakingRequestPopup: function() {
			if(parseFloat('' + yep_staking_status_vue.yepRpUnStakingReqQty) > 0 ) {
				$('.unstaking-request-check').each(function() { $(this).prop('checked', false); });
				$('.unstaking-request-all-check').prop('checked', false);
				$('.unstaking_pop').show();
				$('#yep-rp-unstaking-popup-req-qty').val( nu.fixedCm((parseFloat(''+yep_staking_status_vue.yepRpUnStakingReqQty.replace(/,/g,'')) - parseFloat(''+yep_staking_status_vue.yepRpUnStakingFreezeQty)).toFixed(3), 4) );
				$('#yep-rp-unstaking-popup-freeze-qty').val( nu.fixedCm( parseFloat((''+yep_staking_status_vue.yepRpUnStakingFreezeQty)).toFixed(3), 4 ) ) ;
				yep_unstaking_request_popup_vue.staking_request_datetime = new Date();	// UnStaking 요청시간을 버튼 눌렀을때로 변경
			} else {
				lrt.client('요청수량을 확인하세요.', '요청수량 오류', function() {
					$('#yep-rp-staking-request-qty').focus();
				}, null);
			}
		},
		changeYear: function(id, val) {
			//console.log('id = ' + id + ', val = ' + val);
		},
		changeMonth: function(id, val) {
			//console.log('id = ' + id + ', val = ' + val);
			if(id == 'yep-rp-staking-daily-staking-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepStakingDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-staking-daily-staking-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MONTHLY_STAKING_LIST', yep_staking_status_vue.dailyStakingSummaryList );
			} else if(id == 'yep-rp-staking-my-daily-staking-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepMyStakingDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-staking-my-daily-staking-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MY_STAKING_INFO_LIST', yep_staking_status_vue.myStakingHistoryList );
			}
		},
		// Unstaking 요청 check box Select event 처리
		toggleUnstakingRequestSelect: function(item, $event) {
			// Check box 해제시
			if (yep_staking_status_vue.unStakingRequestMngNo.includes(item.staking_mgt_no)) {
				yep_staking_status_vue.unStakingRequestMngNo.splice(yep_staking_status_vue.unStakingRequestMngNo.findIndex(v => v == item.staking_mgt_no), 1);
				var cur_unstaking_req_qty = parseInt(('' + yep_staking_status_vue.yepRpUnStakingReqQty).replace(/,/g,''));
				var cur_item_req_qty = (item.unstaking_req_qty == undefined || item.unstaking_req_qty == '') ? 0 : parseInt('' + item.unstaking_req_qty);
				// unstaking 수량 차감
				if(item.staking_dur_days < item.staking_obl_days){
					yep_staking_status_vue.yepRpUnStakingReqQty = nu.fixedCm('' + (cur_unstaking_req_qty - cur_item_req_qty), 0);
					// 누적일수가 의무일수보다 작을시 Freezing 수량 차감
					yep_staking_status_vue.yepRpUnStakingFreezeQty -= parseInt('' + item.unstaking_req_qty) * (item.basic_trans_rate - item.basic_trans_rate / item.staking_obl_days * item.staking_dur_days) / 100;
					item.unstaking_req_qty = '';					
				} else if(item.staking_dur_days >= item.staking_obl_days){
					yep_staking_status_vue.yepRpUnStakingReqQty = nu.fixedCm('' + (cur_unstaking_req_qty - cur_item_req_qty), 0);
					// 누적일수가 의무일수보다 많을시 Freezing 수량 차감
					yep_staking_status_vue.yepRpUnStakingFreezeQty -= 0;
					item.unstaking_req_qty = '';							
				}
			} else {
				yep_staking_status_vue.unStakingRequestMngNo.push(item.staking_mgt_no);
				
				// 2019-08-02 민성철 : input focus 처리
				this.$nextTick(function() {
					$($event.currentTarget).closest('tr').find('.mini').focus();
				});
				// end
			}
		},
		checkMaxVal: function(val, item) {
			// 최대치 초과 검사
			item.unstaking_req_qty = val.replace(/[^0-9]/g,'');
			if(val > item.req_qty)  item.unstaking_req_qty = item.req_qty;

			var unStakingRequestQty = 0, unStakingFreezeQty = 0;
			$(this.unStakingList).each(function(idx) {
				var eaData =  yep_staking_status_vue.unStakingList[idx];
				var mngNo = eaData.staking_mgt_no;
				if(yep_staking_status_vue.unStakingRequestMngNo.includes(mngNo) && eaData.unstaking_req_qty != '' && parseInt('' + eaData.unstaking_req_qty) > 0 && eaData.staking_dur_days < eaData.staking_obl_days) {
					unStakingRequestQty += parseInt('' + eaData.unstaking_req_qty);
					// 누적일수가 의무일수보다 작을시 Freezing 수량 증가
					// 동결물량 계산 :: 요청물량 X (동결전환비율 - 일당추가전환비율 X 누적기간) / 100
					unStakingFreezeQty += parseInt('' + eaData.unstaking_req_qty) * (eaData.basic_trans_rate - eaData.basic_trans_rate / eaData.staking_obl_days * eaData.staking_dur_days) / 100;
					this.req_flag = true;
				} else if(yep_staking_status_vue.unStakingRequestMngNo.includes(mngNo) && eaData.unstaking_req_qty != '' && parseInt('' + eaData.unstaking_req_qty) > 0 && eaData.staking_dur_days >= eaData.staking_obl_days){
					unStakingRequestQty += parseInt('' + eaData.unstaking_req_qty);
					// 누적일수가 의무일수보다 많을시 Freezing 수량 차감
					unStakingFreezeQty += 0;
					this.req_flag = true;					
				} else this.req_flag = false;
			});
			this.yepRpUnStakingReqQty = unStakingRequestQty;
			this.yepRpUnStakingFreezeQty = unStakingFreezeQty;
		}
	},
	watch:{
		yepRpStakingReqQty(val) {
			this.yepRpStakingReqQty = nu.fixedCm((''+val).replace(/,/g,''), 0);
		},
		yepRpUnStakingReqQty(val) {
			this.yepRpUnStakingReqQty = nu.fixedCm((''+val).replace(/,/g,''), 0);
		},
	},
	beforeCreate : function () {
		$.ajax({
			url : "/alibit/yeprp/selectYepStakingStatus",
			type : 'POST',
			dataType : 'json',
			data : {
				baseDate : currentDay
			},
			error : function(data,status,error) {
				console.log("에러 >> " + JSON.stringify(data));
			},
			success : function(data, textStatus) {
				console.log(data);
				var TOTAL_STAKING_STATUS_MAP 			= getParam(data, 'TOTAL_STAKING_STATUS_MAP', '');				//  상단 종합 Status 자료 select
				var PREV_DATE_STAKING_INFO_MAP			= getParam(data, 'PREV_DATE_STAKING_INFO_MAP', '');				//  어제의 Staking 참여, Freezing 자료 select
				var CUR_DATE_STAKING_INFO_MAP			= getParam(data, 'CUR_DATE_STAKING_INFO_MAP', '');				//  오늘의 Staking 참여, Freezing 자료 select
				var TODAY_TOP50_YEP_STAKING_RANKER_LIST	= getParam(data, 'TODAY_TOP50_YEP_STAKING_RANKER_LIST', '');	//  오늘의 YEP Staking 기여도 상위자 리스트 조회
				var MY_YESTERDAY_STAKING_STATUS_MAP		= getParam(data, 'MY_YESTERDAY_STAKING_STATUS_MAP', '');		//  어제의 나의 Staking 현황 조회
				var MY_TODAY_STAKING_STATUS_MAP			= getParam(data, 'MY_TODAY_STAKING_STATUS_MAP', '');			//  오늘의 나의 Staking 현황 조회
				var MY_AVAILABLE_STAKING_INFO_MAP		= getParam(data, 'MY_AVAILABLE_STAKING_INFO_MAP', '');			//  나의 Staking 설정 (YEP RP 참여) 데이터 조회
				var MY_UNSTAKING_HISTORY_LIST			= getParam(data, 'MY_UNSTAKING_HISTORY_LIST', '');				//  나의 Unstaking 이력 리스트 조회
				var MONTHLY_STAKING_LIST				= getParam(data, 'MONTHLY_STAKING_LIST', '');					//  일별 Staking 현황 리스트 조회
				var MY_STAKING_INFO_LIST				= getParam(data, 'MY_STAKING_INFO_LIST', '');					//  나의 일별 Staking 내역 리스트 조회
				
				// 1. 상단 YEP Staking 현황 집계표 데이터 SET
				getDataObjFromObject(TOTAL_STAKING_STATUS_MAP, yep_staking_status_vue.totStakingStatusInfo);
				yep_staking_status_vue.myAvailableStakingInfo.staking_min_qty			= getParam(TOTAL_STAKING_STATUS_MAP, 'STAKING_MIN_QTY', '0');	// Staking 가능 최소수량 세팅
				var circul_qty = 0, staking_qty = 0, frz_qty = 0;
				circul_qty = parseInt(getParam(TOTAL_STAKING_STATUS_MAP, 'TOTAL_CIRCUL_QTY', '0'));
				staking_qty = parseInt(getParam(TOTAL_STAKING_STATUS_MAP, 'TOTAL_STAKING_QTY', '0'));
				frz_qty = parseInt(getParam(TOTAL_STAKING_STATUS_MAP, 'TOT_FREEZING_QTY', '0'));
				var circul_per = 0, staking_per = 0, frz_per = 0;
				circul_per = circul_qty / (circul_qty + staking_qty + frz_qty) * 100;
				staking_per = staking_qty / (circul_qty + staking_qty + frz_qty) * 100;
				frz_per = frz_qty / (circul_qty + staking_qty + frz_qty) * 100;
				
				var ctx_staking = document.getElementById('yep-rp-yep-staking-status-chart').getContext('2d');
				yep_staking_chart = new Chart(ctx_staking, {
					type: 'pie',
					data: {
						datasets: [{
							data: [circul_per, staking_per, frz_per],
							backgroundColor: [
								"#607fd7",
								"#5db198",
								"#dbb460"
							],
							borderWidth: 2,
							weight:3
						}]
					},
					options: {
						title: {
							display: false,
							text: "YEP Staking 현황",
							position: 'bottom',
							padding: 10
						},
						events: ['null']
					}
				});
				//yep_staking_chart.data.datasets[0].data = [circul_per, staking_per, frz_per];
				//yep_staking_chart.update();

				// 2. 두번째 어제/오늘 Staking 현황 데이터 SET
				// 2.1 어제 데이터
				getDataObjFromObject(PREV_DATE_STAKING_INFO_MAP, yep_staking_status_vue.prevDayStakingInfo);
				// 2.2 오늘 데이터
				getDataObjFromObject(CUR_DATE_STAKING_INFO_MAP, yep_staking_status_vue.toDayStakingInfo);

				// 3. 세번째 오늘의 Top Ranker 데이터 SET
				getDataObjFromObject(TODAY_TOP50_YEP_STAKING_RANKER_LIST, yep_staking_status_vue.todayStakingRankerList);

				// 4. 네번째 어제/오늘 나의 Staking 현황 데이터 SET
				// 4.1 어제 데이터
				getDataObjFromObject(MY_YESTERDAY_STAKING_STATUS_MAP, yep_staking_status_vue.prevDayMyStakingInfo);
				// 4.2 오늘 데이터
				getDataObjFromObject(MY_TODAY_STAKING_STATUS_MAP, yep_staking_status_vue.toDayMyStakingInfo);

				// 5. 다섯번째 Staking / Unstaking 데이터 SET
				// 5.1 Staking 가능수량
				yep_staking_status_vue.myAvailableStakingInfo.available_staking_qty		= getParam(MY_AVAILABLE_STAKING_INFO_MAP, 'AVAILABLE_QTY', '0');
				yep_staking_status_vue.myAvailableStakingInfo.available_unstaking_qty	= parseFloat(getParam(MY_AVAILABLE_STAKING_INFO_MAP, 'STAKING_QTY', '0')) ; // - parseFloat(getParam(MY_AVAILABLE_STAKING_INFO_MAP, 'UNSTAKING_QTY', '0'));
				// 5.2 UnStaking List
				getDataObjFromObject(MY_UNSTAKING_HISTORY_LIST, yep_staking_status_vue.unStakingList);

				// 6. 여섯번째 일 Staking 내역 데이터 SET
				getDataObjFromObject(MONTHLY_STAKING_LIST, yep_staking_status_vue.dailyStakingSummaryList);

				// 7. 일곱번째 나의 Staking 내역 데이터 SET
				getDataObjFromObject(MY_STAKING_INFO_LIST, yep_staking_status_vue.myStakingHistoryList);
			}
		});
	}
});

////////////////////////////////////////////////////////////////////
// YEPRP-TAB-04 AirDrop Vue
var yep_airdrop_status_vue = new Vue({
	el: '#vtab4',
	data: {
		totAirdropStatusInfo: {
			nor_reward_tot_amt: 0,
			platform_fee_reward_amt: 0,
			platform_fee_tot_amt: 0,
			ref_reward_tot_amt: 0,
			spec_reward_tot_amt: 0,
			trade_fee_tot_amt: 0,
		},
		prevDayAirdropInfo: {},
		curDayAirdropInfo: {},
		todayMiningStakingContributioInfo: {},
		dailyAirdropSummaryList: [],
		myAirdropHistoryList: [],
		myEtcAirdropHistoryList: [],
	},
	methods: {
		changeYear: function(id, val) {
			//console.log('id = ' + id + ', val = ' + val);
		},
		changeMonth: function(id, val) {
			//console.log('id = ' + id + ', val = ' + val);
			if(id == 'yep-rp-airdrop-monthly-airdrop-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepAirdropDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-airdrop-monthly-airdrop-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MONTHLY_AIRDROP_LIST', yep_airdrop_status_vue.dailyAirdropSummaryList );
			} else if(id == 'yep-rp-airdrop-my-monthly-airdrop-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepMyAirdropDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-airdrop-my-monthly-airdrop-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MY_AIRDROP_INFO_LIST', yep_airdrop_status_vue.myAirdropHistoryList );
			} else if(id == 'yep-rp-airdrop-my-etc-monthly-airdrop-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepMyAirdropEtcDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-airdrop-my-etc-monthly-airdrop-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MY_ETC_AIRDROP_INFO_LIST', yep_airdrop_status_vue.myEtcAirdropHistoryList );
			}
		}
	},
	beforeCreate : function () {
		$.ajax({
			url : "/alibit/yeprp/selectYepAirDropStatus",
			type : 'POST',
			dataType : 'json',
			data : {
				baseDate : currentDay
			},
			error : function(data,status,error) {
				console.log("에러 >> " + JSON.stringify(data));
			},
			success : function(data, textStatus) {
				console.log(data);
				var TOTAL_AIRDROP_STATUS_MAP					= getParam(data, 'TOTAL_AIRDROP_STATUS_MAP', '');						// 상단 종합 AirDrop 현황 자료 select
				var PREV_DATE_AIRDROP_INFO_MAP					= getParam(data, 'PREV_DATE_AIRDROP_INFO_MAP', '');						// 어제의 거래수소료, Staking Reward, 기여보너스 자료 select
				var CUR_DATE_AIRDROP_INFO_MAP					= getParam(data, 'CUR_DATE_AIRDROP_INFO_MAP', '');						// 오늘의 거래수소료, Staking Reward, 기여보너스 자료 select
				var TODAY_MINING_STAKING_CONTRIBUTION_INFO_MAP	= getParam(data, 'TODAY_MINING_STAKING_CONTRIBUTION_INFO_MAP', '');		// 나의 Mining/Staking 기여도 현황 조회
				var MONTHLY_AIRDROP_LIST						= getParam(data, 'MONTHLY_AIRDROP_LIST', '');							// 일별 Airdrop 현황 리스트 조회
				var MY_AIRDROP_INFO_LIST						= getParam(data, 'MY_AIRDROP_INFO_LIST', '');							// 나의 일별 Airdrop 내역 리스트 조회
				var MY_ETC_AIRDROP_INFO_LIST					= getParam(data, 'MY_ETC_AIRDROP_INFO_LIST', '');						// 나의 일별 기타  Airdrop 내역 리스트 조회
				
				var normal_reward_per = 0, special_reward_per = 0, referral_reward_per = 0, platform_reward_pool_per = 0, platform_reward_airdrop_per = 0;
				normal_reward_per			= parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'NOR_REWARD_TOT_AMT', '0')) / parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'TRADE_FEE_TOT_AMT', '0')) * 100;
				special_reward_per			= parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'SPEC_REWARD_TOT_AMT', '0')) / parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'TRADE_FEE_TOT_AMT', '0')) * 100;
				referral_reward_per			= parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'REF_REWARD_TOT_AMT', '0')) / parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'TRADE_FEE_TOT_AMT', '0')) * 100;
				platform_reward_pool_per	= parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'PLATFORM_FEE_TOT_AMT', '0')) / parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'TRADE_FEE_TOT_AMT', '0')) * 100;
				platform_reward_airdrop_per	= parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'PLATFORM_FEE_REWARD_AMT', '0')) / parseInt(getParam(TOTAL_AIRDROP_STATUS_MAP, 'TRADE_FEE_TOT_AMT', '0')) * 100;
				
				var ctx_airdrop = document.getElementById('yep-rp-yep-airdrop-status-chart').getContext('2d');
				yep_airdrop_chart = new Chart(ctx_airdrop, {
					type: 'pie',
					data: {
						datasets: [{
							data: [normal_reward_per, special_reward_per, referral_reward_per, platform_reward_pool_per, platform_reward_airdrop_per],
							backgroundColor: [
								"#5e80d6",
								"#5bb095",
								"#dbb460",
								"#e86ba1",
								"#996ae6",
							],
							borderWidth: 2,
							weight:3
						}]
					},
					options: {
						title: {
							display: false,
							text: "YEP AirDrop 현황",
							position: 'bottom',
							padding: 10
						},
						events: ['null']
					}
				});
				//yep_airdrop_chart.data.datasets[0].data = [normal_reward_per, special_reward_per, referral_reward_per, platform_reward_pool_per, platform_reward_airdrop_per];
				//yep_airdrop_chart.update();

				getDataObjFromObject(TOTAL_AIRDROP_STATUS_MAP, yep_airdrop_status_vue.totAirdropStatusInfo);
				getDataObjFromObject(PREV_DATE_AIRDROP_INFO_MAP, yep_airdrop_status_vue.prevDayAirdropInfo);
				getDataObjFromObject(CUR_DATE_AIRDROP_INFO_MAP, yep_airdrop_status_vue.curDayAirdropInfo);
				getDataObjFromObject(TODAY_MINING_STAKING_CONTRIBUTION_INFO_MAP, yep_airdrop_status_vue.todayMiningStakingContributioInfo);
				getDataObjFromObject(MONTHLY_AIRDROP_LIST, yep_airdrop_status_vue.dailyAirdropSummaryList);
				getDataObjFromObject(MY_AIRDROP_INFO_LIST, yep_airdrop_status_vue.myAirdropHistoryList);
				getDataObjFromObject(MY_ETC_AIRDROP_INFO_LIST, yep_airdrop_status_vue.myEtcAirdropHistoryList);
			}
		});
	}
});

////////////////////////////////////////////////////////////////////
// YEPRP-TAB-04 Freezing Vue
var yep_freezing_status_vue = new Vue({
	el: '#vtab5',
	data: {
		totFreezingStatusInfo: {
			total_supply_qty : 0,
			base_day : '',
			total_un_dstrbt_qty : 0,
			total_circul_qty : 0,
			total_staking_qty : 0,
			tot_freezing_qty : 0,
		},
		prevDayFreezingInfo: {},
		curDayFreezingInfo: {},
		dailyFreezingSummaryList: [],
	},
	methods: {
		changeYear: function(id, val) {
			console.log('id = ' + id + ', val = ' + val);
		},
		changeMonth: function(id, val) {
			console.log('id = ' + id + ', val = ' + val);
			if(id == 'yep-rp-freezing-daily-freezing-history-month-select-box') {
				var url = '/alibit/yeprp/selectYepFreezingDailyHistoryList';
				var params = {baseMonth: $('#yep-rp-freezing-daily-freezing-history-year-select-box').val() + val};
				getDataRefresh(url, params, 'MONTHLY_FREEZING_LIST', yep_freezing_status_vue.dailyFreezingSummaryList );
			}
		}
	},
	beforeCreate : function () {
		$.ajax({
			url : "/alibit/yeprp/selectYepFreezingStatus",
			type : 'POST',
			dataType : 'json',
			data : {
				baseDate : currentDay
			},
			error : function(data,status,error) {
				console.log("에러 >> " + JSON.stringify(data));
			},
			success : function(data, textStatus) {
				console.log(data);
				var TOTAL_FREEZING_STATUS_MAP					= getParam(data, 'TOTAL_FREEZING_STATUS_MAP', '');						// 상단 종합 Freezing 현황 자료 select
				var PREV_DATE_FREEZING_INFO_MAP					= getParam(data, 'PREV_DATE_FREEZING_INFO_MAP', '');					// 어제의 Freezing 수량 및 누계  정보 자료 select
				var CUR_DATE_FREEZING_INFO_MAP					= getParam(data, 'CUR_DATE_FREEZING_INFO_MAP', '');						// 오늘의 Freezing 수량 및 누계  정보 자료 select
				var MONTHLY_FREEZING_LIST						= getParam(data, 'MONTHLY_FREEZING_LIST', '');							// 일별 Freezing 내역 리스트 조회

				var circul_per = 0, staking_per = 0, frz_per = 0;
				circul_per  = parseInt(getParam(TOTAL_FREEZING_STATUS_MAP, 'TOTAL_CIRCUL_QTY', '0')) / parseInt(getParam(TOTAL_FREEZING_STATUS_MAP, 'TOTAL_SUPPLY_QTY', '0')) * 100;
				staking_per = parseInt(getParam(TOTAL_FREEZING_STATUS_MAP, 'TOTAL_STAKING_QTY', '0')) / parseInt(getParam(TOTAL_FREEZING_STATUS_MAP, 'TOTAL_SUPPLY_QTY', '0')) * 100;
				frz_per     = parseInt(getParam(TOTAL_FREEZING_STATUS_MAP, 'TOT_FREEZING_QTY', '0')) / parseInt(getParam(TOTAL_FREEZING_STATUS_MAP, 'TOTAL_SUPPLY_QTY', '0')) * 100;
				
				var ctx_freezing = document.getElementById('yep-rp-yep-freezing-status-chart').getContext('2d');
				yep_freezing_chart = new Chart(ctx_freezing, {
					type: 'pie',
					data: {
						datasets: [{
							data: [circul_per, staking_per, frz_per],
							backgroundColor: [
								"#607fd7",
								"#5db198",
								"#dbb460"
							],
							borderWidth: 2,
							weight:3
						}]
					},
					options: {
						title: {
							display: false,
							text: "YEP Freezing 현황",
							position: 'bottom',
							padding: 10
						},
						events: ['null']
					}
				});
				//yep_freezing_chart.data.datasets[0].data = [circul_per, staking_per, frz_per];
				//yep_freezing_chart.update();

				getDataObjFromObject(TOTAL_FREEZING_STATUS_MAP, yep_freezing_status_vue.totFreezingStatusInfo);
				getDataObjFromObject(PREV_DATE_FREEZING_INFO_MAP, yep_freezing_status_vue.prevDayFreezingInfo);
				getDataObjFromObject(CUR_DATE_FREEZING_INFO_MAP, yep_freezing_status_vue.curDayFreezingInfo);
				getDataObjFromObject(MONTHLY_FREEZING_LIST, yep_freezing_status_vue.dailyFreezingSummaryList);
			}
		});
	}
});

////////////////////////////////////////////////////////////////////
// YEPRP-POPUP-01 Staking요청 Popup Vue
var yep_staking_request_popup_vue = new Vue({
	el: '#staking-request-popup',
	data: {
		staking_request_datetime: new Date()
	},
	methods: {
		checkAll: function (val) {
			var chk = val.target.checked;
			$('.staking-request-check').each(function() {
				$(this).prop('checked', chk);
			});
		},
		confirmStaking: function() {
			var agree_chking = true;
			$('.staking-request-check').each(function() {
				if($(this).is(":checked") == false) {
					agree_chking = false;
					lrt.client('모든 내용에 동의하셔야 합니다.', '동의', null, null);
					return false;
				}
			});
			if(agree_chking) {
				$.ajax({
					url : "/alibit/yeprp/requestStaking",
					type : 'POST',
					dataType : 'json',
					data : {
						stake_amount : ('' + yep_staking_status_vue.yepRpStakingReqQty).replace(/,/g,'')
					},
					error : function(data,status,error) {
						console.log("Staking 요청 에러 >> " + JSON.stringify(data));
						lrt.client('Staking 요청 에러가 발생하였습니다.', 'Staking Error', null, null);
						$('.staking_pop').hide();
					},
					success : function(data, textStatus) {
						console.log(data);
						if(data.rtnCd == 0) {
							$('.staking_pop').hide();
							// 일별 staking 리스트 갱신
							var url = '/alibit/yeprp/selectYepStakingDailyHistoryList';
							var params = {baseMonth: $('#yep-rp-staking-daily-staking-history-year-select-box').val() + $('#yep-rp-staking-daily-staking-history-month-select-box').val()};
							getDataRefresh(url, params, 'MONTHLY_STAKING_LIST', yep_staking_status_vue.dailyStakingSummaryList );
							// 일별 나의 staking 리스트 갱신
							url = '/alibit/yeprp/selectYepMyStakingDailyHistoryList';
							params = {baseMonth: $('#yep-rp-staking-my-daily-staking-history-year-select-box').val() + $('#yep-rp-staking-my-daily-staking-history-month-select-box').val()};
							getDataRefresh(url, params, 'MY_STAKING_INFO_LIST', yep_staking_status_vue.myStakingHistoryList );
							
							yep_staking_status_vue.yepRpStakingReqQty = 0;
							// 나의 Staking 가능수량, Unstaking 가능 수량 갱신
							$.ajax({
								url : '/alibit/yeprp/selectYepMyStakingAvailableInfo',
								type : 'POST',
								dataType : 'json',
								data : params,
								error : function(data,status,error) {
									console.log("에러 >> " + JSON.stringify(data));
								},
								success : function(data, textStatus) {
									var MY_AVAILABLE_STAKING_INFO_MAP = getParam(data, 'MY_AVAILABLE_STAKING_INFO_MAP', '');
									yep_staking_status_vue.myAvailableStakingInfo.available_staking_qty		= getParam(MY_AVAILABLE_STAKING_INFO_MAP, 'AVAILABLE_QTY', '0');
									yep_staking_status_vue.myAvailableStakingInfo.available_unstaking_qty	= parseFloat(getParam(MY_AVAILABLE_STAKING_INFO_MAP, 'STAKING_QTY', '0')) ; // - parseFloat(getParam(MY_AVAILABLE_STAKING_INFO_MAP, 'UNSTAKING_QTY', '0'));
								}
							});

							// 나의 unstaking에서 staking history list 갱신
							url = '/alibit/yeprp/selectYepUnstakingHistoryList';
							getDataRefresh(url, {}, 'MY_UNSTAKING_HISTORY_LIST', yep_staking_status_vue.unStakingList );

						} else {
							lrt.client(wordbook[data.rtnCd], '');
						}
					}
				});
			}
		}
	},
});

////////////////////////////////////////////////////////////////////
// YEPRP-POPUP-02 UnStaking요청 Popup Vue
var yep_unstaking_request_popup_vue = new Vue({
	el: '#unstaking-request-popup',
	data: {
		staking_request_datetime: new Date()
	},
	methods: {
		checkAll: function (val) {
			var chk = val.target.checked;
			$('.unstaking-request-check').each(function() {
				$(this).prop('checked', chk);
			});
		},
		confirmUnStaking: function() {
			var agree_chking = true;
			$('.unstaking-request-check').each(function() {
				if($(this).is(":checked") == false) {
					agree_chking = false;
					lrt.client('모든 내용에 동의하셔야 합니다.', '동의', null, null);
					return false;
				}
			});
			if(agree_chking) {
				var unstaking_req_list = [];
				$(yep_staking_status_vue.unStakingList).each(function(idx) {
					if(yep_staking_status_vue.unStakingList[idx].req_flag && yep_staking_status_vue.unStakingList[idx].unstaking_req_qty > 0) unstaking_req_list.push(yep_staking_status_vue.unStakingList[idx]);
				});
				$.ajax({
					url : "/alibit/yeprp/requestUnStaking",
					type : 'POST',
					dataType : 'json',
					data : {
						//unstake_amount : ('' + yep_staking_status_vue.yepRpStakingReqQty).replace(/,/g,''),
						unstake_info_list : JSON.stringify(unstaking_req_list)
					},
					error : function(data,status,error) {
						console.log("UnStaking 요청 에러 >> " + JSON.stringify(data));
						lrt.client('UnStaking 요청 에러가 발생하였습니다.', 'UnStaking Error', null, null);
						$('.unstaking_pop').hide();
					},
					success : function(data, textStatus) {
						console.log(data);
						if(data.rtnCd == 0) {
							$('.unstaking_pop').hide();
							yep_staking_status_vue.unStakingRequestMngNo = [];
							yep_staking_status_vue.yepRpUnStakingReqQty = 0;
							lrt.client('Unstaking을 확정하였습니다.<br>잔고에는 Pending(대기시간) 후 반영됩니다.', 'UnStaking 확정 성공', null, null);
						} else {
							lrt.client(wordbook[data.rtnCd], '');
						}
					}
				});
			}
		}
	},
});

////////////////////////////////////////////////////////////////////
// YEPRP-POPUP-03 Auto Mining 요청 Popup Vue
var yep_auto_mining_request_popup_vue = new Vue({
	el: '#auto-mining-request-popup',
	data: {
		auto_mining_request_datetime: new Date()
	},
	methods: {
		checkAll: function (val) {
			var chk = val.target.checked;
			$('.auto-mining-request-check').each(function() {
				$(this).prop('checked', chk);
			});
		},
		confirmAutoMining: function() {
			var agree_chking = true;
			$('.auto-mining-request-check').each(function() {
				if($(this).is(":checked") == false) {
					agree_chking = false;
					lrt.client('모든 내용에 동의하셔야 합니다.', '동의', null, null);
					return false;
				}
			});
			if(agree_chking) {
				$.ajax({
					url : "/alibit/yeprp/requestAutoMining",
					type : 'POST',
					dataType : 'json',
					data : {
						auto_mining_amount : ('' + yep_mining_status_vue.yepRpAutoMiningReqQty).replace(/,/g,''),
					},
					error : function(data,status,error) {
						console.log("Auto Mining 요청 에러 >> " + JSON.stringify(data));
						lrt.client('Auto Mining 요청 에러가 발생하였습니다.', 'Auto Mining Error', null, null);
						$('.autom_pop').hide();
					},
					success : function(data, textStatus) {
						console.log(data);
						if(data.rtnCd == 0) {
							$('.autom_pop').hide();
							// lrt.client('Auto Mining 설정에 성공하였습니다.', 'Auto Mining 설정 성공', null, null);
							// 나의 KRW 잔고 변경
							$.ajax({
								url : '/alibit/yeprp/selectMyKrwPossession',
								type : 'POST',
								dataType : 'json',
								//data : params,
								error : function(data,status,error) {
									console.log("KRW 잔고 변경 에러 >> " + JSON.stringify(data));
								},
								success : function(data, textStatus) {
									autoMiningProcessTimerFunc();
									var MY_KRW_POSSES_MAP = getParam(data, 'MY_KRW_POSSES_MAP', '');
									//console.log(MY_KRW_POSSES_MAP);
									yep_mining_status_vue.myAutoMiningInfo.total_usable_by_bc = getParam(MY_KRW_POSSES_MAP, 'TOTAL_USABLE_BY_BC', '');
									yep_mining_status_vue.yepRpAutoMiningReqQty = 0;
								}
							});
						} else {
							lrt.client(wordbook[data.rtnCd], '');
						}
					}
				});
			}
		}
	},
});

////////////////////////////////////////////////////////////////////
// YEPRP-COMMON 공통 함수
function getDataRefresh(url, params, result_args_name, target_model) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		data : params,
		error : function(data,status,error) {
			console.log("에러 >> " + JSON.stringify(data));
		},
		success : function(data, textStatus) {
			//console.log(data);
			var SERVER_RESPONSE_DATA					= getParam(data, result_args_name, '');
			getDataObjFromObject(SERVER_RESPONSE_DATA, target_model);
		}
	});
}
function getOneDataRefresh(url, params, result_args_name, target_key_name, target_model, model_key) {
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		data : params,
		error : function(data,status,error) {
			console.log("에러 >> " + JSON.stringify(data));
		},
		success : function(data, textStatus) {
			//console.log(data);
			target_model[model_key] = getParam(getParam(data, result_args_name, ''), target_key_name, '');
		}
	});
}

// Sever에서 받은 Json Data를 파싱하여 targetObject에 넣음 (key는 lower case로 변환)
function getDataObjFromObject(srcObj, targetObj) {
	// Array list의 경우
	if(Array.isArray(srcObj)) {
		targetObj.splice(0, targetObj.length);	// array clear
		$(srcObj).each(function(idx) {
			var item = srcObj[idx];
			var tmpObj = {};
			Object.keys(item).forEach(function(key) {
				tmpObj[key.toLowerCase()] = item[key];
			});
			targetObj.push(tmpObj);
		});
	// 일반 Json Object의 경우
	} else {
		Object.keys(srcObj).forEach(function(key) {
			targetObj[key.toLowerCase()] = srcObj[key];
		});
	}
}

var __G_Auto_Mining_Process_timer = 0;
function autoMiningProcessTimerFunc() {
	var timeout = 3;
	__G_Auto_Mining_Process_timer = setInterval(function() {
		if(timeout == 3) {
			$('#yep-rp-auto-mining-progress-start').attr('class', 'on');
			$('#yep-rp-auto-mining-progress-ing').attr('class', '');
			$('#yep-rp-auto-mining-progress-end').attr('class', '');
		} else if(timeout == 2) {
			$('#yep-rp-auto-mining-progress-start').attr('class', '');
			$('#yep-rp-auto-mining-progress-ing').attr('class', 'on');
			$('#yep-rp-auto-mining-progress-end').attr('class', '');
		} else {
			$('#yep-rp-auto-mining-progress-start').attr('class', '');
			$('#yep-rp-auto-mining-progress-ing').attr('class', '');
			$('#yep-rp-auto-mining-progress-end').attr('class', 'on');
		}

		if (--timeout < 0) {
			timeout = 0;
			clearInterval(__G_Auto_Mining_Process_timer);
			$('#yep-rp-auto-mining-progress-start').attr('class', '');
			$('#yep-rp-auto-mining-progress-ing').attr('class', '');
			$('#yep-rp-auto-mining-progress-end').attr('class', '');
			lrt.client('Auto Mining 설정에 성공하였습니다.', 'Auto Mining 설정 성공', null, null);
		}
	}, 200);
}