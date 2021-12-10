
export function getLocaleContent() {
    var locale = localStorage.getItem("common/LANGUAGE");
    if (locale == null || locale == '') locale = 'ko';
    let content = require('../../resources/data/content/content.'+locale)
    let i = 0 ;
    for (i = 0 ; i < arguments.length ; i++) content = content[arguments[i]];
    if (content == undefined) return arguments[i-1]
    return content;
}


export function getLocaleObject() {
    var locale = localStorage.getItem("common/LANGUAGE");
    if (locale == null || locale == '') locale = 'ko';
    let content = require('../../resources/data/object/object.'+locale)

    let i = 0 ;
    for (i = 0 ; i < arguments.length ; i++) {
        content = content[arguments[i]];
    }
    return content;
}

