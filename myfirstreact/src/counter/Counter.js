
    import React, {useState} from 'react';
    import './Counter.css'


    function Counter () {
        const [count, setCount] = useState(2)
        var num = 1;
        return(
        <>
            <h2> {count} </h2>
            <button onClick={() => setCount(count < 10 ? count+num : 10)}> +{num} </button>
            <button onClick={() => setCount(count > 0 ? count-num : 0)}> -{num} </button>
        </>
        )
    }

    export default Counter;