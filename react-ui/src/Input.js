
function Input() {

    return fetch('\'http://localhost:8080/api/file-reader/calculate/?inputfile=double_encrypted.zip&outputfile=qwe.txt&iszipped=true&decryptionkeys=qwsdcvbgfthyrdfw,asdfghjkqewrtyto\'')
        .then(resp => {
            if (!resp.ok) {
                throw `Server error: [${resp.status}] [${resp.statusText}] [${resp.url}]`;
            }
            return resp.json();
        })
        .then(receivedJson => {
            // your code with json here...
        });
    return (
        <div>
            <h2></h2>
            <div>
                <input/>
                <button>ok</button>
            </div>
        </div>
    )
}

export default Input;