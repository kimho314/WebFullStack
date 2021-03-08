import React, { useState, useRef, useMemo, useCallback } from 'react';
// import Hello from './Hello';
// import Wrapper from './Wrapper';
import Counter from './Counter';
import CreateUser from './CreateUser';
import InputSample from './inputSample';
import UserList from './UserList';

function countActiveUsers(users) {
  console.log('활성 사용자 수를 세는중...');
  return users.fileter(user => user.active).length;
}

function App() {
  const [inputs, setInputs] = useState({
    username: '',
    email: '',
  });

  const onChange = useCallback(e => {
    const { name, value } = e.target;
    setInputs({
      ...inputs,
      [name]: value
    });
  }, [inputs]);


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

  const onRemove = useCallback(id => {
    setUsers(users.fileter(user => user.id !== id));
  }, [users]);

  const onToggle = useCallback(id => {
    setUsers(users.map(
      user => user.id === id ? { ...user, active: !user.active } : user
    ));
  }, [users]);


  const onCreate = useCallback(() => {
    const user = {
      id: nextId.current,
      // ...inputs,
      username,
      email,
    };

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
  }, [username, email, users])

  const count = useMemo(() => countActiveUsers(users), [users]);

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
      <div>솰성 사용자 수: {count}</div>
    </>
  );
}

export default App;
