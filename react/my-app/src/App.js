import React, { useState, useRef } from 'react';
// import Hello from './Hello';
// import Wrapper from './Wrapper';
import Counter from './Counter';
import CreateUser from './CreateUser';
import InputSample from './inputSample';
import UserList from './UserList';

function App() {
  const [inputs, setInputs] = useState({
    username: '',
    email: '',
  });

  const onChange = e => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value
    });
  };

  const onRemove = id => {
    setUsers(users.fileter(user => user.id !== id));
  };

  const onToggle = id => {
    setUsers(users.map(
      user => user.id === id ? { ...user, active: !user.active } : user
    ));
  };

  const { username, email } = inputs;

  const [users, setUsers] = useState([
    {
      id: 1,
      username: 'velopert',
      email: 'public.velopert@gmail.com',
      active: true,
    },
    {
      id: 2,
      username: 'velopert2',
      email: 'public.velopert2@gmail.com',
      active: false,
    },
    {
      id: 3,
      username: 'velopert3',
      email: 'public.velopert3@gmail.com',
      active: false,
    },
  ]);

  const nextId = useRef(4); // rerendering되도 값이 바뀌지 않음

  const onCreate = () => {
    const user = {
      id: nextId.current,
      ...inputs,
    }

    /*
    새로운 배열을 추가할때는 새로운 배열을 만들고 만들어진 새로운 배열에 추가하는 방식을 사용
    e.g. spread operator, concat method
    */
    // spread oprator를 이용하여 배열에 추가
    setUsers([
      ...users,
      user
    ]);

    // concat method를 사용하여 배열에 추가
    // setUsers(users.concat(user));

    setInputs({
      username: '',
      email: '',
    });
    nextId.current += 1;
  }

  return (
    // <Wrapper>
    //   <Hello color='pink' />
    //   <Hello name='react' color='red' isSpecial={true} />
    // </Wrapper>
    // <Counter />
    // <InputSample />
    <>
      <CreateUser username={username} email={email} onChange={onChange} onCreate={onCreate} />
      <UserList users={users} onRemove={onRemove} onToggle={onToggle} />
    </>
  );
}

export default App;
