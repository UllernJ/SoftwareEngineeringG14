import { useState } from 'react'
import {User} from "../types/global";

export const Login = () => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleLogin = async (e: any) => {
        e.preventDefault()
        try {
            const body = {username, password}
            const response = await fetch('http://localhost:8080/api/user/login', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(body)
            })
            const parseRes = await response;
            if (parseRes.status === 200 || parseRes.status === 201) {
                console.log('Login success')
                setUsername('')
                setPassword('')
                const user: User = await response.json()
                sessionStorage.setItem('user', JSON.stringify(user))
                window.location.href = '/'
            } else {
                console.log('Login failed')
            }
        } catch (err: any) {
            console.log("Login failed")
        }
    }

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">
                            <h1>Login</h1>
                        </div>
                        <div className="card-body">
                            <form onSubmit={handleLogin}>
                                <div className="form-group">
                                    <label htmlFor="username">Username</label>
                                    <input type="username"
                                           className="form-control"
                                           id="username"
                                           value={username}
                                           onChange={(e) => setUsername(e.target.value)}
                                           placeholder="Username"/>
                                </div>
                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <input type="password"
                                           className="form-control"
                                           id="password"
                                           value={password}
                                           onChange={(e) => setPassword(e.target.value)}
                                           placeholder="Password"/>
                                </div>
                                <button type="submit" className="btn btn-primary">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
)
}