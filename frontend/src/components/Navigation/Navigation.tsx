import { Routes, Route } from 'react-router-dom'
import {Home} from "../../pages/Home";
import {RegisterUserPage} from "../../pages/RegisterUserPage";
import {RegisterOrganizationPage} from "../../pages/RegisterOrganizationPage";
import {Login} from "../Login";

export const Navigation = () => {

    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register/user" element={<RegisterUserPage/>} />
            <Route path="/register/organization" element={<RegisterOrganizationPage/>} />
            <Route path="/login" element={<Login />} />
        </Routes>
    )
}