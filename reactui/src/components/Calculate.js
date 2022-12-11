import {useState} from "react";
import {Link} from "react-router-dom";
import fetchCalculating from "../fetching/fetchCalculating";

const Calculate = () => {
    const [inputFile, setInputFile] = useState('')
    const [outputFile, setOutputFile] = useState('')
    const [isZipped, setIsZipped] = useState(false)
    const [keys, setKeys] = useState('')
    const [extension, setExtension] = useState('Txt')
    const [resultInfo, setResultInfo] = useState('')
    const handleSubmit = (e) => {
        e.preventDefault();
        fetchCalculating(inputFile, outputFile, isZipped, keys, extension, setResultInfo);
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
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
        </div>
    );
}

export default Calculate;