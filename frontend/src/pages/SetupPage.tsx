import {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";




export default function SetupPage() {



    return (
        <div className="wrapper">
            <div className="card">
                <div className="logo-container-l">
                    <h3>Das ist eine SetupPage</h3>


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
                    <Link className="direction-link" to={"/homepage"}>Logout</Link>
                    </div>
                    <div>
                    <Link className="direction-link" to={"/wecker"}>Wecker</Link>
                     </div>


            </div>

        </div>
    )
}