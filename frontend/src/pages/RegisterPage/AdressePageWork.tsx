import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import { useNavigate} from "react-router-dom";
import axios from "axios";

export default function AdressePageWork() {


    const [stadt, setStadt] = useState("");
    const [strasse, setStrasse] = useState("");
    const [nummer,setNummer] = useState("");


    const nav = useNavigate();


    function onChangeStadt(event: ChangeEvent<HTMLInputElement>) {
        setStadt(event.target.value)
    }
    function onChangeStrasse(event: ChangeEvent<HTMLInputElement>) {
        setStrasse(event.target.value)
    }
    function onChangeNummer(event: ChangeEvent<HTMLInputElement>) {
        setNummer(event.target.value)
    }




    function registerwork(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios({
            method: 'get',
            url: `/api/user/placeid/${stadt}/${strasse}/${nummer}`
        })
            .then(() => nav("/timeplan"))
            .catch((error) => console.log(error))
    }


    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerwork}>
                    <h1>REGISTER Your Workplace</h1>
                    <div className="Register">


                        <input type={"street"} required={true} id={strasse} placeholder={" Work-Street"}
                               onChange={onChangeStrasse}/>
                        <input type={"number"} required={true} id={nummer} placeholder={" Work-Number"}
                               onChange={onChangeNummer}/>

                        <input type={"City"} required={true} id={stadt} placeholder={" Work-City"   }
                               onChange={onChangeStadt}/>









                    </div>

                    <button>registerwork</button>
                </form>


            </div>
        </div>
    )
}
