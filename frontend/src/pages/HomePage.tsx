import {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
;




export default function HomePage() {




    return (
        <div className="wrapper">
            <div className="card">
                <div className="logo-container-l">
                    <h1>Das ist eine Homepage</h1>

                </div>

                        <Link className="direction-link" to={"/homepage"}>Zurück</Link>

                <div>


                        <Link className="direction-link" to={"/setup"}>Setup</Link>


                </div>



            </div>
        </div>
    )
}

