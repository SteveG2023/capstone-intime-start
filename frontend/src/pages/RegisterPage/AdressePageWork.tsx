import {ChangeEvent, FormEvent, useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";


type Props = {
    user: string;

}


export default function AdressePageWork(props: Props) {

    const [username, setUsername] = useState("");
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
                    <h3>REGISTER Your Workplace</h3>
                    <div className="Register">
                        <input
                            type={"street"}
                            required={true}
                            id={strasse}
                            placeholder={" Work-Street"}
                            onChange={onChangeStrasse}
                        />
                        <input
                            type={"number"}
                            required={true}
                            id={nummer}
                            placeholder={" Work-Number"}
                            onChange={onChangeNummer}
                        />
                        <input
                            type={"City"}
                            required={true}
                            id={stadt}
                            placeholder={" Work-City"}
                            onChange={onChangeStadt}
                        />
                    </div>
                    <button disabled={loading}>Save</button>
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
