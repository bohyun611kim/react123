import styled from 'styled-components'
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import { useBreakpoint } from '../../../module/hooks/media.hook'


/*
export default function SlideNotice({list}) {
    const { breakpoint } = useBreakpoint()
    const { innerWidth } = window

    if (list.length == 0 )return null;

    const breakView = ['md', 'sm', 'xs'].includes(breakpoint) ? (['md'].includes(breakpoint) ? 2.5 : (innerWidth < 769 && innerWidth > 600 ? 2: 1)) : 4
    
    const SliderButton = {
        Prev: ({onClick}) => <button className="slide_l" onClick={onClick} />,
        Next: ({onClick}) => <button className="slide_r" onClick={onClick} />
    }
    const settings = {
        dots: false, infinite: true, speed: 500, slidesToShow: breakView, slidesToScroll: breakView, autoplay: true, autoplaySpeed: 8000, pauseOnHover: true,
        nextArrow: <SliderButton.Prev />, prevArrow: <SliderButton.Next />
    }

    return (
        <NewestCoin className="new_coin carousel-wrapper inner">
            <div className="slide_wrap carousel-body">
                <Slider className="list" {...settings}>
                    {list.length > 0 && list.map((item, index) => <SliderItem key={index} item={item}/>)}
                </Slider>
            </div>
        </NewestCoin>
    )
}
*/


function SlideItem({item}) {

    //console.log("slide coin item",item);
    var coins = item.itemNm.split('/');

  return (
        <li style={{ width: "100%", display: "inline-block" }}>
            <img src={`/resources/img/coin-symbol/${coins[0]}.png`} alt="gdc" className="icon_coin" />
            <span>{item.itemNm}</span>
            <span>{item.closePrc}</span>
            {/* <div class="icon_chart" id="areaChart"></div> */}
            {/* <img src={`/resources/img/coin-symbol/${item.itemEngNm}.png`} alt="chart" className="icon_chart" /> */}
        </li>
  );
}

export default function SlideCoin({list}) {
    const { breakpoint } = useBreakpoint()
    const { innerWidth } = window



    if (list.length == 0 )return null;

    if (list.length < 7) {
        list = list.concat(list);
    }

    const breakView = ['md', 'sm', 'xs'].includes(breakpoint) ? (['md'].includes(breakpoint) ? 6 : (innerWidth < 769 && innerWidth > 600 ? 3: 2)) : 6
    console.log("SlideBanner: breakpoint", breakpoint, "breakView:", breakView);
    
    const SliderButton = {
        Prev: ({onClick}) => <button className="slick-prev slick-arrow" onClick={onClick} />,
        Next: ({onClick}) => <button className="slick-next slick-arrow" onClick={onClick} />
    }
    const settings = {
        dots: false, infinite: true, speed: 2000, autoplay: true, autoplaySpeed: 7000, pauseOnHover: true,  slidesToShow: breakView, slidesToScroll: 1,
        opacity: 1,useTransform:true,cssEase: 'ease-out',easing:'linear',loop:true,
        nextArrow: <SliderButton.Prev />, prevArrow: <SliderButton.Next />
    }

    return (
    <>
        {/* <div className="notice_home">
            <p class="notice_tit">동일 색상</p>
            <ul className="notice_content alCenter">
                <li>
                    <a className="noticeText" href="#/support/notice?post=8826">라이브 차트영역</a>
                </li> 
            </ul>
        </div> */}
     
        <article className="slider_notice_wrap">
            <div className="inner_new">
                <ul className="slider_data slick-initialized slick-slider">
                    <div className="slick-list draggable">
                        <Slider className="slick-track" {...settings}>
                            {list.length > 0 && list.map((item, index) => <SlideItem key={index} item={item}/>)}
                        </Slider>
                    </div>
                </ul>
            </div>
        </article>

    </>

    )
}

