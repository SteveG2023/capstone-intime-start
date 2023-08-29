import {ChangeEvent, FormEvent, useState} from "react";
import { useNavigate} from "react-router-dom";
import axios from "axios";

export default function RegisterPageWork() {


    const [arbeitsadressestadt, setArbeitsadresseStadt] = useState("");
    const [arbeitsadressestrasse, setArbeitsAdresseStrasse] = useState("");
    const [arbeitsadressesnummer,setArbeitsAdresseNummer] = useState("");


    const nav = useNavigate();


    function onChangeArbeitsAdresseStadt(event: ChangeEvent<HTMLInputElement>) {
        setArbeitsadresseStadt(event.target.value)
    }
    function onChangeArbeitsAdresseStrasse(event: ChangeEvent<HTMLInputElement>) {
        setArbeitsAdresseStrasse(event.target.value)
    }
    function onChangeArbeitsAdresseNummer(event: ChangeEvent<HTMLInputElement>) {
        setArbeitsAdresseNummer(event.target.value)
    }




    function registerwork(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post("/api/user/register/placeid/", { arbeitsadressestadt, arbeitsadressestrasse, arbeitsadressenummer})

            .then(() => nav("/Iimeplan"))
            .catch((error) => console.log(error))
    }

    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerwork}>
                    <h1>REGISTER</h1>
                    <div className="Register">


                        <input type={"password"} required={true} id={arbeitsadressestrasse} placeholder={"Please insert your Work-Street"}
                               onChange={onChangeArbeitsAdresseStrasse}/>
                        <input type={"vorname"} required={true} id={arbeitsadressesnummer} placeholder={"Please insert your Work-Number"}
                               onChange={onChangeArbeitsAdresseNummer}/>

                        <input type={"nachname"} required={true} id={arbeitsadressestadt} placeholder={"Please insert your Work-Address"}
                               onChange={onChangeArbeitsAdresseStadt}/>









                    </div>

                    <button>Register</button>
                </form>


            </div>
        </div>
    )
}
