import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import "./AdressPageWork.css";

type Props = {
    user: string;

}


export default function AdressePageWork(props: Props) {


    const [stadt, setStadt] = useState("");
    const [strasse, setStrasse] = useState("");
    const [nummer,setNummer] = useState("");
    const [loading, setLoading] = useState(true);

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
            url: `/api/user/placeidwork/${props.user}/${stadt}/${strasse}/${nummer}`
        })
            .then(() => nav("/setuppage"))
            //.catch((error) => console.log(error))
    }


    return (
        <div className="wrapper">
            <div className="card">
                <div className="logoContainerL">

                </div>
                <form onSubmit={registerwork}>
                    <h3 className={"arbeitsort"}>Arbeitsort</h3>
                    <div className="Register">

                     <div>

                        <input
                            type={"City"}
                            required={true}
                            id={stadt}
                            placeholder={" Stadt"}
                            onChange={onChangeStadt}
                        />
                        </div>

                        <div>
                            <input
                                type={"street"}
                                required={true}
                                id={strasse}
                                placeholder={" Strasse"}
                                onChange={onChangeStrasse}
                            />
                        </div>

                        <div>


                            <input
                                type={"number"}
                                required={true}
                                id={nummer}
                                placeholder={" Nummer"}
                                onChange={onChangeNummer}
                            />

                        </div>

                    </div>
                    <button >Speichern</button>
                    <div>
                        <Link className="direction-link" to={"/setuppage"}>
                            zurück
                        </Link>
                    </div>
                </form>


            </div>
        </div>
    )
}
