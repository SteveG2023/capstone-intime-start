import  { useState } from "react";

function Wecker() {
    const [alarmTime, setAlarmTime] = useState(""); // State für die Weckerzeit
    const [alarmActive, setAlarmActive] = useState(false); // State, um den Wecker zu aktivieren/deaktivieren

    const handleAlarmTimeChange = (event) => {
        setAlarmTime(event.target.value);
    };

    const toggleAlarm = () => {
        setAlarmActive(!alarmActive);
    };

    // Hier können Sie die Logik für das Aktivieren/Deaktivieren des Weckers hinzufügen

    return (
        <div>
            <input
                type="time"
                value={alarmTime}
                onChange={handleAlarmTimeChange}
            />
            <button onClick={toggleAlarm}>
                {alarmActive ? "Wecker deaktivieren" : "Wecker aktivieren"}
            </button>
        </div>
    );
}

export default Wecker;