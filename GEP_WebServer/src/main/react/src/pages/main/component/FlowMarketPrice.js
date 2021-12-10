import React from 'react'
import Slider from "react-slick";
import '../../../resources/styles/vendor/slick.css'
import '../../../resources/styles/vendor/slick-theme.css'
import { useBreakpoint } from '../../../module/hooks/media.hook'
import styled from 'styled-components'
//import { toClassName } from '../../module/utils/style.util'
//import { getLocaleWordContent } from '../../module/utils/language.util'

const Wrapper = styled.div`
    /* .slick-slide {width:155px; margin-left:35px; margin-right:35px;} */
`;


export default function FlowMarketPrice({alist}) {
    const { breakpoint } = useBreakpoint()
    const { innerWidth } = window

	const breakView = ['md', 'sm', 'xs'].includes(breakpoint) ? 0 : (innerWidth < 1600 ? 5 : 8)

    const settings = {
        dots: false, infinite: true, slidesToShow: breakView, slidesToScroll: 1, autoplay: true, speed: 15000, autoplaySpeed: 0, cssEase: "linear"
	}
  
  var list = ['', '' , '', '', '']

    return (
      <Wrapper className="slider_notice_wrap">
        <ul>
          <Slider {...settings}>
            {list && list.map((item, index) => 
            <li  key={index}>
              <img src="../../img/gdc.png" alt="gdc" className="icon_coin" />
              <span>GDC/KRW</span>
              <span>11,600</span>
              <img src="../../img/icon_chart.png" alt="chart" className="icon_chart" />
            </li>)}
          </Slider>
        </ul>
      </Wrapper>
    );
}