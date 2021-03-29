import React from 'react';
import Profile from './Profile';
import { NavLink, Link, Route } from 'react-router-dom';
import WithRouterSample from './WithRoutersample';

function Profiles() {
    return (
        <div>
            <h3>사용자 목록</h3>
            <ul>
                <li><NavLink to="/profiles/velopert"
                    activeStyle={{ background: 'black', color: 'white' }}>velopert</NavLink></li>
                <li><NavLink to="/rpofiles/homer"
                    activeStyle={{ background: 'black', color: 'white' }}>homer</NavLink></li>
            </ul>

            <Route path="/profiles" exact render={() => <div>사용자를 선택해주세요</div>}></Route>
            <Route path="/profiles/:username" component={Profile} />
            <WithRouterSample />
        </div>
    )
}

export default Profiles;