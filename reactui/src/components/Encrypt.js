import {useState} from "react";
import {Link} from "react-router-dom";
import fetchEncrypting from "../fetching/fetchEncrypting";

const Encrypt = () => {
    const [inputFile, setInputFile] = useState('')
    const [outputFile, setOutputFile] = useState('')
    const [key, setKey] = useState('')
    const [resultInfo, setResultInfo] = useState('')
    const handleSubmit = (e) => {
        e.preventDefault();
        fetchEncrypting(inputFile, outputFile, key, setResultInfo);
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
                <label>Encrypted keys</label>
                <input
                    type="text"
                    value={key}
                    onChange={(e) => {
                        setKey(e.target.value);
                    }}
                />
                <button>Encrypt</button>
            </form>
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
        </div>
    );
}

export default Encrypt;