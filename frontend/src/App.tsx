import './App.css'
import {useState} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";

import LoginPage from "./pages/LoginPage.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";
import RegisterPage from "./pages/RegisterPage/RegisterPage.tsx";

import WeckerPage from "./pages/WeckerPage.tsx";
import SetupPage from "./pages/SetupPage.tsx";
import AdressePageHome from "./pages/RegisterPage/AdressePageHome.tsx";
import AdressePageWork from "./pages/RegisterPage/AdressePageWork.tsx";
import TimePlan from "./pages/RegisterPage/TimePlan.tsx";

function App() {

    const [user, setUser] = useState("")

    return (


        <Router>
            <Routes>
                    <Route path={"/"}element={<LoginPage setUser={setUser}/> }/>

                    <Route path={"/Register"}element={<RegisterPage/>}/>

                    <Route element={<ProtectedRoute user={user}/>}>

                            <Route path={"/weckerpage"} element={ <WeckerPage user={user} />} />

                            <Route path={"/setuppage"}element={<SetupPage/>}/>
                            <Route path={"/adresspagehome"}element={<AdressePageHome user={user} />} />
                            <Route path={"/adresspagework"} element={<AdressePageWork user={user} />} />
                            <Route path={"/timeplanpage"} element={<TimePlan user={user} /> }/>
                    </Route>

            </Routes>
        </Router>

    )
}

export default App
