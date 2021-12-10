// ActiveMQ Queue 접속 공통 함수
var ie_connection = null;
var ie_regist_dest_list = [];
var ie_regist_func_list = [];
var ie_dest_func_map = simpleMap();
var hogaClient = null;
var hogaClientSub = null;

function TopicHogaLister(callback, destination, origin) {
	if(window.WebSocket) {
		if(isIE() != true) {
			/////////////////////////////////////////////////////////////////////
			// Chrome, Firefox, Opera
			var info = {};
			var client, sub;
			var login = "admin";
			var passcode = "admin";
			
			if(destination == '') return;
			destination = '/topic/' + destination + '/hoga';
	
			// STOMP API를 이용한 웹소켓 서버(ActiveMQ)에 접속
			try {
				if(hogaClient != null && hogaClient.connected) {
					hogaClientSub.unsubscribe();
					hogaClientSub = null;
					hogaClient.disconnect();
				}
				hogaClient = Stomp.client(__G_activemq_url, null);
			} catch(err) {
				console.log('------------------ websocket connection error ----------------------');
				console.log(err);
				try {
					hogaClient = Stomp.client(__G_activemq_url, null);
				} catch(err) {
					//
				}
			}
			
			hogaClient.heartbeat.outgoing = 5000;
			hogaClient.heartbeat.incoming = 0;
			hogaClient.reconnect_delay = 3000;
			hogaClient.debug = function(str) {}
	
			hogaClient.connect(login, passcode, function(frame) {
				hogaClientSub = hogaClient.subscribe(destination, function(message) {
					if(typeof callback === 'function') {
						scktChckTm = new Date().getTime();
						callback(message.body);
					}
				});
			});
		} else {
			/////////////////////////////////////////////////////////////////////
			// IE 11 :: IE 11이하는 Websocket 지원안함
			var dest = destination;
			var fn_name = getFnName(callback);
			ie_regist_dest_list.push(dest);
			ie_regist_func_list.push(fn_name);
			ie_dest_func_map.put(fn_name, callback);
			
			window.WebSocket = window.WebSocket || window.MozWebSocket;

			if(ie_connection != null && ie_connection.readyState == 1) {
				//console.log('ActiveMQ WebSocket Connected...');
				var dest_json = {
					destination : dest,
					function_name : fn_name
				}
				ie_connection.send(JSON.stringify(dest_json));
				//console.log('ActiveMQ WebSocket send dest msg -> ' + JSON.stringify(dest_json));
			}

			// open connection
			if(ie_connection == null) {
				try {
					ie_connection = new WebSocket(__G_activemq_ws_url); // 'wss://www.coinis.net/amqsocket'
				} catch(err) {
					return false;
				}
			}

			ie_connection.onopen = function () {
				while(ie_regist_dest_list.length) {
					var dest = ie_regist_dest_list.pop();
					var fn_name = ie_regist_func_list.pop();
					var dest_json = {
							destination : dest,
							function_name : fn_name
					}
					ie_connection.send(JSON.stringify(dest_json));
					//console.log('ActiveMQ WebSocket send dest msg -> ' + JSON.stringify(dest_json));
				}
			};

			// 메시지 수신
			ie_connection.onmessage = function (message) {
				var fn_name = JSON.parse(message.data).fn_name;
				var _msg = (JSON.parse(message.data).message);
				var callback_func = ie_dest_func_map.get(fn_name);
				//console.log('## Get Message --> ' + JSON.stringify(_msg));
				//console.log('## Get Message func name --> ' + fn_name);
				if (typeof callback_func === 'function') {
					callback_func((_msg));
				}
			};

			// Browser 종료 or Refresh or 페이지 이동
			$( window ).unload(function() {
				//console.log(dest + 'browser unload..');
				if(ie_connection !== null) {
					ie_connection.close()
					ie_connection.onclose = function () {};
					delete ie_connection;
					ie_connection = null;
				}
				return 'browser exit...';
			});

			// 10초마다 connection 상태 체크
			setInterval(function() {
				if (ie_connection.readyState !== 1) {
					//console.log(dest + ' :: 서버와 접속이 끊겼습니다!');
				}
			}, 10000);
		}
	}
}

function TopicLister(callback, destination) {
	///////////////////////////////////////////////////////////////
	//         WebSocket ActiveMQ Topic Listening
	///////////////////////////////////////////////////////////////
	if(window.WebSocket) {
		if(isIE() != true) {
			/////////////////////////////////////////////////////////////////////
			// Chrome, Firefox, Opera
			var client;
			var url = __G_activemq_url;
			var login = "admin";
			var passcode = "admin";
			if(destination == '') return;
			destination = '/topic/' + destination;
	
			// STOMP API를 이용한 웹소켓 서버(ActiveMQ)에 접속
			try {
				client = Stomp.client(url, null);
			} catch(err) {
				console.log('------------------ websocket connection error ----------------------');
				console.log(err);
				try {
					client = Stomp.client(url, null);
				} catch(err) {
					//
				}
			}
			
			client.heartbeat.outgoing = 5000;
			client.heartbeat.incoming = 0;
			client.reconnect_delay = 3000;
			client.debug = function(str) {}
	
			////// 웹소켓 서버(ActiveMQ)에 접속 및 큐에서 메시지 수신 대기.
			// 접속
			client.connect(login, passcode, function(frame) {
				//console.log("connected to Stomp : topic name = " + destination);
				// 큐에서 메시지 수신 대기
				client.subscribe(destination, function(message) {
					// 메시지가 들어오면 callback 함수 호출
					if (typeof callback === 'function') {
						callback(message.body);
					}
				});
			});
			
		} else {
			/////////////////////////////////////////////////////////////////////
			// IE 11 :: IE 11이하는 Websocket 지원안함
			var dest = destination;
			var fn_name = getFnName(callback);
			ie_regist_dest_list.push(dest);
			ie_regist_func_list.push(fn_name);
			ie_dest_func_map.put(fn_name, callback);
			
			window.WebSocket = window.WebSocket || window.MozWebSocket;

			if(ie_connection != null && ie_connection.readyState == 1) {
				//console.log('ActiveMQ WebSocket Connected...');
				var dest_json = {
					destination : dest,
					function_name : fn_name
				}
				ie_connection.send(JSON.stringify(dest_json));
				//console.log('ActiveMQ WebSocket send dest msg -> ' + JSON.stringify(dest_json));
			}

			// open connection
			if(ie_connection == null) {
				try {
					ie_connection = new WebSocket(__G_activemq_ws_url); // 'wss://www.coinis.net/amqsocket'
				} catch(err) {
					return false;
				}
			}

			ie_connection.onopen = function () {
				while(ie_regist_dest_list.length) {
					var dest = ie_regist_dest_list.pop();
					var fn_name = ie_regist_func_list.pop();
					var dest_json = {
							destination : dest,
							function_name : fn_name
					}
					ie_connection.send(JSON.stringify(dest_json));
					//console.log('ActiveMQ WebSocket send dest msg -> ' + JSON.stringify(dest_json));
				}
			};

			// 메시지 수신
			ie_connection.onmessage = function (message) {
				var fn_name = JSON.parse(message.data).fn_name;
				var _msg = (JSON.parse(message.data).message);
				var callback_func = ie_dest_func_map.get(fn_name);
				//console.log('## Get Message --> ' + JSON.stringify(_msg));
				//console.log('## Get Message func name --> ' + fn_name);
				if (typeof callback_func === 'function') {
					callback_func((_msg));
				}
			};

			// Browser 종료 or Refresh or 페이지 이동
			$( window ).unload(function() {
				//console.log(dest + 'browser unload..');
				if(ie_connection !== null) {
					ie_connection.close()
					ie_connection.onclose = function () {};
					delete ie_connection;
					ie_connection = null;
				}
				return 'browser exit...';
			});

			// 10초마다 connection 상태 체크
			setInterval(function() {
				if (ie_connection.readyState !== 1) {
					//console.log(dest + ' :: 서버와 접속이 끊겼습니다!');
				}
			}, 10000);
		}
	// 웹소켓(HTML5) 미 지원 브라우즈 인 경우 출력 메시지
	} else {
		/*$("#connect").html("\
			<h1>최신의 브라우즈를 설치하십시오!</h1>\
			<p>\
			실행중인 브라우즈는 웹소켓을 지원하지 않습니다.<br>\
			웹소켓을 지원하는 브라우즈를 사용해 주십시오 (구글 Chrome 권장).\
			</p>\
		");*/
	}
};
function getFnName(fn) {
    return (fn.toString().match(/function (.+?)\(/)||[,''])[1];
}

