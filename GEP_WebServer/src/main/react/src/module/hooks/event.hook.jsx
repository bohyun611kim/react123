import { useState, useRef, useEffect, useCallback } from 'react'

export function useClickOutside(setShow) {
    const ref = useRef()
    const handleClick = e => { 
      if(!ref.current.contains(e.target)) setShow(false)
    }
    useEffect(() => {
      document.addEventListener('click', handleClick)
      return () => document.removeEventListener('click', handleClick)
    }, []) 
  
    return ref
}

export const useToggle = initialValue => {
  const [value, setValue] = useState(initialValue);
  const toggler = useCallback(() => setValue(value => !value));
  return [value, toggler]
}

export const useFocus = (ref, defaultState = false) => {
  const [state, setState] = useState(defaultState);

  useEffect(() => {
    const onFocus = () => setState(true);
    const onBlur = () => setState(false);
    ref.current.addEventListener("focus", onFocus);
    ref.current.addEventListener("blur", onBlur);

    return () => {
      ref.current.removeEventListener("focus", onFocus);
      ref.current.removeEventListener("blur", onBlur);
    };
  }, []);

  return state;
}

