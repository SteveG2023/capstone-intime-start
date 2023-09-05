import { useEffect, useState} from 'react';


const DigitalClock = () => {

        const [time, setTime] = useState(new Date());

        useEffect(() => {
            const interval = setInterval(() => {
                setTime(new Date());
            }, 1000);

            return () => {
                clearInterval(interval);
            };
        }, []);

        const hours = time.getHours().toString().padStart(2, '0');
        const minutes = time.getMinutes().toString().padStart(2, '0');
        const seconds = time.getSeconds().toString().padStart(2, '0');

        return (
            <div className="digital-clock">
                <h1>Digitaluhr</h1>
                <p>{`${hours}:${minutes}:${seconds}`}</p>
            </div>
        );
    }

    export default DigitalClock;