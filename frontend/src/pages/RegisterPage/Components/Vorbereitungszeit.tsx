import  { useState } from 'react';
import TimePicker from 'react-time-picker';


function Vorbereitungszeit() {
    const [selectedTime, setSelectedTime] = useState('12:00');


    const inputStyle = {
        fontSize: '24px', // Ändere die Schriftgröße nach Bedarf
        width: '200px',   // Ändere die Breite nach Bedarf
        height: '40px',   // Ändere die Höhe nach Bedarf
    };
    const handleTimeChange = (newTime) => {
        setSelectedTime(newTime);
    };

    return (
        <div>

            <TimePicker
                onChange={handleTimeChange}
                value={selectedTime}
                disableClock={true}
                style={inputStyle}
            />
            <p>Ausgewählte Zeit: {selectedTime}</p>
        </div>
    );
}
export default Vorbereitungszeit