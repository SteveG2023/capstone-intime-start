import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import "./LoginPage.css";

type Props = {
    setUser: (user:string) => void
}
export default function LoginPage(loginPageProps: Props) {

    const [username, setUsername] = useState("");

    const [password, setPassword] = useState("");

    const nav = useNavigate();


    useEffect(() => {

        axios.get("/api/user/me")
            .then((response) => {
                setUsername(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    function onChangeHandlerUsername(event: ChangeEvent<HTMLInputElement>) {
        setUsername(event.target.value)
    }

    function onChangeHandlerPassword(event: ChangeEvent<HTMLInputElement>) {
        setPassword(event.target.value)
    }

    function login(event:FormEvent<HTMLFormElement>) {
        try {
            event.preventDefault()

            axios.post("/api/user/login", undefined, {auth: {username, password}})
                .then((response) => loginPageProps.setUser(response.data))

            nav("/weckerpage");
        }
        catch(error)
        {
            alert("Es ist ein Fehler aufgetreten. Die Login-Daten  sind möglicherweise falsch.");
        }


    }





    return (
        <div className="wrapper">
            <div className="card">
                <div className="logo-container-l">

                </div>
                <form onSubmit={login}>
                    <h1>LOGIN</h1>
                    <input type={"text"} id={username} placeholder={"Enter your username"} required={true} onChange={onChangeHandlerUsername}/>
                    <input type={"password"} id={password} placeholder={"Enter your password"} required={true} onChange={onChangeHandlerPassword}/>
                    <button>login</button>
                </form>
                <Link className="direction-link" to={"/register"}>New here? REGISTER NOW</Link>

            </div>
        </div>
    )
}