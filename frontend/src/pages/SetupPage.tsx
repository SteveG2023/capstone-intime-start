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


                    <Link className="direction-link" to={"/adresspagework"}>Workplace</Link>

                    </div>
                    <Link className="direction-link" to={"/adresspagehome"}>Homeplace</Link>
                    </div>

                    <div>
                        <Link className="direction-link" to={"/timeplanpage"}>Timeplan</Link>
                    <div>
                    <Link className="direction-link" to={"/weckerpage"}>Wecker</Link>
                     </div>
                    <Link className="direction-link" to={"/"}>Logout</Link>
                    </div>







            </div>

        </div>
    )
}