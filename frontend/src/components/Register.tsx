import {useState} from "react";
export const Register = () => {
    const [name, setName] = useState<string>('')
    const [username, setUsername] = useState<string>('')
    const [email, setEmail] = useState<string>('')
    const [password, setPassword] = useState<string>('')


    const handleRegister = async (e: any) => {
        e.preventDefault()
        console.log("test")
        console.log(name, email, password);
        try {
            const body = {name, username, email, password}
            const response = await fetch('http://localhost:8080/api/user/register', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(body)
            })
            const parseRes = await response.json()
            console.log(parseRes)
        } catch (err: any) {
            console.error(err.message)
        }
    }

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">
                            <h1>Register</h1>
                        </div>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label htmlFor="name">Name</label>
                                    <input type="name"
                                           className="form-control"
                                           id="name"
                                           onChange={(e) => setName(e.target.value)}
                                           placeholder="Name"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="username">Username</label>
                                    <input type="username"
                                           className="form-control"
                                           id="username"
                                           onChange={(e) => setUsername(e.target.value)}
                                           placeholder="Username"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="email">Email</label>
                                    <input type="email"
                                            className="form-control"
                                            id="email"
                                            onChange={(e) => setEmail(e.target.value)}
                                            placeholder="Email"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <input type="password"
                                            className="form-control"
                                            id="password"
                                            onChange={(e) => setPassword(e.target.value)}
                                            placeholder="Password"/>
                                </div>
                                <button type="submit"
                                        onClick={(e) => handleRegister(e)}
                                        className="btn btn-primary">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}