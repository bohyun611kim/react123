export const compose = (...fns) => fns.reduce((f, g) => (...args) => f(g(...args)))

export function toFormData(object) {
    const formData = new FormData();
    Object.keys(object).forEach(key => formData.append(key, object[key]));
    return formData;
}

export function syntObjectToFormData(obj, rootName, ignoreList) {
    const formData = new FormData();
  
    function appendFormData(data, root) {
        if (!ignore(root)) {
            root = root || '';
            if (data instanceof File) {
                formData.append(root, data);
            } else if (Array.isArray(data)) {
                for (let i = 0; i < data.length; i++) appendFormData(data[i], root + '[' + i + ']');
            } else if (typeof data === 'object' && data) {
                for (let key in data) {
                    if (data.hasOwnProperty(key)) root === '' ? appendFormData(data[key], key) : appendFormData(data[key], root + '.' + key)
                }
            } else {
                if (data !== null && typeof data !== 'undefined') formData.append(root, data);
            }
        }
    }

    function ignore(root){
        return Array.isArray(ignoreList)
            && ignoreList.some(function(x) { return x === root; });
    }
  
    appendFormData(obj, rootName);
    return formData;
}


export function toFileSize(bytes) {
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes === 0) return '0Byte';
    const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return Math.round(bytes / Math.pow(1024, i), 2) + sizes[i];
 }