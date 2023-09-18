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

    const [startZeit    , setStartzeit] = useState("");
    const [endZeit, setEndzeit] = useState("");
    const [vorbereitungsZeit, setVorbereitungsZeit] = useState('');
    const [maximaleweckzeit, setMaximaleWeckzeit] = useState('');
    const nav = useNavigate();






    const onChangeVorbereitungsZeit = (event) => {
        setVorbereitungsZeit(event.target.value);
    }

    // Handler zum Ändern des Benutzernamens und Speichern in einem Cookie
    function onChangeUsername(event: ChangeEvent<HTMLInputElement>) {
        const newValue = event.target.value;
        setUsername(newValue);
    }
    function onChangeStartZeit(event: ChangeEvent<HTMLInputElement>) {
        setStartzeit(event.target.value)
    }

    function onChangeEndZeit(event: ChangeEvent<HTMLInputElement>) {
        setEndzeit(event.target.value)
    }
    function onChangeMaximaleWeckzeit(event: ChangeEvent<HTMLInputElement>) {
        setMaximaleWeckzeit(event.target.value)
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

                startZeit,
                endZeit,
                vorbereitungsZeit,
                maximaleweckzeit,



            });



            // Dritte Axios GET-Anfrage
            setTimeout(() => {
                axios.get(`/api/user/anfragen2/${username}`)

            }, 2500);



            // Navigieren nach erfolgreicher Ausführung der letzten GET-Anfrage
            nav("/weckerpage");
        } catch (error) {
            console.log(error);
            alert("Es ist ein Fehler aufgetreten. Die Adresse ist möglicherweise falsch.");
        }
    }






        return (
            <div className="wrapper">
                <div className="card">
                    <div className="logoContainerL">

                    </div>
                    <form onSubmit={register}>
                        <h3 className={"User"}>Register</h3>
                        <div className="Register">

                            <div>
                                <input type={"text"} required={true} id={username} placeholder={" username"}
                                       onChange={onChangeUsername}/>
                                <input type={"password"} required={true} id={password} placeholder={" password"}
                                       onChange={onChangePassword}/>
                            </div>

                            <div>
                                <input type={"vorname"} required={true} id={vorname} placeholder={" first-name"}
                                       onChange={onChangeVorname}/>
                                <input type={"nachname"} required={true} id={nachname}
                                       placeholder={" last-name"}
                                       onChange={onChangeNachname}/>
                            </div>


                            <input type={"Email"} required={true} id={email} placeholder={" email"}
                                   onChange={onChangeEmail}/>




                        </div>

                        <h3 className={"Wohnort"}> Homplace</h3>
                        <div className="RegisterHome">

                           <div>
                                <input type={"Stadt"} required={true} id={wohnadressestadt}
                                       placeholder={" Stadt"}
                                       onChange={onChangewohnadressestadt}/>

                           </div>

                            <div>
                                <input type={"Strasse"} required={true} id={wohnadressestrasse}
                                       placeholder={" Strasse"}
                                       onChange={onChangewohnadressestrasse}/>
                             </div>


                                <input type={"nummer"} required={true} id={wohnadressenummer}
                                       placeholder={" Nummer"}
                                       onChange={onChangewohnadressenummer}/>

                            </div>



                        <h3 className={"Arbeitsort"}> Workplace</h3>
                        <div className="RegisterWork">


                            <div>

                            <input type={"City"} required={true} id={arbeitsadressestadt}
                                   placeholder={" Stadt"}
                                   onChange={onChangearbeitsadressestadt}/>
                             </div>

                            <div>
                            <input type={"street"} required={true} id={arbeitsadressestrasse}
                                   placeholder={" Strasse"}
                                   onChange={onChangearbeitsadressestrasse}/>
                            </div>

                            <div>

                            <input type={"number"} required={true} id={arbeitsadressenummer}
                                   placeholder={" nummer"}
                                   onChange={onChangearbeitsadressenummer}/>
                             </div>
                        </div>

                         <h3>  Arbeitszeiten  </h3>

                        <div>
                            <h3>Start</h3>
                            <input
                                type="time"
                                required={true}
                                id={startZeit}
                                placeholder="Start"
                                value={startZeit}
                                onChange={onChangeStartZeit}
                            />
                        </div>


                        <div>
                            <h3>Ende</h3>
                            <input
                                type="time"
                                required={true}
                                id={endZeit}
                                placeholder="Ende"
                                value={endZeit}
                                onChange={onChangeEndZeit}
                            />
                        </div>




                        <div>
                            <h3>MaximaleWeckzeit</h3>
                            <input

                                type="time"
                                required={false}
                                id={maximaleweckzeit}
                                placeholder="Maximalweckzeit"
                                onChange={onChangeMaximaleWeckzeit}
                            />
                        </div>
                        <div>
                            <input
                                type="number"
                                required={false}
                                id="vorbereitungsZeit"
                                placeholder="Vorbereitung"
                                value={vorbereitungsZeit}
                                onChange={onChangeVorbereitungsZeit}
                            />
                        </div>







                        <div>
                        <button>Save </button>
                        </div>




                          <Link className="direction-link" to={"/"}>Login</Link>

                    </form>















                </div>
            </div>

        )


}