import './App.css'
import { useState} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";

import LoginPage from "./pages/LoginPage.tsx";
import ProtectedRoute from "./pages/ProtectedRoute.tsx";
import RegisterPage from "./pages/RegisterPage/RegisterPage.tsx";
import Homepage from "./pages/HomePage.tsx";
import WeckerPage from "./pages/WeckerPage.tsx";
import SetupPage from "./pages/SetupPage.tsx";
import AdressePageHome from "./pages/RegisterPage/AdressePageHome.tsx";
import AdressePageWork from "./pages/RegisterPage/AdressePageWork.tsx";
import TimePlan from "./pages/RegisterPage/TimePlan.tsx";

function App() {

    const [user, setUser] = useState("")

    return (


            <Routes>
                <Route
                    path={""}
                    element={
                        <LoginPage setUser={setUser}/>
                    }
                />

                <Route
                    path={"Register"}
                    element={
                        <RegisterPage/>
                    }
                />
                <Route
                    path={"Wecker"}
                    element={
                        <WeckerPage/>
                    }
                />
                <Route
                    path={"HomePage"}
                    element={
                        <Homepage />
                    }
                />
                <Route
                    path={"Setup"}
                    element={
                        <SetupPage />
                    }
                />
                <Route
                    path={"Adresspagehome"}
                    element={
                        <AdressePageHome />
                    }
                />

                <Route
                    path={"Adresspagework"}
                    element={
                        <AdressePageWork />
                    }
                />

                <Route
                    path={"Timeplan"}
                    element={
                        <TimePlan />
                    }
                />
                <Route element={<ProtectedRoute user={user}/>}>


                </Route>
            </Routes>

    )
}

export default App
