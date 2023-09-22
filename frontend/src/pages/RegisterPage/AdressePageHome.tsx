import {ChangeEvent, FormEvent, useState, } from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import "./AdressPageHome.css";

type Props = {
    user: string;
}

export default function AdressePageHome(props: Props) {


    const [stadt, setStadt] = useState("");
    const [strasse, setStrasse] = useState("");
    const [nummer, setNummer] = useState("");
    const nav = useNavigate();


    function onChangeStadt(event: ChangeEvent<HTMLInputElement>) {
        setStadt(event.target.value);
    }

    function onChangeStrasse(event: ChangeEvent<HTMLInputElement>) {
        setStrasse(event.target.value);
    }

    function onChangeNummer(event: ChangeEvent<HTMLInputElement>) {
        setNummer(event.target.value);
    }

    function register(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios({
            method: 'get',
            url: `/api/user/placeidhome/${props.user}/${stadt}/${strasse}/${nummer}`
        })
            .then(() => nav("/setuppage"))
            .catch((error) => console.log(error))
    }

    return (
        <div className="wrapper">
            <div className="">
                <div className="logoContainerL"></div>
                <form className={"register"} onSubmit={register}>
                    <h3 className={"wohnort"}>Wohnort</h3>
                    <div className="Register your Home-Address">
                        <div></div>
                        <input className={"eingabe"} type="text" required={true} value={stadt} placeholder="Stadt" onChange={onChangeStadt}/>
                    </div>
                    <div>
                        <input className={"eingabe"} type="text" required={true} value={strasse} placeholder="Strasse"
                               onChange={onChangeStrasse}/>
                    </div>
                    <div>
                        <input className={"eingabe"} type="text" required={true} value={nummer} placeholder="Nummer"
                               onChange={onChangeNummer}/>
                    </div>
                    <button>Speichern</button>
                    <div>
                        <Link className="direction-link" to="/setuppage">zurück</Link>
                    </div>
                </form>
            </div>
        </div>
    )
}