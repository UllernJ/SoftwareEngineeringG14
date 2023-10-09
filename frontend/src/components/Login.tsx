import {useEffect, useState} from 'react'
import {Organization, User} from "../types/global";
import {organizationLogin, userLogin} from "../service/LoginService";

export const Login = () => {
    const [email, setEmail] = useState<string>('')
    const [username, setUsername] = useState<string>('')
    const [password, setPassword] = useState<string>('')
    const [isOrganization, setIsOrganization] = useState<boolean>(false)

    const handleLogin = async (e: any) => {
        e.preventDefault()
        if (isOrganization) {
            organizationLogin(email, password).then(async res => {
                if (res.status === 200) {
                    const organization: Organization = await res.json()
                    sessionStorage.setItem('organization', JSON.stringify(organization))
                    window.location.href = '/'
                } else {
                    console.error('Error logging in')
                }
            })
        } else {
            userLogin(username, password).then(async res => {
                if (res.status === 200) {
                    const user: User = await res.json()
                    sessionStorage.setItem('user', JSON.stringify(user))
                    window.location.href = '/'
                } else {
                    console.error('Error logging in')
                }
            })
        }
    }

    const switchToOrg = () => {
        setIsOrganization(!isOrganization)
    }

    useEffect(() => {
        if (sessionStorage.getItem('user')) {
            window.location.href = '/'
        }
    }, [])

    const quickLogin = (isOrg: boolean = false) => {
        if (!isOrg) {
            userLogin("user", "user").then(async res => {
                if (res.status === 200) {
                    const user: User = await res.json()
                    sessionStorage.setItem('user', JSON.stringify(user))
                    window.location.href = '/'
                } else {
                    console.error('Error logging in')
                }
            })
        } else {
            const email = "test@test.test"
            const password = "test"
            organizationLogin(email, password).then(async res => {
                if (res.status === 200) {
                    const organization: Organization = await res.json()
                    sessionStorage.setItem('organization', JSON.stringify(organization))
                    window.location.href = '/'
                } else {
                    console.error('Error logging in')
                }
            })
        }
    }

    const quickLoginAdmin = () => {
        userLogin("test", "test").then(async res => {
            if (res.status === 200) {
                const user: User = await res.json()
                sessionStorage.setItem('user', JSON.stringify(user))
                window.location.href = '/'
            } else {
                console.error('Error logging in')
            }
        })
    }


    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">
                            <h1>Login - {isOrganization ? "Organization" : "User"}</h1>
                        </div>
                        <div className="card-body">
                            <form onSubmit={handleLogin}>
                                {isOrganization ? (
                                    <div className="form-group">
                                        <label htmlFor="email">Email</label>
                                        <input type="email"
                                               className="form-control"
                                               id="email"
                                               value={email}
                                               onChange={(e) => setEmail(e.target.value)}
                                               placeholder="Email"/>
                                    </div>
                                ) : (
                                    <div className="form-group">
                                        <label htmlFor="username">Username</label>
                                        <input type="username"
                                               className="form-control"
                                               id="username"
                                               value={username}
                                               onChange={(e) => setUsername(e.target.value)}
                                               placeholder="Username"/>
                                    </div>
                                )}

                                <div className="form-group">
                                    <label htmlFor="password">Password</label>
                                    <input type="password"
                                           className="form-control"
                                           id="password"
                                           value={password}
                                           onChange={(e) => setPassword(e.target.value)}
                                           placeholder="Password"/>
                                </div>
                                <button type="submit" className="btn btn-primary me-lg-4">
                                    Login
                                </button>
                                <button type="button" className="btn btn-primary" onClick={switchToOrg}>
                                    {!isOrganization ? 'Switch to Organization login' : 'Switch to User login'}
                                </button>
                            </form>
                        </div>
                    </div>
                    <div className="col-md-12">
                        <h3>Quick login</h3>
                        <p>For testing purposes</p>
                        <button onClick={() => quickLogin} className="btn btn-primary m-2">Login as user</button>
                        <button onClick={() => quickLogin(true)} className="btn btn-success m-2">Login as organization</button>
                        <button onClick={quickLoginAdmin} className="btn btn-danger m-2">Login as admin</button>
                    </div>
                </div>
            </div>
        </div>
    )
}