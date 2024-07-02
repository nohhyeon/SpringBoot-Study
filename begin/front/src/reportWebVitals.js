//reportWebVitals 함수로 웹 애플리케이션의 성능을 측정하며 onPerfEntry는 콜백 함수를 인수로 받는다.
const reportWebVitals = onPerfEntry => {
	// onPerfEntry가 함수인 경우에만 성능 측정을 수행한다.
	if (onPerfEntry && onPerfEntry instanceof Function) {
		//web-vitals 모듈을 동적으로 임포트하며 이 모듈은 웹 성능 측정에 필요한 함수들을 제공한다.
		import('web-vitals').then(({ getCLS, getFID, getFCP, getLCP, getTTFB }) => { //각 성능 측정 함수를 호출하고 측정된 결과를 onPerfEntry 콜백 함수로 전달한다.
			getCLS(onPerfEntry); //getCLS은 Cumulative Layout Shift로 누적 레이아웃을 이동한다. getFID(onPerfEntry); //getFID는 First Input Delay로 첫 입력을 지연한다. getFCP(onPerfEntry); //getFCP는 First Contentful Paint로 첫 번째 콘텐츠풀을 페인트한다. getLCP(onPerfEntry); //getLCP는 Largest Contentful Paint로 가장 큰 콘텐츠풀을 페인트한다. getTTFB(onPerfEntry); //getTTFB는 Time to First Byte로 첫 바이트까지의 시간이다.
		});
	}
};
//reportWebVitals 함수를 기본 내보내기(default export)로 내보내며 다른 파일에서 이 함수를 임포트하여 사용할 수 있다.
export default reportWebVitals;