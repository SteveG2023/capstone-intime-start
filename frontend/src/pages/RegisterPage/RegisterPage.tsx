import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import "./RegisterPage.css";

export default function RegisterPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [vorname, setVorname] = useState("");
    const [nachname, setNachname] = useState("");
    const [email, setEmail] = useState("");
    const [arbeitsadressestadt, setarbeitsadressestadt] = useState("");
    const [arbeitsadressestrasse, setarbeitsadressestrasse] = useState("");
    const [arbeitsadressenummer, setarbeitsadressenummer] = useState("");
    const [wohnadressestadt, setwohnadressestadt] = useState("");
    const [wohnadressestrasse, setwohnadressestrasse] = useState("");
    const [wohnadressenummer, setwohnadressenummer] = useState("");


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

    function onChangewohnadressestadt(event: ChangeEvent<HTMLInputElement>) {
        setwohnadressestadt(event.target.value)
    }

    function onChangewohnadressestrasse(event: ChangeEvent<HTMLInputElement>) {
        setwohnadressestrasse(event.target.value)
    }

    function onChangewohnadressenummer(event: ChangeEvent<HTMLInputElement>) {
        setwohnadressenummer(event.target.value)
    }

    function onChangearbeitsadressestadt(event: ChangeEvent<HTMLInputElement>) {
        setarbeitsadressestadt(event.target.value)
    }

    function onChangearbeitsadressestrasse(event: ChangeEvent<HTMLInputElement>) {
        setarbeitsadressestrasse(event.target.value)
    }

    function onChangearbeitsadressenummer(event: ChangeEvent<HTMLInputElement>) {
        setarbeitsadressenummer(event.target.value)
    }


    async function register(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        try {
            // Erste Axios POST-Anfrage
            await axios.post("/api/user/register", {
                username,
                password,
                vorname,
                nachname,

                email,
                wohnadressestadt,
                wohnadressestrasse,
                wohnadressenummer,
                arbeitsadressestadt,
                arbeitsadressestrasse,
                arbeitsadressenummer,


            });



            // Dritte Axios GET-Anfrage
            setTimeout(() => {
                axios.get(`/api/user/anfragen/${username}`)

            }, 1500); // 5000 Millisekunden entsprechen 5 Sekunden



            // Navigieren nach erfolgreicher Ausführung der letzten GET-Anfrage
            nav("/homepage");
        } catch (error) {
            console.log(error);
        }
    }




        /*
        event.preventDefault();
        axios({
            method: 'get',
            url: `/api/user/placeidw/${username}${wohnadressestadt}/${wohnadressestrasse}/${wohnadressenummer}`
        */


        return (
            <div className="wrapper">
                <div className="card">
                    <div className="logoContainerL">

                    </div>
                    <form onSubmit={register}>
                        <h3 className={"homeplace"}>Register</h3>
                        <div className="Register">
                            <input type={"text"} required={true} id={username} placeholder={" username"}
                                   onChange={onChangeUsername}/>
                            <input type={"password"} required={true} id={password} placeholder={" password"}
                                   onChange={onChangePassword}/>
                            <input type={"vorname"} required={true} id={vorname} placeholder={" first-name"}
                                   onChange={onChangeVorname}/>
                            <input type={"nachname"} required={true} id={nachname}
                                   placeholder={" last-name"}
                                   onChange={onChangeNachname}/>
                            <input type={"Email"} required={true} id={email} placeholder={" email"}
                                   onChange={onChangeEmail}/>


                        </div>

                        <h3 className={"homeplace"}> Homplace</h3>
                        <div className="Register your Home-Address">
                            <input type={"Stadt"} required={true} id={wohnadressestadt}
                                   placeholder={" Stadt"}
                                   onChange={onChangewohnadressestadt}/>
                            <input type={"Strasse"} required={true} id={wohnadressestrasse}
                                   placeholder={" Strasse"}
                                   onChange={onChangewohnadressestrasse}/>
                            <input type={"nummer"} required={true} id={wohnadressenummer}
                                   placeholder={" Nummer"}
                                   onChange={onChangewohnadressenummer}/>
                        </div>

                        <h3 className={"homeplace"}> Workplace</h3>
                        <div className="Register">





                            <input type={"City"} required={true} id={arbeitsadressestadt}
                                   placeholder={" Stadt"}
                                   onChange={onChangearbeitsadressestadt}/>
                            <input type={"street"} required={true} id={arbeitsadressestrasse}
                                   placeholder={" Strasse"}
                                   onChange={onChangearbeitsadressestrasse}/>

                            <input type={"number"} required={true} id={arbeitsadressenummer}
                                   placeholder={" nummer"}
                                   onChange={onChangearbeitsadressenummer}/>


                        </div>

                        <button>Save </button>

                        <div>


                            <Link className="direction-link" to={"/"}>Login</Link>


                        </div>
                    </form>


                </div>
            </div>

        )


}