import {useState, useEffect} from 'react';
import axios from 'axios';
import "./Wecker.css";

type Props = {
    user: string;

}

function Wecker(props: Props) {
    const [username, setUsername] = useState("");
    const [weckzeit, setWeckzeit] = useState<string | null>(null);
    const [aktiviert, setAktiviert] = useState(false);
    //const [schlummer, setSchlummer] = useState(false);
    const [audio, setAudio] = useState(new Audio());
    const [klingelt, setKlingelt] = useState(false);
    const formatMinutes = (minutes: number) => { return minutes.toString().padStart(2, '0');
    };

    useEffect(() => {


        const sendeAnfrageUndAktualisiereWeckzeit = async () => {
            try {
                const now = new Date();
                const hours = now.getHours();
                const minutes = now.getMinutes();

                if (aktiviert) {


                    // TestEndpunkt
                    // await axios.get(`api/user/duration/${props.user}`);

                    //await axios.get(`api/user/weckzeit/${props.user}`);
                    const response = await axios.get(`api/user/weckzeittest/${props.user}`);
                    console.log(response)

                    if (response.status === 200) {
                        const erhaltenMinutenSeitMitternacht = response.data;
                        console.log(erhaltenMinutenSeitMitternacht);

                        const weckzeitStunden = Math.floor(erhaltenMinutenSeitMitternacht / 60);
                        const weckzeitMinuten = erhaltenMinutenSeitMitternacht % 60;
                        const neueWeckzeit = `${weckzeitStunden}:${weckzeitMinuten}`;
                        setWeckzeit(neueWeckzeit);

                        // Überprüfe, ob die Weckzeit erreicht wurde
                        if (weckzeitStunden === hours && weckzeitMinuten === minutes) {
                            handleWeckzeitErreicht();
                        }
                    } else {
                        console.error('Fehlerhafte API-Antwort:', response);
                    }
                }
            } catch (error) {
                console.error('Fehler beim Abrufen der Weckzeit:', error);
            }
        };

        // Führe die Funktion alle 10 Minuten aus
        const intervalId = setInterval(sendeAnfrageUndAktualisiereWeckzeit, 1000 * 60);

        // Führe die Funktion sofort aus
        sendeAnfrageUndAktualisiereWeckzeit();

        return () => {
            clearInterval(intervalId);
        };
    }, [aktiviert, weckzeit, audio]);

    const handleAktivieren = () => {
        setAktiviert(true);
    };

    const handleStoppen = () => {
        setAktiviert(false);
        audio.pause();
        audio.currentTime = 0;
        setKlingelt(false);
    };

    const handleSchlummern = () => {
        if (weckzeit) {
            const [stunden, minuten] = weckzeit.split(":").map(Number);

            const neueMinuten = minuten + 2;

            if (neueMinuten >= 60) {
                const neueStunden = stunden + 1;
                const neueWeckzeit = `${neueStunden}:${neueMinuten % 60}`;
                setWeckzeit(neueWeckzeit);
            } else {
                const neueWeckzeit = `${stunden}:${neueMinuten}`;
                setWeckzeit(neueWeckzeit);
            }
        }
    };

    const handleWeckzeitErreicht = () => {

        audio.src = "https://www.br.de/radio/bayern1/baustellen-bayern-100~_v-img__16__9__l_-1dc0e8f74459dd04c91a0d45af4972b9069f1135.jpg?version=85f9a";
        audio.play();
        setKlingelt(true);
    };

    return (
        <div>

            {weckzeit && <p>Weckzeit: {weckzeit.split(':')[0]}:{formatMinutes(Number(weckzeit.split(':')[1]))}</p>}
            {klingelt && (
                <div>
                    <div>
                        <img className={"Uhr"}
                             src="https://cdn.pixabay.com/photo/2017/12/24/00/30/clock-3036245_1280.jpg"
                             alt="Klingelbild"/>
                    </div>
                    <div>
                        <img className={"img"}
                             src="https://www.br.de/radio/bayern1/baustellen-bayern-100~_v-img__16__9__l_-1dc0e8f74459dd04c91a0d45af4972b9069f1135.jpg?version=85f9a"
                             alt="Category Image"/>
                    </div>
                    <button onClick={handleSchlummern}>Schlummern</button>
                </div>
            )}


            {!aktiviert ? (
                <button onClick={handleAktivieren}>Aktivieren</button>
            ) : (
                <div>
                    <div>
                        <button onClick={handleStoppen}>Stopp</button>


                    </div>


                </div>


            )}


        </div>
    );
}

export default Wecker;