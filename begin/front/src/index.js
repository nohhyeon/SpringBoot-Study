//React는 컴포넌트를 정의하고 관리하는 데 사용되며 ReactDOM은 React 컴포넌트를 실제 DOM에 렌더링한다.
import React from 'react';
import ReactDOM from 'react-dom/client';
// CSS 파일을 임포트하여 애플리케이션에 스타일을 적용한다.
import './index.css';
// 애플리케이션의 루트 컴포넌트를 지원한다.
import App from './App';
// 웹 애플리케이션의 성능 측정을 지원한다.
import reportWebVitals from './reportWebVitals';
//root라는 id를 가진 DOM 요소를 선택하여 애플리케이션을 렌더링할 루트 컨테이너로 사용한다.
const root = ReactDOM.createRoot(document.getElementById('root')); // 루트 컨테이너에 React 컴포넌트를 렌더링한다.
root.render(<React.StrictMode>
	<App /> </React.StrictMode>
);
// 애플리케이션의 성능을 측정하기 위한 함수를 호출한다.

reportWebVitals();
