import React from 'react'; function Footer() {
	return (
		<footer style={styles.footer}>
			<p>&copy; 2024 어깨동무 사이트. 저작권 소유</p> </footer>
	);
}
const styles = {
	footer: {
		backgroundColor: '#282c34', padding: '10px',
		color: 'white',
		textAlign: 'center',
		position: 'fixed', bottom: 0, width: '100%'
	}
};
export default Footer;