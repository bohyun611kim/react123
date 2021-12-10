import { getLocaleObject } from "../../../module/utils/language.util";
import { getLocaleContent } from "../../../module/utils/language.util";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import { useBreakpoint } from '../../../module/hooks/media.hook'


function SlideItem({item}) {

    console.log("slide item",item);
    //var coins = item.itemNm.split('/');

  return (
        <li style={{ width: "100%", display: "inline-block" }}>
            {/* <img src={`/resources/img/coin-symbol/${coins[0]}.png`} alt="gdc" className="icon_coin" /> */}
            {item.title} <br/> {item.explain}
        </li>
  );
}

export default function EventCard() {

    const { breakpoint } = useBreakpoint()
    const { innerWidth } = window

	var list = getLocaleObject('intro.visuals');
    
    const SliderButton = {
        Prev: ({onClick}) => <button className="slick-prev slick-arrow" onClick={onClick} />,
        Next: ({onClick}) => <button className="slick-next slick-arrow" onClick={onClick} />
    }
    const settings = {
        dots: false, infinite: true, speed: 2000, autoplay: true, autoplaySpeed: 7000, pauseOnHover: true,  slidesToShow: 1, slidesToScroll: 1,
        opacity: 1, arrows: false, 
    }

	return (
		<>
      <div className="slider_notice">
        <ul className="slider_notice_tit slick-initialized slick-slider slick-dotted">
          <div className="slick-list draggable">
              <Slider className="slick-track" {...settings}>
                    {list.length > 0 && list.map((item, index) => <SlideItem key={index} item={item}/>)}
              </Slider>
          </div> 
        </ul>
      </div>
	  </>
  );
}
