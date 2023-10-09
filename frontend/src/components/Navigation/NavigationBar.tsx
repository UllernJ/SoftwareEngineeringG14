import {Link} from "react-router-dom";
import {isUserLoggedIn} from "../../service/UserService";
import {isOrganizationLoggedIn} from "../../service/OrganizationService";

export const NavigationBar = () => {

    const handleLogout = () => {
        sessionStorage.removeItem('organization');
        sessionStorage.removeItem('user');
        window.location.reload();
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container">
                <Link className="navbar-brand" to="/">Home</Link>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/tours">Tours</Link>
                        </li>
                        {!isUserLoggedIn() && !isOrganizationLoggedIn() ? (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/register/user">Register</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/login">Login</Link>
                                </li>
                            </>
                        ) : (
                            <li className="nav-item">
                                <button className="btn btn-danger" onClick={handleLogout}>Logout</button>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    );
}

