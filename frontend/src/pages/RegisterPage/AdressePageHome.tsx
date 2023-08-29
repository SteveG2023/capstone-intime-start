import {ChangeEvent, FormEvent, useState} from "react";
import { useNavigate} from "react-router-dom";
import axios from "axios";

export default function RegisterPageHome() {
    const [wohnadressestadt, setWohnAdresseStadt] = useState("");
    const [wohnadressestrasse, setWohnAdresseStrasse] = useState("");
    const [wohnadressenummer, setWohnAdresseNummer] = useState("");



    const nav = useNavigate();

    function onChangeWohnAdresseStadt(event: ChangeEvent<HTMLInputElement>) {
        setWohnAdresseStadt(event.target.value)
    }

    function onChangeWohnAdresseStrasse(event: ChangeEvent<HTMLInputElement>) {
        setWohnAdresseStrasse(event.target.value)
    }

    function onChangeWohnAdresseNummer(event: ChangeEvent<HTMLInputElement>) {
        setWohnAdresseNummer(event.target.value)
    }






    function registerhome(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post("/api/user/register/placeid/", { wohnadressestadt, wohnadressestrasse, wohnadressenummer})

            .then(() => nav("/Adresspagework"))
            .catch((error) => console.log(error))
    }

    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerhome}>
                    <h1>REGISTER</h1>
                    <div className="Register yout Home-Address">
                        <input type={"text"} required={true} id={wohnadressestadt} placeholder={"Please insert your username"}
                               onChange={onChangeWohnAdresseStadt}/>
                        <input type={"password"} required={true} id={wohnadressestrasse} placeholder={"Please insert your password"}
                               onChange={onChangeWohnAdresseStrasse}/>
                        <input type={"vorname"} required={true} id={wohnadressenummer} placeholder={"Please insert your vorname"}
                               onChange={onChangeWohnAdresseNummer}/>








                    </div>

                    <button>Register</button>
                </form>


            </div>
        </div>
    )
}
