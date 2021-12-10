// 양수값 반환
export function toPositiveNumber(val) {
    // return Math.sign(Number(val)) === -1 ? Math.abs(val) : val
    return Math.sign(Number(val)) === -1 ? Math.max(val, 0) : val
}

// N자리 이후 버림(인수: 값, 소수점 자리수)
export function toFloorDecimals(val, decimals){
    decimals = decimals || 0;
    decimals = Math.pow(10, decimals);
    return Math.floor(val * decimals)/decimals
}

// 소수점 자리수 구하기, 없으면 false
export function toGetPrecision(number = 1) { 
    const numSep = String(number).split(".")
    // const numSep = String(number).split(".")
    return numSep.length > 1 ? numSep[1].length : 0
}

// 자리수 제한
export function toDecimalLimit(number, limit = 0) { 
    // return toGetPrecision(number) > limit ? toFloorDecimals(number, limit) : number
    var ret =toGetPrecision(number) > limit ? Number(number).toFixed(limit) : number;
    return Number(ret)
}

export function toLocale(number, limit = null) { 
    var locale = localStorage.getItem("common/LANGUAGE");
    if (String(number).endsWith('.')) return number;

    // let len = toGetPrecision(number)
    if (String(number).includes('.'))  {
      let reg = new RegExp(`(.*)\\.(\\d+)`);
      let matches =  reg.exec(String(number));
      // let result =  String(number).match(`/(.*)\.(\d+4)/`)  // match(/(.*)\.(\d+4)/)

      if (matches) {
        if (matches.length == 2 || limit == 0) {
          number = matches[1];
        } else {
            if (matches.length == 3) {
                number = (limit == 0 ? matches[1] : matches[1]+"."+matches[2].substr(0,limit));
            }
        }
      }
     //console.log("toLocale1:", number, limit, ", len:",matches.length, ", matches:", matches);
    }

     //console.log("toLocale2:", number, limit);

    let ret = null;
    if (locale == 'ko') {
      ret = toNumber(number).toLocaleString('ko-KR');
    } else if (locale == 'en') {
      ret = toNumber(number).toLocaleString('en-US');
    } else {
      ret = toNumber(number).toLocaleString('ko-KR');
    }

    return ret;
}

export function toNumber(number, limit = 0) { 

        if (String(number).endsWith('.')) {
            number = String(number).replace(/./g, '');
        }

        let n = Number(String(number).replace(/,/g, ''));

        if (Number.isNaN(n)) return 0;
        if (limit != 0) return Number(n.toFixed(limit));
        return n;
}

export function number_format(number, decimals, decPoint = '.', thousandsSep = ',') { // eslint-disable-line camelcase
    //   example 1: number_format(1234.56)
    //   returns 1: '1,235'
    //   example 2: number_format(1234.56, 2, ',', ' ')
    //   returns 2: '1 234,56'
    //   example 3: number_format(1234.5678, 2, '.', '')
    //   returns 3: '1234.57'
    //   example 4: number_format(67, 2, ',', '.')
    //   returns 4: '67,00'
    //   example 5: number_format(1000)
    //   returns 5: '1,000'
    //   example 6: number_format(67.311, 2)
    //   returns 6: '67.31'
    //   example 7: number_format(1000.55, 1)
    //   returns 7: '1,000.6'
    //   example 8: number_format(67000, 5, ',', '.')
    //   returns 8: '67.000,00000'
    //   example 9: number_format(0.9, 0)
    //   returns 9: '1'
    //  example 10: number_format('1.20', 2)
    //  returns 10: '1.20'
    //  example 11: number_format('1.20', 4)
    //  returns 11: '1.2000'
    //  example 12: number_format('1.2000', 3)
    //  returns 12: '1.200'
    //  example 13: number_format('1 000,50', 2, '.', ' ')
    //  returns 13: '100 050.00'
    //  example 14: number_format(1e-8, 8, '.', '')
    //  returns 14: '0.00000001'

    if (String(number).endsWith('.')) return number;

    number = (number + '').replace(/[^0-9+\-Ee.]/g, '')
    const n = !isFinite(+number) ? 0 : +number
    const prec = !isFinite(+decimals) ? 0 : Math.abs(decimals)
    const sep = (typeof thousandsSep === 'undefined') ? ',' : thousandsSep
    const dec = (typeof decPoint === 'undefined') ? '.' : decPoint
    let s = ''
    const toFixedFix = function (n, prec) {
      if (('' + n).indexOf('e') === -1) {
        return +(Math.round(n + 'e+' + prec) + 'e-' + prec)
      } else {
        const arr = ('' + n).split('e')
        let sig = ''
        if (+arr[1] + prec > 0) {
          sig = '+'
        }
        return (+(Math.round(+arr[0] + 'e' + sig + (+arr[1] + prec)) + 'e-' + prec)).toFixed(prec)
      }
    }
    // @todo: for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec).toString() : '' + Math.round(n)).split('.')
    if (s[0].length > 3) {
      s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep)
    }
    if ((s[1] || '').length < prec) {
      s[1] = s[1] || ''
      s[1] += new Array(prec - s[1].length + 1).join('0')
    }
    return s.join(dec)
  }