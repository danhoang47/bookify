function useDebounce(callback, timeout = 300) {
    let timer;

    return (...args) => {
        clearTimeout(timer);
        timer = setTimeout(() => {
            callback(args)
        }, timeout)
    }
}

export default useDebounce;