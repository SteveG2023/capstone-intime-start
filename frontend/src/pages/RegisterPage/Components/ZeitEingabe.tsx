import  { useState } from 'react';

function ZeitEingabe() {
    const [zeit, setZeit] = useState('');

    const handleZeitAenderung = (e) => {
        const neueZeit = e.target.value;
        // Hier kannst du die Eingabe validieren und sicherstellen, dass sie ein gültiges Zeitformat hat.
        setZeit(neueZeit);
    };

    return (
        <div>
            <input
                type="text"
                value={zeit}
                onChange={handleZeitAenderung}
                placeholder="hh:mm"
            />
        </div>
    );
}

export default ZeitEingabe;