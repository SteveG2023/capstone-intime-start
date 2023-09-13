import React, { useState, useEffect } from 'react';
import axios from 'axios';


function Weckerdatenbank() {
    const [alarmTime, setAlarmTime] = useState(''); // State für die Weckerzeit
    const [additionalWeckerTime, setAdditionalWeckerTime] = useState(''); // State für die zusätzliche Weckzeit
    const [alarmActive, setAlarmActive] = useState(false); // State, um den Wecker zu aktivieren/deaktivieren
    const [intervalMinutes, setIntervalMinutes] = useState(60000);

    const handleAlarmTimeChange = (event) => {
        setAlarmTime(event.target.value);
    };

    const toggleAlarm = () => {
        setAlarmActive(!alarmActive);
    };

    async function fetchWeckzeit() {
        try {
            const response = await axios.get("/api/user/username/weckzeit"); // Passe den Pfad an
            if (response.data && typeof response.data === 'string') {
                return response.data; // Die Weckzeit aus der Antwort extrahieren
            }
        } catch (error) {
            console.error('Error fetching weckzeit:', error);
        }
        return null; // Im Fehlerfall oder wenn keine Weckzeit gefunden wird
    }

    useEffect(() => {
        const handleIntervalAlarmRing = async () => {
            try {
                const currentTime = new Date();
                const selectedTime = new Date(currentTime.toDateString() + ' ' + alarmTime);

                // Führe die Axios-Anfrage für die Verkehrszeit aus
                const responseTraffic = await axios.get("/api/user/duration/s");

                // Berechne die neue Weckzeit basierend auf der Serverantwort
                if (responseTraffic.data && typeof responseTraffic.data === 'number') {
                    const newInterval = responseTraffic.data;
                    const updatedSelectedTime = new Date(selectedTime.getTime() + newInterval * 1000);
                    setAlarmTime(updatedSelectedTime.toISOString().substr(11, 5));

                    console.log('New alarm time updated successfully');
                }
            } catch (error) {
                console.error('Error updating alarm time:', error);
            }
        };

        if (alarmActive) {
            const currentTime = new Date();
            const selectedTime = new Date(currentTime.toDateString() + ' ' + alarmTime);

            if (currentTime >= selectedTime) {
                // Alarmzeit erreicht, Klingeln
                //handleAlarmRing();

                // Starte das Intervall für periodische Abfragen alle 10 Minuten
                const intervalId = setInterval(handleIntervalAlarmRing, intervalMinutes * 2);

                // Stopp das Intervall, wenn der Wecker deaktiviert wird
                return () => clearInterval(intervalId);
            }
        }
    }, [alarmActive, alarmTime, intervalMinutes]);

    // Funktion zum Holen und Setzen der zusätzlichen Weckzeit
    async function setAdditionalWecker() {
        const weckzeitFromDB = await fetchWeckzeit();
        if (weckzeitFromDB) {
            // Setze die zusätzliche Weckzeit aus der Datenbank
            setAdditionalWeckerTime(weckzeitFromDB);
        }
    }

    // Rufe die Funktion zum Setzen der zusätzlichen Weckzeit auf, wenn die Komponente montiert ist
    useEffect(() => {
        setAdditionalWecker();
    }, []);

    return (
        <div>
            <input
                type="time"
                value={alarmTime}
                onChange={handleAlarmTimeChange}
            />
            <button onClick={toggleAlarm}>
                {alarmActive ? 'Wecker deaktivieren' : 'Wecker aktivieren'}
            </button>
            <p>Zusätzliche Weckzeit aus der Datenbank: {additionalWeckerTime}</p>
        </div>
    );
}

export default Weckerdatenbank;