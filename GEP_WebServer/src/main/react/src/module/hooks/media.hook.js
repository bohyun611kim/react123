import { useState, useEffect } from 'react'
import { toBreakpoint } from '../utils/media.util'

export function useBreakpoint() {
    const [breakpoint, setBreakpoint] = useState(toBreakpoint())
    const setBreakpointHelper = () => setBreakpoint(toBreakpoint())

    useEffect(() => {
        window.addEventListener("resize", setBreakpointHelper)
        // return () => window.removeEventListener("resize", setBreakpointHelper)
    }, [toBreakpoint()])

    useEffect(() => { 
        return () => window.removeEventListener("resize", setBreakpointHelper)
    }, [])
    
    return { breakpoint }
}

