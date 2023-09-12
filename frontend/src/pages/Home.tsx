import {User} from "../types/global";
import {getUser, isUserLoggedIn} from "../utils/User";

export const Home = () => {

    const user: User = getUser();

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    {user && isUserLoggedIn() ? (
                        <h1>Welcome, {user.username}</h1>
                    ) : (
                        <h1>Welcome, guest</h1>
                    )}
                </div>
            </div>
        </div>
    )
}