<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<script src="/resources/js/jquery/Chart.min.js"></script>
<script src="/resources/js/vuejs/core/vue.js"></script>
<script src="/resources/js/vuejs/lib/moment.js"></script>

<!--컨텐츠 내용 영역 시작-->
<section class="main">

	<article class="layoutE">

		<aside>
			<h1 class="menutitle">
				YEP RP
			</h1>
			<ul class="vtab">
				<li><a href="#vtab1" class="vselected">YEP 유통 현황</a></li>
				<li><a href="#vtab2">Mining</a></li>
				<li><a href="#vtab3">Staking</a></li>
				<li><a href="#vtab4">AirDrop</a></li>
				<li><a href="#vtab5">Freeze</a></li>
			</ul>

		</aside>
		
		<div class="content">
			<ul class="vpanel">
				<!-- ================================================================================================================= -->
				<!--                                        yep 유통현황 시작                                                           -->
				<!-- ================================================================================================================= -->
				<li id="vtab1">
					<h1 class="subtitle">YEP 유통 현황</h1>

					<div class="yep_wrap" style="display:none;"><!-- yep_wrap start -->

						<div class="left_side"><!-- left_side start -->
							<div style="width:206px;height:206px;margin-left:37px;">
								<canvas id="yep-rp-yep-distribution-status-chart" width="300" height="300"></canvas>
							</div>
							<!-- <img src="/resources/img/yaho/yeprp_graph.png" alt= "" /> -->
						</div><!-- left_side end -->

						<div class="right_side"><!-- right_side start -->

							<div class="type_yep"><!-- type_yep start -->
								<table>
									<colgroup>
										<col style="width: 20%;">
										<col style="width: 20%;">
										<col style="width: 25%;">
										<col style="width: 35%;"> 
									</colgroup>
									<thead>
									<tr>
										<th colspan="2">총발행량</th>
										<th class="type_num">100.00 %</th>
										<th class="type_num">{{total_qty | numComma(0)}} YEP</th>
									</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="2"><span class="legend"></span><span>미배분량</span></td>
											<td class="type_num"><span>{{undistribution_per | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{undistribution_qty | numComma(0)}} YEP</span></td>
										</tr>
										<tr>
											<td colspan="2"><span class="legend green"></span><span>유통량</span></td>
											<td class="type_num"><span>{{distribution_per | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{distribution_qty | numComma(0)}} YEP</span></td>
										</tr>
										<tr>
											<td rowspan="2" class="border_right">비유통량 </td>
											<td ><span class="legend yellow"></span><span>Staking</span></td>
											<td class="type_num"><span>{{staking_per | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{staking_qty | numComma(0)}} YEP</span></td>
										</tr>
										<tr>
											<td><span class="legend pink"></span><span>Freeze</span></td>
											<td class="type_num"><span>{{freeze_per | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{freeze_qty | numComma(0)}} YEP</span></td>
										</tr>
									</tbody>
								</table>
								<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>
							</div><!-- type_yep end -->

						</div><!-- right_side end -->

					</div><!-- yep_wrap end -->

				</li>
				<!--yep 유통현황 끝-->

				<!-- ================================================================================================================= -->
				<!--                                                   mining 시작                                                      -->
				<!-- ================================================================================================================= -->
				<li id="vtab2" style="display:none;">
					<h1 class="subtitle">Mining</h1>
					<h2 class="tabletitle bolder">YEP Mining 현황<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->

						<div class="left_side"><!-- left_side start -->
							<div style="width:165px;height:165px;margin-left:57.5px;">
								<canvas id="yep-rp-yep-mining-status-chart" width="400" height="400"></canvas>
							</div>
						</div><!-- left_side end -->

						<div class="right_side"><!-- right_side start -->

							<div class="type_yep"><!-- type_yep start -->
							<table>
								<colgroup>
									<col style="width: 40%;">
									<col style="width: 25%;">
									<col style="width: 35%;"> 
								</colgroup>
								<thead>
									<tr>
										<th>Mining Pool</th>
										<th class="type_num">100.00 %</th>
										<th class="type_num">{{totMiningStatusInfo.total_mining_qty | numComma(0)}} YEP</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><span class="legend"></span><span>마이닝 대기</span></td>
										<td class="type_num"><span>{{parseFloat(parseInt(totMiningStatusInfo.total_mining_wait_qty) / parseInt(totMiningStatusInfo.total_mining_qty) * 100).toFixed(2) | nvl()}} %</span></td>
										<td class="type_num"><span>{{totMiningStatusInfo.total_mining_wait_qty | numComma(0)}} YEP</span></td>
									</tr>
									<tr>
										<td><span class="legend green"></span><span>마이닝 완료</span></td>
										<td class="type_num"><span>{{parseFloat(parseInt(totMiningStatusInfo.total_mining_compl_qty) / parseInt(totMiningStatusInfo.total_mining_qty) * 100).toFixed(2) | nvl()}} %</span></td>
										<td class="type_num"><span>{{totMiningStatusInfo.total_mining_compl_qty | numComma(0)}} YEP</span></td>
									</tr>
									<tr>
										<td><span class="legend yellow"></span><span>Freeze A</span></td>
										<td class="type_num"><span>{{parseFloat(parseInt(totMiningStatusInfo.tot_freezing_qty) / parseInt(totMiningStatusInfo.total_mining_qty) * 100).toFixed(2) | nvl()}} %</span></td>
										<td class="type_num"><span>{{totMiningStatusInfo.tot_freezing_qty | numComma(0)}} YEP</span></td>
									</tr>
								</tbody>
							</table>
							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>
							</div><!-- type_yep end -->

						</div><!-- right_side end -->

						<div class="p50box_wrap"><!-- p50box_wrap start -->

							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title"><span>어제</span></p>
								<ul class="cost_list"> 
									<li class="first desc">
										<strong>채굴단가</strong>
										<p><span class="num imp_num">{{prevDayMiningDstrbInfo.mining_unit_price | numComma(0)}}</span> KRW</p>
									</li>
									<li>
										<strong>전체 발생거래수수료</strong>
										<p><span class="num">{{prevDayMiningDstrbInfo.fee_amt | numComma(0)}}</span>KRW</p>
									</li>
							<!-- 		<li>
										<strong>YEP 평단가</strong>
										<p><span class="num">{{prevDayMiningDstrbInfo.yeb_avg_price | numComma(0)}}</span>KRW</p>
									</li> -->
									<li class="comp">
										<p>조정기여마이닝량({{(prevDayMiningDstrbInfo.real_dstrbt_rate) * 1 | numComma(2, true)}} %)</p>
									</li>
									<li>
										<strong>실배분량</strong>
										<p><span class="num">{{prevDayMiningDstrbInfo.real_dstrbt_qty | numComma(3, true)}}</span>YEP</p>
									</li>
									<li>
										<strong>Freeze(조정비율반영)</strong>
										<p><span class="num">{{prevDayMiningDstrbInfo.daily_mining_frz_qty | numComma(3, true)}}</span>YEP</p>
									</li>
									<!-- <li class="comp">
										<p>조정기여보너스량({{(prevDayMiningDstrbInfo.real_dstrbt_rate) * 1 | numComma(2, true)}} %)</p>
									</li>
									<li>
										<strong>실배분량</strong>
										<p><span class="num">{{prevDayMiningDstrbInfo.cntrbt_bonus_dstrbt_qty | numComma(3, true)}}</span>YEP</p>
										<p><span class="num">{{(22260 * prevDayMiningDstrbInfo.real_dstrbt_rate / 100) | numComma(3, true)}}</span>YEP</p>
									</li>
									<li>
										<strong>Freeze</strong>
										<p><span class="num">{{22260 - (22260 * prevDayMiningDstrbInfo.real_dstrbt_rate / 100) | numComma(3, true)}}</span>YEP</p>
									</li> -->
									<li class="comp">
										<p>발생수수료가 100,000 KRW일 때 받는 YEP (단순계산 추정치)</p>
									</li>
									<li>
										<strong><span>100,000</span> KRW</strong>
										<p><span class="num">{{(100000 / prevDayMiningDstrbInfo.mining_unit_price ) | numComma(3, true)}}</span> YEP</p>
									</li>
								</ul>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title today"><span>오늘</span></p>
								<ul class="cost_list"> 
									<li class="first desc">
										<strong>채굴단가</strong>
										<p><span class="num imp_num">{{toDayMiningDstrbInfo.mining_unit_price | numComma(0)}}</span> KRW</p>
									</li>
									<li>
										<strong>전체 발생거래수수료</strong>
										<p><span class="num">{{toDayMiningDstrbInfo.fee_amt | numComma(0)}}</span>KRW</p>
									</li>
							<!-- 		<li>
										<strong>YEP 평단가</strong>
										<p><span class="num">{{toDayMiningDstrbInfo.yeb_avg_price | numComma(0)}}</span>KRW</p>
									</li> -->
									<li class="comp">
										<p>조정기여마이닝량({{(toDayMiningDstrbInfo.real_dstrbt_rate) * 1 | numComma(2, true)}} %)</p>
									</li>
									<li>
										<strong>실배분량</strong>
										<p><span class="num">{{toDayMiningDstrbInfo.real_dstrbt_qty | numComma(3, true)}}</span>YEP</p>
									</li>
									<li>
										<strong>Freeze(조정비율반영)</strong>
										<p><span class="num">{{toDayMiningDstrbInfo.daily_mining_frz_qty | numComma(3, true)}}</span>YEP</p>
									</li>
									<!-- <li class="comp">
										<p>조정기여보너스량({{(toDayMiningDstrbInfo.real_dstrbt_rate) * 1 | numComma(2, true)}} %)</p>
									</li>
									<li>
										<strong>실배분량</strong>
										<p><span class="num">{{(22260 * toDayMiningDstrbInfo.real_dstrbt_rate / 100) | numComma(3, true)}}</span>YEP</p>
									</li>
									<li>
										<strong>Freeze</strong>
										<p><span class="num">{{toDayMiningDstrbInfo.cntrbt_bonus_frz_qty | numComma(3, true)}}</span>YEP</p>
										<p><span class="num">{{22260 - (22260 * toDayMiningDstrbInfo.real_dstrbt_rate / 100) | numComma(3, true)}}</span>YEP</p>
									</li> -->
									<li class="comp">
										<p>발생수수료가 100,000 KRW일 때 받는 YEP (단순계산 추정치)</p>
									</li>
									<li>
										<strong><span>100,000</span> KRW</strong>
										<p><span class="num">{{(100000 / toDayMiningDstrbInfo.mining_unit_price) | numComma(3, true)}}</span> YEP</p>
									</li>
								</ul>
							</div><!-- p50box_right end -->

							<p class="mining_desc">※ 채굴단가는 단순계산 (<em><span>전체 발생거래수수료</span><span>전체산정량, 일가변마이닝량 비교 조건값</span></em>) 참고 데이터 입니다. 회원님의 정보는 나의 Mining 내역에서 확인하시기 바랍니다.</p>

							<p class="red_desc">※ 조정비율 반영된 일가변보너스 동결수량은 상단 현황에 반영됩니다.</p>
							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>

						</div><!-- p50box_wrap end -->

					</div><!-- yep_wrap end -->
					
					<h2 class="tabletitle bolder">Mining 기여도 상위자 현황<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->

					<div class="table_opt"><!-- table_opt start -->
					<strong class="title_today">오늘</strong>
					<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않습니다.<br />※ 1시간 단위로 갱신됩니다.</p>
					</div><!-- table_opt end -->

					<div class="more_table"><!-- more_table start -->

					<div class="folding"><!-- folding start -->
					<table>
						<colgroup>
							<col style="width:12%;">
							<col style="width:12%;">
							<col style="width:19%;">
							<col style="width:26%;"> 
							<col style="width:19%;">
							<col style="width:12%;">
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>대상인원</th>
								<th class="cost">발생거래수수료 (KRW)</th>
								<th>기여도</th>
								<th>기여보너스 지급률</th>
								<th>대상자(이메일)</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="item in todayRankerMinerList">
								<td><p>상위 {{(item.top_pct == 0 ? 1 : item.top_pct) | numComma(0, true)}}%</p></td>
								<td><p>1명</p></td>
								<td><p class="cost">{{item.fee_amt | numComma(0)}}</p></td>
								<td><p>{{item.cntrbt_rate | numComma(2, true)}}%</p></td>
								<td><p v-if="item.weight_applied_pct == 0">-</p><p v-else>{{ item.weight_applied_pct | numComma(0, true)}} %</p></td>
								<td><p>{{item.user_id | masking(2)}}</p></td>
							</tr>
						</tbody>
					</table>
					</div><!-- folding end -->

					<p class="btn-folding"><button type="button">더보기</button></p>
					</div><!-- more_table end -->

					</div><!-- yep_wrap end -->
					
					<h2 class="tabletitle bolder">나의 Mining 현황<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap type2"><!-- yep_wrap start -->

						<div class="p50box_wrap"><!-- p50box_wrap start -->

							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title"><span>어제</span><em><span>상위</span><span v-if="prevDayMyMiningInfo.top_pct == 0">0.1</span><span v-else-if="prevDayMyMiningInfo.top_pct == 100"></span><span v-else>{{prevDayMyMiningInfo.top_pct | numComma(1, true)}}</span> %</em></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>기여도</strong>
										<p><span class="num imp_num">{{prevDayMyMiningInfo.cntrbt_rate | numComma(2, true)}}</span>%</p>
									</li>
									<li>
										<strong>나의 발생거래수수료</strong>
										<p><span class="num">{{0 + prevDayMyMiningInfo.fee_amt | numComma(0)}}</span>KRW</p>
									</li>
									<li>
										<strong>전체 발생거래수수료</strong>
										<p><span class="num">{{prevDayMyMiningInfo.tot_fee_amt | numComma(0)}}</span>KRW</p>
									</li>
								</ul>

							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title today"><span>오늘</span><em><span>상위</span><span v-if="toDayMyMiningInfo.top_pct == 0">0.1</span><span v-else-if="toDayMyMiningInfo.top_pct == 100"></span><span v-else>{{toDayMyMiningInfo.top_pct | numComma(1, true)}}</span> %</em></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>기여도</strong>
										<p><span class="num imp_num">{{toDayMyMiningInfo.cntrbt_rate | numComma(2, true)}}</span>%</p>
									</li>
									<li>
										<strong>나의 발생거래수수료</strong>
										<p><span class="num">{{0 + toDayMyMiningInfo.fee_amt | numComma(0)}}</span>KRW</p>
									</li>
									<li>
										<strong>전체 발생거래수수료</strong>
										<p><span class="num">{{toDayMyMiningInfo.tot_fee_amt | numComma(0)}}</span>KRW</p>
									</li>
								</ul>
							</div><!-- p50box_right end -->

							<p class="red_desc">※ 기여도는 마이닝마스터 가중치가 적용된 수치입니다.</p>
							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>

						</div><!-- p50box_wrap end -->

					</div><!-- yep_wrap end -->

<!-- Auto Minig 기능 잠시 막아둠 (버그수정) 2019.09.06-->
					<!-- 2019-07-30 추가 시작 -->
					<h2 class="tabletitle bolder">나의 Auto Mining 설정(자동채굴 기능)</h2>

					<div class="yep_wrap type3 border_r"><!-- yep_wrap start -->

						<div class="p50box_wrap"><!-- p50box_wrap start -->
							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title today"><span>Auto Mining</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>Auto Mining 가능 수량</strong>
										<p><span class="num imp_num">{{myAutoMiningInfo.total_usable_by_bc | numComma(0, true)}}</span>KRW</p>
									</li>
									<li class="input_type">
										<strong>Auto Mining 요청</strong>
										<p>
											<span>입력</span>
											<input type="text" id="yep-rp-auto-mining-request-qty" placeholder="0" v-model="yepRpAutoMiningReqQty" />
											<button class="popClick" v-on:click="autoMiningRequestPopup">요청</button>
											<em>최소재원 <span>50,000</span>KRW</em>
											<a href="javascript:void(0);" v-on:click="setAutoMiningReqMax">MAX</a>
										</p>
									</li>
								</ul>
								<p class="red_desc">※ 최소재원은 시장상황에 따라 변동됩니다.</p>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title"><span>진행사항</span></p>
								<ul class="autom_step">
									<li class="" id="yep-rp-auto-mining-progress-start"><span>시작</span></li>
									<li class="" id="yep-rp-auto-mining-progress-ing"><span>진행 중</span></li>
									<li class="" id="yep-rp-auto-mining-progress-end"><span>완료</span></li>
								</ul>
							</div><!-- p50box_right end -->
						</div><!-- p50box_wrap end -->
						
						<!-- 2019-08-01 수정(ul추가 시작) -->
						<ul style="margin-top:20px; border-top:1px solid #dae0e6; border-bottom:1px solid #dae0e6;">
							<li style="font-size: 12px; color:#888!important; text-align: center;">※ Auto Mining 거래는 한번의 요청으로 입력한 재원을 모두 소진하여, 거래수수료를 발생시키는 거래입니다.</li>
							<li style="font-size: 12px; color:#888!important; text-align: center;">※ 호가창에 주문은 들어가지 않으며, 거래완료 내역은 '체결내역' 및 '회원체결'창에서 확인할 수 있습니다.</li>
							<li style="font-size: 12px; color:red; text-align: center;">※ Auto Mining 특성상 호가창에 주문이 안들어가므로 호가와 시장가의 차이 및 미체결 거래가 발생할 수 있습니다.</li>
						</ul>
						<!-- 2019-08-01 수정(ul추가 끝) -->
					</div><!-- yep_wrap end --> <!-- 2019-07-30 추가 끝 -->
					
					<div class="yep_tab"><!-- yep_tab start --><!-- 2019-07-19 수정(div 추가) -->

						<!-- 2019-07-19 수정(ul 추가 시작) -->
						<ul class="yep_tab_btn">
							<li class="on"><a href="#">일 Mining 내역</a></li>
							<li><a href="#">나의 Mining 내역</a></li>
						</ul>
						<!-- 2019-07-19 수정(ul 추가 끝) -->

						<div class="yep_tab_cont"><!-- yep_tab_cont start --><!-- 2019-07-19 수정(div 추가) -->

							<h2 class="tabletitle bolder"><span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

							<div class="yep_wrap"><!-- yep_wrap start -->
								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-mining-daily-mining-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-mining-daily-mining-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:10%;">
												<col style="width:18%;">
												<col style="width:15%;">
												<col style="width:15%;">
												<!-- <col style="width:15%;"> -->
												<col style="width:12%;">
												<col style="width:15%;">
											</colgroup>
											<thead>
												<tr>
													<th>날짜</th>
													<th class="cost">전체 발생거래수수료(KRW)</th>
													<th class="cost">채굴단가</th>
													<th class="cost">조정기여마이닝량(YEP)</th>
													<!-- <th class="cost">조정기여보너스량(YEP)</th> -->
													<th class="cost">Freeze A(YEP)</th>
													<th class="cost">10만 KRW 당(YEP)</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="monthlyMiningList.length < 1">
													<td colspan="6" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in monthlyMiningList">
													<td><p>{{item.base_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.fee_amt | numComma(0)}}</p></td>
													<td><p class="cost">{{item.mining_unit_price | numComma(0)}}</p></td>
													<td><p class="cost">{{item.dstrbt_qty_by_cntrbt | numComma(3, true)}}</p></td>
													<!-- <td><p class="cost">{{item.cntrbt_bonus_dstrbt_qty | numComma(3, true)}}</p></td> -->
													<td><p class="cost">{{item.freezing_qty | numComma(3, true)}}</p></td>
													<td><p class="cost">{{item.yep_per_10k | numComma(0)}}</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->
									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->

								<!-- <div>
									<yahobit-pagination :total="49" :per-page="10" v-on:input="test"></yahobit-pagination>
								</div> -->

							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end --><!-- 2019-07-19 수정(div 추가) -->

						<div class="yep_tab_cont"><!-- yep_tab_cont start --><!-- 2019-07-19 수정(div 추가) --> 
							<h2 class="tabletitle bolder"><span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

							<div class="yep_wrap"><!-- yep_wrap start -->

								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-mining-daily-my-mining-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-mining-daily-my-mining-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
									<p class="red_desc">※ 마이닝마스터 가중치가 반영된 내역입니다.</p>
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:15%;">
												<col style="width:18%;">
												<col style="width:17%;">
												<col style="width:15%;">
												<col style="width:18%;">
												<col style="width:17%;">
											</colgroup>
											<thead>
												<tr>
													<th>날짜</th>
													<th class="cost">발생거래수수료(KRW)</th>
													<th>마이닝마스터</th>
													<th>기여도</th>
													<th class="cost">채굴량(YEP)</th>
													<th class="cost">채굴단가(KRW)</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="myMiningList.length < 1">
													<td colspan="6" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in myMiningList">
													<td><p>{{item.trade_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.fee_amt | numComma(0)}}</p></td>
													<td><p>{{(item.apply_compl_yn == 1) ? '적용' : '-'}}</p></td>
													<td><p>{{item.cntrbt_rate | numComma(2, true)}}%</p></td>
													<td><p class="cost">{{(item.real_dstrbt_qty) | numComma(3, true)}}</p></td>
													<td><p class="cost">{{item.mining_unit_price | numComma(0)}}</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->
									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->

							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end --><!-- 2019-07-19 수정(div 추가) -->

					</div><!-- yep_tab end --><!-- 2019-07-19 수정(div 추가) -->

				</li>
				<!--mining 끝-->

				<!-- ================================================================================================================= -->
				<!--                                                  staking 시작                                                      -->
				<!-- ================================================================================================================= -->
				<li id="vtab3" style="display:none;">
					<h1 class="subtitle">Staking</h1>

					<h2 class="tabletitle bolder">YEP Staking 현황<!-- <span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span> --></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->

						<div class="left_side"><!-- left_side start -->
							<div style="width:165px;height:165px;margin-left:57.5px;">
								<canvas id="yep-rp-yep-staking-status-chart" width="400" height="400"></canvas>
							</div>
						</div><!-- left_side end -->

						<div class="right_side"><!-- right_side start -->
							<div class="type_yep"><!-- type_yep start -->
								<table>
									<colgroup>
										<col style="width: 20%;">
										<col style="width: 20%;">
										<col style="width: 25%;">
										<col style="width: 35%;"> 
									</colgroup>
<!-- 									<thead>
										<tr>
											<th colspan="2">배분량</th>
											<th class="type_num">100.00 %</th>
											<th class="type_num">{{totStakingStatusInfo.total_circul_qty + totStakingStatusInfo.total_staking_qty + totStakingStatusInfo.tot_freezing_qty | numComma(0)}} YEP</th>
											<th class="type_num">{{totStakingStatusInfo.tot_dstrbt_qty | numComma(0)}} YEP</th>
										</tr>
									</thead> -->
									<thead>
										<tr>
											<th colspan="2"><span class="legend"></span><span>유통량</span></th>
											<th class="type_num"><span style="font-size:14px;">{{parseFloat(parseInt(totStakingStatusInfo.total_circul_qty) / (totStakingStatusInfo.total_circul_qty + totStakingStatusInfo.total_staking_qty + totStakingStatusInfo.tot_freezing_qty) * 100).toFixed(2) | nvl()}} %</span></th>
											<th class="type_num"><span style="font-size:14px;">{{totStakingStatusInfo.total_circul_qty | numComma(0)}} YEP</span></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td rowspan="2" class="border_right">비유통량</td>
											<td><span class="legend green"></span><span>Staking</span></td>
											<td class="type_num"><span>{{parseFloat(parseInt(totStakingStatusInfo.total_staking_qty) / (totStakingStatusInfo.total_circul_qty + totStakingStatusInfo.total_staking_qty + totStakingStatusInfo.tot_freezing_qty) * 100).toFixed(2) | nvl()}} %</span></td>
											<td class="type_num"><span>{{totStakingStatusInfo.total_staking_qty | numComma(0)}} YEP</span></td>
										</tr>
										<tr>
											<td><span class="legend yellow"></span><span>Freeze B</span></td>
											<td class="type_num"><span>{{parseFloat(parseInt(totStakingStatusInfo.tot_freezing_qty) / (totStakingStatusInfo.total_circul_qty + totStakingStatusInfo.total_staking_qty + totStakingStatusInfo.tot_freezing_qty) * 100).toFixed(2) | nvl()}} %</span></td>
											<td class="type_num"><span>{{totStakingStatusInfo.freezing_compl_qty | numComma(0)}} YEP</span></td>
										</tr>
									</tbody>
								</table>
								<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>
							</div><!-- type_yep end -->
						</div><!-- right_side end -->

						<div class="p50box_wrap"><!-- p50box_wrap start -->
							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title"><span>어제</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>전체 Staking 수량</strong>
										<p><span class="num">{{prevDayStakingInfo.total_staking_qty | numComma(0)}}</span>YEP</p>
									</li>
									<li class="first">
										<strong>평균 Staking 수량</strong>
										<p><span class="num">{{prevDayStakingInfo.avg_staking_qty | numComma(3, true)}}</span>YEP</p>
									</li>
						<!-- 			<li>
										<strong>전체 Staking 참여자수</strong>
										<p><span class="num">{{prevDayStakingInfo.tot_staking_users | numComma(0)}}</span>명</p>
									</li> -->
							<!-- 		<li>
										<strong>Staking 수량</strong>
										<p><span class="num">{{prevDayStakingInfo.staking_qty | numComma(3, true)}}</span>YEP</p>
									</li> -->
									<li>
										<strong>유통전환 수량</strong>
										<p><span class="num">{{prevDayStakingInfo.un_freezing_qty | numComma(3, true)}}</span>YEP</p>
									</li>
									<li>
										<strong>Freeze B 수량</strong>
										<p><span class="num">{{prevDayStakingInfo.freezing_qty | numComma(3, true)}}</span>YEP</p>
									</li>
								</ul>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title today"><span>오늘</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>전체 Staking 수량</strong>
										<p><span class="num">{{toDayStakingInfo.total_staking_qty | numComma(0)}}</span>YEP</p>
									</li>
									<li class="first">
										<strong>평균 Staking 수량</strong>
										<p><span class="num">{{toDayStakingInfo.avg_staking_qty | numComma(3, true)}}</span>YEP</p>
									</li>
						<!-- 			<li>
										<strong>전체 Staking 참여자수</strong>
										<p><span class="num">{{toDayStakingInfo.tot_staking_users | numComma(0)}}</span>명</p>
									</li> -->
						<!-- 			<li>
										<strong>Staking 수량</strong>
										<p><span class="num">{{toDayStakingInfo.staking_qty | numComma(3, true)}}</span>YEP</p>
									</li> -->
									<li>
										<strong>유통전환 수량</strong>
										<p><span class="num">{{toDayStakingInfo.un_freezing_qty | numComma(3, true)}}</span>YEP</p>
									</li>
									<li>
										<strong>Freeze B 수량</strong>
										<p><span class="num">{{toDayStakingInfo.freezing_qty | numComma(3, true)}}</span>YEP</p>
									</li>
								</ul>
							</div><!-- p50box_right end -->

							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>

						</div><!-- p50box_wrap end -->

					</div><!-- yep_wrap end -->

					<h2 class="tabletitle bolder">Staking 기여도 상위자 현황<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->
						<div class="table_opt"><!-- table_opt start -->
							<strong class="title_today">오늘</strong>
							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않습니다.<br />※ 1시간 단위로 갱신됩니다.</p>
						</div><!-- table_opt end -->

						<div class="more_table"><!-- more_table start -->
							<div class="folding"><!-- folding start -->
								<table>
									<colgroup>
										<col style="width:12%;">
										<col style="width:12%;">
										<col style="width:17%;">
										<col style="width:13%;"> 
										<col style="width:17%;"> 
										<col style="width:17%;">
										<col style="width:12%;">
									</colgroup>
									<thead>
										<tr>
											<th>순위</th>
											<th>대상인원</th>
											<th class="cost">Staking 환산총액(KRW)</th>
											<th>기여도</th>
											<th>Special Reward 가중치</th>
											<th>기여보너스 지급률</th>
											<th>대상자(이메일)</th>
										</tr>
									</thead>
									<tbody>
										<tr v-for="item in todayStakingRankerList">
											<td><p>상위 {{(item.top_pct == 0 ? 1 : item.top_pct) | numComma(0, true)}}%</p></td>
											<td><p>1명</p></td>
											<td><p class="cost">{{item.avg_prc_staking_amt | numComma(0)}}</p></td>
											<td><p>{{item.avg_prc_staking_cntrbt_pct | numComma(2, true)}}%</p></td>
											<td><p>{{(item.cntrbt_weight_applied_pct) + 100 | numComma(0)}}%</p></td>
											<!-- <td><p>{{item.cntrbt_weight_applied_pct | numComma(2, true)}}%</p></td> -->
											<td><p v-if="item.cntrbt_weight_applied_pct == 0">-</p><p v-else>{{ item.cntrbt_weight_applied_pct | numComma(0, true)}} %</p></td>
											<td><p>{{item.user_id | masking(2)}}</p></td>
										</tr>
									</tbody>
								</table>
							</div><!-- folding end -->

							<p class="btn-folding"><button type="button">더보기</button></p>
						</div><!-- more_table end -->

					</div><!-- yep_wrap end -->

					<h2 class="tabletitle bolder">나의 Staking 현황<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap type2"><!-- yep_wrap start -->
						<div class="p50box_wrap"><!-- p50box_wrap start -->

							<div class="p50box_left mb20"><!-- p50box_left start -->
								<p class="cost_title"><span>어제</span><em><span>상위</span><span v-if="prevDayMyStakingInfo.staking_cntrbt_pct == 0"></span><span v-else>{{prevDayMyStakingInfo.top_pct | numComma(1)}}</span>%</em></p>
								<ul class="cost_list"> 
									<li class="first line4_type2">
										<strong>기여도</strong>
										<p><span class="num">{{prevDayMyStakingInfo.staking_cntrbt_pct | numComma(2, true)}}</span>%</p>
									</li>
									<li>
										<strong>나의 Staking 총수량</strong>
										<p><span class="num">{{prevDayMyStakingInfo.staking_qty | numComma(3)}}</span> YEP</p>
									</li>
									<li class="divine">
										<strong>전체 Staking 총수량</strong>
										<p><span class="num">{{prevDayMyStakingInfo.total_staking_qty | numComma(3)}}</span> YEP</p>
									</li>
									<li>
										<strong>나의 Staking 총환산</strong>
										<p><span class="num">{{prevDayMyStakingInfo.staking_qty * prevDayMyStakingInfo.avg_buy_price | numComma(0)}}</span> KRW</p>
									</li>
								</ul>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title today"><span>오늘</span><em><span>상위</span><span v-if="toDayMyStakingInfo.staking_cntrbt_pct == 0"></span><span v-else>{{toDayMyStakingInfo.top_pct | numComma(1)}}</span>%</em></p>
								<ul class="cost_list"> 
									<li class="first line4_type2">
										<strong>기여도</strong>
										<p><span class="num">{{toDayMyStakingInfo.staking_cntrbt_pct | numComma(2, true)}}</span>%</p>
									</li>
									<li>
										<strong>나의 Staking 총수량</strong>
										<p><span class="num">{{toDayMyStakingInfo.staking_qty | numComma(3)}}</span> YEP</p>
									</li>
									<li class="divine">
										<strong>전체 Staking 총수량</strong>
										<p><span class="num">{{toDayMyStakingInfo.total_staking_qty | numComma(3)}}</span> YEP</p>
									</li>
									<li>
										<strong>나의 Staking 총환산</strong>
										<p><span class="num">{{toDayMyStakingInfo.staking_qty * toDayMyStakingInfo.avg_buy_price | numComma(0)}}</span> KRW</p>
									</li>
								</ul>
							</div><!-- p50box_right end -->

							<p class="staking_last_desc">※ 환산총액은 계산식("YEP" 매수평균가×"Staking 수량")을 KRW로 환산한 추정치입니다. 'Special Reward' 및 '기여보너스' Air Drop에 활용됩니다.</p>

							<p class="red_desc">※ 회원님이 Staking 확정한 수량은 '나의 Staking 수량 및 나의 Staking 환산'수치에 즉시 반영 됩니다.</p>
							<p class="red_desc">※ 회원님 수치 외 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>

						</div><!-- p50box_wrap end -->

					</div><!-- yep_wrap end -->

					<h2 class="tabletitle bolder">나의 Staking 설정(YEP RP 참여)</h2>

					<div class="yep_wrap type3"><!-- yep_wrap start -->
						<div class="p50box_wrap"><!-- p50box_wrap start -->
							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title staking"><span>Staking</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>Staking 가능 수량</strong>
										<p><span class="num">{{myAvailableStakingInfo.available_staking_qty | numComma(3)}}</span>YEP</p>
									</li>
									<li class="input_type green">
										<strong>Staking 요청</strong>
										<p>
											<span>입력</span>
											<input type="text" id="yep-rp-staking-request-qty" placeholder="0" v-model="yepRpStakingReqQty" />
											<button class="popClick" v-on:click="stakingRequestPopup">요청</button>
											<em>최소수량 <span>{{myAvailableStakingInfo.staking_min_qty | numComma(3)}}</span>YEP</em>
											<a href="javascript:void(0);" v-on:click="setStakingReqMax">MAX</a>
										</p>
									</li>
								</ul>
								<p class="red_desc">※ 최소수량은 시장상황에 따라 변동됩니다.</p>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title unstaking"><span>Unstaking</span></p>
								<ul class="cost_list"> 
									<li class="table_type">
										<table>
											<colgroup>
												<col style="width:5%;">
												<col style="width:17%;">
												<col style="width:26%;">
												<col style="width:21%;">
												<col style="width:15%;"> 
												<col style="width:15%;">
											</colgroup>
											<thead>
												<tr>
													<th></th>
													<th>Staking 일시</th>
													<th class="cost">Staking 수량</th>
													<th class="cost">Staking 누적기간</th>
													<th class="cost">유통전환수량</th>
													<th class="cost">Freeze수량</th>
												</tr>
											</thead>
											<tbody class="mCustomScrollbar">
												<tr v-if="unStakingList.length < 1">
													<td colspan="6" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in unStakingList" v-on:click="toggleUnstakingRequestSelect(item, $event)">
													<td onclick="event.cancelBubble = true;"><label><input type="checkbox" v-on:click="toggleUnstakingRequestSelect(item, $event)" v-bind:checked="unStakingRequestMngNo.includes(item.staking_mgt_no)"><em></em></label></td>
													<td><p>{{item.staking_activate_dt | dateFmt('yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss')}}</p></td>
													<td class="td3">
														<p class="cost">{{item.req_qty | numComma(0)}}&nbsp;<input type="input" v-on:keyup="checkMaxVal($event.target.value, item)" class="mini" v-model="item.unstaking_req_qty" v-if="unStakingRequestMngNo.includes(item.staking_mgt_no)" placeholder="요청수량" onclick="event.cancelBubble = true;"></p>
													</td>
													<td><p class="cost">{{item.staking_dur_days | numComma(0)}}</p></td>
													<td><p class="cost">{{item.pull_back_compl_qty | numComma(3)}}</p></td>
													<td><p class="cost">{{item.freezing_compl_qty | numComma(3)}}</p></td>
												</tr>
											</tbody>
										</table>
									</li>
									<li class="first"><!-- 2019-07-19 수정(first_long클래스 삭제후 first 클래스 추가) -->
										<strong>Unstaking 가능 수량</strong>
										<p><span class="num">{{myAvailableStakingInfo.available_unstaking_qty | numComma(0)}}</span>YEP</p>
									</li>
									<li class="input_type">
										<strong>Unstaking 요청</strong>
										<p>
											<!-- <span>입력</span> -->
											<input id="yep-rp-unstaking-request-qty" type="text" v-model="yepRpUnStakingReqQty" v-text="yepRpUnStakingReqQty" readonly/>
											<button class="popClick" v-on:click="unStakingRequestPopup">요청</button>
											<!-- <em>최소수량 <span>{{myAvailableStakingInfo.staking_min_qty | numComma(3)}}</span>YEP</em>
											<a href="javascript:void(0);" v-on:click="setUnStakingReqMax">MAX</a> -->
										</p>
									</li>
								</ul>
								<p class="red_desc">※ Unstaking 요청 후 실제 잔고에는 약 1분 후 반영됩니다.</p>
							</div><!-- p50box_right end -->
						</div><!-- p50box_wrap end -->
					</div><!-- yep_wrap end -->

					<div class="yep_tab"><!-- yep_tab start --><!-- 2019-07-19 수정(div 추가) -->

						<!-- 2019-07-19 수정(ul 추가 시작) -->
						<ul class="yep_tab_btn">
							<li class="on"><a href="#">일 Staking 내역</a></li>
							<li><a href="#">나의 Staking 내역</a></li>
						</ul>
						<!-- 2019-07-19 수정(ul 추가 끝) -->

						<div class="yep_tab_cont"><!-- yep_tab_cont start --><!-- 2019-07-19 수정(div 추가) -->

							<!-- <h2 class="tabletitle bolder"><span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2> -->

							<div class="yep_wrap"><!-- yep_wrap start -->
								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-staking-daily-staking-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-staking-daily-staking-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:10%;">
												<col style="width:15%;">
												<col style="width:15%;">
											<!-- 	<col style="width:15%;"> -->
												<col style="width:17%;">
												<col style="width:15%;">
												<col style="width:13%;">
											</colgroup>
											<thead>
												<tr>
													<th>날짜</th>
													<th class="cost">Staking 수량(YEP)</th>
													<th class="cost">전체Staking수량(YEP)</th>
												<!-- 	<th class="cost">전체Staking참여자수</th> -->
													<th class="cost">평균 Staking수량(YEP)</th>
													<th class="cost">유통전환수량(YEP)</th>
													<th class="cost">Freeze B(YEP)</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="dailyStakingSummaryList.length < 1">
													<td colspan="7" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in dailyStakingSummaryList">
													<td><p>{{item.base_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.staking_qty | numComma(3, true)}}</p></td>
													<td><p class="cost">{{item.total_staking_qty | numComma(3, true)}}</p></td>
												<!-- 	<td><p class="cost">{{item.tot_staking_users | numComma(0)}} 명</p></td> -->
													<td><p class="cost">{{item.avg_staking_qty | numComma(3, true)}}</p></td>
													<td><p class="cost">{{item.un_freezing_qty | numComma(3, true)}}</p></td>
													<td><p class="cost">{{item.freezing_qty | numComma(3, true)}}</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->

									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->
							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end --><!-- 2019-07-19 수정(div 추가) -->

						<div class="yep_tab_cont"><!-- yep_tab_cont start --><!-- 2019-07-19 수정(div 추가) -->
							<!-- <h2 class="tabletitle bolder"><span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2> -->

							<div class="yep_wrap"><!-- yep_wrap start -->
								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-staking-my-daily-staking-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-staking-my-daily-staking-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
									<!-- <p class="red_desc">※ 집계 {{new Date() | dateFmtMnt}}</p> -->
									<!-- <p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p> -->
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding line2"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:12%;">
												<col style="width:12%;">
												<col style="width:12%;">
												<col style="width:10%;">
												<col style="width:10%;">
												<col style="width:10%;">
												<col style="width:10%;">
												<col style="width:10%;">
												<col style="width:14%;">
											</colgroup>
											<thead>
												<tr>
													<th>Staking일시</th>
													<th class="cost">Staking(YEP)</th>
													<th>Unstaking<br />요청일시</th>
													<th class="cost">Unstaking(YEP)</th>
													<th>Staking<br />누적기간</th>
													<th>유통수량<br />전환비율</th>
													<th class="cost">유통전환(YEP)</th>
													<th class="cost">Freeze(YEP)</th>
													<th class="cost">Unstaking<br />가능수량(YEP)</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="myStakingHistoryList.length < 1">
													<td colspan="9" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in myStakingHistoryList">
													<td><p>{{item.base_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.staking_qty + item.unstaking_req_qty | numComma(3)}}</p></td>
													<td><p>{{item.unstaking_date | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.unstaking_req_qty | numComma(3)}}</p></td>
													<td><p v-if="item.staking_dur_days > 0">{{item.staking_dur_days}}</p><p v-else>1일 미만</p></td>
													<td><p>{{item.change_rate | numComma(2, true)}} %</p></td>
													<td><p class="cost">{{item.unstaking_req_qty - item.freezing_qty | numComma(3)}}</p></td>
													<td><p class="cost">{{item.freezing_qty | numComma(3)}}</p></td>
													<td><p class="cost">{{item.staking_qty | numComma(3)}}</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->
									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->
							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end --><!-- 2019-07-19 수정(div 추가) -->
					</div><!-- yep_tab end --><!-- 2019-07-19 수정(div 추가) -->
				</li>
				<!--staking 끝-->

				
				<!-- ================================================================================================================= -->
				<!--                                             airdrop 시작                                                           -->
				<!-- ================================================================================================================= -->
				<li id="vtab4" style="display:none;">
					<h1 class="subtitle">Airdrop</h1>

					<h2 class="tabletitle bolder">AirDrop 현황<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->

						<div class="left_side"><!-- left_side start -->
							<div style="width:252px;height:252px;margin-left:4px;">
								<canvas id="yep-rp-yep-airdrop-status-chart" width="400" height="400"></canvas>
							</div>
						</div><!-- left_side end -->

						<div class="right_side"><!-- right_side start -->
							<div class="type_yep"><!-- type_yep start -->
								<table>
									<colgroup>
										<col style="width: 20%;">
										<col style="width: 20%;">
										<col style="width: 25%;">
										<col style="width: 35%;"> 
									</colgroup>
									<thead>
										<tr>
											<th colspan="2">거래수수료 Reward Pool</th>
											<th class="type_num">100.00 %</th>
											<th class="type_num">{{totAirdropStatusInfo.trade_fee_tot_amt | numComma(0)}} KRW</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="2"><span class="legend"></span><span>General Reward</span></td>
											<td class="type_num"><span>{{(totAirdropStatusInfo.nor_reward_tot_amt/totAirdropStatusInfo.trade_fee_tot_amt) * 100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totAirdropStatusInfo.nor_reward_tot_amt | numComma(0)}} KRW</span></td>
										</tr>
										<tr>
											<td colspan="2"><span class="legend green"></span><span>Special Reward</span></td>
											<td class="type_num"><span>{{(totAirdropStatusInfo.spec_reward_tot_amt/totAirdropStatusInfo.trade_fee_tot_amt)*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totAirdropStatusInfo.spec_reward_tot_amt | numComma(0)}} KRW</span></td>
										</tr>
										<tr>
											<td colspan="2"><span class="legend yellow"></span><span>Referral Reward</span></td>
											<td class="type_num"><span>{{(totAirdropStatusInfo.ref_reward_tot_amt/totAirdropStatusInfo.trade_fee_tot_amt)*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totAirdropStatusInfo.ref_reward_tot_amt | numComma(0)}} KRW</span></td>
										</tr>
										<tr>
											<td rowspan="2" class="border_right">Platform Reward</td>
											<td><span class="legend pink"></span><span>Pool<sup>1)</sup></span></td>
											<td class="type_num"><span>{{(totAirdropStatusInfo.platform_fee_tot_amt/totAirdropStatusInfo.trade_fee_tot_amt)*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totAirdropStatusInfo.platform_fee_tot_amt | numComma(0)}} KRW</span></td>
										</tr>
										<tr>
											<td><span class="legend purple"></span><span>AirDrop</span></td>
											<td class="type_num"><span>{{(totAirdropStatusInfo.platform_fee_reward_amt/totAirdropStatusInfo.trade_fee_tot_amt)*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totAirdropStatusInfo.platform_fee_reward_amt | numComma(0)}} KRW</span></td>
										</tr>
									</tbody>
								</table>
								<p class="red_desc">※ 상기 데이터는 누적데이터 입니다.</p>
							</div><!-- type_yep end -->
						</div><!-- right_side end -->

						<div class="p50box_wrap"><!-- p50box_wrap start -->

							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title"><span>어제</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>거래수수료 Reward Pool</strong>
										<p><span class="num imp_num">{{prevDayAirdropInfo.trade_fee_tot_amt | numComma(0)}}</span>KRW</p>
									</li>
									<li class="first">
										<strong>1,000 YEP Staking Reward<sup>2)</sup></strong>
										<p><span class="num">{{prevDayAirdropInfo.staking_reward_1k_yep | numComma(0)}}</span>KRW</p>
									</li>
									<li class="first desc2">
										<strong>조정기여보너스</strong>
										<p><span class="num">{{prevDayAirdropInfo.tot_cntrbt_bonus_dstrbt_qty | numComma(0)}}</span>YEP</p>
									</li>
									<li class="sp_line">
										<strong>전체 발생거래수수료</strong>
										<div>
											<p><span class="num">100.00</span>%</p>
											<p><span class="num">{{prevDayAirdropInfo.trade_fee_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>General Reward</strong>
										<div>
											<p><span class="num">{{prevDayAirdropInfo.nor_reward_tot_amt / prevDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{prevDayAirdropInfo.nor_reward_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>Special Reward</strong>
										<div>
											<p><span class="num">{{prevDayAirdropInfo.spec_reward_tot_amt / prevDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{prevDayAirdropInfo.spec_reward_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>Referral Reward</strong>
										<div>
											<p><span class="num">{{prevDayAirdropInfo.ref_reward_tot_amt / prevDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{prevDayAirdropInfo.ref_reward_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>Platform Reward</strong>
										<div>
											<p><span class="num">{{prevDayAirdropInfo.platform_fee_reward_amt / prevDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{prevDayAirdropInfo.platform_fee_reward_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
								</ul>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title today"><span>오늘</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>거래수수료 Reward Pool</strong>
										<p><span class="num imp_num">{{curDayAirdropInfo.trade_fee_tot_amt | numComma(0)}}</span>KRW</p>
									</li>
									<li class="first">
										<strong>1,000 YEP Staking Reward<sup>2)</sup></strong>
										<p><span class="num">{{curDayAirdropInfo.staking_reward_1k_yep | numComma(0)}}</span>KRW</p>
									</li>
									<li class="first desc2">
										<strong>조정기여보너스</strong>
										<p><span class="num">{{curDayAirdropInfo.tot_cntrbt_bonus_dstrbt_qty | numComma(0)}}</span>YEP</p>
									</li>
									<li class="sp_line">
										<strong>전체 발생거래수수료</strong>
										<div>
											<p><span class="num">100.00</span>%</p>
											<p><span class="num">{{curDayAirdropInfo.trade_fee_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>General Reward</strong>
										<div>
											<p><span class="num">{{curDayAirdropInfo.nor_reward_tot_amt / curDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{curDayAirdropInfo.nor_reward_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>Special Reward</strong>
										<div>
											<p><span class="num">{{curDayAirdropInfo.spec_reward_tot_amt / curDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{curDayAirdropInfo.spec_reward_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>Referral Reward</strong>
										<div>
											<p><span class="num">{{curDayAirdropInfo.ref_reward_tot_amt / curDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{curDayAirdropInfo.ref_reward_tot_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
									<li>
										<strong>Platform Reward</strong>
										<div>
											<p><span class="num">{{curDayAirdropInfo.platform_fee_reward_amt / curDayAirdropInfo.trade_fee_tot_amt * 100 | numComma(2, true) }}</span>%</p>
											<p><span class="num">{{curDayAirdropInfo.platform_fee_reward_amt | numComma(0)}}</span>KRW</p>
										</div>
									</li>
								</ul>
							</div><!-- p50box_right end -->

							<ol class="mining_desc2">
								<li>1) Platform Reward Pool 은 거래수수료를 제외한 플랫폼에서 발생한 기타수수료가 담겨있습니다. 에어드랍 시기 및 방법 등은 공지를 통해 안내됩니다.</li>
								<li>2) 1,000 YEP Staking 당 Reward 기준은 단순계산 (<em><span>전체발생거래수수료</span><span>전체 "Staking" 수량</span></em>) × 1,000 YEP ) 참고 데이터 입니다.</li>
								<li>Staking 기여도에 따라 General 및 Special Reward 보상 기준이 달라지므로 회원님의 정보는 '나의 AirDrop 내역' 에서 확인하시기 바랍니다.</li>
							</ol>

							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>

						</div><!-- p50box_wrap end -->

					</div><!-- yep_wrap end -->

					<h2 class="tabletitle bolder">나의 기여도 현황(오늘 기준)<span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

					<div class="yep_wrap type2"><!-- yep_wrap start -->
						<div class="p50box_wrap"><!-- p50box_wrap start -->
							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title today"><span>Mining</span><em><span>상위</span><span>{{(todayMiningStakingContributioInfo.top_pct == 0) ? 0.1 : todayMiningStakingContributioInfo.top_pct | numComma(1, true)}}</span>%</em></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>기여도</strong>
										<p><span class="num imp_num">{{todayMiningStakingContributioInfo.cntrbt_rate | numComma(2, true)}}</span>%</p>
									</li>
									<li>
										<strong>나의 발생거래수수료</strong>
										<p><span class="num">{{todayMiningStakingContributioInfo.my_fee_amt | numComma(0)}}</span>KRW</p>
									</li>
									<li>
										<strong>전체 발생거래수수료</strong>
										<p><span class="num">{{todayMiningStakingContributioInfo.tot_fee_amt | numComma(0)}}</span>KRW</p>
									</li>
								</ul>
								<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title staking"><span>Staking</span><em><span>상위</span><span>{{(todayMiningStakingContributioInfo.staking_top_pct == 0) ? 0.1 : todayMiningStakingContributioInfo.staking_top_pct | numComma(1, true)}}</span>%</em></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>기여도</strong>
										<p><span class="num imp_num">{{todayMiningStakingContributioInfo.staking_cntrbt_pct | numComma(2, true)}}</span>%</p>
									</li>
									<li>
										<strong>나의 Staking 총수량</strong>
										<p><span class="num">{{todayMiningStakingContributioInfo.staking_qty | numComma(3)}}</span>YEP</p>
									</li>
									<li>
										<strong>전체 Staking 총수량</strong>
										<p><span class="num">{{todayMiningStakingContributioInfo.tot_staking_qty | numComma(3)}}</span>YEP</p>
									</li>
								</ul>
								<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>
							</div><!-- p50box_right end -->
						</div><!-- p50box_wrap end -->
					</div><!-- yep_wrap end -->

					<div class="yep_tab"><!-- yep_tab start --><!-- 2019-07-19 수정(div 추가) -->

						<!-- 2019-07-19 수정(ul 추가 시작) -->
						<ul class="yep_tab_btn">
							<li class="on"><a href="#">일 AirDrop 내역</a></li>
							<li><a href="#">나의 AirDrop 내역</a></li>
							<li><a href="#">나의 기타 AirDrop 내역</a></li>
						</ul>
						<!-- 2019-07-19 수정(ul 추가 끝) -->

						<div class="yep_tab_cont"><!-- yep_tab_cont start --><!-- 2019-07-19 수정(div 추가) -->

							<h2 class="tabletitle bolder"><span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span></h2>

							<div class="yep_wrap"><!-- yep_wrap start -->
								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-airdrop-monthly-airdrop-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-airdrop-monthly-airdrop-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding line2"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:10%;">
												<col style="width:13%;">
												<col style="width:13%;">
												<col style="width:13%;">
												<col style="width:13%;">
												<col style="width:13%;">
												<col style="width:13%;">
												<col style="width:12%;">
											</colgroup>
											<thead>
												<tr>
													<th>날짜</th>
													<th class="cost">발생거래수수료(KRW)</th>
													<th class="cost">General Reward(KRW)</th>
													<th class="cost">Special Reward(KRW)</th>
													<th class="cost">Referral Reward(KRW)</th>
													<th class="cost">Platform Reward(KRW)</th>
													<th class="cost">1,000 YEP 당 추정(KRW)</th>
													<th class="cost">조정기여보너스(YEP)</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="dailyAirdropSummaryList.length < 1">
													<td colspan="8" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in dailyAirdropSummaryList">
													<td><p>{{item.base_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.trade_fee_tot_amt | numComma(0)}}</p></td>
													<td><p class="cost">{{item.nor_reward_tot_amt | numComma(0)}}</p><p class="cost">({{item.nor_reward_tot_amt / item.trade_fee_tot_amt * 100 | numComma(2, true) }}%)</p></td>
													<td><p class="cost">{{item.spec_reward_tot_amt | numComma(0)}}</p><p class="cost">({{item.spec_reward_tot_amt / item.trade_fee_tot_amt * 100 | numComma(2, true) }}%)</p></td>
													<td><p class="cost">{{item.ref_reward_tot_amt | numComma(0)}}</p><p class="cost">({{item.ref_reward_tot_amt / item.trade_fee_tot_amt * 100 | numComma(2, true) }}%)</p></td>
													<td><p class="cost">{{item.platform_fee_reward_amt | numComma(0)}}</p></td>
													<td><p class="cost">{{item.staking_reward_1k_yep | numComma(0)}}</p></td>
													<td><p class="cost">{{item.tot_cntrbt_bonus_dstrbt_qty | numComma(0)}}</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->

									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->

							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end -->

						<div class="yep_tab_cont">
							<h2 class="tabletitle bolder"><span class="h2_desc">※ Special Reward 의 경우 가중치가 반영된 내역입니다.</span></h2>

							<div class="yep_wrap"><!-- yep_wrap start -->
								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-airdrop-my-monthly-airdrop-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-airdrop-my-monthly-airdrop-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding line3"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:10%;">
												<col style="width:11%;">
												<col style="width:11%;">
												<col style="width:14%;">
												<col style="width:13%;">
												<col style="width:14%;">
												<col style="width:13%;">
												<col style="width:13%;">
											</colgroup>
											<thead>
												<tr>
													<th>날짜</th>
													<th class="cost">Staking 수량(YEP)</th>
													<th class="cost">Staking 환산(KRW)</th>
													<th class="cost">General Reward<br>(BTC)</th>
													<th class="cost">Special Reward<br>(BTC)</th>
													<th class="cost">Referral Reward<br>(BTC)</th>
													<th class="cost">Platform Reward<br>(BTC)</th>
													<th class="cost">Reward 합<br>(BTC)</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="myAirdropHistoryList.length < 1">
													<td colspan="8" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in myAirdropHistoryList">
													<td><p>{{item.trade_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p class="cost">{{item.staking_qty | numComma(0)}}</p></td>
													<td><p class="cost">{{item.avg_prc_staking_amt | numComma(0)}}</p></td>
													<td><p class="cost">{{(item.nor_reward_amt )/item.cur_mkt_prc_btc| numComma(6)}} BTC</p><p class="cost gray_txt">≈{{item.nor_reward_amt  | numComma(0)}} KRW</p></td>
													<td><p class="cost">{{(item.spec_reward_amt )/item.cur_mkt_prc_btc| numComma(6)}} BTC</p><p class="cost gray_txt">≈{{item.spec_reward_amt  | numComma(0)}} KRW</p></td>
													<td><p class="cost">{{(item.ref_reward_amt )/item.cur_mkt_prc_btc| numComma(6)}} BTC</p><p class="cost gray_txt">≈{{item.ref_reward_amt  | numComma(0)}} KRW</p></td>
													<td><p class="cost">{{(item.platform_reward_amt )/item.cur_mkt_prc_btc| numComma(6)}} BTC</p><p class="cost gray_txt">≈{{item.platform_reward_amt  | numComma(0)}} KRW</p></td>
													<td><p class="cost">{{(item.tot_reward_amt )/item.cur_mkt_prc_btc| numComma(6)}} BTC</p><p class="cost gray_txt">≈{{item.tot_reward_amt  | numComma(0)}} KRW</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->
									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->

							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end -->

						<div class="yep_tab_cont">
							<h2 class="tabletitle bolder"></h2>

							<div class="yep_wrap"><!-- yep_wrap start -->
								<div class="table_opt pb20"><!-- table_opt start -->
									<p class="drop">
										<yahobit-year-selectbox id="yep-rp-airdrop-my-etc-monthly-airdrop-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
									</p>
									<p class="drop">
										<yahobit-month-selectbox id="yep-rp-airdrop-my-etc-monthly-airdrop-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
									</p>
								</div><!-- table_opt end -->

								<div class="more_table"><!-- more_table start -->
									<div class="folding line2_type2"><!-- folding start -->
										<table>
											<colgroup>
												<col style="width:10%;">
												<col style="width:35%;">
												<col style="width:35%;">
												<col style="width:20%;">
											</colgroup>
											<thead>
												<tr>
													<th>날짜</th>
													<th>구분</th>
													<th>내용</th>
													<th class="cost">AirDrop 대상</th>
												</tr>
											</thead>
											<tbody>
												<tr v-if="myEtcAirdropHistoryList.length < 1">
													<td colspan="4" align="center" height="200">데이터가 없습니다.</td>
												</tr>
												<tr v-else v-for="item in myEtcAirdropHistoryList">
													<td><p>{{item.trade_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
													<td><p>{{item.gubun}}</p></td>
													<td><p>상위 {{((item.top_pct == 0) ? 0.1 : item.top_pct) | numComma(1)}}%</p></td>
													<td><p class="cost">{{item.cntrbt_bonus_dstrbt_qty | numComma(0)}} YEP</p><p class="cost gray_txt">≈{{item.estmt_cntrbt_bonus_amt | numComma(0)}} KRW</p></td>
												</tr>
											</tbody>
										</table>
									</div><!-- folding end -->
									<p class="btn-folding"><button type="button">더보기</button></p>
								</div><!-- more_table end -->
							</div><!-- yep_wrap end -->
						</div><!-- yep_tab_cont end -->
					</div><!-- yep_tab end -->
				</li>
				<!--airdrop 끝-->

				<!-- ================================================================================================================= -->
				<!--                                                   freeze 시작                                                     -->
				<!-- ================================================================================================================= -->
				<li id="vtab5" style="display:none;">
					<h1 class="subtitle">Freeze</h1>

					<h2 class="tabletitle bolder">Freeze 현황<!-- <span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span> --></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->

						<div class="left_side"><!-- left_side start -->
							<div style="width:165px;height:165px;margin-left:57.5px;">
								<canvas id="yep-rp-yep-freezing-status-chart" width="400" height="400"></canvas>
							</div>
						</div><!-- left_side end -->

						<div class="right_side"><!-- right_side start -->
							<div class="type_yep"><!-- type_yep start -->
								<table>
									<colgroup>
										<col style="width: 20%;">
										<col style="width: 20%;">
										<col style="width: 25%;">
										<col style="width: 35%;"> 
									</colgroup>
									<thead>
										<tr>
											<th colspan="2">배분량</th>
											<th class="type_num">100.00 %</th>
											<th class="type_num">{{totFreezingStatusInfo.total_supply_qty | numComma(0)}} YEP</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="2"><span class="legend"></span><span>유통량</span></td>
											<td class="type_num"><span>{{(totFreezingStatusInfo.total_circul_qty/(totFreezingStatusInfo.total_circul_qty+totFreezingStatusInfo.total_staking_qty+totFreezingStatusInfo.tot_freezing_qty ))*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totFreezingStatusInfo.total_circul_qty | numComma(0)}} YEP</span></td>
										</tr>
										<tr>
											<td rowspan="2"  class="border_right">비유통량 </td>
											<td><span class="legend green"></span><span>Staking</span></td>
											<td class="type_num"><span>{{(totFreezingStatusInfo.total_staking_qty/(totFreezingStatusInfo.total_circul_qty+totFreezingStatusInfo.total_staking_qty+totFreezingStatusInfo.tot_freezing_qty ))*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totFreezingStatusInfo.total_staking_qty | numComma(0)}} YEP</span></td>
										</tr>
										<tr>
											<td><span class="legend yellow"></span><span>Freeze</span></td>
											<td class="type_num"><span>{{(totFreezingStatusInfo.tot_freezing_qty/(totFreezingStatusInfo.total_circul_qty+totFreezingStatusInfo.total_staking_qty+totFreezingStatusInfo.tot_freezing_qty ))*100 | numComma(2, true)}} %</span></td>
											<td class="type_num"><span>{{totFreezingStatusInfo.tot_freezing_qty | numComma(0)}} YEP</span></td>
										</tr>
									</tbody>
								</table>
								<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>
							</div><!-- type_yep end -->

						</div><!-- right_side end -->

						<div class="p50box_wrap"><!-- p50box_wrap start -->

							<div class="p50box_left"><!-- p50box_left start -->
								<p class="cost_title"><span>어제</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>Freeze 누계</strong>
										<p><span class="num imp_num">{{prevDayFreezingInfo.tot_freezing_qty | numComma(0)}}</span> YEP</p>
									</li>
									<li class="first">
										<strong>Freeze 수량</strong>
										<p><span class="num">{{prevDayFreezingInfo.freezing_qty | numComma(0)}}</span> YEP</p>
									</li>
								</ul>
							</div><!-- p50box_left end -->

							<div class="p50box_right"><!-- p50box_right start -->
								<p class="cost_title today"><span>오늘</span></p>
								<ul class="cost_list"> 
									<li class="first">
										<strong>Freeze 누계</strong>
										<p><span class="num imp_num">{{curDayFreezingInfo.tot_freezing_qty | numComma(0)}}</span> YEP</p>
									</li>
									<li class="first">
										<strong>Freeze 수량</strong>
										<p><span class="num">{{curDayFreezingInfo.freezing_qty | numComma(0)}}</span> YEP</p>
									</li>
								</ul>
							</div><!-- p50box_right end -->

							<ul class="mining_desc3">
								<li>※ Freeze 누계는 가변기여마이닝 정책에 의해 조정되어 발생한 Freeze 수량과 Unstaking 후 Freeze 규정에 의해 발생한 수량의 누적합계입니다.</li>
								<li>※ Unstaking 으로 발생한 Freeze수량은 투표를 통해 사용처를 결정합니다.</li>
								<li>※ 투표방법 등은 공지를 통해 안내되며, 거래소는 투표에 참여하지 않습니다.</li>
							</ul>

							<p class="red_desc">※ 실시간 변하는 데이터는 반영하고 있지 않으며, 1시간 단위로 갱신됩니다.</p>

						</div><!-- p50box_wrap end -->

					</div><!-- yep_wrap end -->

					<h2 class="tabletitle bolder">일 Freeze 내역<!-- <span class="h2_desc">※ 편의를 위해 KRW 환산 추정치로 표기합니다.</span> --></h2>

					<div class="yep_wrap"><!-- yep_wrap start -->
						<div class="table_opt pb20"><!-- table_opt start -->
							<p class="drop">
								<yahobit-year-selectbox id="yep-rp-freezing-daily-freezing-history-year-select-box" v-bind:selected-year="new Date().toISOString().slice(0,4)" v-on:selected="changeYear" />
							</p>
							<p class="drop">
								<yahobit-month-selectbox id="yep-rp-freezing-daily-freezing-history-month-select-box" v-bind:selected-month="new Date().toISOString().slice(5,7)" v-on:selected="changeMonth" />
							</p>
						</div><!-- table_opt end -->

						<div class="more_table"><!-- more_table start -->
							<div class="folding"><!-- folding start -->
								<table>
									<colgroup>
										<col style="width:25%;">
										<col style="width:25%;">
										<col style="width:25%;">
										<col style="width:25%;">
										<!-- <col style="width:20%;">
										<col style="width:15%;"> -->
									</colgroup>
									<thead>
										<tr>
											<th>날짜</th>
											<th class="cost">Freeze 누계(YEP)</th>
											<th class="cost">Freeze 발생(YEP)</th>
											<th class="cost">재유통(YEP)</th>
											<!-- <th>내용</th>
											<th>투표결과</th> -->
										</tr>
									</thead>
									<tbody>
										<tr v-if="dailyFreezingSummaryList.length < 1">
											<td colspan="6" align="center" height="200">데이터가 없습니다.</td>
										</tr>
										<tr v-else v-for="item in dailyFreezingSummaryList">
											<td><p>{{item.base_day | dateFmt('yyyyMMdd', 'yyyy-MM-dd')}}</p></td>
											<td><p class="cost">{{item.tot_freezing_qty | numComma(3, true)}}</p></td>
											<td><p class="cost">{{item.freezing_qty | numComma(3, true)}}</p></td>
											<td><p class="cost">{{0 | numComma(3)}}</p></td> 
											<!-- <td><p class="cost">{{item.un_freezing_qty | numComma(3)}}</p></td> -->
											<!-- <td><p>AirDrop</p></td>
											<td><p>찬성</p></td> -->
										</tr>
									</tbody>
								</table>
							</div><!-- folding end -->

							<p class="btn-folding"><button type="button">더보기</button></p>
						</div><!-- more_table end -->

					</div><!-- yep_wrap end -->

<!--
					<h2 class="tabletitle bolder">나의 투표 내역<span class="h2_desc">※ Special Reward 의 경우 가중치가 반영된 내역입니다.</span></h2>
					<div class="yep_wrap">
						<div class="table_opt pb20">
							<p class="drop">
								<select class="select-box">
									<option value="">2019년</option>
								</select>
							</p>
							<p class="drop">
								<select class="select-box">
									<option value="">1월</option>
									<option value="">2월</option>
									<option value="">3월</option>
									<option value="">4월</option>
									<option value="">5월</option>
									<option value="">6월</option>
									<option value="">7월</option>
									<option value="">8월</option>
									<option value="">9월</option>
									<option value="">10월</option>
									<option value="">11월</option>
									<option value="">12월</option>
								</select>
							</p>
						</div>

						<div class="more_table">

							<div class="folding">
								<table>
									<colgroup>
										<col style="width:15%;">
										<col style="width:20%;">
										<col style="width:20%;">
										<col style="width:15%;">
										<col style="width:15%;">
										<col style="width:15%;">
									</colgroup>
									<thead>
										<tr>
											<th>날짜</th>
											<th class="cost">재유통(KRW)</th>
											<th>내용</th>
											<th class="cost">투표지분</th>
											<th>투표</th>
											<th>투표결과</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><p>2019.05.03</p></td>
											<td><p class="cost">111,112,972,000</p></td>
											<td><p>AirDrop</p></td>
											<td><p class="cost">0.78%</p></td>
											<td><p>찬성</p></td>
											<td><p>찬성</p></td>
										</tr>
										<tr>
											<td><p>2019.05.03</p></td>
											<td><p class="cost">111,112,972,000</p></td>
											<td><p>Mining Pool 인입</p></td>
											<td><p class="cost">0.78%</p></td>
											<td><p>찬성</p></td>
											<td><p>찬성</p></td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>

							<p class="btn-folding"><button type="button">더보기</button></p>
						</div>
					</div>
-->
				</li>
				<!--freeze 끝-->
			</ul>
			
		</div>
	</article>

</section>
<!--컨텐츠 내용 영역 끝-->


<!-- Staking 요청 팝업 시작-->
<div class="popup staking_pop" id="staking-request-popup">
	<div class="pop-title">
		<span>Staking 요청</span>
		<span class="top-close">
			<img src="/resources/img/btn-pop-close.png" onClick="javascript:$('.staking_pop').hide();" alt="닫기버튼">
		</span>
	</div>

	<div class="popup-cont">
		<p class="desc_top">※ 요청시간 <span>{{staking_request_datetime | dateFmtMnt}}</span></p>
		<ul class="inputs_sta">
			<li>
				<label><span>Staking 수량</span><input id="yep-rp-staking-popup-req-qty" type="text" title="Staking 수량" readonly/><span>YEP</span></label>
			</li>
			<li>
				<label><span>유통가능 수량</span><input id="yep-rp-staking-popup-balance-qty" type="text" title="유통가능 수량" readonly/><span>YEP</span></label>
			</li>
			<li class="btn"><button class="big top-close-btn" v-on:click="confirmStaking">Staking 확정</button></li>
		</ul>
		<ul class="check_sta">
			<li>
			<label><input type="checkbox" class="staking-request-check"><em></em>Staking 요청시 YEP RP 규정에 의한 Reward가 Staking 기간 동안 발생할 수 있습니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="staking-request-check"><em></em>Staking 확정시 유통가능 수량으로 되돌리기 위해선 Unstaking 요청이 필수이고,<br />이때 Freeze 규정에 의해 수량 손실이 발생할 수 있음을 백서를 읽고 이해했습니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="staking-request-check"><em></em>Staking 확정시 유통가능 해당 요청은 취소가 되지 않으며, 되돌릴 수 없습니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="staking-request-check"><em></em>Staking 확정시 확정시 해당 수량은 거래, 출금 등의 기능적 제한을 받습니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="staking-request-check"><em></em>Staking 확정시 위 표시된 Staking 수량은 회원 계정에 반영되어 유통가능 수량은 줄어듭니다.</label>
			</li>
		</ul>
		<p><label><input type="checkbox" class="staking-request-all-check" v-on:click="checkAll"><em></em>위 경고 문구를 이해했으며, 내용에 모두 동의합니다.</label></p>
	</div>
	<!-- 확인 버튼 : Start
	<div class="btn-group mgt20">
		<button class="big top-close-btn" v-on:click="confirmStaking">Staking 확정</button>
	</div>
	 확인 버튼 : End -->
</div>
<!-- Staking 요청 팝업 끝-->

<!-- Unstaking 요청 팝업 시작-->
<div class="popup unstaking_pop" id="unstaking-request-popup">
	<div class="pop-title">
		<span>Unstaking 요청</span>
		<span class="top-close">
			<img src="/resources/img/btn-pop-close.png" onClick="javascript:$('.unstaking_pop').hide()" alt="닫기버튼">
		</span>
	</div>
	<div class="popup-cont">
		<p class="desc_top">※ 요청시간 <span>{{staking_request_datetime | dateFmtMnt}}</span></p>
		<ul class="inputs_sta">
			<li>
				<label><span>유통전환 수량</span><input type="text" id="yep-rp-unstaking-popup-req-qty" title="유통전환 수량" readonly/><span>YEP</span></label>
			</li>
			<li>
				<label><span>Freeze 수량</span><input type="text" id="yep-rp-unstaking-popup-freeze-qty" title="Freeze 수량" readonly/><span>YEP</span></label>
			</li>
			<li class="btn"><button class="big top-close-btn" v-on:click="confirmUnStaking">Unstaking 확정</button></li>
		</ul>
		<ul class="check_sta">
			<li>
			<label><input type="checkbox" class="unstaking-request-check"><em></em>Unstaking 요청시 Freeze 규정에 의한 Staking 기간에 따라 유통전환 수량과 Freeze 수량으로<br />나뉘고, 수량 손실이 발생할 수 있음을 백서를 읽고 이해했습니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="unstaking-request-check"><em></em>Unstaking 확정시 해당 요청은 취소가 되지 않으며, 되돌릴 수 없습니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="unstaking-request-check"><em></em>Unstaking 확정시 Freeze 수량에 대한 소유권은 상실됩니다.</label>
			</li>
			<li>
			<label><input type="checkbox" class="unstaking-request-check"><em></em>Unstaking 확정시 위 표시된 수량이 확정되어 회원 계정에 반영됩니다.</label>
			</li>
		</ul>
		<p><label><input type="checkbox" class="unstaking-request-all-check" v-on:click="checkAll"><em></em>위 경고 문구를 이해했으며, 내용에 모두 동의합니다.</label></p>
	</div>
	<!-- 확인 버튼 : Start 
	<div class="btn-group mgt20">
		<button class="big top-close-btn" v-on:click="confirmUnStaking">Unstaking 확정</button>
	</div>
	확인 버튼 : End -->
</div>
<!-- Unstaking 요청 팝업 끝-->

<!-- Auto Mining 요청 팝업 시작--><!-- 2019-07-30 추가 시작-- -->
<div class="popup popup_new autom_pop" id="auto-mining-request-popup">
	<div class="pop-title">
		<span>Auto Mining 요청</span>
		<span class="top-close">
			<img src="/resources/img/btn-pop-close.png" alt="닫기버튼">
		</span>
	</div>

	<div class="popup-cont">
		<p class="desc_top">※ 요청시간 <span>{{auto_mining_request_datetime | dateFmtMnt}}</span></p>
		<ul class="inputs_sta">
			<li>
				<label><span>Auto Mining 재원</span><input type="text" id="yep-rp-auto-mining-popup-req-qty" title="Auto Mining 재원" readonly/></label>
			</li>
			<li class="btn"><button class="big top-close-btn" v-on:click="confirmAutoMining">Auto Mining 확정</button></li>
		</ul>
		<ul class="check_sta">
			<li>
				<label><input type="checkbox" class="auto-mining-request-check"><em></em>Auto Mining 은 YEP/KRW 페어에서만 진행 됩니다.</label>
			</li>
			<li>
				<label><input type="checkbox" class="auto-mining-request-check"><em></em>Auto Mining 확정시 가변기여마이닝 정책에 의해 사용한 재원이 다음날 YEP으로 배분됩니다.</label>
			</li>
			<li>
				<label><input type="checkbox" class="auto-mining-request-check"><em></em>Auto Mining 확정시 재원의 가치와 배분된 YEP의 가치가 다를 수 있으며, 손실이 발생할 위험성을 인지하고, 손실에 대한 책임은 모두 회원 본인에게 있습니다.</label>
			</li>
			<li>
				<label><input type="checkbox" class="auto-mining-request-check"><em></em>Auto Mining 확정시 해당 요청은 취소가 되지 않으며, 되돌릴 수 없습니다.</label>
			</li>
			<li>
				<label><input type="checkbox" class="auto-mining-request-check"><em></em>Auto Mining 확정시 재원은 거래, 출금 등의 기능적 제한을 받으며, 회원 잔고에서 소진됩니다.</label>
			</li>
			<!-- <li>
				<label><input type="checkbox" class="auto-mining-request-check"><em></em>요청 재원이 5,000 KRW 미만이 되면 Auto Mining은 종료됩니다.</label>
			</li> -->
		</ul>
		<p><label><input type="checkbox" class="auto-mining-request-all-check" v-on:click="checkAll"><em></em>위 경고 문구를 이해했으며, 내용에 모두 동의합니다.</label></p>
	</div>
</div>
<!-- Auto Mining 요청 팝업 끝--><!-- 2019-07-30 추가 끝 -->

<%
String browser = "";
String userAgent = request.getHeader("User-Agent");
if (userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0) {
		browser = "IE";
} else {
	%>
	<script src="/resources/js/yahobit/yeprp/yahobit.yeprp.js?v=<spring:message code="yahobit.yeprp.js.version"/>"></script>
	<%
}
%>
	
<%-- <script src="/resources/js/yahobit/yeprp/yahobit.yeprp.js?v=<spring:message code="yahobit.yeprp.js.version"/>"></script> --%>

<script type="text/javascript">
	//프로그래스바
	$(function() {
		if(isIE()) {
			lrt.client('본 화면은 IE에서 지원 되지 않습니다<br>Chrome, Firefox, Edge등의 Browser를 이용하시기 바랍니다.', '알림', function() {
				location.href = '/';
			});
		}
		
		$("#progressbar").progressbar({
			value: 37
		});
		$("#progressbar1").progressbar({
			value: 50
		});
	});
	
	//세로탭메뉴
	$(function() {
		$("ul.vpanel > li:not(" + $("ul.vtab li a.vselected").attr("href") + ")").hide() // 2019-07-19 수정(선택자 변경 ul.vpanel 의 직계자식 li만 컨트롤 되도록)
		$("ul.vtab li a").click(function() {
			$("ul.vtab li a").removeClass("vselected");
			$(this).addClass("vselected");
			$("ul.vpanel > li").hide();// 2019-07-19 수정(선택자 변경 ul.vpanel 의 직계자식 li만 컨트롤 되도록)
			$($(this).attr("href")).show();
			return false;
		});
	});

	//탭메뉴  2019-07-19 수정(탭메뉴 추가)
	$(document).on(
		"click", ".yep_tab_btn li", 
		function(){
			$(this).addClass("on").siblings().removeClass("on");
			var thisNum=$(this).index();
			$(this).parents(".yep_tab").find(".yep_tab_cont").hide();
			$(this).parents(".yep_tab").find(".yep_tab_cont").eq(thisNum).show();
			return false;
		}
	);
</script>
<script>
	//마켓별 전체보기 (2019-06-12 수정)
	$(document).ready(function() {
		$('.btn-folding').click(function() {
			var thisFold=$(this).prev(".folding");

			thisFold.toggleClass('more');
		});
	});

	/*북마크*/
	$('.bookmark').click(function() {
		$(this).toggleClass('mark-on');
	});

	/*슬릭스 슬라이더*/
	$(".regular").slick({
		dots: true,
		infinite: true,
		slidesToShow: 1,
		slidesToScroll: 1,
		autoplay: false,
		autoplaySpeed: 4000
	});

	//팝업 열고 닫기   
	function openForm() {
		document.getElementById("myForm").style.display = "block";
	}

	function closeForm() {
		document.getElementById("myForm").style.display = "none";
	}

	//레이어팝업닫기
	$(document).ready(function() {
		$('.top-close').click(function() {
			$(this).parents('.popup').hide();
		});
	});
	
	//탭
	$(document).ready(function() {
		$("#tabs").tabs();
	});
	
	/* 팝업열기  시작 */
	$(document).on( "click", ".popClick", 
		function(){
			var thePop=$(this).attr("popView");
			$("."+thePop+"").show();
		}
	);
	/* 팝업열기  끝 */
	
	// 퍼센트 버튼 선택
	$('.buy-percent div').click(function() {
		$('.buy-percent div').removeClass('active');
		$(this).addClass('active');
	});
</script>

<script>
$(document).ready(function() {
	Chart.defaults.global.elements.arc.borderWidth = 100
	// YEP 유통현황 PIE Chart
	
	// YEP Mining 현황 PIE Chart
	
	// YEP Staking 현황 PIE Chart
	
	// YEP AirDrop 현황 PIE Chart
	
	// YEP Freezing 현황 PIE Chart
	
});
</script>
