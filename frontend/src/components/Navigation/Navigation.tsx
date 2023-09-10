import { Routes, Route } from 'react-router-dom'
import {Home} from "../../pages/Home";
import {RegisterPage} from "../../pages/RegisterPage";

export const Navigation = () => {

    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/register" element={<RegisterPage/>} />
        </Routes>
    )
}