import styled from 'styled-components'
import Slider from "react-slick";

import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import { useBreakpoint } from '../../../module/hooks/media.hook'
import { getLocaleObject } from '../../../module/utils/language.util';

function SlideItem({item}) {
  // console.log("list",item)

  const handleClick = (e, target) => {
    e.preventDefault();
    location.href = target;
  }

  return (
    <li style={{ width: "100%", display: "inline-block", cursor:"pointer" }} onClick={e=> handleClick(e, item.path)}>
        <img src={item.image} alt={item.alt} />
        {/* <div className="detail_exchange"><p className="detail_tit"> {item.detail} </p></div> */}
    </li>
       
  );
}

export default function SlideBanner() {
    const { breakpoint } = useBreakpoint()
    const { innerWidth } = window

    var list =  getLocaleObject('intro.newcoins');

    console.log("SlideBanner: count", list);

    const breakView = ['md', 'sm', 'xs'].includes(breakpoint) ? (['md'].includes(breakpoint) ? 4 : (innerWidth < 769 && innerWidth > 600 ? 2: 1)) : 4
    
    const SliderButton = {
        Prev: ({onClick}) => <button className="slick-prev slick-arrow" aria-label="Previous" type="button" onClick={onClick} />,
        Next: ({onClick}) => <button className="slick-next slick-arrow" aria-label="Next" type="button" onClick={onClick} />
    }
    const settings = {
        dots: false, infinite: true, speed: 4000, autoplay: true, autoplaySpeed: 5000, pauseOnHover: true,  slidesToShow: breakView, slidesToScroll: 1,
        opacity: 1, arrows: true,
        nextArrow: <SliderButton.Prev />, prevArrow: <SliderButton.Next />
    }
    return (
        <ul className="list_assets slick-initialized slick-slider">
          <div className="slick-list draggable"> 
              <Slider className="slick-track" {...settings}>
                    {list.length > 0 && list.map((item, index) => <SlideItem key={index} item={item}/>)}
              </Slider>
          </div>
        </ul>
    )
}


