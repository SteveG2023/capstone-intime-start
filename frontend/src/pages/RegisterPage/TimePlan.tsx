import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";


import axios from "axios";

export default function TimePlan() {

    const username = 'z';
    const [startZeit, setStartzeit] = useState("");
    const [endZeit, setEndzeit] = useState("");
    const [vorbereitungsZeit, setVorbereitungsZeit] = useState('');
    const [maximalWeckZeit, setMaximalWeckZeit] = useState("");

    const onChangeVorbereitungsZeit = (event) => {
        setVorbereitungsZeit(event.target.value);
    }
    const nav = useNavigate();


    function onChangeStartZeit(event: ChangeEvent<HTMLInputElement>) {
        setStartzeit(event.target.value)
    }

    function onChangeEndZeit(event: ChangeEvent<HTMLInputElement>) {
        setEndzeit(event.target.value)
    }

    function onChangeMaximalWeckZeit(event: ChangeEvent<HTMLInputElement>) {
        setMaximalWeckZeit(event.target.value)
    }


    function registerTime(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post(`/api/user/time/${username}/${startZeit}/${endZeit}/${vorbereitungsZeit}/${maximalWeckZeit}`)

        .then(() => nav("/setuppage"))
     //.catch((error) => console.log(error))


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
                                <h3>Maximal-Weckzeit</h3>
                                <input
                                    type="time"
                                    required={true}
                                    id="maximalweckzeit"
                                    placeholder="maximalweckzeit"
                                    value={maximalWeckZeit}
                                    onChange={onChangeMaximalWeckZeit}
                                />
                            </div>

                        </div>

                    </div>
                    <div>

                        <button>Save</button>

                    </div>

                    <Link className="direction-link" to={"/setuppage"}>zurück</Link>


                </form>


            </div>
        </div>
    )

}