
export function toRegexCheck(key, val) {
    switch(key) {
      case 'email': return !/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(val)
      case 'password': return !/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!~@#$%^*&+=]).*$/.test(val)
      // case 'phone': return !/^\d{3}-\d{3,4}-\d{4}$/.test(val)
      case 'phone': return !/^[0-9]{10,}$/.test(val)
      case 'birthDay': return !/^[0-9]{8}$/.test(val)
      default: break;      
    }
}


