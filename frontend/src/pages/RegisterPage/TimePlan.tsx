import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from './Components/Uhrzeiteneingabe.tsx';
import Uhrzeiteneingabe from "./Components/Uhrzeiteneingabe.tsx";
import TimeInput from './Components/Timeinput.tsx';
export default function TimePlan() {


    const [startZeit    , setStartzeit] = useState("");
    const [endZeit, setEndzeit] = useState("");
    const [vorbereitungsZeit,setVorbereitungsZeit] = useState("");


    const nav = useNavigate();


    function onChangeStartZeit(event: ChangeEvent<HTMLInputElement>) {
        setStartzeit(event.target.value)
    }
    function onChangeEndZeit(event: ChangeEvent<HTMLInputElement>) {
        setEndzeit(event.target.value)
    }
    function onChangeVorbereitungsZeit(event: ChangeEvent<HTMLInputElement>) {
        setVorbereitungsZeit(event.target.value)
    }



    function registerTime(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
       // axios.post(´/api/user/"time/a"/${startZeit}/${endzeit}/${VorbereitungZeit) };


    }

    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerTime}>
                    <h3>REGISTER YOUR TIME</h3>
                    <div className="Register Your Time">

                        <div>




                        <input type={"startzeit"} required={true} id={startZeit} placeholder={" Start"}
                               onChange={onChangeStartZeit}/>

                        <input type={"endzeit"} required={true} id={endZeit} placeholder={" Ende"}
                               onChange={onChangeEndZeit}/>

                        <input type={"vorbereitung"} required={true} id={vorbereitungsZeit} placeholder={" Vorbereitung"}
                               onChange={onChangeVorbereitungsZeit}/>


                    </div>







                    </div>

                    <button>Register</button>
                    <Link className="direction-link" to={"/homepage"}>HomePage</Link>
                </form>


            </div>
        </div>
    )
}
