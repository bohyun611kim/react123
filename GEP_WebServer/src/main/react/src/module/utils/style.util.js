
// 적용이 안된다 해도, 어떤 타입인지 표기는 가능한듯.
// 혹은 기본값으로 지정해서 타입 인지
export function toClassName(classname = []) {
    // let className = 'tab-item'; if(activeTab === label) className += ' is-active';
    // let className = activeTab === label ? ['tab-item', 'is-active'].join(' ') : 'tab-item'
    // let className = ['tab-item', activeTab === label && 'is-active'].join(' ')
    // let className = ['tab-item'].concat(activeTab === label && 'is-active').filter(x => x).join(' ')
    // let className = ['tab-item', activeTab === label && 'is-active'].filter(isNaN).join(' ')
    // let className = ['tab-item', activeTab === label && 'is-active'].filter(x => x).join(' ')
    // let className = 'tab-item' + [activeTab === label && ' is-active'].filter(x => x)
    // let className = 'tab-item' + (activeTab === label ? ' ' + activeClass : '') + (tabClass ? ' ' + tabClass : '')
    return (classname).filter(x => x).join(' ')
}