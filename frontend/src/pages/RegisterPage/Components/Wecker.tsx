import { useState, useEffect } from 'react';
import axios from 'axios';

function Wecker() {
    const [alarmTime, setAlarmTime] = useState(''); // State für die Weckerzeit
    const [alarmActive, setAlarmActive] = useState(false); // State, um den Wecker zu aktivieren/deaktivieren

    const handleAlarmTimeChange = (ev) => {
        setAlarmTime(ev.target.value);
    };

    const toggleAlarm = () => {
        setAlarmActive(!alarmActive);
    };

    const handleAlarmRing = async () => {
        try {
            const currentTime = new Date();
            const selectedTime = new Date(currentTime.toDateString() + ' ' + alarmTime);

           //  Hier holst du die Verkehrszeit und die Vorbereitungszeit vom Server
            const responseTraffic = await axios.get(`/api/user/traffictime/${"w"}`);
            const responseNoTraffic = await axios.get(`/api/user/duration/${"w"}`);
            const preparationTime = await axios.get(`/api/user/duration/${"w"}`);

            // Berechne die neue Weckzeit basierend auf den Serverantworten
            if (responseTraffic.data && responseNoTraffic.data && preparationTime.data) {
                const totalMinutesToAdd = responseTraffic.data + responseNoTraffic.data + preparationTime.data;
                selectedTime.setMinutes(selectedTime.getMinutes() + totalMinutesToAdd);

                // Sende die neue Weckzeit an den Server
                const response = await axios.post(`/api/user/traffictime/${"w"}`, {
                    newAlarmTime: selectedTime.toISOString(),
                });

                // Aktualisiere den State für die Weckzeit
                if (response.data && typeof response.data === 'number') {
                    setAlarmTime(selectedTime.toISOString().substr(11, 5));
                }

                console.log('New alarm set successfully');
            }
        } catch (error) {
            console.error('Error setting new alarm:', error);
        }
    };

    useEffect(() => {
        if (alarmActive) {
            const currentTime = new Date();
            const selectedTime = new Date(currentTime.toDateString() + ' ' + alarmTime);

            if (currentTime >= selectedTime) {
                // Alarmzeit erreicht, Klingeln
                handleAlarmRing();
            }
        }
    }, [alarmActive, alarmTime]);

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