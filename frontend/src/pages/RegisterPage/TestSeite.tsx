





/*
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import { useEffect, useState } from 'react'


export default function RegisterPage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [vorname , setVorname ] = useState("");
    const [nachname,setNachname ]= useState("");
    const [wohnadressestadt,setWohnAdresseStadt]=useState();
    const [wohnadressestreet,setWohnAdresseStreet]=useState();
    const [wohnadressenumber,setWohnAdresseNumber]=useState();
    const [arbeitsadressestadt,setArbeitsAdresseStadt]=useState();
    const [arbeitsadressestrasse,setArbeitsAdresseStrasse]=useState();
    const [arbeitsadressenumber,setArbeitsAdresseNumber]=useState();
    const [fortbewegung ,setFortbewegung ]=useState();












    const nav = useNavigate();

    function onChangeHandlerPassword(event: FormEvent<HTMLFormElement>) {
        setPassword(event.target.value)
    }

    function onChangeHandlerUsername(event: FormEvent<HTMLFormElement>) {
        setUsername(event.target.value)


    }
    function onChangeHandlerVorname(event: FormEvent<HTMLFormElement>) {
        setVorname(event.target.value)
        1

    }
    function onChangeHandlerNachname(event: FormEvent<HTMLFormElement>) {
        setNachname(event.target.value)


    }

    function onChangeHandlerWohnAdressseStadt(event: FormEvent<HTMLFormElement>) {
        setWohnAdresseStadt(event.target.value)


    }

    function onChangeHandlerWohnAdresseStreet(event: FormEvent<HTMLFormElement>) {
        setWohnAdresseStreet(event.target.value)


    }
    function onChangeHandlerWohnAdresseNumber(event: FormEvent<HTMLFormElement>) {
        setWohnAdresseNumber(event.target.value)

    }

    function onChangeHandlerArbeitsAdresseStadt(event: FormEvent<HTMLFormElement>) {
        setArbeitsAdresseStadt(event.target.value)

    }
    function onChangeHandlerArbeitsAdresseStrasse(event: FormEvent<HTMLFormElement>) {
        setArbeitsAdresseStrasse(event.target.value)

    }

    function onChangeHandlerArbeitsAdresseNumber(event: FormEvent<HTMLFormElement>) {
        setArbeitsAdresseNumber(event.target.value)

    }
    function onChangeHandlerFortbewegung (event: FormEvent<HTMLFormElement>) {
        setFortbewegung(event.target.value)

    }






function logout(event:FormEvent<HTMLFormElement>) {
    event.preventDefault()
    axios.post("/api/user/logout")
        .then((respose) => console.log(respose.data))
        .catch((error) => console.log(error ))
}

function register(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); // damit sich die Seite nicht neu Zeichnet

    axios.post("/api/user/register", {username, vorname,nachname,wohnadressestadt,wohnadressestreet,wohnadressenumber,arbeitsadressestadt,arbeitsadressestrasse,arbeitsadressenumber,fortbewegung })
        .then(() => nav("/"))
        .catch(((error) => console.log(error)))
}

return (
    <div>
        <h1>Register</h1>
        <form onSubmit={register}/>
        <div></div>
            <input type={"text"} id={username}placeholder={"enter your username"} required={true}
               onChange={onChangeHandlerUsername}/>
             <input type={"password"} id={password} placeholder={"enter your password"} required={true}
               onChange={onChangeHandlerPassword}/>



            <input type={"Vorname"} id={vorname} placeholder={"enter your vorname"} required={true}
               onChange={onChangeHandlerVorname}/>
             <input type={"Nachname"} id={nachname} placeholder={"enter your nachname"} required={true}
               onChange={onChangeHandlerNachname}/>

            <input type={"WohnAdresseStadt"} id={wohnadressestadt} placeholder={"enter your Home-City"} required={true}
               onChange={onChangeHandlerWohnAdressseStadt}/>

             <input type={"WohnAdresseStrasse"} id={wohnadressestreet} placeholder={"enter your Home-Street"} required={true}
               onChange={onChangeHandlerWohnAdresseStreet}/>



             <input type={"WohnAdresseNummer"} id={wohnadressenumber} placeholder={"enter your Home-Number"} required={true}
               onChange={onChangeHandlerWohnAdresseNumber}/>
             <input type={"ArbeitsAdresseStadt"} id={arbeitsadressestadt} placeholder={"enter your Work-City"} required={true}
               onChange={onChangeHandlerArbeitsAdresseStadt}/>

             <input type={"ArbeitsAdresseStrasse"} id={arbeitsadressestrasse} placeholder={"enter Work-Street"} required={true}
               onChange={onChangeHandlerArbeitsAdresseStrasse}/>
             <input type={"ArbeitsAdresseNummer"} id={arbeitsadressenumber} placeholder={"enter your Work-number"} required={true}
               onChange={onChangeHandlerArbeitsAdresseNumber}/>
            <input type={"ArbeitsAdresseNummer"} id={fortbewegung} placeholder={"enter your Work-number"} required={true}
               onChange={onChangeHandlerFortbewegung}/>

                 <button onClick={register}>Register</button>


        <form/>


    </div>

)
}
*/