
import jquery from 'jquery';

export function format_date(date) {
  Date.prototype.yyyymmdd = function() {
    var mm = this.getMonth() + 1; // getMonth() is zero-based
    var dd = this.getDate();

    return [this.getFullYear(),
        (mm>9 ? '' : '0') + mm,
        (dd>9 ? '' : '0') + dd
      ].join('.');
  };

	return date.yyyymmdd();
}

export function inject_script(path=null, type='') {
  if (process.browser && path != null) {
    var aScript = document.createElement('script');
    // aScript.type = type;
    //aScript.src = "/resources/js/common/common.js";
    aScript.src = path;

    document.head.appendChild(aScript);
    aScript.onload = () => {

    };

  }

  if (typeof window !== "undefined") {
    window.$ = window.jQuery = window.jquery = jquery;
  }
}


export function is_empty(val) {

  // test results
  //---------------
  // []        true, empty array
  // {}        true, empty object
  // null      true
  // undefined true
  // ""        true, empty string
  // ''        true, empty string
  // 0         false, number
  // true      false, boolean
  // false     false, boolean
  // Date      false
  // function  false

  if (val === undefined) return true;
  if (typeof (val) == 'function' || typeof (val) == 'number' || typeof (val) == 'boolean' || Object.prototype.toString.call(val) === '[object Date]') return false;
  // null or 0 length array
  if (val == null || val.length === 0) return true;
  // empty object
  if (typeof (val) == "object") {
    for (var f in val) return false;
    return true;
  }
  return false;
}


export async function sha256(message) {
  const msgBuffer = new TextEncoder('utf-8').encode(message);
  const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
  const hashArray = Array.from(new Uint8Array(hashBuffer));
  const hashHex = hashArray.map(b => ('00' + b.toString(16)).slice(-2)).join('');
  return hashHex;
  /* sha256(payload).then( (hash) => { console.log('Login-hash:', hash); service.send(EX.SETUP, { hash } ); */
}


export function hash_code64(str) {
  var i = str.length
  var hash1 = 5381
  var hash2 = 52711
  while (i--) {
    const char = str.charCodeAt(i)
    hash1 = (hash1 * 33) ^ char
    hash2 = (hash2 * 33) ^ char
  }
  return (hash1 >>> 0) * 4096 + (hash2 >>> 0)
}

export function hash_code(s) {
  if (Array.prototype.reduce) {
    return s.split("").reduce(function (a, b) { a = ((a << 5) - a) + b.charCodeAt(0); return a & a }, 0);
  }
  var hash = 0;
  if (s.length === 0) return hash;
  for (var i = 0; i < s.length; i++) {
    var character = s.charCodeAt(i);
    hash = ((hash << 5) - hash) + character;
    hash = hash & hash; // Convert to 32bit integer
  }
  return hash;
}

export function hash_equal(obj, hash) {
  const str1 = JSON.stringify(obj);
  return hash_code(str1) === hash;
}


export function serializeJSON(obj) {
  var t = typeof (obj);
  if (t !== "object" || obj === null) {
    // simple data type
    if (t === "string") obj = '"' + obj + '"';
    return String(obj);
  }
  else {
    // recurse array or object
    var n, v, json = [], arr = (obj && obj.constructor === Array);

    for (n in obj) {
      v = obj[n]; t = typeof (v);

      if (t === "string") v = '"' + v + '"';
      else if (t === "object" && v !== null) v = JSON.stringify(v);

      json.push((arr ? "" : '"' + n + '":') + String(v));
    }

    return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
  }
};

