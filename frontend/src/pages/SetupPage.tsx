import {ChangeEvent, FormEvent, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";




export default function SetupPage() {



    return (
        <div className="wrapper">
            <div className="card">
                <div className="logo-container-l">
                    <h1>Das ist eine SetupPage</h1>

                </div>

                <Link className="direction-link" to={"/register"}>New here? REGISTER NOW</Link>
            </div>
        </div>
    )
}