import React from "react";
import { Link } from "react-router-dom";
import DigitalClock from "./RegisterPage/Components/DigitalClock";
import Wecker from "./RegisterPage/Components/Wecker"; // Importieren Sie die Wecker-Komponente
import "./WeckerPage.css";

export default function WeckerPage() {
    return (
        <div className={"Background"}>


                <div className="logo-container-l"/>
                <h1>Wecker</h1>
                <div>
                    <Link className={"zurueck"} to={"/setup"}>
                        Setup
                    </Link>
                    <Link className={"logout"} to={"/"}>
                        logout
                    </Link>


                </div>

                <div className="digital-clock">
                    <DigitalClock />
                </div>
                <div className="clock-content grid">
                    {/* Fügen Sie den Wecker hier ein */}
                    <Wecker />
                </div>
                <div id="root"></div>

                <img
                    className="img-priority"
                    src={
                        "https://www.br.de/radio/bayern1/baustellen-bayern-100~_v-img__16__9__l_-1dc0e8f74459dd04c91a0d45af4972b9069f1135.jpg?version=85f9a"
                    }
                    alt="Category Image"
                />
                <div>
                    <Link className="direction-link" to={"/Adresseepagework"}>
                        Stopp
                    </Link>
                    <Link className="direction-link" to={"/Adresseepagework"}>
                        Schlummern
                    </Link>
                </div>

        </div>
    );
}