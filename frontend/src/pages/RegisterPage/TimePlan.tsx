import {ChangeEvent, FormEvent, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";

export default function TimePlan() {


    const [arbeituhrzeitstart, setArbeitsUhrzeitStart] = useState("");
    const [arbeitsuhrzeitende, setAArbeitsUhrzeitEnd] = useState("");
    const [datum,setDatum] = useState("");
    const [driveBy,setDriveBy] = useState("");

    const nav = useNavigate();


    function onChangeArbeitsUhrzeitStart(event: ChangeEvent<HTMLInputElement>) {
        setArbeitsUhrzeitStart(event.target.value)
    }
    function onChangeArbeitsuhrzeitEnde(event: ChangeEvent<HTMLInputElement>) {
        setAArbeitsUhrzeitEnd(event.target.value)
    }
    function onChangeDatum(event: ChangeEvent<HTMLInputElement>) {
        setDatum(event.target.value)
    }
    function onChangeDriveBy(event: ChangeEvent<HTMLInputElement>) {
        setDriveBy(event.target.value)
    }



    function registerTime(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post("/api/user/register/", { arbeituhrzeitstart, arbeitsuhrzeitende, datum,driveBy})

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


                        <input type={"WorkTimeStart"} required={true} id={arbeituhrzeitstart} placeholder={" WorkTimeStart"}
                               onChange={onChangeArbeitsUhrzeitStart}/>
                        <input type={"WorkTimeEnde"} required={true} id={arbeitsuhrzeitende} placeholder={" WorkTimeEnd"}
                               onChange={onChangeArbeitsuhrzeitEnde}/>

                        <input type={"nachname"} required={true} id={datum} placeholder={" Work-Date"}
                               onChange={onChangeDatum}/>
                        <input type={"nachname"} required={true} id={driveBy} placeholder={" Drive_By"}
                               onChange={onChangeDriveBy}/>








                    </div>

                    <button>Register</button>
                    <Link className="direction-link" to={"/homepage"}>HomePage</Link>
                </form>


            </div>
        </div>
    )
}
