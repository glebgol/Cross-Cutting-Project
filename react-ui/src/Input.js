import {useEffect, useState} from "react";

function Input() {
    const [items, setItems] = useState([])

    useEffect(() => {
        fetch('/api/file-reader/calculate/?inputfile=double_encrypted.zip&outputfile=ass.txt&iszipped=true&decryptionkeys=qwsdcvbgfthyrdfw,asdfghjkqewrtyto&extension=txt')
            .then((res) => res.json())
            .then((resJson) => {
                console.log(resJson);
                items[0] = resJson;
            })
    }, [])

    return  (
        <h2>
            {items[0]}
        </h2>
    );


}

export default Input;