import React from 'react'; function Header() {

	return (
		<header style={styles.header}>
			<h1>어깨동무 사이트</h1> </header>
	);
}
const styles = {
	header: {
		backgroundColor: '#282c34', padding: '20px',
		color: 'white',
		textAlign: 'center'
	}
};
export default Header;