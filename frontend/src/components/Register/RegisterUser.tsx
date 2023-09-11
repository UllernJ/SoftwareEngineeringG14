import {useState} from "react";
export const RegisterUser = () => {
    const [name, setName] = useState<string | null>(null)
    const [username, setUsername] = useState<string | null>(null)
    const [email, setEmail] = useState<string | null>(null)
    const [password, setPassword] = useState<string | null>(null)
    const [message, setMessage] = useState<string | null>(null)

    const handleRegister = async (e: any) => {
        e.preventDefault()
        try {
            const body = {name, username, email, password}
            const response = await fetch('http://localhost:8080/api/user/register', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(body)
            })
            const parseRes = await response;
            if (parseRes.status === 200 || parseRes.status === 201) {
                setMessage('Register success')
                setName(null)
                setUsername(null)
                setEmail(null)
                setPassword(null)
            } else {
                setMessage('Register failed')
            }
        } catch (err: any) {
            setMessage("Register failed")
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
                                           value={name || ''}
                                           onChange={(e) => setName(e.target.value)}
                                           placeholder="Name"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="username">Username</label>
                                    <input type="username"
                                           className="form-control"
                                           id="username"
                                           value={username || ''}
                                           onChange={(e) => setUsername(e.target.value)}
                                           placeholder="Username"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="email">Email</label>
                                    <input type="email"
                                            className="form-control"
                                            id="email"
                                            value={email || ''}
                                            onChange={(e) => setEmail(e.target.value)}
                                            placeholder="Email"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <input type="password"
                                            className="form-control"
                                            id="password"
                                            value={password || ''}
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
            {message && <div className="alert alert-primary" role="alert">{message}</div>}
        </div>
    )
}