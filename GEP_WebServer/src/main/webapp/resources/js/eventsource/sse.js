/**
 * 
 */
//const url = 'https://www.ali-bit.com/events/topic';

var sseMap = [];

var url = __G_sse_server_url;
if(url == undefined || url == null) url = 'https://www.ali-bit.com/events/topic';
function createServerSentEvents(cllBckFn, topic) {
	if(typeof cllBckFn === 'function') {
		
		if(sseMap.length > 0) {
			var item = sseMap.find(it => {
				return it.topic == topic;
			})

			if (item !== undefined) {
				//console.log("createServerSentEvents. client:", item.client);
				item.client.close();
				item.client = null;
				sseMap = sseMap.filter(obj => obj.topic !== topic);
			}
		}

		//console.log("createServerSentEvents+++size:", sseMap.length);
		let contractClient;
		if(isIE() == false) {
			contractClient = new EventSource(url + '?topic=' + topic);
		} else {
			contractClient = new EventSourcePolyfill(url + '?topic=' + topic);
		}
		
		contractClient.addEventListener("message", function(message) {
			//console.log("createServerSentEvents<===", message);
			if(message.type === 'message') {cllBckFn(message.data);}
		});
		
		contractClient.addEventListener("error", function(message) {
			//if(message.type === 'message') {cllBckFn(message.data);}
		});

		sseMap.push({"topic":topic, "client" : contractClient});
		//console.log("sseMap:", sseMap);
	}
}

var hogaClient;

function createHogaServerSentEvents(cllBckFn, destination) {
	if(typeof cllBckFn === 'function') {
		destination = destination + '/hoga';
		
		if(hogaClient != undefined) {
			hogaClient.close();
		}
		hogaClient = null;
		if(isIE() == false) {
			hogaClient = new EventSource(url + '?topic=' + destination);
		} else {
			hogaClient = new EventSourcePolyfill(url + '?topic=' + destination);
		}
		
		hogaClient.addEventListener("message", function(message) {
			if(message.type === 'message') {cllBckFn(message.data);}
		});
		
		hogaClient.addEventListener("error", function(message) {
			//if(message.type === 'message') {cllBckFn(message.data);}
		});
	}
}