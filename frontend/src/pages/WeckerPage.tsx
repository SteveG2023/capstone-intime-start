
import { Link } from "react-router-dom";
import DigitalClock from "./RegisterPage/Components/DigitalClock";
import Wecker from "./RegisterPage/Components/Wecker"; // Importieren Sie die Wecker-Komponente
import "./WeckerPage.css";
import {useEffect, useState} from "react";
import axios from "axios";


export default function WeckerPage() {



    const [username, setUsername] = useState("");

    useEffect(() => {

        axios.get("/api/user/me2")
            .then((response) => {
                setUsername(response.data);
            })
            .then(axios.get)
            .catch((error) => console.log(error));
    }, []);






    return (
        <div className={"Background"}>


                <div className="logo-container-l"/>
            <video autoPlay muted loop id="video-background"> </video>


                <div>
                    <Link className={"setup"} to={"/setuppage"}>
                          Setup
                    </Link>
                </div>

                <div>
                    <Link className={"logout"} to={"/"}>
                        logout
                    </Link>
                </div>
                    <div>

                        <h1>Wecker</h1>


                        <div className="digital-clock">



                            <DigitalClock />
                        </div>
                        <div className="clock-content grid">
                                {<Wecker />}

                        </div>

                        <div id="root"></div>
                        <div className={"box"}>
                            <img
                                className="img-priority"
                                src={
                                    "https://www.br.de/radio/bayern1/baustellen-bayern-100~_v-img__16__9__l_-1dc0e8f74459dd04c91a0d45af4972b9069f1135.jpg?version=85f9a"
                                }
                                alt="Category Image"
                            />

                    </div>




                </div>

        </div>
    );
}