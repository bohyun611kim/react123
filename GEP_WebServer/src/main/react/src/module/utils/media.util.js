export function toBreakpoint() {
    const status = { xl:1200, lg: 1023, md: 768, sm: 360, xs: 1 }
    const { innerWidth } = window, { xl, lg, md, sm, xs} = status
    if(innerWidth > xl) return 'xl'
    else if(innerWidth > lg && innerWidth <= xl) return 'lg'
    else if(innerWidth > md && innerWidth <= xl) return 'md'
    else if(innerWidth > sm && innerWidth <= lg) return 'sm'
    else if(innerWidth > xs && innerWidth <= sm) return 'xs'
    else return false
}
