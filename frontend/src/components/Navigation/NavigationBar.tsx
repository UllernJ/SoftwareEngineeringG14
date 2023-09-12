import {Link} from "react-router-dom";
import {isUserLoggedIn} from "../../service/UserService";

export const NavigationBar = () => {

    const handleLogout = () => {
        sessionStorage.removeItem('user');
        window.location.reload();
    }

    return (
        <nav style={navStyle}>
            <div className="container" style={containerStyle}>
                <Link to="/" style={linkStyle}>Home</Link>
                <Link to="/tours" style={linkStyle}>Tours</Link>
                <div style={spacerStyle}></div>
                {!isUserLoggedIn() ? (
                    <>
                    <Link to="/register/user" style={linkStyle}>Register</Link>
                    <Link to="/login" style={linkStyle}>Login</Link>
                    </>
                ) : (
                    <>
                    <button onClick={handleLogout}>
                        Logout
                    </button>
                    </>
                )}
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
