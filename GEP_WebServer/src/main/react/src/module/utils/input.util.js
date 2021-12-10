

export function toUseValue(e) {
    const { value, type, checked } = e.target
    const useValue = ['checkbox', 'radio'].includes(type) ? checked : value
    return useValue
}

export function toUseNames(e, index) {
    const { name } = e.target, useNames = name.split('.')
    return String(index) ? useNames[index] : useNames
}


export function toEmptyValue(object) {
    let status = false
    Object.keys(object).forEach(key => {
        if([''].includes(object[key]) === true) return status = true
    })
    return status
}
