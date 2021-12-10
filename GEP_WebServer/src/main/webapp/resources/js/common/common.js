// object 에서 key 값 얻어오기 (default_value 인자가 있을때 key값이 없으면 default_value 반환)
function getParam(object, key, default_value) {
	var keyVal = object[Object.keys(object).filter(function(k) {
		return k.toLowerCase() === key.toLowerCase();
	})[0]];
	if(default_value != undefined && (keyVal == undefined || keyVal == null)) keyVal = default_value;
	return keyVal;
}

// 숫자형 데이터 여부 확인하여 값 리턴(판별 값, default 값)
function checkNumberData(v, d) {
	d = d == undefined ? 0:d;
	return v == null ? d:v == undefined ? d:isNaN(v) ? d:isFinite(v)? v:d;
}

// 브라우저 정보 얻기
function getClientInfo() {
    var userAgent = navigator.userAgent;
    var reg = null;
    var browser = {
        name: null,
        version: null
    };

    userAgent = userAgent.toLowerCase();

    if (userAgent.indexOf(" opr") !== -1) {
        reg = /opr\/(\S+)/;
        browser.name = "Opera";
        browser.version = reg.exec(userAgent)[1];
    } else if (userAgent.indexOf("edge") !== -1) {
        reg = /edge\/(\S+)/;
        browser.name = "Edge";
        browser.version = reg.exec(userAgent)[1];
    } else if (userAgent.indexOf("chrome") !== -1) {
        reg = /chrome\/(\S+)/;
        browser.name = "Chrome";
        browser.version = reg.exec(userAgent)[1];
    } else if (userAgent.indexOf("safari") !== -1) {
        reg = /safari\/(\S+)/;
        browser.name = "Safari";
        browser.version = reg.exec(userAgent)[1];
    } else if (userAgent.indexOf("firefox") !== -1) {
        reg = /firefox\/(\S+)/;
        browser.name = "Firefox";
        browser.version = reg.exec(userAgent)[1];
    } else if (userAgent.indexOf("trident") !== -1) {
        browser.name = "IE";
        
        if (userAgent.indexOf("msie") !== -1) {
            reg = /msie (\S+)/;
            browser.version = reg.exec(userAgent)[1];
            browser.version = browser.version.replace(";", "");
        } else {
            reg = /rv:(\S+)/;
            browser.version = reg.exec(userAgent)[1];
            browser.version = browser.version.replace(")", "");
        }
    }

    return browser.name + " " + browser.version;
}

// os 정보 얻기
function getOS() {
	var OSName = "Unknown OS";
	if (navigator.userAgent.indexOf("Win") != -1) OSName = "Windows";
	if (navigator.userAgent.indexOf("Mac") != -1) OSName = "Macintosh";
	if (navigator.userAgent.indexOf("Linux") != -1) OSName = "Linux";
	if (navigator.userAgent.indexOf("Android") != -1) OSName = "Android";
	if (navigator.userAgent.indexOf("like Mac") != -1) OSName = "iOS";
	return OSName;
}

// ie 체크
function isIE() {
   var agent = navigator.userAgent.toLowerCase();
   if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
      return true;
   } else {
      return false;
   }
}

// user ip 얻기
function getUserIP(onIP) { //  onIp - callback param
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");
    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
		return false;
	} else {
		try {
		    //compatibility for firefox and chrome
		    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
		    var pc = new myPeerConnection({
		        iceServers: []
		    }),
		    noop = function() {},
		    localIPs = {},
		    ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
		    key;

		    function iterateIP(ip) {
		        if (!localIPs[ip]) onIP(ip);
		        localIPs[ip] = true;
		    }
		     //create a bogus data channel
		    pc.createDataChannel("");
		    // create offer and set local description
		    pc.createOffer().then(function(sdp) {
		        sdp.sdp.split('\n').forEach(function(line) {
		            if (line.indexOf('candidate') < 0) return;
		            line.match(ipRegex).forEach(iterateIP);
		        });

		        pc.setLocalDescription(sdp, noop, noop);
		    }).catch(function(reason) {
		        // An error occurred, so handle the failure to connect
		    });

		    //listen for candidate events
		    pc.onicecandidate = function(ice) {
		        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
		        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
		    };
		} catch(err) {
			// noop.
		}
	}
}

function simpleMap() {
	var map = {};
	map.value = {};
	map.getKey = function(id) {
		return "k_"+id;
	};
	map.put = function(id, value) {
		var key = map.getKey(id);
		map.value[key] = value;
	};
	map.contains = function(id) {
		var key = map.getKey(id);
		if(map.value[key]) {
			return true;
		} else {
			return false;
		}
	};
	map.get = function(id) {
		var key = map.getKey(id);
		if(map.value[key]) {
			return map.value[key];
		}
		return null;
	};
	map.remove = function(id) {
		var key = map.getKey(id);
		if(map.contains(id)){
			map.value[key] = undefined;
		}
	};
	return map;
}

//콤마 추가하여 반환(값, 소수점 자를 자릿수)
function nwc(n, f) {
	var v = jts(n, f);
	
	if(v.indexOf('.') > -1) {
		var parts = v.toString().split(".");
		return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,",") + (parts[1] ? "." + parts[1] : parts[1] == undefined ? "": ".");
	} else {
		return v.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
}

//지수 숫자 문자열형태의 숫자로 반환(값, 소수점 자를 자릿수)
function jts(n, f) {
	var v = ('' + checkNumberData(n)).toLocaleLowerCase();
	var r = '';
	var d = v.indexOf('.');
	
	if(v.indexOf('e') > 0 && ('' + parseFloat(v)).indexOf('e') > -1) {
		var a = v.split('e');
		var p = parseFloat(a[1]);
		
		for(var i=0; i<Math.abs(p); i++) { r += '0'; }
		
		if(p > 0) { r = a[0].replace('.', '') + r; } 
		else { r = r.substr(0,1) + '.' + r.substr(1) + a[0].replace('.', ''); }
		
		return r;
	} else {
		return v;
	}
}

// nubmer utils
var nu = {
	// 콤마 추가하여 반환(값, 소수점 자를 자릿수, toFixed 처리 여부)
	cm : function(n, f, b) {
		let v;
		
		v = nu.js(n, f);
		
		if(b != undefined && b) {v = nu.zp(v, f)}
		
		if(nu.isN(v)) {
			if(v.indexOf('.') > -1) {
				let parts = v.toString().split(".");
				return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,",") + (parts[1] ? "." + parts[1] : parts[1] == undefined ? "": ".");
			} else {
				return v.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			}
		} else {
			return '0';
		}
	},
	nonCm : function(n, f, b) {
		let v = n;
		
		if(nu.isN(v)) {
			if(v.indexOf('.') > -1) {
				let parts = v.toString().split(".");
				return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,",") + (parts[1] ? "." + parts[1] : parts[1] == undefined ? "": ".");
			} else {
				return v.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			}
		} else {
			return '0';
		}
	},
	fixedCm : function(n, f, b) {
		let v;
		
		if(b === true) {
			n = parseFloat(n).toFixed(f);
		}
		
		v = nu.js(n, f);
		
		if(b != undefined && b) {v = nu.zp(v, f)}
		
		if(nu.isN(v)) {
			if(v.indexOf('.') > -1) {
				let parts = v.toString().split(".");
				return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,",") + (parts[1] ? "." + parts[1] : parts[1] == undefined ? "": ".");
			} else {
				return v.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			}
		} else {
			return '0';
		}
	},
	// 지수 숫자 문자열형태의 숫자로 반환(값, 소수점 자를 자릿수)
	js : function(n, f) {
		f = f === undefined ? 0:f;
		
		let v = nu.isN(n) ? n:0;
		let r = '';
		let d = ('' + v).indexOf('.');
		
		v = '' + parseFloat(v); 
		
		if(v.indexOf('e') > 0 && ('' + parseFloat(v)).indexOf('e') > -1) {
			let a = v.split('e');
			let p = parseFloat(a[1]);
			
			r += ('' + Math.pow(10, Math.abs(p))).substr(1);
			
			if(p > 0) { r = a[0].replace('.', '') + r; } 
			else { r = r.substr(0,1) + '.' + r.substr(1) + a[0].replace('.', ''); }
			
			return nu.rd(r, f);
		} else {
			return nu.rd(v, f);
		}
	},
	// 일정자릿수 이하 버림 처리
	rd : function(v, p) {
		let i = ('' + v).indexOf('.');
		
		p = nu.isN(p) ? parseFloat(p):0;
		
		if(i > -1) {
			let s = v.split('.'), suffix = s[1];
			suffix = suffix.length > p ? suffix.substr(0, p):suffix;
			v = s[0] + (p !== 0 ? '.' + suffix:'');
		}
		
		return v;
	},
	// right 0 padding
	zp : function(v, p) {
		let i = ('' + v).indexOf('.');
		
		p = nu.isN(p) ? parseFloat(p):0;
		v = '' + v;
		
		if(i > -1) {
			let s = v.split('.'), suffix = s[1];
			suffix += suffix.length > p ? '':('' + Math.pow(10, p - suffix.length)).substr(1);
			v = s[0] + (p !== 0 ? '.' + suffix:'');
		} else {
			v += (p !== 0 ? '.' + ('' + Math.pow(10, p)).substr(1):'');
		}
		
		return v;  
	},
	// 숫자 여부 판별(boolean 형태로 결과 반환)
	isN : function(v, d) {
		return v === null ? false:v === undefined ? false:v === '' ? false:isNaN(v) ? false:isFinite(v)? true:false;
	},
	// 숫자 디폴트 값 설정
	nvl : function(v, d) {
		d = d === undefined ? 0:d;
		return v === null ? d:v === undefined ? d:v === '' ? d:isNaN(v) ? d:isFinite(v)? parseFloat(v):d;
	},
	// 더하기
	plus : function(a, b) {
		let aPnt = ('' + a).indexOf('.') == -1 ? -1:('' + a).split('.')[1].length;
		let bPnt = ('' + b).indexOf('.') == -1 ? -1:('' + b).split('.')[1].length;
		
		if(aPnt > -1 || bPnt > -1) {
			let pnt = aPnt > bPnt ? aPnt:bPnt;
			let square = Math.pow(10, pnt);
			let aVal = parseFloat((a * square).toFixed(0));
			let bVal = parseFloat((b * square).toFixed(0));
			
			return nu.nvl((aVal + bVal) / square);
		} else {
			return a+b;
		}
	},
	// 빼기
	minus : function(a, b) {
		let aPnt = ('' + a).indexOf('.') == -1 ? -1:('' + a).split('.')[1].length;
		let bPnt = ('' + b).indexOf('.') == -1 ? -1:('' + b).split('.')[1].length;
		
		if(aPnt > -1 || bPnt > -1) {
			let pnt = aPnt > bPnt ? aPnt:bPnt;
			let square = Math.pow(10, pnt);
			let aVal = parseFloat((a * square).toFixed(0));
			let bVal = parseFloat((b * square).toFixed(0));
			
			return nu.nvl((aVal - bVal) / square);
		} else {
			return a-b;
		}
	},
	// 곱하기
	multi : function(a, b) {
		let aPnt = ('' + a).indexOf('.');
		let bPnt = ('' + b).indexOf('.');
		
		if(aPnt > -1 || bPnt > -1) {
			let pnt = ('' + a).substr(aPnt).length - 1 + ('' + b).substr(bPnt).length - 1;
			let square = Math.pow(10, pnt);
			let aVal = parseFloat(a) * square;
			let bVal = parseFloat(b) * square;
			
			return nu.nvl((aVal * bVal) / (square * square));
		} else {
			return parseFloat(a)*parseFloat(b);
		}
	},
	// 나누기
	div : function(a, b) {
		let aPnt = ('' + a).indexOf('.');
		let bPnt = ('' + b).indexOf('.');
		
		if(aPnt > -1 || bPnt > -1) {
			let pnt = aPnt - bPnt;
			let square = Math.pow(10, pnt);
			let aVal = parseFloat(a) * square;
			let bVal = parseFloat(b) * square;
			
			return nu.nvl((aVal / bVal));
		} else {
			return parseFloat(a)/parseFloat(b);
		}
	},
	// 나머지
	remainder : function(a, b) {
		let aPnt = ('' + a).indexOf('.') == -1 ? -1:('' + a).split('.')[1].length;
		let bPnt = ('' + b).indexOf('.') == -1 ? -1:('' + b).split('.')[1].length;
		
		if(aPnt > -1 || bPnt > -1) {
			let pnt = aPnt > bPnt ? aPnt:bPnt;
			let square = Math.pow(10, pnt);
			let aVal = parseFloat((a * square).toFixed(0));
			let bVal = parseFloat((b * square).toFixed(0));
			
			return (aVal % bVal) / square;
		} else {
			return a%b;
		}
	}
}

// 문자열  utils
var su = {
	// 전달 받은 값을 복사한다
	copy : function(text) {
		var $copy = $("<input>");
		$("body").append($copy);
		$copy.val(text).select();
		document.execCommand("copy");
		$copy.remove();
	},
	nvl : function(text, d) {
		d = d === undefined ? '':d;
		return text === undefined ? d:text === null ? d:text;
	}
}

// ajax 요청 처리(url, parameter, 성공 처리 함수, 에러 처리 함수, 전송전 처리 함수, 완료 후 처리 함수)
function ajax(u, p, s, e, b, c) {
	$.ajax({
		url : u,
		type : 'POST',
		dataType : 'json',
		data : p,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			if(typeof b === 'function') { b(); }
		},
		success : function(data) {
			if(typeof s === 'function') { s(data); }
		},
		error : function(data) {
			if(typeof e === 'function') { e(data); }
		},
		complete : function(data) {
			if(typeof c === 'function') { c(data); }
		}
	})
}

// ajax 처리(parameter)
// .url : 요청 url
// .data : 전송할 파라메터
// .beforeSend : 전송 전 처리 한수
// .success : 성공 처리 함수
// .error : 에러 처리 함수
// .complete : 완료 처리 함수
// .done : ajax 처리 후 처리 함수
function ajaxOption(o) {
	$.ajax({
		url : o.url,
		type : o.type === undefined ? 'POST':o.type,
		dataType : o.dataType === undefined ? 'json':o.dataType,
		data : o.data,
		processData: o.processData === undefined ? true:o.processData,
        contentType: o.contentType === undefined ? 'application/x-www-form-urlencoded; charset=UTF-8':o.contentType,
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			if(typeof o.beforeSend === 'function') { o.beforeSend(xhr); }
			if(o.loading === true) { loading.show(); }
		},
		success : function(data) {
			if(typeof o.success === 'function') { o.success(data); }
		},
		error : function(data) {
			if(typeof o.error === 'function') { o.error(data); }
		},
		complete : function(data) {
			if(o.loading === true) { loading.hide(); }
			if(typeof o.complete === 'function') { o.complete(data); }
		}
	}).done(o.done)
}

// alert 표출
var lrt = {
	// 확인 버튼만 있는 alert
	client : function(text, title, callBackFun, data) {
		var target = '#lrtClnt';
		var $this = $(target);
		
		lrt.center(target);
		
		title = title === undefined ? '':title === null ? '':title;
		text = text === undefined ? '':text === null ? '':text;
		
		$this.find('.alert-title').html(title);
		$this.find('.alert-message').html(text);
		$this.find('.top-close').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('#lrtClntOk').off();
			$this.hide();
			
			if(typeof callBackFun === 'function') { callBackFun(data); }
		})
		$this.find('#lrtClntOk').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('#lrtClntOk').off();
			$this.hide();
			
			if(typeof callBackFun === 'function') { callBackFun(data); }
		});
		
		$this.show();
	},
	// 확인, 취소 버튼 있는 alert
	confirm : function(text, title, callBackFun, data) {
		var target = '#lrtCnfrm';
		var $this = $(target);
		
		lrt.center(target);
		
		title = title === undefined ? '':title === null ? '':title;
		text = text === undefined ? '':text === null ? '':text;
		
		$this.find('.alert-title').html(title);
		$this.find('.alert-message').html(text);
		$this.find('.top-close').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
		})
		$this.find('#lrtCnfrmCncl').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
		});
		$this.find('#lrtCnfrmOk').off().on('click', function() {
			$this.find('.top-close').off();
			$this.find('button').off();
			$this.hide();
			
			if(typeof callBackFun === 'function') { callBackFun(data); }
		});

		$this.off().on('keydown', function(key) {
			if(key.keyCode==13) { 
				$this.find('.top-close').off();
				$this.find('button').off();
				$this.hide();
				
				if(typeof callBackFun === 'function') { callBackFun(data); }
			}
		});
		
		$this.show();
		$this.focus();
	},
	// 전달 받은 selector 가운데 정렬
	center : function(i) {
		var height = $(i).height();
		height = ($(window).height()-height)/2;
		var top = height < 0 ? 0:height;
		$(i).css({"top":top});
	}
}

// jquery utils
var ju = {
		// 달력 생성
		datepicker : function(t) {
			$(t).datepicker({
				inline: true,
				dateFormat: 'yy/mm/dd'
			});
		},
		// 커스터 마이징 용도
		dp : function(d) {
			$(d.selector).datepicker({
				inline: true,
				dateFormat: d.format === undefined ? 'yy/MM/dd':d.format
			});
		}
}

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|hh|mm|ss)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            default: return $1;
        }
    });
};


Date.prototype.formatZ = function(f) {
    if (!this.valueOf()) return " ";
 
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|hh|mm|ss)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getUTCFullYear();
            case "yy": return (d.getUTCFullYear() % 1000).zf(2);
            case "MM": return (d.getUTCMonth() + 1).zf(2);
            case "dd": return d.getUTCDate().zf(2);
            case "HH": return d.getUTCHours().zf(2);
            case "hh": return ((h = d.getUTCHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getUTCMinutes().zf(2);
            case "ss": return d.getUTCSeconds().zf(2);
            default: return $1;
        }
    });
};

 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

// date utils
var du = {
	// 전달 받은 데이터 형식의 문자열을 입력받은 format으로 변환하여 반환
	// date : 변환 할 날짜 형식 데이터(ex. yyyyMMddHHmmssSSS)
	// date : 변환 할 날짜 포맷
	// date : 변경 될 날짜 포맷
	getCstmFrmt : function(date, origin, format) {
		var result = format;
		
		if(date == 0 || date == '') {
			return '';
		}

		// if((origin.indexOf('yyYY') > -1 || origin.indexOf('yyyy') > -1) && origin.indexOf('MM') > -1 && origin.indexOf('dd') > -1 && origin.indexOf('HH') > -1 && origin.indexOf('mm') > -1 && origin.indexOf('ss') > -1 && (date+'').length >= 14) {
		// 	var utc_date = '';
			
		// 	if(origin.indexOf('yyYY') > -1) {
		// 		utc_date = (date + '').substr(origin.indexOf('yyYY'), 4);
		// 	} else if(origin.indexOf('yyyy') > -1) {
		// 		utc_date = (date + '').substr(origin.indexOf('yyyy'), 4);
		// 	}
			
		// 	utc_date = utc_date + '-' + (date + '').substr(origin.indexOf('MM'), 2)
		// 					+ '-' + (date + '').substr(origin.indexOf('dd'), 2)
		// 					+ 'T' + (date + '').substr(origin.indexOf('HH'), 2)
		// 					+ ':' + (date + '').substr(origin.indexOf('mm'), 2)
		// 					+ ':' + (date + '').substr(origin.indexOf('ss'), 2);
		// 	/*
		// 	if(origin.indexOf('SSSSSS') > -1) {
		// 		utc_date = utc_date + '.' + (date + '').substr(origin.indexOf('SSSSSS'), 6);
		// 	} else if(origin.indexOf('SSS') > -1) {
		// 		utc_date = utc_date + '.' + (date + '').substr(origin.indexOf('SSS'), 3);
		// 	}
		// 	*/
		// 	utc_date = utc_date + 'Z';
	
		// 	var local_date = '' + new Date(utc_date).format("yyyyMMddHHmmss");
		// 	if(origin.indexOf('SSSSSS') > -1 && (date+'').length >= 20) {
		// 		local_date = local_date + (date + '').substr(origin.indexOf('SSSSSS'), 6);
		// 	} else if(origin.indexOf('SSS') > -1 && (date+'').length >= 17) {
		// 		local_date = local_date + (date + '').substr(origin.indexOf('SSS'), 3);
		// 	}
	
		// 	//console.log(date + '-->' + utc_date + ':' + local_date);

		// 	date = '' + local_date;
		// }

		if(format.indexOf('yyyy') > -1) {
			result = result.replace(/yyyy/g, (date + '').substr(origin.indexOf('yyyy'), 4));
		}
		if(format.indexOf('YY') > -1) {
			result = result.replace(/YY/g, (date + '').substr(origin.indexOf('YY'), 2));
		}
		if(format.indexOf('MM') > -1) {
			result = result.replace(/MM/g, (date + '').substr(origin.indexOf('MM'), 2));
		}
		if(format.indexOf('dd') > -1) {
			result = result.replace(/dd/g, (date + '').substr(origin.indexOf('dd'), 2));
		}
		if(format.indexOf('HH') > -1) {
			result = result.replace(/HH/g, (date + '').substr(origin.indexOf('HH'), 2));
		}
		if(format.indexOf('mm') > -1) {
			result = result.replace(/mm/g, (date + '').substr(origin.indexOf('mm'), 2));
		}
		if(format.indexOf('ss') > -1) {
			result = result.replace(/ss/g, (date + '').substr(origin.indexOf('ss'), 2));
		}
		if(format.indexOf('SSSSSS') > -1) {
			result = result.replace(/SSSSSS/g, (date + '').substr(origin.indexOf('SSSSSS'), 6));
		}
		if(format.indexOf('SSS') > -1) {
			result = result.replace(/SSS/g, (date + '').substr(origin.indexOf('SSS'), 3));
		}
		
		return result;
	}
}

// cookie utils
var cu = {
		setCookie : function(cookieName, cookieValue, cookieExpireDay){
			cookieName = cookieName.replace(/^\s+|\s+$/g,'');
			cookieValue = cookieValue.replace(/^\s+|\s+$/g,'');
		    var expire = new Date();
		    expire.setDate(expire.getDate() + cookieExpireDay);
		    cookies = cookieName + '=' + escape(cookieValue) + '; path=/ '; // 한글 깨짐을 막기위해 escape(cValue)처리
		    if(typeof cookieExpireDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
		    document.cookie = cookies;
		},
		getCookie : function(cookieName) {
			cookieName = cookieName + '=';
		    var cookieData = document.cookie;
		    var start = cookieData.indexOf(cookieName);
		    var cookieValue = '';
		    if(start != -1){
		        start += cookieName.length;
		        var end = cookieData.indexOf(';', start);
		        if(end == -1)end = cookieData.length;
		        cookieValue = cookieData.substring(start, end);
		    }
		    return unescape(cookieValue);
		}
}

// date utils
//전달 받은 데이터 형식의 문자열을 입력받은 format으로 변환하여 반환
// date : 변환 할 날짜 형식 데이터(ex. yyyyMMddHHmmssSSS)
// date : 변환 할 날짜 포맷
// date : 변경 될 날짜 포맷
function getCstmFrmt(date, origin, format) {
	var result = format;

	if(date == 0 || date == '') {
		return '';
	}

	// if(origin.indexOf('yyyy') > -1 && origin.indexOf('MM') > -1 && origin.indexOf('dd') > -1 && origin.indexOf('HH') > -1 && origin.indexOf('mm') > -1 && origin.indexOf('ss') > -1 && (date+'').length >= 14) {
	// 	var utc_date = (date + '').substr(origin.indexOf('yyyy'), 4)
	// 					+ '-' + (date + '').substr(origin.indexOf('MM'), 2)
	// 					+ '-' + (date + '').substr(origin.indexOf('dd'), 2)
	// 					+ 'T' + (date + '').substr(origin.indexOf('HH'), 2)
	// 					+ ':' + (date + '').substr(origin.indexOf('mm'), 2)
	// 					+ ':' + (date + '').substr(origin.indexOf('ss'), 2);
	// 	/*
	// 	if(origin.indexOf('SSSSSS') > -1) {
	// 		utc_date = utc_date + '.' + (date + '').substr(origin.indexOf('SSSSSS'), 6);
	// 	} else if(origin.indexOf('SSS') > -1) {
	// 		utc_date = utc_date + '.' + (date + '').substr(origin.indexOf('SSS'), 3);
	// 	}
	// 	*/
	// 	utc_date = utc_date + 'Z';

	// 	var local_date = '' + new Date(utc_date).format("yyyyMMddHHmmss");
	// 	if(origin.indexOf('SSSSSS') > -1 && (date+'').length >= 20) {
	// 		local_date = local_date + (date + '').substr(origin.indexOf('SSSSSS'), 6);
	// 	} else if(origin.indexOf('SSS') > -1 && (date+'').length >= 17) {
	// 		local_date = local_date + (date + '').substr(origin.indexOf('SSS'), 3);
	// 	}

	// 	//console.log(date + '-->' + utc_date + ':' + local_date);

	// 	date = '' + local_date;
	// }


	if(format.indexOf('yyyy') > -1) {
		result = result.replace(/yyyy/g, (date + '').substr(origin.indexOf('yyyy'), 4));
	}
	if(format.indexOf('MM') > -1) {
		result = result.replace(/MM/g, (date + '').substr(origin.indexOf('MM'), 2));
	}
	if(format.indexOf('dd') > -1) {
		result = result.replace(/dd/g, (date + '').substr(origin.indexOf('dd'), 2));
	}
	if(format.indexOf('HH') > -1) {
		result = result.replace(/HH/g, (date + '').substr(origin.indexOf('HH'), 2));
	}
	if(format.indexOf('mm') > -1) {
		result = result.replace(/mm/g, (date + '').substr(origin.indexOf('mm'), 2));
	}
	if(format.indexOf('ss') > -1) {
		result = result.replace(/ss/g, (date + '').substr(origin.indexOf('ss'), 2));
	}
	if(format.indexOf('SSSSSS') > -1) {
		result = result.replace(/SSSSSS/g, (date + '').substr(origin.indexOf('SSSSSS'), 6));
		result = result.replace(/SSS/g, (date + '').substr(origin.indexOf('SSS'), 3));
	}
	return result;
}

function getUtcDateFrmt(origin)
{
	// //origin: yyyy.mm.dd format
	// var utc_date = '' + new Date(origin.replace(/\./g, '-')).formatZ("yyyy.MM.dd");
	// console.log('origin: ' + origin + ' --> ' + utc_date + ' GMT');

	// var xxx = Date(origin.replace(/\./g, '-'))
	// console.log('==> ' + xxx.toString() + ', GMT: ' + xxx.toGMTString());


	// return utc_date;

	return origin;
}



var wordbook = {};
function getWordbook() {
	ajaxOption({
		url:'/common/getWordBook',
		success:function(data) {
			let length = data.length;
			for(let i=0; i<length; i++) {
				wordbook[data[i].id] = data[i].txt;
			}
		}
	})
}

function showNotice(data) {
	$('#notiTitle').html(data.title);
	$('#notiDt').html(du.getCstmFrmt(data.regDt, 'yyyyMMddHHmmss', 'yyyy-MM-dd HH:mm:ss'));
	$('#notiContents').html(data.contents);
	$('#notiContents').html($('#notiContents').text());
	
	$('#lrtNotice').show();
}

function parseQuery(queryString) {
    var query = {};
    var pairs = (queryString[0] === '?' ? queryString.substr(1) : queryString).split('&');
    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split('=');
        query[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1] || '');
    }
    return query;
}

$(document).ready(function() {
	getWordbook();
})

var loading = {
	show : function() {
		$('#loading').show();
	},
	hide : function() {
		$('#loading').hide();
	}
}