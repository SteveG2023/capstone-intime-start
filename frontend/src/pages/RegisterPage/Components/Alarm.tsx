// Alarm.js
import  { useEffect } from 'react';
import axios from 'axios';

const Alarm = ({ alarmTime }) => {
    useEffect(() => {
        if (alarmTime) {
            // Hier können Sie die Axios-Anfrage zum Abrufen einer neuen Weckzeit durchführen.
            // Ersetzen Sie die URL und die Anfrage entsprechend Ihrem Backend-Endpunkt.
            axios.get('Ihre-Weckzeit-API-URL')
                .then((response) => {
                    const newAlarmTime = response.data.newAlarmTime; // Passen Sie die Datenstruktur an Ihre API-Antwort an.
                    // Verwenden Sie newAlarmTime, um die Weckzeit zu setzen.
                })
                .catch((error) => {
                    console.error('Fehler beim Abrufen der Weckzeit:', error);
                });
        }
    }, [alarmTime]);

    return (
        <div>
            <h2>Alarmzeit: {alarmTime.toLocaleTimeString()}</h2>
            {/* Hier können Sie die Logik für das Auslösen des Alarms implementieren. */}
        </div>
    );
};

export default Alarm;