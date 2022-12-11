import {useState} from "react";

const Calculate = () => {
    const [inputFile, setInputFile] = useState('')
    const [outputFile, setOutputFile] = useState('')
    const [isZipped, setIsZipped] = useState(false)
    const [keys, setKeys] = useState('')
    const [extension, setExtension] = useState('Txt')
    const [info, setInfo] = useState('')
    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('api/file-reader/calculate/?inputfile=' + inputFile + '&outputfile=' + outputFile + '&iszipped=' + isZipped + '&decryptionkeys=' + keys + '&extension=' + extension)
            .then(response => console.log(response.json()))
            .catch(re => console.log(re));
    }
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>Input file:</label>
                <input
                    type="text"
                    required
                    value={inputFile}
                    onChange={(e) => setInputFile(e.target.value)}
                />
                <label>Output file:</label>
                <input
                    type="text"
                    required
                    value={outputFile}
                    onChange={(e) => setOutputFile(e.target.value)}
                />
                <label>Is zipped?</label>
                <input
                    type="checkbox"
                    onChange={() => {
                        setIsZipped(!isZipped);
                    }}
                />
                <label>Encrypted keys</label>
                <input
                    type="text"
                    required
                    value={keys}
                    onChange={(e) => {
                        setKeys(e.target.value);
                    }}
                />
                <select
                    value={extension}
                    onChange={(e) => {
                        setExtension(e.target.value);
                        console.log(e.target.value);
                    }}
                >
                    <option value="txt">Txt</option>
                    <option value="json">Json</option>
                    <option value="xml">Xml</option>
                </select>
                <button>Calculate</button>
            </form>
        </div>
    );
}

export default Calculate;