import { useState, useEffect } from 'react';
import axios from 'axios';

function Wecker() {
    const [alarmTime, setAlarmTime] = useState(''); // State für die Weckerzeit
    const [alarmActive, setAlarmActive] = useState(false); // State, um den Wecker zu aktivieren/deaktivieren
    const [intervalMinutes, setIntervalMinutes] = useState(10);

    const handleAlarmTimeChange = (ev) => {
        setAlarmTime(ev.target.value);
    };

    const toggleAlarm = () => {
        setAlarmActive(!alarmActive);
    };

    useEffect(() => {
        const handleIntervalAlarmRing = async () => {
            try {
                const currentTime = new Date();
                const selectedTime = new Date(currentTime.toDateString() + ' ' + alarmTime);

                // Führe die Axios-Anfrage für die Verkehrszeit aus
                const responseTraffic = await axios.get(`/api/user/traffictime/w}`);

                // Berechne die neue Weckzeit basierend auf der Serverantwort
                if (responseTraffic.data && typeof responseTraffic.data === 'number') {
                    const newInterval = responseTraffic.data;
                    const updatedSelectedTime = new Date(selectedTime.getTime() + newInterval * 60000);
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
                const intervalId = setInterval(handleIntervalAlarmRing, intervalMinutes * 60000);

                // Stopp das Intervall, wenn der Wecker deaktiviert wird
                return () => clearInterval(intervalId);
            }
        }
    }, [alarmActive, alarmTime, intervalMinutes]);

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
        </div>
    );
}

export default Wecker;