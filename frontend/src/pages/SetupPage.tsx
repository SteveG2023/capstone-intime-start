import {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import "./SetupPage.css";



export default function SetupPage() {



    return (
        <div className="suche">
            <div className="card">
                <div className="logo-container-l">
                    <h3>Einstellungen</h3>


                    <div>
                    <Link className="direction-link" to={""}>Klingelton</Link>



                    </div>

                    <Link className="direction-link" to={""}>Farbe</Link>
                    <div>

                    <Link className="direction-link" to={"/adresspagework"}>Workplace</Link>

                    </div>
                    <Link className="direction-link" to={"/adresspagehome"}>Homeplace</Link>
                    </div>
                    <div>
                    <Link className="direction-link" to={"/homepage"}>Homepage</Link>
                    </div>
                    <div>
                        <Link className="direction-link" to={"/timeplan"}>Timepage</Link>
                    <div>
                    <Link className="direction-link" to={"/wecker"}>Wecker</Link>
                     </div>
                    <Link className="direction-link" to={"/"}>Logout</Link>
                    </div>

            </div>

        </div>
    )
}