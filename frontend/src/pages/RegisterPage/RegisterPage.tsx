import {ChangeEvent, FormEvent, useState} from "react";
import { useNavigate} from "react-router-dom";
import axios from "axios";

export default function RegisterPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [vorname, setVorname] = useState("");
    const [nachname, setNachname] = useState("");
    const [email, setEmail] = useState("");
    const nav = useNavigate();



    function onChangeUsername(event: ChangeEvent<HTMLInputElement>) {
        setUsername(event.target.value)
    }

    function onChangeVorname(event: ChangeEvent<HTMLInputElement>) {
        setVorname(event.target.value)
    }

    function onChangeNachname(event: ChangeEvent<HTMLInputElement>) {
        setNachname(event.target.value)
    }

    function onChangePassword(event: ChangeEvent<HTMLInputElement>) {
        setPassword(event.target.value)
    }
    function onChangeEmail(event: ChangeEvent<HTMLInputElement>) {
        setEmail(event.target.value)
    }

    function register(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post("/api/user/register", {username, password, vorname, nachname, email})

            .then(() => nav("/adreepagehome"))
            .catch((error) => console.log(error))
    }

    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={register}>
                    <h1>REGISTER</h1>
                        <div className="Register">
                        <input type={"text"} required={true} id={username} placeholder={"Please insert your username"}
                           onChange={onChangeUsername}/>
                        <input type={"password"} required={true} id={password} placeholder={"Please insert your password"}
                           onChange={onChangePassword}/>
                        <input type={"vorname"} required={true} id={vorname} placeholder={"Please insert your vorname"}
                           onChange={onChangeVorname}/>
                        <input type={"nachname"} required={true} id={nachname} placeholder={"Please insert your nachname"}
                               onChange={onChangeNachname}/>
                        <input type={"Email"} required={true} id={email} placeholder={"Please insert your email"}
                                   onChange={onChangeEmail}/>

                        </div>

                    <button>Register</button>
                </form>


            </div>
        </div>
    )
}

