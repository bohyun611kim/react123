import axios from 'axios'
import { BASE_URL } from '../myconstants'
// import AuthService from './auth.service';

const request = axios.create({
  baseURL: BASE_URL,
  timeout: 10000,
})

request.interceptors.request.use(config => {
  //if (new AuthService().signedIn())
    //config.headers.Authorization = new AuthService().getToken().token_type + ' ' + new AuthService().getToken().access_token;
    // const locale = store.getState().navigate.getIn(['language', 'selected'])
    const locale = 'ko';
    config.headers.post['Accept-Language'] = locale;
    config.headers.get['Accept-Language'] = locale;
    return config
});

// request.interceptors.response.use(response => {
//   return new AuthService().checkStatus(response)
// }, error => {
//   return Promise.reject(error)
// }
// )

request.defaults.headers.post['Accept-Language'] = 'ko';


export default request
