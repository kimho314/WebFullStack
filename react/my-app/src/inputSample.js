import React, { useState, useRef } from 'react';

function InputSample() {
    // const [text, setText] = useState('');
    const [inputs, setInputs] = useState({
        name: '',
        nickname: '',
    });

    const nameInput = useRef();

    const { name, nickname } = inputs;

    const onChange = (e) => {
        const { name, value } = e.target;
        console.log(e.target.name);
        console.log(e.target.value);

        setInputs({
            ...inputs,
            [name]: value,
        });
        // setText(e.target.value);
    };

    const onReset = () => {
        // setText('');
        setInputs({
            name: '',
            nickname: '',
        });
        nameInput.current.focus();
    };

    return (
        <div>
            <input name="name" placeholder="이름" onChange={onChange} value={name} ref={nameInput} />
            <input name="nickname" placeholder="닉네임" onChange={onChange} value={nickname} />
            {/* <input onChange={onChange} value={text} /> */}
            <button onClick={onReset}>초기화</button>
            <div>
                <b>값: </b>
                {/* {text} */}
                {name} ( {nickname} )
            </div>
        </div>
    );
}

export default InputSample;