import {RegisterUser} from "../components/Register/RegisterUser";
import {Link} from "react-router-dom";

export const RegisterUserPage = () => {
    return (
        <div className="container">
            <span>
                Organization? <Link to="/register/organization">Register here</Link>
            </span>
        <RegisterUser />
        </div>
    )
}