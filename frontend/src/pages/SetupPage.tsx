import {Link, useNavigate} from "react-router-dom";
import "./SetupPage.css";


export default function SetupPage() {


    return (
        <div className="wrapper">


            <div className="">

                <h3 className={"einstellung"}>Einstellungen</h3>

                <div className={"test"}>
                    <Link className="direction-link" to={"/adresspagework"}>Workplace</Link>


                    <Link className="direction-link" to={"/adresspagehome"}>Homeplace</Link>


                    <Link className="direction-link" to={"/timeplanpage"}>Timeplan</Link>


                    <Link className="direction-link" to={"/weckerpage"}>Wecker</Link>

                    <Link className="direction-link" to={"/"}>Logout</Link>


                </div>
            </div>

        </div>


    )
}