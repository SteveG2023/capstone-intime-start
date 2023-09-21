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

            <div>
                <Link className={"logout"} to={"/"}>
                    logout
                </Link>

                <Link className={"setup"} to={"/setuppage"}>
                    Setup
                </Link>
            </div>


            <div className="logo-container-l"/>
            <video autoPlay muted loop id="video-background"> </video>





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


                </div>




            </div>

        </div>
    );
}