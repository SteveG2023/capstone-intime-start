import  { useState } from 'react';

function TimeInput() {
    const [time, setTime] = useState('');

    const handleTimeChange = (event) => {
        const inputValue = event.target.value;

        // Hier können Sie überprüfen, ob die Eingabe das gewünschte Zeitformat hat
        // Zum Beispiel: HH:MM

        // Regulärer Ausdruck zur Überprüfung des Zeitformats (HH:MM)
        const timeRegex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;

        if (timeRegex.test(inputValue) || inputValue === '') {
            setTime(inputValue);
        }
    };

    return (
        <div>
            <label htmlFor="timeInput">Uhrzeit eingeben (HH:MM):</label>
            <input
                type="text"
                id="timeInput"
                value={time}
                onChange={handleTimeChange}
            />
        </div>
    );
}

export default TimeInput;