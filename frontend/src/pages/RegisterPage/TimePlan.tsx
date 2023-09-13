import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";


import axios from "axios";

export default function TimePlan() {

    const username = 'w';
    const [startZeit    , setStartzeit] = useState("");
    const [endZeit, setEndzeit] = useState("");
    const [vorbereitungsZeit, setVorbereitungsZeit] = useState('');

    const onChangeVorbereitungsZeit = (event) => { const newValue = event.target.value; const minutes = newValue.split(':')[1];
        setVorbereitungsZeit(minutes)};

    const nav = useNavigate();


    function onChangeStartZeit(event: ChangeEvent<HTMLInputElement>) {
        setStartzeit(event.target.value)
    }
    function onChangeEndZeit(event: ChangeEvent<HTMLInputElement>) {
        setEndzeit(event.target.value)
    }




    function registerTime(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post(`/api/user/time/${username}/${startZeit}/${endZeit}/${vorbereitungsZeit}`);


    }

    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerTime}>
                    <h3>Geben Sie Ihre Arbeitszeit ein</h3>
                    <div className="Register Your Time">

                        <div>
                            <div>
                                <h3>Start</h3>
                            <input
                                type="time"
                                required={true}
                                id="startZeit"
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
                                id="endZeit"
                                placeholder="Ende"
                                value={endZeit}
                                onChange={onChangeEndZeit}
                            />
                            </div>
                            <div>
                                <h3>Vorbereitung</h3>
                               <input

                                    type="count"
                                    required={false}
                                    id="vorbereitungsZeit"
                                    placeholder="Vorbereitung"
                                    onChange={onChangeVorbereitungsZeit}
                                    />
                            </div>
                            <button type="submit">Zeit registrieren</button>
                        </div>







                    </div>

                    <button>Register</button>
                    <Link className="direction-link" to={"/homepage"}>HomePage</Link>
                </form>


            </div>
        </div>
    )
}
