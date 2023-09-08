import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from './Components/TimePicker';
import TimePicker from "./Components/TimePicker";
export default function TimePlan() {


    const [workTimeStart, setWorkTimeStart] = useState("");
    const [workTimeEnd, setWorkTimeEnd] = useState("");
    const [preparationTime,setPreparationTime] = useState("");


    const nav = useNavigate();


    function onChangeWorkTimeStart(event: ChangeEvent<HTMLInputElement>) {
        setWorkTimeStart(event.target.value)
    }
    function onChangeWorkTimeEnd(event: ChangeEvent<HTMLInputElement>) {
        setWorkTimeEnd(event.target.value)
    }
    function onChangePreparationTime(event: ChangeEvent<HTMLInputElement>) {
        setPreparationTime(event.target.value)
    }



    function registerTime(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post("/api/user/register/", { workTimeStart, workTimeEnd, preparationTime})

            .then(() => nav("/hompage"))
            .catch((error) => console.log(error))
    }

    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerTime}>
                    <h3>REGISTER YOUR TIME</h3>
                    <div className="Register Your Time">


                        <input type={"WorkTimeStart"} required={true} id={preparationTime} placeholder={" Preparation"}
                               onChange={onChangePreparationTime}/>

                        <input type={"WorkTimeEnde"} required={true} id={workTimeEnd} placeholder={" WorkTimeEnd"}
                               onChange={onChangeWorkTimeEnd}/>

                        <input type={"nachname"} required={true} id={workTimeStart} placeholder={" WorkTimeStart"}
                               onChange={onChangeWorkTimeStart}/>










                    </div>

                    <button>Register</button>
                    <Link className="direction-link" to={"/homepage"}>HomePage</Link>
                </form>


            </div>
        </div>
    )
}
