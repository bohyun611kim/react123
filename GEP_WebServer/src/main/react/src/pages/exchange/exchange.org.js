import { useEffect, useRef, useState } from "react";
import { RecoilRoot, useRecoilState,useRecoilValue,useRecoilValueLoadable, useSetRecoilState } from "recoil";
import { BASE_PREFIX } from "../../module/myconstants";
import request from "../../module/services/request.service";
import { is_empty } from "../../module/utils/misc.util";
import ExchangeContent from "./component/ex_content";


function Exchange() {

	const myref = useRef({});

	const [curr, setCurrClass] = useState({})
	var hogaBuy = useRef([])
	var hogaSell = useRef([])
	var marketInfo = useRef({})

	useEffect(() => {
		console.log("+++++++++++++ Exchange +++++++++++++++++");
		myref.current.drawAsking = drawAsking;
		myref.current.drawHoga = drawHoga;
		myref.current.drawMarket = drawMarket;
		window.askingTable = myref.current;
	}, [])

	const drawMarket = (data) => {
		marketInfo = data.body;
		console.log("-drawMarket----market.closePrice:", marketInfo.closePrice);

		//let $tr = $('#tab-group-1').find('tr[data-code=' + data.itemCode + ']');
	}

	const drawHoga = (data, isfirst = false) => {

		if (isfirst === true) {
			let i = 0;
			hogaBuy = [], hogaSell = [];
			for (i = 0 ; i < data.buy.length ; i++) {
				hogaBuy.push({BuyHoga:data.buy.ordPrc});
			}
			for (i = 0 ; i < data.sell.length ; i++) {
				hogaSell.push({SellHoga:data.sell.ordPrc});
			}
			return;
		}

		//console.log("drawHoga==>", data);
		let closeHoga = data.closeHogaList;
		let buyList = data.BuyHogaList;
		let sellList = data.SellHogaList;


		if (hogaBuy.length > 0) {
			let i = 0, j = 0;
			for (i=0; i < hogaBuy.length ; i++) {
				console.log("==> buy.hoga, close:", hogaBuy[i].BuyHoga, marketInfo.closePrice);
				if (Number(hogaBuy[i].BuyHoga).toFixed(2) == Number(marketInfo.closePrice).toFixed(2)) {
					var op_id = getHogaID(hogaBuy[i].BuyHoga);
					var asking_row = $(op_id);
					let myclass = asking_row.attr('class');
					/*
					if (!myclass.includes('border')) {
						asking_row.attr('class', myclass + " border down")
					}
					*/
					break;
				}
			}
		}

		if (hogaSell.length > 0) {
			let i = 0, j = 0;
			for (i=0; i < hogaSell.length ; i++) {
				console.log("==> buy.hoga, close:", hogaSell[i].SellHoga, marketInfo.closePrice);
				if (Number(hogaSell[i].SellHoga).toFixed(2) == Number(marketInfo.closePrice).toFixed(2)) {
					var op_id = getHogaID(hogaSell[i].SellHoga);
					var asking_row = $(op_id);
					let myclass = asking_row.attr('class');
					/*
					if (!myclass.includes('border')) {
						asking_row.attr('class', myclass + " border up")
					}
					*/
					break;
				}
			}
		}

		hogaBuy = buyList;
		hogaSell = sellList;

		//console.log(">>>>>>>>> hogaBuy", hogaBuy);
		//console.log(">>>>>>>>> hogaSell", hogaSell);
	}


	const drawAsking = (op_id) => {
		console.log("AskingTable::drawAsking:", op_id);
		var asking_row = $("[id='"+op_id+"']");

		let prev_class = curr;


		let curr_class = {'id':op_id, 'class': asking_row.attr('class')};
		console.log("-- 체결 -----------cur class:", curr_class);

		/*
		let myclass = asking_row.attr('class');
		if (!myclass.includes('border')) {
			asking_row.attr('class', myclass + " border up")
		}
		*/


		//console.log("----- bgred blue + border down ",  asking_row.attr('class'));
		setCurrClass(curr_class);


		/*
		let $tr = $('.list1').children('tr'), sellTotal = 0;
		for(let i=9; i>=0; i--) {
			let $td = $tr.eq(i).children('td'), temp = {};
			let attr_class = $td.eq(2).attr('class');
		}
		*/

		//console.log("seleted sel +++++", asking_row.prop('outerHTML'));
		//console.log("seleted sel +++++", asking_row.prop('outerHTML'));
	}

	return (
		<>
		</>
	);
}

function ExchangeRoot() {
	return (
		<RecoilRoot>
			<Exchange/>
		</RecoilRoot>
	);
}

let domContainer = document.querySelector('#like_exchange_content');
ReactDOM.render(<ExchangeRoot/>, domContainer);

