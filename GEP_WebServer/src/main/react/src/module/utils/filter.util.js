// 콤마 제거
export function toRemoveComma(val) {
    return String(val).includes(",") ? val.replace(/,/gi, '') : val
}

// 콤마 추가
export function toWithCommas(x) {
    if(String(x).includes(",")) { x = x.replace(/,/gi, '') }
    // return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    if(x.toString().includes('.')) {
        const parts = x.toString().split(".")
        parts[0] += '.';
        // const number = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + parts[1].length > 8 ? parts[1].substring(0, 8) : parts[1]
        const number = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + parts[1].substring(0, 8)
        return number

        // if(isNaN(toRemoveComma(number))) return x
        // else return number
        // if(number) return x
        // else return number
    } else {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
    }
    // if(!isNaN(result)) return result
}
// 점이 안쳐지는 문제..

// 아스키 필터
export function toFilterAscii(val) {
    return val.replace(/[^\u0020-\u007e]+/g, '')
}

// 이메일 마스킹
export function emailMasking(email) {
    // const len = email.split('@')[0].length - 3;
    // return email.replace(new RegExp('.(?=.{0,' + len + '}@)', 'g'), '*');    
    // return email.replace(/(...)(.+)/g, "$1****");
    // return email.replace(/(...)(.+)/g, "")
    return email.split('@')[0]
}

// Path 주소
export function toPathName(paths) {
    paths.forEach((path, index) => paths[index] = '/' + paths[index])
    return paths.join('')
}