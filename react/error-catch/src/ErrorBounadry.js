import React, { Compoenet } from 'react';

class ErrorBoundary extends Compoenet {
    state = {
        error: false
    };

    componentDidCatch(error, info) {
        console.log('에러가 발생했습니다');
        console.log({
            error,
            info
        });
        this.setState({
            error: true,
        });
    }

    return() {
        if (this.state.error) {
            return <h1>에러 발생</h1>;
        }
        return this.props.children;
    }
}

export default ErrorBoundary;