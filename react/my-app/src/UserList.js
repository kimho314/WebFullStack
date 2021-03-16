import React, { useEffect, useContext } from 'react';
import { UserDispatch } from './App';

// const User = React.memo(function User({ user, onRemove, onToggle }) {
const User = React.memo(function User({ user }) {
    const { username, email, id, active } = user;
    const dispatch = useContext(UserDispatch);

    // useEffect(() => {
    //     console.log('컴포넌트가 화면에 나타남');
    //     // props -> state
    //     // request REST API
    //     // D3, Video.js
    //     // setInterval, setTimeout
    //     // access DOM
    //     return () => {
    //         // clearInterval, clearTimeout
    //         // remove library instance
    //         console.log('컴포넌트가 화면에서 사라짐');
    //     }
    // }, []);

    useEffect(() => {
        console.log('user 값이 설정됨');
        console.log(user);
        return () => {
            console.log('user 값이 바뀌기 전');
            console.log(user);
        }
    }, [user]); // user값이 업데이트될때 호출

    return (
        // <div>
        //     <b style={{
        //         color: active ? 'green' : 'black',
        //         cursor: 'pointer'
        //     }}
        //         onClick={() => onToggle(id)}
        //     >
        //         {username}
        //     </b>
        //     &nbsp;
        //     <span>({email})</span>
        //     <button noClick={() => onRemove(id)}>삭제</button>
        // </div>
        <div>
            <b style={{
                color: active ? 'green' : 'black',
                cursor: 'pointer'
            }}
                onClick={() => dispatch({ type: 'TOGGLE_USER', id })}
            >
                {username}
            </b>
            &nbsp;
            <span>({email})</span>
            <button onClick={() => dispatch({ type: 'REMOVE_USER', id })}>삭제</button>
        </div>
    )
})

// function UserList({ users, onRemove, onToggle }) {
function UserList({ users }) {

    return (
        <div>
            {/* {users.map(user =>
                <User user={user} key={user.id} onRemove={onRemove} onToggle={onToggle} />
            )} */}
            {users.map(user =>
                <User user={user} key={user.id} />
            )}
        </div>
    )
}

export default React.memo(UserList);