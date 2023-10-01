import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import "./TimePlan.css";

type Props = {
    user: string;

}

export default function TimePlan(props: Props) {


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
        axios.post(`/api/user/time/${props.user}/${startZeit}/${endZeit}/${vorbereitungsZeit}/${maximalWeckZeit}`)

            .then(() => nav("/setuppage"))
            .catch((error) => console.log(error))


    }

    return (
        <div className="wrapper">
            <div className="">

                <form onSubmit={registerTime}>
                    <h3 className={"uberschrift"}>Geben Sie Ihre Arbeitszeit ein</h3>
                    <div className="Register Your Time">

                        <div>
                            <div>
                                <h3 className={"uberschrift"}>Start</h3>
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
                                <h3 className={"uberschrift"}>Ende</h3>
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
                                <h3 className={"uberschrift"}>Vorbereitungszeit</h3>
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
                                <h3 className={"uberschrift"}>Maximal-Weckzeit</h3>
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

                        <button>Speichern</button>

                    </div>

                    <Link className="direction-link" to={"/setuppage"}>zurück</Link>


                </form>


            </div>
        </div>
    )

}