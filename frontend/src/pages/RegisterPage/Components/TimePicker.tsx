import  { useState } from 'react';



function TimePicker() {
    const [selectedTime, setSelectedTime] = useState(null);

    const handleTimeChange = (time) => {
        setSelectedTime(time);
    };

    return (
        <div>
            <h2>Wählen Sie eine Uhrzeit aus:</h2>
            <TimePicker>
                selected={selectedTime}
                onChange={handleTimeChange}
                showTimeSelect
                showTimeSelectOnly
                timeIntervals={15} // Intervalle für die Minutenanzeige
                dateFormat="h:mm aa" // Format der Uhrzeitanzeige
            </TimePicker>
        </div>
    );
}

export default TimePicker;