import { Link } from "react-router-dom";
import DigitalClock from "./RegisterPage/Components/DigitalClock";
import Wecker from "./RegisterPage/Components/Wecker"; // Importieren Sie die Wecker-Komponente
import "./WeckerPage.css";



type Props = {
    user: string;
}

export default function WeckerPage(props: Props) {



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
                    <Wecker user={props.user} />

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