import  { useState } from 'react';
import TimePicker from 'react-time-picker';


function Uhrzeiteneingabe() {
    const [selectedTime, setSelectedTime] = useState('12:00');
    const [workTimeStart, setWorkTimeStart] = useState(""); // Zustand für die Arbeitsbeginn-Uhrzeit
    const [workTimeEnd, setWorkTimeEnd] = useState(""); // Zustand für die Arbeitsende-Uhrzeit
    const [preparationTime, setPreparationTime] = useState(""); // Zustand für die Vorbereitungszeit

    // Funktion, um die Arbeitsbeginn-Uhrzeit zu aktualisieren
    const handleWorkTimeStartChange = (event) => {
        setWorkTimeStart(event.target.value);
    };

    // Funktion, um die Arbeitsende-Uhrzeit zu aktualisieren
    const handleWorkTimeEndChange = (event) => {
        setWorkTimeEnd(event.target.value);
    };

    // Funktion, um die Vorbereitungszeit zu aktualisieren
    const handlePreparationTimeChange = (event) => {
        setPreparationTime(event.target.value);
    };

    // Hier kannst du die Uhrzeiten an das Backend senden, wenn du möchtest
    const sendDataToBackend = () => {
        // Hier kannst du den Code zum Senden der Uhrzeiten an das Backend einfügen
    }


        return (

            <div>
                <h3>Arbeitszeit</h3>
                <h3>Wann willst du auf der Arbeit sein</h3>
                <input
                    type="time"
                    value={workTimeStart}
                    onChange={handleWorkTimeStartChange}
                />
                <h3>Wann hast du Schluss</h3>
                <input
                    type="time"
                    value={workTimeEnd}
                    onChange={handleWorkTimeEndChange}
                />
                <h3>Wie lange ist deine Vorbereitungszeit</h3>
                <input
                    type="time"
                    value={preparationTime}
                    onChange={handlePreparationTimeChange}
                />
                <button onClick={sendDataToBackend}>Daten speichern</button>
            </div>
        );
    }
export default Uhrzeiteneingabe;