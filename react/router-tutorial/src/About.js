import React from 'react';
import qs from 'qs';

function About({ location }) {
    console.log(location);
    const query = qs.parse(location.search, {
        ignoreQueryPrefix: true,
    });
    const detail = query.detail === 'true'; //값을 문자열로 읽음


    return (
        <div>
            <h1>소개</h1>
            <p>이 프로젝트는 리엑트 라우터 기초를 실습해보는 예제 프로젝트입니다.</p>
            {detail && <p>detail값이 true입니다!!!</p>}
        </div>
    );
}

export default About;