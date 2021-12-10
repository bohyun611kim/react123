
export default class StorageService {
    constructor() {
        this.language = {...this.defaultStorage('common/LANGUAGE', 1)}
        this.rememberMe = {...this.defaultStorage('user/REMEMBER_EMAIL', 1)}
        this.favList = {...this.objectStorage('exchange/FAV_LIST', 1)}
        this.exTheme = {...this.defaultStorage('exchange/THEME', 2)}
        this.notModal = {...this.defaultStorage('intro/NOT_MODAL', 1)}
        this.tabMobile = {...this.defaultStorage('common/TAB_MOBILE', 1)}
    }

    storageType = type => {
        if(type === 1) return localStorage
        else if(type === 2) return sessionStorage
    }
    defaultStorage(key, type) {
        const storageType = this.storageType
        return {
            get() { return storageType(type).getItem(key) },
            set(value) { return storageType(type).setItem(key, value) },
            remove() { return storageType(type).removeItem(key) }
        }
    }
    objectStorage(key, type) {
        const storageType = this.storageType
        return {
            get() { 
                const value = storageType(type).getItem(key)
                try { return JSON.parse(value) } catch { return null }    
            },
            set(value) { 
                if(typeof value === "object") { value = JSON.stringify(value) }
                return storageType(type).setItem(key, value)
            },
            remove() { return storageType(type).removeItem(key) }
        }
    }




}