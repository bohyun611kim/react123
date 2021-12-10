import {useEffect, useState} from 'react'

import StorageService from "./module/services/storage.service";
import MenuMobile from './menumobile';

function LikeFooter({sign}) {

	if (new StorageService().language.get() == undefined) {
		new StorageService().language.set('ko')
	}

	const [locale, setLocale] = useState(new StorageService().language.get());
    console.log("locale = ", locale)

	useEffect(()=> {
		//setLogin(sign);
	}, []);

  const handlePrivacy = (e, cont) => {
    console.log("cont = ", cont)
    e.preventDefault();
    location.href = "/site/support?cont=" + cont;
  }
  const handleSupport = (e, num) => {
    e.preventDefault();
    location.href = "/site/support?tab=" + num;
  }
  const handleInfor = (e, num) => {
    e.preventDefault();
    location.href = "/site/info_new?tab=" + num;
  }

	return (
	<>
        <div class="inner_new">
            <div class="footer_content">
                <div class="footer_top_new">
                    <div class="footer_logo_new">
                        <img src="/resources/img/newbita/logo_footer.png" alt="logo"/>
                    </div>
                    <address class="footer_body_new">
                        <p>회사명 : 주식회사 오병이어 홀딩스 | 사업자 등록번호 : 738-85-01926</p> 
                        <p>회사주소 : 서울시 강남구 봉은사로 112길6 익성빌딩 5F</p>
                        <p>고객센터 : 1833-9078 | 고객문의 이메일 : help@bita500.com</p>
                        <p>상장문의 이메일 : listing@bita500.com</p>
                    </address>
                    <p class="copyright">Copyright©bita500.com All rights reserved.</p>
                </div>
                <nav class="footer_gnb_new">
                    <ul class="gnb_ul_new">
                      <li class="gnb_li_tit" onClick={e=>handlePrivacy(e, "about")}>
                            <a href="#none">회사정보</a>
                      </li>
                      <li onClick={e=>handlePrivacy(e, "about")}> <a href="#">회사소개</a> </li>
                      <li onClick={e=>handlePrivacy(e, "terms")}> <a href="#">이용약관</a> </li>
                      <li onClick={e=>handlePrivacy(e, "privacy")}> <a href="#">개인정보처리방침</a> </li>
                    </ul>
                    <ul class="gnb_ul_new">
                        <li class="gnb_li_tit" onClick={e=>handleSupport(e, "1")}>
                            <a href="#none">고객지원</a>
                        </li>
                        <li onClick={e=>handleInfor(e, "2")}>
                            <a href="#none">인증센터</a>
                        </li>
                        <li onClick={e=>handleSupport(e, "4")}>
                            <a href="#none">이용안내</a>
                        </li>

                        <li onClick={e=>handleSupport(e, "1")}>
                            <a href="#none">공지사항</a>
                        </li>
                        <li onClick={e=>handleSupport(e, "7")}>
                            <a href="#none">자주하는 질문</a>
                        </li>
                        <li onClick={e=>handleSupport(e, "4")}>
                            <a href="#none">수수료 안내</a>
                        </li>
                        <li onClick={e=>handleSupport(e, "8")}>
                            <a href="#none">1:1 문의하기 (평일 09:00 - 18:00)</a>
                        </li>
                        <li>
                            <a href="http://pf.kakao.com/_Excrvs/chat">카카오톡 문의</a>
                        </li>
                        <li onClick={e=>handleSupport(e, "5")}>
                            <a href="#none">상장안내99</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <MenuMobile />
	</>
	);
}
  
let domContainer = document.querySelector('#footer_new');
ReactDOM.render(<LikeFooter/>, domContainer);
