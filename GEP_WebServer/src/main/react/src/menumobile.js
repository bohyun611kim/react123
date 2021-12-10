import React ,{useState} from 'react'
import { getLocaleObject } from './module/utils/language.util';
import StorageService from "./module/services/storage.service";
export default function MenuMobile(){

    if (new StorageService().tabMobile.get() == undefined) {
		new StorageService().tabMobile.set('image1')
	}

    const [tab,setOnTab] = useState(new StorageService().tabMobile.get())
    const handleActive = (e, tab, target) =>{
        if(e) e.preventDefault();
        setOnTab(new StorageService().tabMobile.set(tab));
        location.href = target;
    }
    return(
        <div className="menu_mobile text-center">
            <ul>
                {	getLocaleObject('tab.mobile').map((item, index) => {
						return <li key={index} onClick={(e)=>handleActive(e, item.name, item.path)}>
									 <a href={item.path} className={tab === item.name ? "active" : ""}>
                                        <img src={`/resources/img/newbita/${tab === item.name ? `icon_tab${index+1}_atc.png`:`icon_tab${index+1}.png`}`} alt={item.title} />
                                        <span>{tab === item.name ? item.title : ""}</span>
                                    </a>
								</li>
						})
				}
            </ul>
        </div>
    )
}