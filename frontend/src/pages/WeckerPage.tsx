import {ChangeEvent, FormEvent, ReactNode, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
//import navigate, useNavigate} from "react-router-dom";
import "./WeckerPage.css"

//const navigate = useNavigate();





export default function WeckerPage() {


    return (
        <div>

            <img className="Background" src={"https://cdn.pixabay.com/photo/2016/04/09/14/21/clock-1318131_1280.jpg"} alt="Category Image" />


                <div className="logo-container-l">
                    <h1>Das ist eine Weckerseite</h1>
                    <div>
                        <Link className="direction-link" to={"/Adresseepagework"}>Schlummern</Link>
                    </div>

                    <div>
                        <Link className="direction-link" to={"/Adresseepagework"}>Stopp</Link>
                    </div>

                         <img className="img-priority" src={"https://www.br.de/radio/bayern1/baustellen-bayern-100~_v-img__16__9__l_-1dc0e8f74459dd04c91a0d45af4972b9069f1135.jpg?version=85f9a"} alt="Category Image" />




                    <div>
                        <Link className="direction-link" to={"/Adresseepagework"}>Stopp</Link>
                    </div>





            </div>
        </div>
    )
}