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
                loginPageProps.setUser(response.data);
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
        event.preventDefault()
        axios.post("/api/user/login", undefined, {auth: {username, password}})
            .then((response) => loginPageProps.setUser(response.data))
            .then(() => nav("/weckerpage"))
            .catch((error) => console.log(error))
    }




    return (
        <div className="wrapper">
            <div className="">

                        <form onSubmit={login}>
                            <h1 className={"intime"}>In-Time</h1>
                            <h2 className={"login"}>Login</h2>

                            <input className={"Felder"} type={"text"} id={username} placeholder={"Enter your username"} required={true} onChange={onChangeHandlerUsername}/>



                            <input className={"Felder"} type={"password"} id={password} placeholder={"Enter your password"} required={true} onChange={onChangeHandlerPassword}/>


                            <button className={"Felder1"}>Login</button>
                            <Link  className={"Felder2"} to={"/register"}>New here? REGISTER NOW</Link>

                        </form>

        </div>


                </div>

    )
}