import {Link} from "react-router-dom";

export const NavigationBar = () => {

    return (
        <nav style={navStyle}>
            <div className="container" style={containerStyle}>
                <Link to="/" style={linkStyle}>Home</Link>
                <div style={spacerStyle}></div>
                <Link to="/register/user" style={linkStyle}>Register</Link>
                <Link to="/login" style={linkStyle}>Login</Link>
            </div>
        </nav>
    );
}

const navStyle = {
    backgroundColor: '#f8f9fa',
    padding: '10px 0',
};

const containerStyle = {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
};

const linkStyle = {
    textDecoration: 'none',
    color: '#333',
    margin: '0 10px',
};

const spacerStyle = {
    flex: '1',
};
